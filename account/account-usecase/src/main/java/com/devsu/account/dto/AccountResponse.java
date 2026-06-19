package com.devsu.account.dto;

import java.util.UUID;
import com.devsu.account.domain.model.Account;
import com.devsu.account.domain.model.AccountType;
import lombok.Data;

@Data
public class AccountResponse {

    private UUID id;
    private String accountNumber;
    private AccountType accountType;
    private double balance;
    private Boolean state;
    private UUID customerId;

    public static AccountResponse fromDomain(Account account) {
        AccountResponse response = new AccountResponse();
        response.setId(account.getId());
        response.setAccountNumber(account.getAccountNumber());
        response.setAccountType(account.getAccountType());
        response.setBalance(account.getBalance());
        response.setState(account.getState());
        response.setCustomerId(account.getCustomerId());
        return response;
    }
}
