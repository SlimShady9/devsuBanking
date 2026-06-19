package com.devsu.customer.adapter.presenter;

import java.util.List;
import java.util.stream.Collectors;

import com.devsu.customer.adapter.viewmodel.CustomerViewModel;
import com.devsu.customer.dto.CustomerResponse;

public class CustomersPresenter {
    private List<CustomerViewModel> viewModel;

    public void present(List<CustomerResponse> responses) {
        this.viewModel = responses.stream()
                .map(res -> new CustomerViewModel(
                        res.getCustomerId().toString(),
                        res.getName(),
                        res.getGender(),
                        res.getAge(),
                        res.getState(),
                        res.getPassword()))
                .collect(Collectors.toList());
    }

    public List<CustomerViewModel> getViewModel() {
        return viewModel;
    }

}
