package com.necmi.exchangesolution.controllers;

import java.math.BigDecimal;

import com.necmi.exchangesolution.integration.dto.CurrencyServicesResponse;

public class ConversionResponse extends CurrencyServicesResponse{

	private BigDecimal targetAmount;

	public BigDecimal getTargetAmount() {
		return targetAmount;
	}

	public void setTargetAmount(BigDecimal targetAmount) {
		this.targetAmount = targetAmount;
	}
	
	
}
