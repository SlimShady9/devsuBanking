package com.devsu.customer.usecase;

import java.util.UUID;

import com.devsu.customer.domain.repository.CustomerRepository;

public class DeleteCustomer {

    private final CustomerRepository customerRepository;

    public DeleteCustomer(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void execute(UUID id) {
        customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customerRepository.delete(id);
    }
}
