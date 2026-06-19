package com.devsu.account.adapter.controller;

import com.devsu.account.dto.MovementRequest;
import com.devsu.account.usecase.CreateMovement;

public class CreateMovementController {
    private final CreateMovement useCase;

    public CreateMovementController(CreateMovement useCase) {
        this.useCase = useCase;
    }

    public void handle(String accountNumber, double amount) {
        MovementRequest request = new MovementRequest();
        request.setAccountNumber(accountNumber);
        request.setAmount(amount);

        useCase.execute(request);
    }
}