package com.devsu.customer.usecase;

import java.util.UUID;

import com.devsu.customer.domain.model.Customer;
import com.devsu.customer.domain.repository.CustomerRepository;
import com.devsu.customer.dto.CustomerResponse;

public class FindCustomerById {

    private final CustomerRepository customerRepository;

    public FindCustomerById(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponse execute(UUID id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return CustomerResponse.fromDomain(customer);
    }
}
