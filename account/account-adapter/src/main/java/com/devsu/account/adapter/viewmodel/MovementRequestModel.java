package com.devsu.account.adapter.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MovementRequestModel {

    private String accountNumber;
    private double amount;

}