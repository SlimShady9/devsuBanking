package com.devsu.account.usecase;

import com.devsu.account.domain.model.Account;
import com.devsu.account.domain.model.Movement;
import com.devsu.account.domain.repository.AccountRepository;
import com.devsu.account.domain.repository.MovementRepository;
import com.devsu.account.dto.MovementRequest;
import com.devsu.account.dto.MovementResponse;

public class CreateMovement {
    private final AccountRepository accountRepository;
    private final MovementRepository movementRepository;

    public CreateMovement(AccountRepository accountRepository, MovementRepository movementRepository) {
        this.accountRepository = accountRepository;
        this.movementRepository = movementRepository;
    }

    public MovementResponse execute(MovementRequest request) {
        // Find account by number
        Account account = accountRepository.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new RuntimeException("Account not found"));
        // Perform transaction (positive deposit or negative withdrawal)
        account.makeTransaction(request.getAmount());
        // Persist updated account (assuming repository.update is needed)
        accountRepository.update(account.getId(), account);
        // Create movement record
        Movement movement = Movement.createMovement(account.getId(), request.getAmount());
        Movement savedMovement = movementRepository.save(movement);
        return MovementResponse.fromDomain(savedMovement);
    }
}
