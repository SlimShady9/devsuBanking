package com.devsu.account.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.devsu.account.domain.model.Account;
import com.devsu.account.domain.model.AccountType;
import com.devsu.account.domain.model.Movement;
import com.devsu.account.domain.repository.AccountRepository;
import com.devsu.account.domain.repository.MovementRepository;
import com.devsu.account.dto.MovementRequest;
import com.devsu.account.dto.MovementResponse;

public class CreateMovementTest {

    private CreateMovement useCase;
    private AccountRepository accountRepository;
    private MovementRepository movementRepository;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);
        movementRepository = mock(MovementRepository.class);
        useCase = new CreateMovement(accountRepository, movementRepository);
    }

    @Test
    void should_create_movement_and_return_response() {
        Account account = new Account(UUID.randomUUID(), "1234567890", AccountType.SAVINGS, 1000.0, true, UUID.randomUUID());
        when(accountRepository.findByAccountNumber("1234567890")).thenReturn(Optional.of(account));
        when(accountRepository.update(eq(account.getId()), any(Account.class))).thenReturn(account);

        MovementRequest request = new MovementRequest();
        request.setAccountNumber("1234567890");
        request.setAmount(200.0);

        // Simulate movement saved
        Movement savedMovement = new Movement(UUID.randomUUID(), account.getId(), 200.0);
        when(movementRepository.save(any(Movement.class))).thenReturn(savedMovement);

        MovementResponse response = useCase.execute(request);
        assertEquals(savedMovement.getId(), response.getId());
        assertEquals(account.getId(), response.getAccountId());
        assertEquals(200.0, response.getAmount());
    }

    @Test
    void should_throw_exception_when_account_not_found() {
        when(accountRepository.findByAccountNumber("nonexistent")).thenReturn(Optional.empty());
        MovementRequest request = new MovementRequest();
        request.setAccountNumber("nonexistent");
        request.setAmount(100.0);
        assertThrows(RuntimeException.class, () -> useCase.execute(request));
    }
}
