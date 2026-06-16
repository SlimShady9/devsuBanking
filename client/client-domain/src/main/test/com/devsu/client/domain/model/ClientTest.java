package com.devsu.client.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ClientTest {

    @Test
    void givenValidData_whenCreatingClient_thenShouldSucceed() {
        Client client = new Client(
            1, 
            "123456789", 
            "Juan", 
            "123456789", 
            "juan@example.com", 
            "1990-01-01", 
            "MALE", 
            "PASS123"
        );

        assertAll("Client Properties",
            () -> assertEquals(1, client.getId()),
            () -> assertEquals("123456789", client.getPerson().getIdentification()),
            () -> assertEquals("Juan", client.getPerson().getName()),
            () -> assertEquals("123456789", client.getPassword()),
            () -> assertEquals("juan@example.com", client.getPerson().getEmail()),
            () -> assertEquals("1990-01-01", client.getPerson().getDateOfBirth()),
            () -> assertEquals("MALE", client.getPerson().getGender()),
            () -> assertEquals("PASS123", client.getClientId())
        );
    }
}
