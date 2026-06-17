package com.devsu.account.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.devsu.account.domain.model.Account;
import com.devsu.account.domain.model.AccountType;
import com.devsu.account.domain.repository.AccountRepository;
import com.devsu.account.dto.AccountResponse;

public class ReadAccountTest {

    private ReadAccount useCase;
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);
        useCase = new ReadAccount(accountRepository);
    }

    @Test
    void should_return_account_when_found() {
        UUID id = UUID.randomUUID();
        Account account = new Account(id, "1234567890", AccountType.SAVINGS, 1000.0, true, UUID.randomUUID());
        when(accountRepository.findByAccountNumber("1234567890")).thenReturn(Optional.of(account));

        AccountResponse result = useCase.execute("1234567890");
        assertEquals(id, result.getId());
        assertEquals("1234567890", result.getAccountNumber());
        assertEquals(AccountType.SAVINGS, result.getAccountType());
        assertEquals(1000.0, result.getBalance());
        assertEquals(true, result.getState());
    }

    @Test
    void should_throw_exception_when_account_not_found() {
        when(accountRepository.findByAccountNumber("nonexistent")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> useCase.execute("nonexistent"));
    }
}
