package com.demo.codetest.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demo.codetest.dto.PageDTO;
import com.demo.codetest.dto.TransactionDTO;
import com.demo.codetest.dto.TransactionSearchDTO;
import com.demo.codetest.dto.TransactionUpdateRequestDTO;
import com.demo.codetest.entity.Transaction;
import com.demo.codetest.enums.ErrorCode;
import com.demo.codetest.exception.CustomWebServiceException;
import com.demo.codetest.interf.ITransactionService;
import com.demo.codetest.repository.TransactionRepository;
import com.demo.codetest.spec.TransactionSpecification;
import com.demo.codetest.utils.CommonConstants;
import com.demo.codetest.utils.RedisKey;

@Service
public class TransactionService implements ITransactionService {

	private final Logger logger = LoggerFactory.getLogger(TransactionService.class);

	private final TransactionRepository transactionRepository;
	
	private final RedissonClient redissonClient;

	public TransactionService(TransactionRepository transactionRepository, RedissonClient redissonClient) {
		this.transactionRepository = transactionRepository;
		this.redissonClient = redissonClient;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void processFile(MultipartFile file) {
		try {
			BufferedReader reader;

			reader = new BufferedReader(new InputStreamReader(file.getInputStream()));

			List<Transaction> transactions = new ArrayList<>();
			String line;

			while ((line = reader.readLine()) != null) {
				// Skip the header line
				if (line.startsWith("ACCOUNT_NUMBER")) {
					continue;
				}

				// Parse the line
				String[] fields = line.split("\\|");
				String accountNumber = fields[0];
				BigDecimal trxAmount = new BigDecimal(fields[1]);
				String description = fields[2];
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime trxDateTime = LocalDateTime.parse((fields[3] + " " + fields[4]), formatter);
				Long customerId = Long.parseLong(fields[5]);
				Transaction transaction = new Transaction();
				transaction.setAccountNumber(accountNumber);
				transaction.setTrxAmount(trxAmount);
				transaction.setDescription(description);
				transaction.setTrxDateTime(trxDateTime);
				transaction.setCustomerId(customerId);
				transaction.setCreatedDate(LocalDateTime.now());
				transaction.setUpdatedDate(LocalDateTime.now());
				transactions.add(transaction);

				// Save in batches of 100 for performance
				if (transactions.size() == 100) {
					transactionRepository.saveAll(transactions);
					transactions.clear();
				}
			}

			// Save remaining transactions
			if (!transactions.isEmpty()) {
				transactionRepository.saveAll(transactions);
			}

		} catch (IOException e) {
			logger.error("processFile System Internal Abnormal occur cause by [{}].", e.getMessage());
		} catch (NumberFormatException e) {
			logger.error("processFile System Internal Abnormal occur cause by [{}].", e.getMessage());
		}
	}

	@Override
	public PageDTO<TransactionDTO> getTransactions(TransactionSearchDTO transactionSearchDTO) {
		PageDTO<TransactionDTO> pageDTO = new PageDTO<>();
		if (transactionSearchDTO.getPage() == null || !(transactionSearchDTO.getPage() > 0))
			transactionSearchDTO.setPage(1);
		if (transactionSearchDTO.getSize() == null || !(transactionSearchDTO.getSize() > 0)) {
			transactionSearchDTO.setSize(CommonConstants.DEFAULT_SIZE);
		}
		Sort sort = Sort.by(Sort.Direction.DESC, "id");
		Pageable pageable = PageRequest.of(transactionSearchDTO.getPage() - 1, transactionSearchDTO.getSize(), sort);
		Specification<Transaction> transactionSpec = TransactionSpecification.getTransactions(transactionSearchDTO);
		Page<Transaction> page = transactionRepository.findAll(transactionSpec, pageable);
		List<Transaction> transactionList = page.getContent();
		List<TransactionDTO> transactionDTOList = new ArrayList<>();
		for (Transaction trx : transactionList) {
			TransactionDTO trxDTO = new TransactionDTO(trx);
			transactionDTOList.add(trxDTO);
		}

		pageDTO.setDataList(transactionDTOList);
		pageDTO.setNumberofElements(page.getNumberOfElements());
		pageDTO.setPage(page.getNumber());
		pageDTO.setSize(page.getSize());
		pageDTO.setTotalElements(page.getTotalElements());
		pageDTO.setTotalPages(page.getTotalPages());
		return pageDTO;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public TransactionDTO updateTransaction(TransactionUpdateRequestDTO transactionUpdateRequestDTO) {
		String lockKey = RedisKey.BOOKING_CLASS_SCHEDULE_KEY + transactionUpdateRequestDTO.getId();
		RLock lock = redissonClient.getLock(lockKey);
		try {
			if (!lock.tryLock(5, 10, TimeUnit.SECONDS)) {
				logger.warn("Failed to acquire lock cause {}", String.format(ErrorCode.ERROR_000025.getDesc(), transactionUpdateRequestDTO.getId()));
				throw new CustomWebServiceException(ErrorCode.ERROR_000025.getCode(),
						String.format(ErrorCode.ERROR_000025.getDesc(), transactionUpdateRequestDTO.getId()));
			}
		Transaction transaction = checkValidTransaction(transactionUpdateRequestDTO.getId());
		transaction.setDescription(transactionUpdateRequestDTO.getDescription());
		transaction = transactionRepository.save(transaction);
		TransactionDTO transactionDTO = new TransactionDTO(transaction);
		return transactionDTO;
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			logger.error("Lock acquisition interrupted ", ErrorCode.ERROR_000025.getDesc());
			throw new CustomWebServiceException(ErrorCode.ERROR_000025.getCode(), ErrorCode.ERROR_000025.getDesc());
		} finally {
			if (lock.isHeldByCurrentThread()) {
				lock.unlock();
			}
		}
	}

	private Transaction checkValidTransaction(Long id) {
		Optional<Transaction> transactionOpt = transactionRepository.findById(id);
		if (!transactionOpt.isPresent()) {
			logger.warn("checkValidTransaction Error Occur cause {}",
					String.format(ErrorCode.ERROR_000004.getDesc(), "Transaction"));
			throw new CustomWebServiceException(ErrorCode.ERROR_000004.getCode(),
					String.format(ErrorCode.ERROR_000004.getDesc(), "Transaction"));
		}
		return transactionOpt.get();
	}

	@Override
	public TransactionDTO getById(Long id) {
		Transaction transaction = checkValidTransaction(id);
		TransactionDTO transactionDTO = new TransactionDTO(transaction);
		return transactionDTO;
	}

}
