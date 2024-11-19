package com.demo.codetest.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class TransactionSearchDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -6056458621783866788L;

	private String customerId;
	
	private String accountNumber;
	
	private String description;
	
	Integer page = 1;
	
	Integer size = 10;
}
