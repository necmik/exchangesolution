package com.necmi.exchangesolution.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.necmi.exchangesolution.dto.ExchangeTransactionRequest;
import com.necmi.exchangesolution.entities.ExchangeTransaction;
import com.necmi.exchangesolution.integration.CurrencyLayerRestClient;
import com.necmi.exchangesolution.repos.ExchangeTransactionRepository;
import com.necmi.exchangesolution.services.ExchangeTransactionService;

@Controller
public class TransactionController {

	@Autowired
	ExchangeTransactionRepository repository;
	
	@Autowired
	ExchangeTransactionService transactionService;
	
	@Autowired
	CurrencyLayerRestClient currencyLayer;
	
	@RequestMapping("/createTransaction")
	public String createTransaction(ModelMap modelMap) {
		List<ExchangeTransaction> allTransactions = repository.findAll();
		modelMap.addAttribute("transactions", allTransactions);
		return "createTransaction";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@RequestParam("sellCurrency") String sellCurrency, @RequestParam("sellAmount") BigDecimal sellAmount,
			@RequestParam("buyCurrency") String buyCurrency,  ModelMap modelMap) {
		
		ConversionResponse conversionResponse = currencyLayer.convertAmount(sellAmount, sellCurrency, buyCurrency);
		if (conversionResponse.isSuccess()) {
			BigDecimal convertedAmount = conversionResponse.getTargetAmount();
			ExchangeTransactionRequest transactionRequest = new ExchangeTransactionRequest();
			transactionRequest.setFromCurrency(sellCurrency);
			transactionRequest.setToCurrency(buyCurrency);
			transactionRequest.setSellAmount(sellAmount);
			transactionRequest.setBuyAmount(convertedAmount);
			ExchangeTransaction savedTransaction = transactionService.createTransaction(transactionRequest);
			modelMap.addAttribute("msg", "Transaction succesfully created with id:" + savedTransaction.getTransactionId());
		} else {
			modelMap.addAttribute("msg", conversionResponse.getErrorInfo());
		}
		return "createTransaction";
	}
	
}
