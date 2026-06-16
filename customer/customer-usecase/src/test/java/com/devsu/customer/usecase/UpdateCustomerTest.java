package com.devsu.customer.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import com.devsu.customer.domain.model.Customer;
import com.devsu.customer.domain.model.Gender;
import com.devsu.customer.domain.model.Person;
import com.devsu.customer.domain.repository.CustomerRepository;
import com.devsu.customer.dto.CustomerRequest;

public class UpdateCustomerTest {

    private UpdateCustomer useCase;
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        useCase = new UpdateCustomer(customerRepository);
    }

    @Test
    void should_update_a_customer_when_provided_with_valid_data() {

        UUID id = UUID.randomUUID();
        Person person = new Person(
                id,
                "Juan Old",
                Gender.MALE,
                21);
        Customer customer = new Customer(id, "securePass123!", true, person);
        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

        CustomerRequest dto = new CustomerRequest();
        dto.setName("Juan Updated");
        dto.setGender("FEMALE");
        dto.setAge(25);
        dto.setState(false);

        useCase.execute(id, dto);

        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).update(eq(id), captor.capture());
        assertEquals("Juan Updated", captor.getValue().getPerson().getName());
        assertEquals("FEMALE", captor.getValue().getPerson().getGender().name());
        assertEquals(25, captor.getValue().getPerson().getAge());
        assertEquals(false, captor.getValue().getState());
    }

    @Test
    void should_throw_customer_not_found_exception_when_customer_does_not_exist() {

        UUID id = UUID.randomUUID();
        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        CustomerRequest dto = new CustomerRequest();
        dto.setName("Juan Updated");
        dto.setGender("FEMALE");
        dto.setAge(25);
        dto.setState(false);

        assertThrows(RuntimeException.class, () -> useCase.execute(id, dto));
    }

}
