package com.demo.codetest.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.demo.codetest.entity.Transaction;
import com.demo.codetest.utils.CommonConstants;
import com.demo.codetest.utils.CommonUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class TransactionDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 7357455550869299011L;

	private Long id;

	private String accountNumber;

	private BigDecimal trxAmount;

	private String description;

	private String trxDateTime;

	private Long customerId;
	
	public TransactionDTO(Transaction entity) {
		this.id = entity.getId();
		this.accountNumber = entity.getAccountNumber();
		this.trxAmount = entity.getTrxAmount();
		this.description = entity.getDescription();
		this.trxDateTime = entity.getTrxDateTime() == null ? null : CommonUtils.formatLocalDateTime(entity.getTrxDateTime(), CommonConstants.DATE_FORMAT_yyyymmdd_HHMMSS);
	    this.customerId = entity.getCustomerId();
	}
}
