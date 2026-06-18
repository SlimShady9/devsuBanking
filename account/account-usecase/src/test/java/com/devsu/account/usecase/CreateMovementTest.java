package com.devsu.account.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import com.devsu.account.domain.model.Account;
import com.devsu.account.domain.model.AccountType;
import com.devsu.account.domain.model.Movement;
import com.devsu.account.domain.repository.AccountRepository;
import com.devsu.account.domain.repository.MovementRepository;
import com.devsu.account.dto.MovementRequest;

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
        Account account = new Account(UUID.randomUUID(), "1234567890", AccountType.SAVINGS, 1000.0, true,
                UUID.randomUUID());
        when(accountRepository.findByAccountNumber("1234567890")).thenReturn(Optional.of(account));
        when(accountRepository.update(eq(account.getId()), any(Account.class))).thenReturn(account);
        when(movementRepository.save(any(Movement.class)))
                .thenReturn(new Movement(UUID.randomUUID(), account.getId(), 200.0));

        MovementRequest request = new MovementRequest();
        request.setAccountNumber("1234567890");
        request.setAmount(200.0);

        useCase.execute(request);

        // Simulate movement saved
        ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
        verify(accountRepository).update(eq(account.getId()), accountCaptor.capture());
        Account updatedAccount = accountCaptor.getValue();
        assertEquals(1200.0, updatedAccount.getBalance());

        ArgumentCaptor<Movement> movementCaptor = ArgumentCaptor.forClass(Movement.class);
        verify(movementRepository).save(movementCaptor.capture());
        Movement savedMovement = movementCaptor.getValue();
        assertEquals(account.getId(), savedMovement.getAccountId());
        assertEquals(200.0, savedMovement.getAmount());
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
