package com.devsu.customer.adapter.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.devsu.customer.adapter.viewmodel.CustomerViewModel;
import com.devsu.customer.usecase.FindAllCustomers;

public class FindAllCustomersController {

    private final FindAllCustomers useCase;

    public FindAllCustomersController(FindAllCustomers useCase) {
        this.useCase = useCase;
    }

    public List<CustomerViewModel> handle() {
        return useCase.execute().stream()
                .map(customerResponse -> new CustomerViewModel(
                        customerResponse.getCustomerId().toString(),
                        customerResponse.getName(),
                        customerResponse.getGender(),
                        customerResponse.getAge(),
                        customerResponse.getState()))
                .collect(Collectors.toList());
    }

}
