package com.devsu.account.adapter.viewmodel;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MovementViewModel {

    private String id;
    private String accountId;
    private double amount;
    private Instant date;

}
