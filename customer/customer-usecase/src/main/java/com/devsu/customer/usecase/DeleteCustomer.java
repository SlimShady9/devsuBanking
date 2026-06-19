package com.devsu.customer.usecase;

import java.util.MissingResourceException;
import java.util.UUID;

import com.devsu.customer.domain.event.CustomerDeleted;
import com.devsu.customer.domain.repository.CustomerRepository;
import com.devsu.customer.ports.EventPublisher;

public class DeleteCustomer {

    private final CustomerRepository customerRepository;
    private final EventPublisher eventPublisher;

    public DeleteCustomer(CustomerRepository customerRepository, EventPublisher eventPublisher) {
        this.customerRepository = customerRepository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(UUID id) {
        customerRepository.findById(id)
                .orElseThrow(() -> new MissingResourceException("Customer not found", "Customer", id.toString()));

        customerRepository.delete(id);
        eventPublisher.notifyCustomerDeleted(new CustomerDeleted(id));
    }
}
