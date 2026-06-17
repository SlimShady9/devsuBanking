package com.devsu.account.usecase;

import java.util.UUID;

import com.devsu.account.domain.model.Account;
import com.devsu.account.domain.model.AccountType;
import com.devsu.account.domain.repository.AccountRepository;
import com.devsu.account.dto.AccountRequest;

public class CreateAccount {

    private final AccountRepository accountRepository;

    public CreateAccount(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void execute(AccountRequest accountRequest) {

        Account account = new Account(
                UUID.randomUUID(),
                accountRequest.getAccountNumber(),
                AccountType.valueOf(accountRequest.getAccountType()),
                accountRequest.getBalance(),
                accountRequest.getState(),
                accountRequest.getClientId());
        accountRepository.save(account);

    }
}
