package com.devsu.customer.usecase;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.devsu.customer.domain.model.Customer;
import com.devsu.customer.domain.model.Gender;
import com.devsu.customer.domain.model.Person;
import com.devsu.customer.domain.repository.CustomerRepository;

public class DeleteCustomerTest {

    private DeleteCustomer useCase;
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        useCase = new DeleteCustomer(customerRepository);
    }

    @Test
    void should_delete_customer_when_exists() {
        UUID id = UUID.randomUUID();
        Person person = new Person(id, "Juan Test", Gender.MALE, 30);
        Customer customer = new Customer(id, "securePass123!", true, person);

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

        useCase.execute(id);

        verify(customerRepository).delete(id);
    }

    @Test
    void should_throw_exception_when_deleting_non_existent_customer() {
        UUID id = UUID.randomUUID();
        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> useCase.execute(id));
    }
}
