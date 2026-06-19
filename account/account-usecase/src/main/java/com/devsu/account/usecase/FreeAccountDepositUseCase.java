package com.devsu.account.usecase;

import java.util.UUID;

import com.devsu.account.domain.model.Account;
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
        double giftBalance = 100.0;

        Account account = Account.createAccount(
                accountNumberCreator.generateAccountNumber(),
                clientId,
                giftBalance);

        Account newAccount = accountRepository.save(account);

        Movement movement = Movement.createMovement(newAccount.getId(), giftBalance);
        movementRepository.save(movement);
    }
}
