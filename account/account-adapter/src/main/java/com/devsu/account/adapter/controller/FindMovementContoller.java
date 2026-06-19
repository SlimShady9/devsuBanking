package com.devsu.account.adapter.controller;

import java.util.UUID;

import com.devsu.account.adapter.viewmodel.MovementViewModel;
import com.devsu.account.dto.MovementResponse;
import com.devsu.account.usecase.FindMovement;

public class FindMovementContoller {

    private final FindMovement useCase;

    public FindMovementContoller(FindMovement useCase) {
        this.useCase = useCase;
    }

    public MovementViewModel handle(String id) {

        MovementResponse movementResponse = useCase.execute(UUID.fromString(id));
        return new MovementViewModel(
                movementResponse.getId().toString(),
                movementResponse.getAccountId().toString(),
                movementResponse.getAmount(),
                movementResponse.getCreationDate());

    }

}
