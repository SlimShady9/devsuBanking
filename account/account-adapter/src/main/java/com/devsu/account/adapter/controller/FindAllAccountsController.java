package com.devsu.account.adapter.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.devsu.account.adapter.viewmodel.AccountViewModel;
import com.devsu.account.usecase.FindAccounts;

public class FindAllAccountsController {

    private final FindAccounts useCase;

    public FindAllAccountsController(FindAccounts useCase) {
        this.useCase = useCase;
    }

    public List<AccountViewModel> handle() {
        return useCase.execute().stream()
                .map(accountResponse -> new AccountViewModel(
                        accountResponse.getId().toString(),
                        accountResponse.getAccountNumber(),
                        accountResponse.getAccountType().toString(),
                        accountResponse.getState(),
                        accountResponse.getBalance(),
                        accountResponse.getCustomerId()))
                .collect(Collectors.toList());
    }

}
