package com.example.demoservice.service;

import com.example.demoservice.model.Transaction;
import com.example.demoservice.model.TransactionLimit;
import com.example.demoservice.repository.TransactionLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.DoubleToIntFunction;

@Service
public class TransactionLimitService {

    private final TransactionLimitRepository transactionLimitRepository;

    @Autowired
    public TransactionLimitService(TransactionLimitRepository transactionLimitRepository) {
        this.transactionLimitRepository = transactionLimitRepository;
    }

    public void setLimitIfNotExists( Long accountFrom){
        if (!transactionLimitRepository.existsByAccount(accountFrom)) {
            TransactionLimit limit = new TransactionLimit();
            limit.setLimitSum(1000.0);
            limit.setLimitCurrencyShortname("USD");
            limit.setLimitDatetime(LocalDateTime.now());
            limit.setExceeded(false);
            limit.setAccount(accountFrom);
            limit.setLimitLeft(1000.0);
            transactionLimitRepository.save(limit);
        }
    }

    public TransactionLimit setLimit(Long account, Double limit){
        TransactionLimit accountLimit = transactionLimitRepository.findAllByAccount(account);
        accountLimit.setLimitLeft(limit);
        accountLimit.setLimitDatetime(LocalDateTime.now());
        accountLimit.setLimitSum(limit);
        transactionLimitRepository.save(accountLimit);
        return accountLimit;
    }

    public List<TransactionLimit> getAllTransactionLimits() {
        return transactionLimitRepository.findAll();
    }

    public void setLimitExceededFlag(Transaction transaction) {
        TransactionLimit accountLimit = transactionLimitRepository.findAllByAccount(transaction.getAccountFrom());
        if (accountLimit.getLimitLeft() <= 0.0){
            accountLimit.setExceeded(true);
            transactionLimitRepository.save(accountLimit);
        }


    }

}
