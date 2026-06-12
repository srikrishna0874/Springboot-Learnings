package com.springboot.currency_converter.controllers;

import com.springboot.currency_converter.clients.CurrencyConverterClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/convertCurrency")
@RequiredArgsConstructor
public class CurrencyConverterController {

    private final CurrencyConverterClient currencyConverterClient;

    @GetMapping
    public ResponseEntity<Double> convertCurrency(@RequestParam("fromCurrency") String from,
                                                  @RequestParam("toCurrency") String to,
                                                  @RequestParam("units") double units) {

        double res = currencyConverterClient.convertCurrency(from, to, units);

        System.out.println(res);

        return ResponseEntity.ok(res);

    }

}
