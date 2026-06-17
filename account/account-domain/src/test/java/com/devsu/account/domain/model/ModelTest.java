package com.devsu.account.domain.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class ModelTest {

    @Test
    void should_add_instant_date_when_movement_is_created() {
        Movement movement = new Movement(UUID.randomUUID(), UUID.randomUUID(), 100.0);
        assertNotNull(movement.getCreationDate());
    }

    @Test
    void should_throw_error_when_not_valid_movement() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Movement(null, UUID.randomUUID(), 100.0);
        });
    }

    @Test
    void should_throw_error_when_not_valid_account_id() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Movement(UUID.randomUUID(), null, 100.0);
        });
    }

    @Test
    void should_throw_error_when_zero_amount() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Movement(UUID.randomUUID(), UUID.randomUUID(), 0.0);
        });
    }
}
