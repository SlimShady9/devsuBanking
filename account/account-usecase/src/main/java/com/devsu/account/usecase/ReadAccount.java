package com.devsu.account.usecase;

import com.devsu.account.domain.model.Account;
import com.devsu.account.domain.repository.AccountRepository;
import com.devsu.account.dto.AccountResponse;

public class ReadAccount {

    private final AccountRepository accountRepository;

    public ReadAccount(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountResponse execute(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return AccountResponse.fromDomain(account);
    }
}
