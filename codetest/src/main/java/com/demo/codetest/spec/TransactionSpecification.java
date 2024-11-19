package com.demo.codetest.spec;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.demo.codetest.dto.TransactionSearchDTO;
import com.demo.codetest.entity.Transaction;

public class TransactionSpecification {

	public static Specification<Transaction> getTransactions(TransactionSearchDTO transactionSearchDTO) {

		return new Specification<Transaction>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Transaction> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {

				final Collection<Predicate> predicates = new ArrayList<>();

				if (!StringUtils.isEmpty(transactionSearchDTO.getCustomerId())) {
					final Predicate customerIdPredicate = criteriaBuilder.equal(root.get("customerId"),
							transactionSearchDTO.getCustomerId());
					predicates.add(customerIdPredicate);
				}

				if (!StringUtils.isEmpty(transactionSearchDTO.getAccountNumber())) {
					final Predicate accountNumberPredicate = criteriaBuilder.equal(root.get("accountNumber"),
							transactionSearchDTO.getAccountNumber());
					predicates.add(accountNumberPredicate);
				}

				if (!StringUtils.isEmpty(transactionSearchDTO.getDescription())) {
					final Predicate descriptionPredicate = criteriaBuilder.like(root.get("description"),
							"%" + transactionSearchDTO.getDescription() + "%");
					predicates.add(descriptionPredicate);
				}

				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}

		};
	}
}
