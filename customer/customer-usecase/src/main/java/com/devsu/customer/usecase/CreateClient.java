package com.devsu.customer.usecase;

import java.util.UUID;

import com.devsu.customer.domain.model.Customer;
import com.devsu.customer.domain.model.Gender;
import com.devsu.customer.domain.model.Password;
import com.devsu.customer.domain.model.Person;
import com.devsu.customer.domain.repository.CustomerRepository;
import com.devsu.customer.domain.repository.PasswordHasher;
import com.devsu.customer.dto.CreateClientRequest;

public class CreateClient {

    private final CustomerRepository customerRepository;
    private final PasswordHasher passwordHasher;

    public CreateClient(CustomerRepository customerRepository, PasswordHasher passwordHasher) {
        this.customerRepository = customerRepository;
        this.passwordHasher = passwordHasher;
    }

    public void execute(CreateClientRequest request) {

        Password password = new Password(request.getPassword(), passwordHasher);

        Person person = new Person(UUID.randomUUID(), request.getName(), Gender.fromString(request.getGender()),
                request.getAge());

        Customer customer = new Customer(UUID.randomUUID(), password.getHashedPassword(), request.getState(), person);

        customerRepository.save(customer);
    }

}
