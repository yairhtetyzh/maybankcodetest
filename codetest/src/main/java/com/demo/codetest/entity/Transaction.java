package com.demo.codetest.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "transaction")
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 305362821368097331L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "account_number")
	private String accountNumber;

	@Column(name = "trx_amount")
	private BigDecimal trxAmount;

	@Column(name = "description")
	private String description;

	@Column(name = "trx_date_time")
	private LocalDateTime trxDateTime;

	@Column(name = "customer_id")
	private Long customerId;
}
