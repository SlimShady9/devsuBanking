package com.devsu.account.usecase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import com.devsu.account.domain.model.Account;
import com.devsu.account.domain.model.AccountType;
import com.devsu.account.domain.model.Movement;
import com.devsu.account.domain.repository.AccountNumberCreator;
import com.devsu.account.domain.repository.AccountRepository;
import com.devsu.account.domain.repository.MovementRepository;
import com.devsu.account.dto.AccountRequest;

public class FreeAccountDepositCreationTest {

    private FreeAccountDepositUseCase useCase;
    private AccountRepository accountRepository;
    private AccountNumberCreator accountNumberCreator;
    private MovementRepository movementRepository;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);
        accountNumberCreator = mock(AccountNumberCreator.class);
        movementRepository = mock(MovementRepository.class);
        when(accountNumberCreator.generateAccountNumber()).thenReturn("1234567890");
        useCase = new FreeAccountDepositUseCase(accountRepository, accountNumberCreator, movementRepository);
    }

    @Test
    void should_create_account_with_initial_balance_and_movement() {
        UUID clientId = UUID.randomUUID();

        // Execute use case
        useCase.execute(clientId);

        // Capture saved account
        ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
        verify(accountRepository).save(accountCaptor.capture());
        Account savedAccount = accountCaptor.getValue();
        assertEquals("1234567890", savedAccount.getAccountNumber());
        assertEquals(AccountType.SAVINGS, savedAccount.getAccountType());
        assertEquals(100.0, savedAccount.getBalance());
        assertEquals(true, savedAccount.getState());
        assertEquals(clientId, savedAccount.getCustomerId());

        // Capture saved movement
        ArgumentCaptor<Movement> movementCaptor = ArgumentCaptor.forClass(Movement.class);
        verify(movementRepository).save(movementCaptor.capture());
        Movement savedMovement = movementCaptor.getValue();
        assertEquals(savedAccount.getId(), savedMovement.getAccountId());
        assertEquals(100.0, savedMovement.getAmount());
    }
}