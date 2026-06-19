package com.devsu.account.adapter.controller;

import java.util.UUID;

import com.devsu.account.dto.AccountRequest;
import com.devsu.account.usecase.CreateAccount;

public class CreateAccountController {
    private final CreateAccount useCase;

    public CreateAccountController(CreateAccount useCase) {
        this.useCase = useCase;
    }

    public void handle(String customerId, String accountType) {
        AccountRequest request = new AccountRequest();
        request.setAccountType(accountType);
        request.setClientId(UUID.fromString(customerId));
        useCase.execute(request);
    }
}