package com.devsu.account.usecase;

import java.util.UUID;

import com.devsu.account.domain.model.Account;
import com.devsu.account.domain.model.AccountType;
import com.devsu.account.domain.model.Movement;
import com.devsu.account.domain.repository.AccountNumberCreator;
import com.devsu.account.domain.repository.AccountRepository;
import com.devsu.account.domain.repository.MovementRepository;

public class FreeAccountDepositUseCase {

    private final AccountRepository accountRepository;
    private final AccountNumberCreator accountNumberCreator;
    private final MovementRepository movementRepository;

    public FreeAccountDepositUseCase(AccountRepository accountRepository, AccountNumberCreator accountNumberCreator,
            MovementRepository movementRepository) {
        this.accountRepository = accountRepository;
        this.accountNumberCreator = accountNumberCreator;
        this.movementRepository = movementRepository;
    }

    public void execute(UUID clientId) {
        Account account = new Account(
                UUID.randomUUID(),
                accountNumberCreator.generateAccountNumber(),
                AccountType.SAVINGS,
                100.0,
                true,
                clientId);

        accountRepository.save(account);

        Movement movement = new Movement(UUID.randomUUID(), account.getId(), 100.0);
        movementRepository.save(movement);
    }
}
