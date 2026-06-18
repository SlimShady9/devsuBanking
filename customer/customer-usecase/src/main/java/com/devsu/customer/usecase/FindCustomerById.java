package com.devsu.customer.usecase;

import java.util.MissingResourceException;
import java.util.UUID;

import com.devsu.customer.domain.model.Customer;
import com.devsu.customer.domain.repository.CustomerRepository;
import com.devsu.customer.dto.CustomerResponse;

public class FindCustomerById {

    private final CustomerRepository customerRepository;

    public FindCustomerById(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponse execute(String id) {
        Customer customer = customerRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new MissingResourceException("Customer not found", "Customer", id));
        return CustomerResponse.fromDomain(customer);
    }
}
