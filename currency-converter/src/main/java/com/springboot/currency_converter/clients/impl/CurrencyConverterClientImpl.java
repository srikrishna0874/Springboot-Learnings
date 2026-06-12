package com.springboot.currency_converter.clients.impl;

import com.springboot.currency_converter.clients.CurrencyConverterClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyConverterClientImpl implements CurrencyConverterClient {

    private final RestClient restClient;

    @Value("${currency_converter.api.key}")
    private String API_KEY;

    @Override
    public double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        HashMap response = restClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("api_key", API_KEY)
                        .queryParam("base_currency", fromCurrency)
                        .queryParam("currencies", toCurrency)
                        .build())
                .retrieve()
                .body(HashMap.class);

        Map<String, Double> responseMap = (Map<String, Double>) response.get("data");
        double exchangeRate = responseMap.get(toCurrency);
        double res = amount * exchangeRate;

        System.out.println("API Response: " + response);

         // Extract the exchange rate from the response
        return res;
    }
}
