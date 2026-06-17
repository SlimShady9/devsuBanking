package com.devsu.account.usecase;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.devsu.account.domain.model.Account;
import com.devsu.account.domain.model.AccountType;
import com.devsu.account.domain.repository.AccountRepository;
import com.devsu.account.dto.AccountRequest;

public class CreateAccountTest {

    private CreateAccount useCase;
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);
        useCase = new CreateAccount(accountRepository);
    }

    @Test
    void should_create_a_new_account_when_provided_with_valid_data() {

        AccountRequest dto = new AccountRequest();
        dto.setAccountNumber("1234567890");
        dto.setAccountType("SAVINGS");
        dto.setBalance(1000.0);
        dto.setState(Boolean.TRUE);
        dto.setClientId(UUID.randomUUID());

        useCase.execute(dto);

        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);

        verify(accountRepository).save(captor.capture());

        assertEquals("1234567890", captor.getValue().getAccountNumber());
        assertEquals(AccountType.SAVINGS, captor.getValue().getAccountType());
        assertEquals(1000.0, captor.getValue().getBalance());
        assertEquals(true, captor.getValue().getState());
        assertEquals(dto.getClientId(), captor.getValue().getCustomerId());

    }

    @Test
    void should_throw_error_when_not_valid_account_number() {

        AccountRequest dto = new AccountRequest();
        dto.setAccountNumber("");
        dto.setAccountType("SAVINGS");
        dto.setBalance(1000.0);
        dto.setState(true);
        dto.setClientId(UUID.randomUUID());

        assertThrows(IllegalArgumentException.class, () -> {
            useCase.execute(dto);
        });
    }

}
