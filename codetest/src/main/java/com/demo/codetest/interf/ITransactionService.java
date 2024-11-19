package com.demo.codetest.interf;

import org.springframework.web.multipart.MultipartFile;

import com.demo.codetest.dto.PageDTO;
import com.demo.codetest.dto.TransactionDTO;
import com.demo.codetest.dto.TransactionSearchDTO;
import com.demo.codetest.dto.TransactionUpdateRequestDTO;

public interface ITransactionService {

	void processFile(MultipartFile file);

	PageDTO<TransactionDTO> getTransactions(TransactionSearchDTO transactionSearchDTO);

	TransactionDTO updateTransaction(TransactionUpdateRequestDTO transactionUpdateRequestDTO);

	TransactionDTO getById(Long id);

}
