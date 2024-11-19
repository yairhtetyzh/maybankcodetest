package com.demo.codetest.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.codetest.dto.PageDTO;
import com.demo.codetest.dto.ResponseDTO;
import com.demo.codetest.dto.TransactionDTO;
import com.demo.codetest.dto.TransactionSearchDTO;
import com.demo.codetest.dto.TransactionUpdateRequestDTO;
import com.demo.codetest.enums.ErrorCode;
import com.demo.codetest.exception.CustomWebServiceException;
import com.demo.codetest.interf.ITransactionService;

@RestController
@RequestMapping(value = "api/auth/transaction")
public class TransactionController {

	private final Logger logger = LoggerFactory.getLogger(TransactionController.class);

	private final ITransactionService transactionService;

	public TransactionController(ITransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@PostMapping("/process")
	public ResponseEntity<?> processBatchFile(@RequestParam("file") MultipartFile file) {
		try {
			if (file.isEmpty()) {
				return ResponseEntity.badRequest()
						.body(new ResponseDTO<>(ErrorCode.ERROR_000024.getCode(), ErrorCode.ERROR_000024.getDesc()));
			}

			// Process the file
			transactionService.processFile(file);

			return ResponseEntity
					.ok(new ResponseDTO<>(ErrorCode.ERROR_000000.getCode(), ErrorCode.ERROR_000000.getDesc()));
		} catch (CustomWebServiceException e) {
			logger.error("processBatchFile CustomException occur cause by [{}].", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDTO<>(e.getErrorCode(), e.getErrorMsg()));
		} catch (Exception e) {
			logger.error("processBatchFile System Internal Abnormal occur cause by [{}].", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDTO<>(ErrorCode.ERROR_999999.getCode(), ErrorCode.ERROR_999999.getDesc()));
		}
	}

	
	@RequestMapping(value = "get-all", method = RequestMethod.POST)
	public ResponseEntity<?> getTransactions(@RequestBody TransactionSearchDTO transactionSearchDTO) {
		logger.debug("getTransactions start.....");
		try {
			PageDTO<TransactionDTO> transactionPageDTO = transactionService.getTransactions(transactionSearchDTO);
			logger.debug("getTransactions package end......");
			return ResponseEntity.ok(new ResponseDTO<PageDTO<TransactionDTO>>(transactionPageDTO));
		} catch (CustomWebServiceException e) {
			logger.error("getTransactions CustomException occur cause by [{}].", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDTO<>(e.getErrorCode(), e.getErrorMsg()));
		} catch (Exception e) {
			logger.error("getTransactions System Internal Abnormal occur cause by [{}].", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDTO<>(ErrorCode.ERROR_999999.getCode(), ErrorCode.ERROR_999999.getDesc()));
		}
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ResponseEntity<?> updateTransaction(
			@Valid @RequestBody TransactionUpdateRequestDTO transactionUpdateRequestDTO) {
		logger.debug("updateTransaction start.....");
		try {
			TransactionDTO transactionDTO = transactionService.updateTransaction(transactionUpdateRequestDTO);
			logger.debug("updateTransaction package end......");
			return ResponseEntity.ok(new ResponseDTO<TransactionDTO>(transactionDTO));
		} catch (CustomWebServiceException e) {
			logger.error("updateTransaction CustomException occur cause by [{}].", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDTO<>(e.getErrorCode(), e.getErrorMsg()));
		} catch (Exception e) {
			logger.error("updateTransaction System Internal Abnormal occur cause by [{}].", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDTO<>(ErrorCode.ERROR_999999.getCode(), ErrorCode.ERROR_999999.getDesc()));
		}
	}

	@RequestMapping(value = "getbyId/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		logger.debug("Start by transaction.....");
		try {
			TransactionDTO transactionDTO = transactionService.getById(id);
			logger.debug("findById End......");
			return ResponseEntity.ok(new ResponseDTO<TransactionDTO>(transactionDTO));
		} catch (CustomWebServiceException e) {
			logger.error("findById CustomException occur cause by [{}].", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDTO<>(e.getErrorCode(), e.getErrorMsg()));
		} catch (Exception e) {
			logger.error("findById System Internal Abnormal occur cause by [{}].", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDTO<>(ErrorCode.ERROR_999999.getCode(), ErrorCode.ERROR_999999.getDesc()));
		}
	}
}
