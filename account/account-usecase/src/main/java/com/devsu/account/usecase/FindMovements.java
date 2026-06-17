package com.devsu.account.usecase;

import com.devsu.account.domain.model.Account;
import com.devsu.account.domain.model.Movement;
import com.devsu.account.domain.repository.AccountRepository;
import com.devsu.account.domain.repository.MovementRepository;
import com.devsu.account.dto.MovementResponse;
import java.util.List;
import java.util.stream.Collectors;

public class FindMovements {

    private final AccountRepository accountRepository;
    private final MovementRepository movementRepository;

    public FindMovements(AccountRepository accountRepository, MovementRepository movementRepository) {
        this.accountRepository = accountRepository;
        this.movementRepository = movementRepository;
    }

    public List<MovementResponse> execute(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        List<Movement> movements = movementRepository.findByAccountId(account.getId());
        return movements.stream()
                .map(MovementResponse::fromDomain)
                .collect(Collectors.toList());
    }
}
