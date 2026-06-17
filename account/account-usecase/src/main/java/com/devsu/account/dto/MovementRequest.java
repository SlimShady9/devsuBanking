package com.devsu.account.dto;

import lombok.Data;

@Data
public class MovementRequest {

    private String accountNumber;
    private double amount;
}
