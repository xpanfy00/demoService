package com.example.demoservice.controller;

import com.example.demoservice.repository.CurrencyRateRepository;
import com.example.demoservice.service.CurrencyRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currency-rates")
public class CurrencyRateController {

    private final CurrencyRateService currencyRateService;
    private final CurrencyRateRepository currencyRateRepository;

    @Autowired
    public CurrencyRateController(CurrencyRateService currencyRateService, CurrencyRateRepository currencyRateRepository) {
        this.currencyRateService = currencyRateService;
        this.currencyRateRepository = currencyRateRepository;
    }
    @GetMapping
    public void curRate(){
        currencyRateService.jsonResponseworker();
    }


}

