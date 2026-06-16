package com.devsu.customer.usecase;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.devsu.customer.domain.repository.CustomerRepository;
import com.devsu.customer.domain.repository.PasswordHasher;
import com.devsu.customer.dto.CreateClientRequest;

public class CreateClientTest {

    private CreateClient useCase;
    private CustomerRepository customerRepository;
    private PasswordHasher passwordHasher;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        passwordHasher = mock(PasswordHasher.class);
        when(passwordHasher.hashPassword(anyString())).thenReturn("hash_SecurePassword123.!");
        useCase = new CreateClient(customerRepository, passwordHasher);
    }

    @Test
    void should_save_a_new_customer_when_provided_with_valid_data() {

        CreateClientRequest dto = new CreateClientRequest();
        dto.setName("Juan");
        dto.setGender("MALE");
        dto.setAge(30);
        dto.setPassword("SecurePassword123!");
        dto.setState(true);

        useCase.execute(dto);

    }

    @Test
    void should_fail_to_save_a_new_customer_when_provided_with_invalid_password() {

        CreateClientRequest dto = new CreateClientRequest();
        dto.setName("Juan");
        dto.setGender("MALE");
        dto.setAge(30);
        dto.setPassword("SecurePassword");
        dto.setState(true);

        assertThrows(IllegalArgumentException.class, () -> {
            useCase.execute(dto);
        });
    }
}
