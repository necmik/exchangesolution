package com.necmi.exchangesolution.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.necmi.exchangesolution.entities.ExchangeTransaction;
import com.necmi.exchangesolution.repos.ExchangeTransactionRepository;

@RestController
@RequestMapping("/transactions")
public class ExchangeTransactionRestController {
	
	@Autowired
	ExchangeTransactionRepository repository;
	
	@GetMapping 
	public List<ExchangeTransaction> getTransactions() {
		return repository.findAll();
	}
	
	@GetMapping("/{id:[0-9]+}")
	public ExchangeTransaction getTransactionById(@PathVariable Long id) {
		Optional<ExchangeTransaction> transaction = repository.findById(id);
		if (transaction.isPresent()) {
			return transaction.get();
		}
		return null;
	}
	
	@PostMapping
	public ExchangeTransaction createTransaction(@RequestBody ExchangeTransaction transaction) {
		if (null == transaction.getTransactionDate()) {
			transaction.setTransactionDate(new Date());
		}
		return repository.save(transaction);
	}
	
	@GetMapping("/{transactionDate:^\\\\d{4}-\\\\d{2}-\\\\d{2}$}")
	public List<ExchangeTransaction> getTransactionByDate(@PathVariable Date transactionDate) {
		List<ExchangeTransaction> transactions = repository.findByTransactionDate(transactionDate);
		return transactions;
	}
}
