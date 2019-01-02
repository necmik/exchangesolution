package com.necmi.exchangesolution.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="exc_transaction")
public class ExchangeTransaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="transaction_id")
	private Long transactionId;
	@Column(name="transaction_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date transactionDate;
	@Column(name="from_currency")
	private String fromCurrency;
	@Column(name="to_currency")
	private String toCurrency;
	@Column(name="sell_amount")
	private BigDecimal sellAmount;
	@Column(name="buy_amount")
	private BigDecimal buyAmount;
	
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getFromCurrency() {
		return fromCurrency;
	}
	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}
	public String getToCurrency() {
		return toCurrency;
	}
	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}
	public BigDecimal getSellAmount() {
		return sellAmount;
	}
	public void setSellAmount(BigDecimal sellAmount) {
		this.sellAmount = sellAmount;
	}
	public BigDecimal getBuyAmount() {
		return buyAmount;
	}
	public void setBuyAmount(BigDecimal buyAmount) {
		this.buyAmount = buyAmount;
	}
	@Override
	public String toString() {
		return "ExchangeTransaction [transactionId=" + transactionId + ", transactionDate=" + transactionDate
				+ ", fromCurrency=" + fromCurrency + ", toCurrency=" + toCurrency + ", sellAmount=" + sellAmount
				+ ", buyAmount=" + buyAmount + "]";
	}
}
