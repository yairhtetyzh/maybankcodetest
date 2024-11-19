package com.demo.codetest.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class TransactionUpdateRequestDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 747907061107350343L;

	private Long id;

	private String description;
}
