package com.example.demoservice.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "currencyrate")
public class CurrencyRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currency_code")
    private String currencyCode;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "close_rate")
    private Double closeRate;
    @Column(name = "previous_close_rate")
    private Double previousCloseRate;

}
