package com.springboot.currency_converter;

import com.springboot.currency_converter.clients.CurrencyConverterClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CurrencyConverterApplicationTests {

	@Autowired
	private CurrencyConverterClient currencyConverterClient;

	@Test
	void contextLoads() {
	}

	@Test
	public void convertAmount() {
		System.out.println(currencyConverterClient.convertCurrency("INR", "USD", 20));
	}

}
