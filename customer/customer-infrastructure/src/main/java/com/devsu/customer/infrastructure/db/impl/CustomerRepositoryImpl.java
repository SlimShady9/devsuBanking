package com.devsu.customer.infrastructure.db.impl;

import java.util.List;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.devsu.customer.domain.model.Customer;
import com.devsu.customer.domain.repository.CustomerRepository;
import com.devsu.customer.infrastructure.db.jpa.entities.CustomerEntity;
import com.devsu.customer.infrastructure.db.jpa.repository.JpaCustomerRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final JpaCustomerRepository jpaCustomerRepository;

    @Override
    public Customer save(Customer customer) {

        CustomerEntity customerEntity = CustomerEntity.fromDomain(customer);

        CustomerEntity newCustomerEntity = jpaCustomerRepository.save(customerEntity);

        return newCustomerEntity.toDomain();
    }

    @Override
    public void delete(UUID id) {

        jpaCustomerRepository.deleteById(id);
    }

    @Override
    public Optional<Customer> findById(UUID id) {

        return jpaCustomerRepository.findById(id).map(CustomerEntity::toDomain);

    }

    @Override
    public List<Customer> findAll() {

        return jpaCustomerRepository.findAll().stream().map(CustomerEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public Customer update(UUID id, Customer customer) {

        Optional<CustomerEntity> customerEntityOptional = jpaCustomerRepository.findById(id);

        if (!customerEntityOptional.isPresent()) {
            throw new MissingResourceException("Customer not found", "Customer", id.toString());
        }

        CustomerEntity customerEntity = customerEntityOptional.get();
        customerEntity.setName(customer.getPerson().getName());
        customerEntity.setGender(customer.getPerson().getGender().name());
        customerEntity.setAge(customer.getPerson().getAge());
        customerEntity.setState(customer.getState());
        return jpaCustomerRepository.save(customerEntity).toDomain();

    }

    @Override
    public Optional<Customer> findByName(String name) {
        return jpaCustomerRepository.findByName(name).map(CustomerEntity::toDomain);
    }

}
