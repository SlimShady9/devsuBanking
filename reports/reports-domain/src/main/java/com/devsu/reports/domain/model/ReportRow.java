package com.devsu.reports.domain.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class ReportRow {
    private final LocalDate date;
    private final String customer;
    private final String accountNumber;
    private final String type;
    private final double initialBalance;
    private final boolean state;
    private final double movement;
    private final double balance;

}