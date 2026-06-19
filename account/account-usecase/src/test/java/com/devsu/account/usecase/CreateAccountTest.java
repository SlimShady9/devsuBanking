package com.devsu.account.usecase;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.devsu.account.domain.model.Account;
import com.devsu.account.domain.model.AccountType;
import com.devsu.account.domain.repository.AccountNumberCreator;
import com.devsu.account.domain.repository.AccountRepository;
import com.devsu.account.dto.AccountRequest;

public class CreateAccountTest {

    private CreateAccount useCase;
    private AccountRepository accountRepository;
    private AccountNumberCreator accountNumberCreator;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);
        accountNumberCreator = mock(AccountNumberCreator.class);
        when(accountNumberCreator.generateAccountNumber()).thenReturn("1234567890");
        useCase = new CreateAccount(accountRepository, accountNumberCreator);
    }

    @Test
    void should_create_a_new_account_when_provided_with_valid_data() {

        AccountRequest dto = new AccountRequest();
        dto.setAccountType("SAVINGS");
        dto.setBalance(1000.0);
        dto.setClientId(UUID.randomUUID());

        useCase.execute(dto);

        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);

        verify(accountRepository).save(captor.capture());

        assertEquals("1234567890", captor.getValue().getAccountNumber());
        assertEquals(AccountType.SAVINGS, captor.getValue().getAccountType());
        assertEquals(0.0, captor.getValue().getBalance()); // Inicial balance is 0 for created accounts
        assertEquals(true, captor.getValue().getState());
        assertEquals(dto.getClientId(), captor.getValue().getCustomerId());

    }

    @Test
    void should_throw_error_when_not_valid_account_number() {
        when(accountNumberCreator.generateAccountNumber()).thenReturn("");
        AccountRequest dto = new AccountRequest();
        dto.setAccountType("SAVINGS");
        dto.setBalance(1000.0);
        dto.setClientId(UUID.randomUUID());

        assertThrows(IllegalArgumentException.class, () -> {
            useCase.execute(dto);
        });
    }

}
