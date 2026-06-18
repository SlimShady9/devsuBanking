package com.devsu.customer.adapter.controller;

import java.util.UUID;

import com.devsu.customer.usecase.DeleteCustomer;

public class DeleteCustomerController {
    private final DeleteCustomer useCase;

    public DeleteCustomerController(DeleteCustomer useCase) {
        this.useCase = useCase;
    }

    public void handle(String id) {
        useCase.execute(UUID.fromString(id));
    }
}
