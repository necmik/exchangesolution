package com.necmi.exchangesolution.repos;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.necmi.exchangesolution.entities.ExchangeTransaction;

public interface ExchangeTransactionRepository extends JpaRepository<ExchangeTransaction, Long>{

	@Query("select e from ExchangeTransaction e where DATE(e.transactionDate)=:transactionDate")
	public List<ExchangeTransaction> findByTransactionDate(@Param("transactionDate") Date transactionDate);
}
