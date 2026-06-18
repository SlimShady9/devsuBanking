package com.devsu.account.usecase;

import java.util.List;
import java.util.UUID;

import com.devsu.account.domain.model.Account;
import com.devsu.account.domain.repository.AccountRepository;

public class DeletionOfCustomerUseCase {

    private final AccountRepository accountRepository;

    public DeletionOfCustomerUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void execute(UUID clientId) {
        List<Account> accountsAssociated = accountRepository.findByClientId(clientId);

        // call deactiate and remove customer methods
        accountsAssociated.stream()
                .forEach(account -> {
                    account.deactiveAccount();
                    account.removeCustomer();
                    accountRepository.update(account.getId(), account);
                });
    }
}
