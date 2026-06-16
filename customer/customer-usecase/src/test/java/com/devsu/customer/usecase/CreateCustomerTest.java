package com.devsu.customer.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import com.devsu.customer.domain.model.Customer;
import com.devsu.customer.domain.repository.CustomerRepository;
import com.devsu.customer.domain.repository.PasswordHasher;
import com.devsu.customer.dto.CustomerRequest;

public class CreateCustomerTest {

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

        CustomerRequest dto = new CustomerRequest();
        dto.setName("Juan");
        dto.setGender("MALE");
        dto.setAge(30);
        dto.setPassword("SecurePassword123!");
        dto.setState(true);

        useCase.execute(dto);

        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).save(captor.capture());
        assertEquals("Juan", captor.getValue().getPerson().getName());
        assertEquals("hash_SecurePassword123.!", captor.getValue().getPassword());

    }

    @Test
    void should_fail_to_save_a_new_customer_when_provided_with_invalid_password() {

        CustomerRequest dto = new CustomerRequest();
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
