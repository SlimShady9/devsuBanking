package com.devsu.customer.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;;

public class CustomerTest {

    @Test
    void should_CreateCustomer_WhenAllDataIsCorrect() {

        Person person = new Person(
                UUID.randomUUID(),
                "Juan",
                Gender.MALE,
                21);

        Customer customer = new Customer(UUID.randomUUID(), "1234", true, person);

        assertAll("Customer Properties",
                () -> assertNotNull(customer.getId()),
                () -> assertNotNull(customer.getPassword()),
                () -> assertNotNull(customer.getPerson()),
                () -> assertEquals("1234", customer.getPassword()),
                () -> assertEquals(person, customer.getPerson()),
                () -> assertEquals("Juan", customer.getPerson().getName()),
                () -> assertEquals(Gender.MALE, customer.getPerson().getGender()),
                () -> assertEquals(21, customer.getPerson().getAge()));
    }

    @Test
    void should_FailToCreateCustomer_WhenAllDataIsCorrect() {

        Person person = new Person(
                UUID.randomUUID(),
                "Juan",
                Gender.MALE,
                21);

        assertThrows(IllegalArgumentException.class, () -> {
            new Customer(UUID.randomUUID(), null, true, person);
        });
    }

    @Test
    void should_FailToCreateCustomer_WhenPasswordIsEmpty() {

        Person person = new Person(
                UUID.randomUUID(),
                "Juan",
                Gender.MALE,
                21);

        assertThrows(IllegalArgumentException.class, () -> {
            new Customer(UUID.randomUUID(), null, true, person);
        });
    }

    @Test
    void should_FailToCreateCustomer_WhenStateIsIncorrect() {

        Person person = new Person(
                UUID.randomUUID(),
                "Juan",
                Gender.MALE,
                21);

        assertThrows(IllegalArgumentException.class, () -> {
            new Customer(UUID.randomUUID(), "1234", null, person);
        });
    }

    @Test
    void should_FailToCreateCustomer_WhenPersonIsIncorrect() {

        assertThrows(IllegalArgumentException.class, () -> {
            new Customer(UUID.randomUUID(), "1234", true, null);
        });
    }

    @Test
    void should_Update_CustomerInformation_WhenDataIsCorrect() {

        Person person = new Person(
                UUID.randomUUID(),
                "Juan",
                Gender.MALE,
                21);

        Customer customer = new Customer(UUID.randomUUID(), "1234", true, person);

        customer.updateCustomerData("Juana", Gender.FEMALE, false, 25);

        assertAll("Customer Properties",
                () -> assertNotNull(customer.getId()),
                () -> assertNotNull(customer.getPassword()),
                () -> assertNotNull(customer.getPerson()),
                () -> assertEquals("Juana", customer.getPerson().getName()),
                () -> assertEquals(Gender.FEMALE, customer.getPerson().getGender()),
                () -> assertEquals(25, customer.getPerson().getAge()),
                () -> assertEquals(false, customer.getState()));

    }

    @Test
    void should_failToUpdateCustomer_WhenDataIsIncorrect() {

        Person person = new Person(
                UUID.randomUUID(),
                "Juan",
                Gender.MALE,
                21);

        Customer customer = new Customer(UUID.randomUUID(), "1234", true, person);

        assertThrows(IllegalArgumentException.class, () -> {
            customer.updateCustomerData(null, Gender.FEMALE, false, 25);
        });
    }
}
