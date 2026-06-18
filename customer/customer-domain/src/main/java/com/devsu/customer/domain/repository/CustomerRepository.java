package com.devsu.customer.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.devsu.customer.domain.model.Customer;

public interface CustomerRepository {

    Customer save(Customer customer);

    void delete(UUID id);

    Optional<Customer> findById(UUID id);

    Optional<Customer> findByName(String name);

    List<Customer> findAll();

    Customer update(UUID id, Customer customer);

}
