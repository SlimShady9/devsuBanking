package com.devsu.account.usecase;

import java.util.MissingResourceException;
import java.util.UUID;

import com.devsu.account.domain.model.Account;
import com.devsu.account.domain.model.AccountType;
import com.devsu.account.domain.repository.AccountRepository;
import com.devsu.account.dto.AccountResponse;

public class UpdateAccount {
    private final AccountRepository accountRepository;

    public UpdateAccount(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountResponse execute(UUID accountId, String accountType, Boolean state) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new MissingResourceException("Account not found", Account.class.getName(),
                        accountId.toString()));
        account.updateAccountData(AccountType.valueOf(accountType), state);
        Account updated = accountRepository.update(accountId, account);
        return AccountResponse.fromDomain(updated);
    }
}
