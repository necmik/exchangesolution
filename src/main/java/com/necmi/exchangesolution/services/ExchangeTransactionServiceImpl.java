package com.necmi.exchangesolution.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.necmi.exchangesolution.dto.ExchangeTransactionRequest;
import com.necmi.exchangesolution.entities.ExchangeTransaction;
import com.necmi.exchangesolution.repos.ExchangeTransactionRepository;

@Service
public class ExchangeTransactionServiceImpl implements ExchangeTransactionService {

	@Autowired
	ExchangeTransactionRepository repository;
	
	@Override
	@Transactional
	public ExchangeTransaction createTransaction(ExchangeTransactionRequest request) {
		ExchangeTransaction transaction = new ExchangeTransaction();
		transaction.setTransactionDate(new Date());
		transaction.setFromCurrency(request.getFromCurrency());
		transaction.setToCurrency(request.getToCurrency());
		transaction.setSellAmount(request.getSellAmount());
		transaction.setBuyAmount(request.getBuyAmount());
		
		ExchangeTransaction savedTransaction = repository.save(transaction);
		
		return savedTransaction;
	}

	@Override
	public ExchangeTransaction getTransactionById(Long transactionId) {
		Optional<ExchangeTransaction> transaction = repository.findById(transactionId);
		if (transaction.isPresent()) {
			return transaction.get();
		}
		return null;
	}

	@Override
	public List<ExchangeTransaction> getTransactionListByDate(@Param("transactionDate") Date transactionDate) {
		List<ExchangeTransaction> transactions = repository.findByTransactionDate(transactionDate);
		return transactions;
	}

}
