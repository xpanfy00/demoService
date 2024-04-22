package com.example.demoservice.controller;

import com.example.demoservice.model.TransactionLimit;
import com.example.demoservice.repository.TransactionLimitRepository;
import com.example.demoservice.service.TransactionLimitService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/transaction-limits")
public class TransactionLimitController {

    private final TransactionLimitService transactionLimitService;
    private final TransactionLimitRepository transactionLimitRepository;

    @Autowired
    public TransactionLimitController(TransactionLimitService transactionLimitService, TransactionLimitRepository transactionLimitRepository) {
        this.transactionLimitService = transactionLimitService;
        this.transactionLimitRepository = transactionLimitRepository;
    }

    @GetMapping
    public List<TransactionLimit> getAllTransactionLimits() {
        return transactionLimitService.getAllTransactionLimits();
    }

    @PostMapping("/set-limit")
    public TransactionLimit setLimit(@RequestBody TransactionLimitRequest transactionLimitRequest) {

        return transactionLimitService.setLimit(transactionLimitRequest.getAccount(), transactionLimitRequest.getLimit());
    }

    @Getter
    @Setter
    public static class TransactionLimitRequest {
        private Long account;
        private Double limit;
        private LocalDateTime localDate;
        public TransactionLimitRequest() {
        }


    }
}


