package com.devsu.customer.adapter.controller;

import java.util.UUID;

import com.devsu.customer.dto.CustomerRequest;
import com.devsu.customer.usecase.UpdateCustomer;

public class UpdateCustomerController {
    private final UpdateCustomer useCase;

    public UpdateCustomerController(UpdateCustomer useCase) {
        this.useCase = useCase;
    }

    public void handle(String id, String name, String gender, int age, boolean state) {
        CustomerRequest request = new CustomerRequest();
        request.setName(name);
        request.setGender(gender);
        request.setAge(age);
        request.setState(state);

        useCase.execute(UUID.fromString(id), request);
    }
}
