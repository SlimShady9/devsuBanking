package com.devsu.account.adapter.controller;

import java.util.UUID;

import com.devsu.account.usecase.UpdateAccount;

public class UpdateAccountController {
    private final UpdateAccount useCase;

    public UpdateAccountController(UpdateAccount useCase) {
        this.useCase = useCase;
    }

    public void handle(String id, String accountType, Boolean state) {

        useCase.execute(UUID.fromString(id), accountType, state);
    }
}
