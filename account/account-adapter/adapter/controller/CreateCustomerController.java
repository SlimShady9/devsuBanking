package com.devsu.customer.adapter.controller;

import com.devsu.customer.dto.CustomerRequest;
import com.devsu.customer.usecase.CreateCustomer;

public class CreateCustomerController {
    private final CreateCustomer useCase;

    public CreateCustomerController(CreateCustomer useCase) {
        this.useCase = useCase;
    }

    public void handle(String name, String gender, int age, boolean state, String password) {
        CustomerRequest request = new CustomerRequest();
        request.setName(name);
        request.setGender(gender);
        request.setAge(age);
        request.setState(state);
        request.setPassword(password);

        useCase.execute(request);
    }
}