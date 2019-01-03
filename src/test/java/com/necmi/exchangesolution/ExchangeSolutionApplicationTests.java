package com.necmi.exchangesolution;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.necmi.exchangesolution.entities.ExchangeTransaction;
import com.necmi.exchangesolution.repos.ExchangeTransactionRepository;
import com.necmi.exchangesolution.services.ExchangeTransactionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExchangeSolutionApplicationTests {

	@Autowired
	ExchangeTransactionRepository repository;
	
	@Autowired
	ExchangeTransactionService service;
	
	@Test
	public void testFindTransactionById() {
		Optional<ExchangeTransaction> transaction = repository.findById(1L);
		assertTrue(transaction.isPresent());
	}
	
	@Test
	public void testFindTransactionListByDate() throws ParseException {
		Date date=new SimpleDateFormat("yyyyMMdd").parse("20190101");
		List<ExchangeTransaction> transactions = repository.findByTransactionDate(date);
		assertTrue(transactions.size() >= 2);
	}
	
	@Test
	public void testCreateTransaction() {
		ExchangeTransaction transaction = new ExchangeTransaction();
		transaction.setTransactionDate(new Date());
		transaction.setFromCurrency("USD");
		transaction.setToCurrency("TRY");
		transaction.setSellAmount(new BigDecimal(5000L));
		transaction.setBuyAmount(new BigDecimal(900L));
		
		ExchangeTransaction savedTransaction = repository.save(transaction);
		System.out.println(savedTransaction.getTransactionId());
	}
	
	@Test
	public void testServiceFindTransactionById() {
		ExchangeTransaction transaction = service.findTransactionById(1L);
		assertTrue(transaction.getFromCurrency().equals("TRY"));
	}

}

