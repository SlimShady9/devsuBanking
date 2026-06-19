package com.devsu.customer.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.devsu.customer.domain.event.CustomerCreated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Customer {
    private UUID id;
    private String password;
    private Boolean state;
    private Person person;

    private final List<CustomerCreated> events = new ArrayList<>();

    public Customer(UUID id, String hashedPassword, Boolean state, Person person) {
        if (hashedPassword == null) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (state == null) {
            throw new IllegalArgumentException("State cannot be null");
        }
        if (person == null) {
            throw new IllegalArgumentException("Person cannot be null");
        }
        this.id = id;
        this.password = hashedPassword;
        this.state = state;
        this.person = person;

        this.events.add(new CustomerCreated(id));
    }

    public static Customer createCustomer(String hashedPassword, Boolean state, Person person) {
        if (hashedPassword == null) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (state == null) {
            throw new IllegalArgumentException("State cannot be null");
        }
        if (person == null) {
            throw new IllegalArgumentException("Person cannot be null");
        }

        Customer customer = new Customer();
        customer.setPassword(hashedPassword);
        customer.setState(state);
        customer.setPerson(person);
        return customer;

    }

    public void updateCustomerData(String name, Gender gender, Boolean state, int age) {
        this.person.updatePersonData(name, gender, age);
        if (state == null) {
            throw new IllegalArgumentException("State cannot be null");
        }
        this.state = state;
    }

}
