package com.devsu.account.usecase;

import java.util.UUID;

import com.devsu.account.domain.model.Account;
import com.devsu.account.domain.model.AccountType;
import com.devsu.account.domain.repository.AccountNumberCreator;
import com.devsu.account.domain.repository.AccountRepository;
import com.devsu.account.dto.AccountRequest;

public class CreateAccount {

    private final AccountRepository accountRepository;
    private final AccountNumberCreator accountNumberCreator;

    public CreateAccount(AccountRepository accountRepository, AccountNumberCreator accountNumberCreator) {
        this.accountRepository = accountRepository;
        this.accountNumberCreator = accountNumberCreator;
    }

    public void execute(AccountRequest accountRequest) {

        Account account = new Account(
                UUID.randomUUID(),
                accountNumberCreator.generateAccountNumber(),
                AccountType.valueOf(accountRequest.getAccountType()),
                accountRequest.getBalance(),
                accountRequest.getState(),
                accountRequest.getClientId());
        accountRepository.save(account);

    }
}
