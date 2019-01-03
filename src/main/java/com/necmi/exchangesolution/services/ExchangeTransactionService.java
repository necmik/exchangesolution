package com.necmi.exchangesolution.services;

import java.util.Date;
import java.util.List;

import com.necmi.exchangesolution.dto.ExchangeTransactionRequest;
import com.necmi.exchangesolution.entities.ExchangeTransaction;

public interface ExchangeTransactionService {
	
	public ExchangeTransaction createTransaction(ExchangeTransactionRequest request);
	public ExchangeTransaction findTransactionById(Long transactionId);
	public List<ExchangeTransaction> findTransactionListByDate(Date transactionDate);

}
