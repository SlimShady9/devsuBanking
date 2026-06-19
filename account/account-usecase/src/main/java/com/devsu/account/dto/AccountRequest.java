package com.devsu.account.dto;

import java.util.UUID;
import lombok.Data;

@Data
public class AccountRequest {

    private String accountType;
    private double balance;
    private UUID clientId;
}
