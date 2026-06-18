package com.devsu.customer.infrastructure.db.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import com.devsu.customer.domain.model.Customer;
import com.devsu.customer.domain.model.Gender;
import com.devsu.customer.domain.model.Person;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class CustomerEntity extends PersonEntity {

    @Column(name = "password")
    private String password;

    @Column(name = "state")
    private Boolean state;

    public static CustomerEntity fromDomain(Customer customer) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customer.getPerson().getName());
        customerEntity.setGender(customer.getPerson().getGender().name());
        customerEntity.setAge(customer.getPerson().getAge());
        customerEntity.setPassword(customer.getPassword());
        customerEntity.setState(customer.getState());
        return customerEntity;
    }

    public Customer toDomain() {
        Person person = new Person(this.getId(), this.getName(), Gender.fromString(this.getGender()),
                this.getAge());

        Customer customer = new Customer(this.getId(), password, state, person);

        return customer;

    }

}
