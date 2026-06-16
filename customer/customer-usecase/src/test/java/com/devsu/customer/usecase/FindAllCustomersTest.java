package com.devsu.customer.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.devsu.customer.domain.model.Customer;
import com.devsu.customer.domain.model.Gender;
import com.devsu.customer.domain.model.Person;
import com.devsu.customer.domain.repository.CustomerRepository;
import com.devsu.customer.dto.CustomerResponse;

public class FindAllCustomersTest {

    private FindAllCustomers useCase;
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        useCase = new FindAllCustomers(customerRepository);
    }

    @Test
    void should_return_list_of_customers() {
        UUID id1 = UUID.randomUUID();
        Person person1 = new Person(id1, "Juan Test 1", Gender.MALE, 30);
        Customer customer1 = new Customer(id1, "securePass123!", true, person1);

        UUID id2 = UUID.randomUUID();
        Person person2 = new Person(id2, "Maria Test", Gender.FEMALE, 25);
        Customer customer2 = new Customer(id2, "securePass456!", true, person2);

        when(customerRepository.findAll()).thenReturn(List.of(customer1, customer2));

        List<CustomerResponse> result = useCase.execute();

        assertEquals(2, result.size());
        assertEquals("Juan Test 1", result.get(0).getName());
        assertEquals("Maria Test", result.get(1).getName());
    }

    @Test
    void should_return_empty_list_when_no_customers_exist() {
        when(customerRepository.findAll()).thenReturn(Collections.emptyList());

        List<CustomerResponse> result = useCase.execute();

        assertTrue(result.isEmpty());
    }
}
