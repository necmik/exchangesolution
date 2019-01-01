package com.necmi.exchangesolution.repos;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Temporal;

import com.necmi.exchangesolution.entities.ExchangeTransaction;

public interface ExchangeTransactionRepository extends JpaRepository<ExchangeTransaction, Long>{

	public List<ExchangeTransaction> findByTransactionDate(@Temporal(TemporalType.DATE) Date transactionDate);
}
