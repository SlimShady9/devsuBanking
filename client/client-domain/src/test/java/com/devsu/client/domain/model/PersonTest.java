package com.devsu.client.domain.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    @Test
    void shouldCreatePersonWithValidData() {
        Person person = new Person(
                UUID.randomUUID(),
                "Juan Perez",
                Gender.MALE,
                20);

        assertAll("Person Properties",
                () -> assertNotNull(person.getId()),
                () -> assertEquals("Juan Perez", person.getName()),
                () -> assertEquals(Gender.MALE, person.getGender()),
                () -> assertEquals(20, person.getAge()));
    }
}
