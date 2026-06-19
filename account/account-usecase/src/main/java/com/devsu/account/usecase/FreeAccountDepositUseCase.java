package com.devsu.account.usecase;

import java.util.UUID;

import com.devsu.account.domain.model.Account;
import com.devsu.account.domain.model.AccountType;
import com.devsu.account.domain.model.Movement;
import com.devsu.account.domain.repository.AccountNumberCreator;
import com.devsu.account.domain.repository.AccountRepository;
import com.devsu.account.domain.repository.MovementRepository;
import com.devsu.account.dto.AccountRequest;

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

    public void execute(AccountRequest accountRequest) {
        Account account = new Account(
                UUID.randomUUID(),
                accountNumberCreator.generateAccountNumber(),
                AccountType.valueOf(accountRequest.getAccountType()),
                accountRequest.getBalance(),
                true,
                accountRequest.getClientId());

        accountRepository.save(account);

        Movement movement = new Movement(UUID.randomUUID(), account.getId(), accountRequest.getBalance());
        movementRepository.save(movement);
    }
}
