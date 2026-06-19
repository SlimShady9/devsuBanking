package com.devsu.account.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.devsu.account.domain.model.Account;
import com.devsu.account.domain.model.AccountType;
import com.devsu.account.domain.repository.AccountRepository;
import com.devsu.account.dto.AccountResponse;

public class UpdateAccountTest {

    private UpdateAccount useCase;
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);
        useCase = new UpdateAccount(accountRepository);
    }

    @Test
    void should_update_account_fields_and_return_response() {
        UUID id = UUID.randomUUID();
        Account existing = new Account("1234567890", AccountType.SAVINGS, 1000.0, true, UUID.randomUUID());
        when(accountRepository.findById(id)).thenReturn(Optional.of(existing));
        when(accountRepository.update(eq(id), any(Account.class))).thenReturn(existing);

        AccountResponse response = useCase.execute(id, "SAVINGS", false);
        assertEquals(AccountType.SAVINGS, response.getAccountType());
        assertEquals(false, response.getState());
    }

    @Test
    void should_throw_when_account_not_found() {
        UUID id = UUID.randomUUID();
        when(accountRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> useCase.execute(id, "SAVINGS", true));
    }
}
