package com.springboot.currency_converter.clients;

import org.springframework.stereotype.Service;


public interface CurrencyConverterClient {

    double convertCurrency(String fromCurrency, String toCurrency, double amount);
}
