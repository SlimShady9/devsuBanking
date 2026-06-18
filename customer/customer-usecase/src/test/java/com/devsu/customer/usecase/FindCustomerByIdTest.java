package com.devsu.customer.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.MissingResourceException;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.devsu.customer.domain.model.Customer;
import com.devsu.customer.domain.model.Gender;
import com.devsu.customer.domain.model.Person;
import com.devsu.customer.domain.repository.CustomerRepository;
import com.devsu.customer.dto.CustomerResponse;

public class FindCustomerByIdTest {

    private FindCustomerById useCase;
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        useCase = new FindCustomerById(customerRepository);
    }

    @Test
    void should_return_customer_when_found() {
        UUID id = UUID.randomUUID();
        Person person = new Person(id, "Juan Test", Gender.MALE, 30);
        Customer customer = new Customer(id, "securePass123!", true, person);

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

        CustomerResponse result = useCase.execute(id.toString());

        assertEquals(id, result.getCustomerId());
        assertEquals("Juan Test", result.getName());
        assertEquals("MALE", result.getGender());
        assertEquals(30, result.getAge());
        assertEquals(true, result.getState());
    }

    @Test
    void should_throw_exception_when_customer_not_found() {
        when(customerRepository.findById(UUID.randomUUID())).thenReturn(Optional.empty());

        assertThrows(MissingResourceException.class, () -> useCase.execute(UUID.randomUUID().toString()));
    }
}
