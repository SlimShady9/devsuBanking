package com.devsu.account.adapter.controller;

import java.util.UUID;

import com.devsu.account.adapter.viewmodel.AccountViewModel;
import com.devsu.account.dto.AccountResponse;
import com.devsu.account.usecase.FindAccount;

public class FindAccountController {

    private final FindAccount useCase;

    public FindAccountController(FindAccount useCase) {
        this.useCase = useCase;
    }

    public AccountViewModel handle(String id) {

        AccountResponse accountResponse = useCase.execute(UUID.fromString(id));
        AccountViewModel viewModel = new AccountViewModel();
        viewModel.setId(accountResponse.getId().toString());
        viewModel.setCustomerId(accountResponse.getCustomerId());
        viewModel.setAccountType(accountResponse.getAccountType().toString());
        viewModel.setBalance(accountResponse.getBalance());
        viewModel.setState(accountResponse.getState());
        return viewModel;
    }

}
