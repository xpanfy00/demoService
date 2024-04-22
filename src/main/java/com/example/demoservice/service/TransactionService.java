package com.example.demoservice.service;
import com.example.demoservice.model.CurrencyRate;
import com.example.demoservice.model.Transaction;
import com.example.demoservice.model.TransactionLimit;
import com.example.demoservice.repository.CurrencyRateRepository;
import com.example.demoservice.repository.TransactionLimitRepository;
import com.example.demoservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionLimitService transactionLimitService;
    private final TransactionLimitRepository transactionLimitRepository;
    private final CurrencyRateRepository currencyRateRepository;
    private final  CurrencyRateService currencyRateService;
    private TransactionLimit findByAccountFrom(Long accountFrom) {
        return transactionLimitRepository.findAllByAccount(accountFrom);
    }

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, TransactionLimitRepository transactionLimitRepository, TransactionLimitService transactionLimitService, CurrencyRateRepository currencyRateRepository, CurrencyRateService currencyRateService) {
        this.transactionRepository = transactionRepository;
        this.transactionLimitService = transactionLimitService;
        this.transactionLimitRepository = transactionLimitRepository;

        this.currencyRateRepository = currencyRateRepository;
        this.currencyRateService = currencyRateService;
    }

    public Transaction createTransaction(Transaction transaction) {
        transactionLimitService.setLimitIfNotExists(transaction.getAccountFrom());
        TransactionLimit accountLimit = transactionLimitRepository.findAllByAccount(transaction.getAccountFrom());
        if (checkLimit(transaction.getAccountFrom(), transaction.getSum()) || !accountLimit.isExceeded() ) {
            setNewLimit(transaction.getAccountFrom(), transaction.getSum());
            transactionLimitService.setLimitExceededFlag(transaction);
            return transactionRepository.save(transaction);
        }
        return null;

    }



    public boolean checkLimit(Long accountFrom , Double sum){

        TransactionLimit limit = findByAccountFrom(accountFrom);
        if (limit.getLimitLeft() >= sum/getCurrencyRate()){
            return true;
        }
        return false;
    }

    public void setNewLimit(Long accountFrom, Double sum){
        TransactionLimit limit = transactionLimitRepository.findAllByAccount(accountFrom);
        if (limit != null) {
            Double currentLimitLeft = limit.getLimitLeft();
            Double updatedLimitLeft = currentLimitLeft - (sum / getCurrencyRate());
            limit.setLimitLeft(updatedLimitLeft);

        }else {
            System.out.println("TransactionLimit cannot be null");
        }
    }

    public Double getCurrencyRate(){
        currencyRateService.jsonResponseworker();
        List<CurrencyRate> currencyRates = currencyRateRepository.findAllByDate(LocalDate.now());
        Double currencyKZT = 0.0;
        for (CurrencyRate t: currencyRates){
            if (t.getCurrencyCode().equals("KZT")){
                currencyKZT = t.getCloseRate();
            }
        }
        return currencyKZT;
    }





}
