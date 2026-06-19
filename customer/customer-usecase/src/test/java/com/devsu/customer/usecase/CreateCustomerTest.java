package com.devsu.customer.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import com.devsu.customer.domain.model.Customer;
import com.devsu.customer.domain.model.Gender;
import com.devsu.customer.domain.model.Person;
import com.devsu.customer.domain.repository.CustomerRepository;
import com.devsu.customer.domain.repository.PasswordHasher;
import com.devsu.customer.dto.CustomerRequest;
import com.devsu.customer.ports.EventPublisher;

public class CreateCustomerTest {

    private CreateCustomer useCase;
    private CustomerRepository customerRepository;
    private PasswordHasher passwordHasher;
    private EventPublisher eventPublisher;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        passwordHasher = mock(PasswordHasher.class);
        eventPublisher = mock(EventPublisher.class);
        when(passwordHasher.hashPassword(anyString())).thenReturn("hash_SecurePassword123.!");
        useCase = new CreateCustomer(customerRepository, passwordHasher, eventPublisher);
    }

    @Test
    void should_save_a_new_customer_when_provided_with_valid_data() {

        Person person = Person.createPerson("Juan", Gender.MALE, 30);
        Customer customer = new Customer();
        customer.setPerson(person);
        customer.setPassword("hash_SecurePassword123.!");
        customer.setState(true);

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

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
