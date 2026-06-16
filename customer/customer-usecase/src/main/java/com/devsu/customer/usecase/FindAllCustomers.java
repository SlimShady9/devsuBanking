package com.devsu.customer.usecase;

import java.util.List;
import java.util.stream.Collectors;

import com.devsu.customer.domain.repository.CustomerRepository;
import com.devsu.customer.dto.CustomerResponse;

public class FindAllCustomers {

    private final CustomerRepository customerRepository;

    public FindAllCustomers(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerResponse> execute() {
        return customerRepository.findAll().stream()
                .map(CustomerResponse::fromDomain)
                .collect(Collectors.toList());
    }
}
