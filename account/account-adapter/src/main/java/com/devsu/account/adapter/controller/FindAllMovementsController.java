package com.devsu.account.adapter.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.devsu.account.adapter.viewmodel.MovementViewModel;
import com.devsu.account.usecase.FindMovements;

public class FindAllMovementsController {

    private final FindMovements useCase;

    public FindAllMovementsController(FindMovements useCase) {
        this.useCase = useCase;
    }

    public List<MovementViewModel> handle(String accountNumber) {
        return useCase.execute(accountNumber).stream()
                .map(movementResponse -> new MovementViewModel(
                        movementResponse.getId().toString(),
                        movementResponse.getAccountId().toString(),
                        movementResponse.getAmount(),
                        movementResponse.getCreationDate()))
                .collect(Collectors.toList());
    }

}
