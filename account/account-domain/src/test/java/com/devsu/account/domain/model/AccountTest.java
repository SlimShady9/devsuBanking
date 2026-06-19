package com.devsu.account.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class AccountTest {

    @Test
    void should_create_a_new_account_when_provided_with_valid_data() {

        UUID customerId = UUID.randomUUID();
        Account account = new Account(
                "1234567890",
                AccountType.SAVINGS,
                1000.0,
                true,
                customerId);
        assertEquals("1234567890", account.getAccountNumber());
        assertEquals(AccountType.SAVINGS, account.getAccountType());
        assertEquals(1000.0, account.getBalance());
        assertEquals(true, account.getState());
        assertEquals(customerId, account.getCustomerId());
    }

    @Test
    void should_throw_error_when_not_valid_account_number() {

        assertThrows(IllegalArgumentException.class, () -> {
            new Account(
                    "",
                    AccountType.SAVINGS,
                    1000.0,
                    true,
                    UUID.randomUUID());
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Account(
                    null,
                    AccountType.SAVINGS,
                    1000.0,
                    true,
                    UUID.randomUUID());
        });
    }

    @Test
    void should_throw_error_when_not_valid_client_id() {

        assertThrows(IllegalArgumentException.class, () -> {
            new Account(
                    "1234567890",
                    AccountType.SAVINGS,
                    1000.0,
                    true,
                    null);
        });
    }

    @Test
    void should_throw_error_when_not_valid_balance() {

        assertThrows(IllegalArgumentException.class, () -> {
            new Account(
                    "1234567890",
                    AccountType.SAVINGS,
                    -1000.0,
                    true,
                    UUID.randomUUID());
        });
    }

    @Test
    void should_thow_error_when_add_movement_in_inactive_account() {
        Account account = new Account(
                "1234567890",
                AccountType.SAVINGS,
                1000.0,
                false,
                UUID.randomUUID());
        assertThrows(IllegalStateException.class, () -> {
            account.makeTransaction(100.0);
        });
    }

    @Test
    void should_throw_error_when_not_enough_money_in_withdrawal() {
        Account account = new Account(
                "1234567890",
                AccountType.SAVINGS,
                1000.0,
                true,
                UUID.randomUUID());
        assertThrows(IllegalArgumentException.class, () -> {
            account.makeTransaction(-2000.0);
        });
    }

}
