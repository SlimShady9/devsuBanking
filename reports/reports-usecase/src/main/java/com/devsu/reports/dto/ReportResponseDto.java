package com.devsu.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class ReportResponseDto {
    private final LocalDate date;
    private final String customer;
    private final String accountNumber;
    private final String type;
    private final double initialBalance;
    private final boolean state;
    private final double movement;
    private final double balance;
}
