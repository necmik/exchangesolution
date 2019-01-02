package com.necmi.exchangesolution.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.necmi.exchangesolution.services.ExchangeTransactionService;

@Controller
public class TransactionController {

	@Autowired
	ExchangeTransactionService transactionService;
	
	
}
