package com.necmi.exchangesolution.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.necmi.exchangesolution.integration.CurrencyLayerRestClient;
import com.necmi.exchangesolution.integration.dto.ExchangeRateResponse;

@RestController
@RequestMapping("/currency")
public class CurrencyLayerClientRestController {

	@Autowired
	CurrencyLayerRestClient currencyLayer;
	
	@GetMapping("/exchangerate/{sourceCurrency}/{targetCurrency}")
	public ExchangeRateResponse getExchangeRate(@PathVariable String sourceCurrency, @PathVariable String targetCurrency) {
		return currencyLayer.getExchangeRate(sourceCurrency.toUpperCase(), targetCurrency.toUpperCase());
	}
	
	@GetMapping("/exchangerate/{sourceCurrency}/{targetCurrency}/{amount}")
	public ConversionResponse convertAmount(@PathVariable String sourceCurrency, @PathVariable String targetCurrency, @PathVariable BigDecimal amount) {
		return currencyLayer.convertAmount(amount, sourceCurrency.toUpperCase(), targetCurrency.toUpperCase());
	}
	
}
