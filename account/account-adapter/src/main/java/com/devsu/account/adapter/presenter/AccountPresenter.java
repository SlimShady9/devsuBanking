package com.devsu.account.adapter.presenter;

import com.devsu.account.adapter.viewmodel.AccountViewModel;
import com.devsu.account.dto.AccountResponse;

public class AccountPresenter {

    private AccountViewModel viewModel;

    public void present(AccountResponse response) {
        this.viewModel = new AccountViewModel(
                response.getId().toString(),
                response.getAccountNumber(),
                response.getAccountType().toString(),
                response.getState(),
                response.getBalance(),
                response.getCustomerId());
    }

    public AccountViewModel getViewModel() {
        return viewModel;
    }

}
