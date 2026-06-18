package com.devsu.customer.usecase;

import java.util.MissingResourceException;
import java.util.UUID;

import com.devsu.customer.domain.model.Customer;
import com.devsu.customer.domain.model.Gender;
import com.devsu.customer.domain.repository.CustomerRepository;
import com.devsu.customer.dto.CustomerRequest;

public class UpdateCustomer {

    private final CustomerRepository customerRepository;

    public UpdateCustomer(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void execute(UUID id, CustomerRequest request) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new MissingResourceException("Customer not found", "Customer", id.toString()));

        customer.updateCustomerData(
                request.getName(),
                Gender.fromString(request.getGender()),
                request.getState(),
                request.getAge());

        customerRepository.update(id, customer);
    }

}
