package com.example.demoservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "transaction_limit")
public class TransactionLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "limit_sum")
    private Double limitSum;

    @Column(name = "limit_datetime")
    private LocalDateTime limitDatetime;

    @Column(name = "limit_currency_shortname")
    private String limitCurrencyShortname;

    @Column(name = "limit_exceeded")
    private boolean exceeded;

    @Column(name = "account")
    private Long account;

    @Column(name = "limit_left")
    private Double limitLeft;

    @OneToMany(mappedBy = "transactionLimit")
    private List<Transaction> transactions;

}
