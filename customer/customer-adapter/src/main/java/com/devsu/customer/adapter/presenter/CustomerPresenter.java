package com.devsu.customer.adapter.presenter;

import com.devsu.customer.adapter.viewmodel.CustomerViewModel;
import com.devsu.customer.dto.CustomerResponse;

public class CustomerPresenter {

    private CustomerViewModel viewModel;

    public void present(CustomerResponse response) {
        this.viewModel = new CustomerViewModel(
                response.getCustomerId().toString(),
                response.getName(),
                response.getGender(),
                response.getAge(),
                response.getState(),
                response.getPassword());
    }

    public CustomerViewModel getViewModel() {
        return viewModel;
    }

}
