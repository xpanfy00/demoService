package com.example.demoservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "Transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_from")
    private Long accountFrom;

    @Column(name = "account_to")
    private Long accountTo;

    @Column(name = "currency_shortname")
    private String currencyShortname;

    @Column(name = "sum")
    private Double sum;

    @Column(name = "expense_category")
    private String expenseCategory;

    @Column(name = "datetime")
    private LocalDateTime datetime;

    @ManyToOne
    @JoinColumn(name = "account_from", referencedColumnName = "account", insertable = false, updatable = false)
    private TransactionLimit transactionLimit;



}
