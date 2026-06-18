package com.devsu.account.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Optional;
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

public class DeletionOfCustomerUseCaseTest {

    private DeletionOfCustomerUseCase useCase;
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);
        useCase = new DeletionOfCustomerUseCase(accountRepository);
    }

    @Test
    void should_inactivate_account_when_request_delete_customer() {
        // 1. Arrange data
        UUID customerId = UUID.randomUUID();
        List<Account> accounts = List.of(
                new Account(UUID.randomUUID(), customerId.toString(), AccountType.SAVINGS, 1000.0, true, customerId),
                new Account(UUID.randomUUID(), customerId.toString(), AccountType.CURRENT, 1000.0, true, customerId));

        ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
        when(accountRepository.findByClientId(customerId)).thenReturn(accounts);

        // 2. Act
        useCase.execute(customerId);

        // 3. Assert and Verify (The missing fix)
        // We expect 'update' to be called 2 times (once per account in the list)
        verify(accountRepository, times(2)).update(any(UUID.class), accountCaptor.capture());

        // Retrieve all captured accounts from the execution loop
        List<Account> updatedAccounts = accountCaptor.getAllValues();
        assertEquals(2, updatedAccounts.size());

        // Assert that both accounts were deactivated
        assertFalse(updatedAccounts.get(0).getState());
        assertFalse(updatedAccounts.get(1).getState());
    }

}