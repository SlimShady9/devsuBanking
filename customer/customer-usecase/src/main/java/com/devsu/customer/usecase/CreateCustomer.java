package com.devsu.customer.usecase;

import java.util.UUID;

import com.devsu.customer.domain.model.Customer;
import com.devsu.customer.domain.model.Gender;
import com.devsu.customer.domain.model.Password;
import com.devsu.customer.domain.model.Person;
import com.devsu.customer.domain.repository.CustomerRepository;
import com.devsu.customer.domain.repository.PasswordHasher;
import com.devsu.customer.dto.CustomerRequest;
import com.devsu.customer.ports.EventPublisher;

public class CreateCustomer {

    private final CustomerRepository customerRepository;
    private final PasswordHasher passwordHasher;
    private final EventPublisher eventPublisher;

    public CreateCustomer(CustomerRepository customerRepository, PasswordHasher passwordHasher,
            EventPublisher eventPublisher) {
        this.customerRepository = customerRepository;
        this.passwordHasher = passwordHasher;
        this.eventPublisher = eventPublisher;
    }

    public void execute(CustomerRequest request) {

        Password password = new Password(request.getPassword(), passwordHasher);

        Person person = Person.createPerson(request.getName(), Gender.fromString(request.getGender()),
                request.getAge());

        Customer customer = Customer.createCustomer(password.getHashedPassword(), request.getState(), person);
        Customer savedCustomer = customerRepository.save(customer);

        savedCustomer.getEvents().forEach(eventPublisher::publishCustomerCreated);

    }

}
