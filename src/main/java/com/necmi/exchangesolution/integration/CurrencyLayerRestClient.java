package com.necmi.exchangesolution.integration;

import java.math.BigDecimal;

import com.necmi.exchangesolution.controllers.ConversionResponse;
import com.necmi.exchangesolution.integration.dto.ExchangeRateResponse;

public interface CurrencyLayerRestClient {

	//Info : Because the service is not free to send source currency we just get the default rate between currency pair
	public ExchangeRateResponse getExchangeRate(String currency1, String currency2);
	
	public ConversionResponse convertAmount(BigDecimal sourceAmount, String sourceCurrency, String targetCurrency);
	
}
