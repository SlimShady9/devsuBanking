package com.devsu.customer.usecase;

import java.util.MissingResourceException;
import java.util.UUID;

import com.devsu.customer.domain.repository.CustomerRepository;

public class DeleteCustomer {

    private final CustomerRepository customerRepository;

    public DeleteCustomer(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void execute(UUID id) {
        customerRepository.findById(id)
                .orElseThrow(() -> new MissingResourceException("Customer not found", "Customer", id.toString()));

        customerRepository.delete(id);
    }
}
