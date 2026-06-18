package com.devsu.customer.adapter.controller;

import com.devsu.customer.adapter.viewmodel.CustomerViewModel;
import com.devsu.customer.dto.CustomerResponse;
import com.devsu.customer.usecase.FindCustomerById;

public class FindCustomerContoller {

    private final FindCustomerById useCase;

    public FindCustomerContoller(FindCustomerById useCase) {
        this.useCase = useCase;
    }

    public CustomerViewModel handle(String id) {
        try {
            CustomerResponse customerResponse = useCase.execute(id);
            return new CustomerViewModel(customerResponse.getCustomerId().toString(), customerResponse.getName(),
                    customerResponse.getGender(), customerResponse.getAge(), customerResponse.getState());
        } catch (RuntimeException e) {
            return null;
        }
    }

}
