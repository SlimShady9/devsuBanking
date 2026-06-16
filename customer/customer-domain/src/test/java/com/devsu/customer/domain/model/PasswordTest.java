package com.devsu.customer.domain.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.devsu.customer.domain.repository.PasswordHasher;
import static org.mockito.Mockito.*;

public class PasswordTest {

    private PasswordHasher passwordHasher;

    @BeforeEach
    void setUp() {
        this.passwordHasher = mock(PasswordHasher.class);

        when(this.passwordHasher.hashPassword(anyString())).thenReturn("hashedToSecurePassword123.");

    }

    @Test
    void should_failToCreatePassword_whenPasswordIsTooShort() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Password("123", this.passwordHasher);
        });
    }

    @Test
    void should_failToCreatePassword_whenPasswordIsTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Password("12345678901231234321343214321", this.passwordHasher);
        });
    }

    @Test
    void should_failToCreatePassword_whenPasswordDoesNotContainNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Password("password", this.passwordHasher);
        });
    }

    @Test
    void should_failToCreatePassword_whenPasswordDoesNotContainSpecialCharacter() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Password("password123", this.passwordHasher);
        });
    }

    @Test
    void should_createPassword_whenPasswordIsCorrect() {
        Password password = new Password("password123!", this.passwordHasher);
        assertNotNull(password.getHashedPassword());
    }
}
