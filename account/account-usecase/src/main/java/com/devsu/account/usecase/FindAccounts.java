package com.devsu.account.usecase;

import com.devsu.account.domain.model.Account;
import com.devsu.account.domain.repository.AccountRepository;
import com.devsu.account.dto.AccountResponse;

import java.util.List;
import java.util.stream.Collectors;

public class FindAccounts {

    private final AccountRepository accountRepository;

    public FindAccounts(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<AccountResponse> execute() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(AccountResponse::fromDomain)
                .collect(Collectors.toList());
    }
}
