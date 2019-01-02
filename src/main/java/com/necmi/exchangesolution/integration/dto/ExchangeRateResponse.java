package com.necmi.exchangesolution.integration.dto;

import java.math.BigDecimal;

public class ExchangeRateResponse extends CurrencyServicesResponse{

	private String sourceCurrency;
	private String targetCurrency;
	private BigDecimal rate;
	
	public String getSourceCurrency() {
		return sourceCurrency;
	}
	public void setSourceCurrency(String sourceCurrency) {
		this.sourceCurrency = sourceCurrency;
	}
	public String getTargetCurrency() {
		return targetCurrency;
	}
	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	
	
}
