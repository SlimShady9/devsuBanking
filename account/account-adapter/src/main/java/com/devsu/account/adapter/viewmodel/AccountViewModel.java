package com.devsu.account.adapter.viewmodel;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountViewModel {

    private String id;
    private String accountNumber;
    private String accountType;
    private Boolean state;
    private double balance;
    private UUID customerId;

}
