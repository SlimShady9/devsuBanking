package com.devsu.account.instrastructure.db.jpa;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.devsu.account.infrastructure.db.jpa.entities.MovementEntity;
import com.devsu.account.infrastructure.db.jpa.repository.JpaMovementRepository;
import com.devsu.account.instrastructure.config.ConfigurationTest;

import jakarta.persistence.EntityManager;

@Transactional
public class JpaMovementRepositoryTest extends ConfigurationTest {

    @Autowired
    private JpaMovementRepository movementRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void testFindByAccountNumberWhenExists() {
        MovementEntity movement = new MovementEntity();
        movement.setAccountId(UUID.randomUUID());
        movement.setAmount(100.0);
        movement.setCreationDate(Instant.now());
        entityManager.persist(movement);
        entityManager.flush();

        assertFalse(movementRepository.findByAccountId(movement.getAccountId()).isEmpty());
    }

    @Test
    void testFindByAccountNumberWhenNotExists() {
        assertTrue(movementRepository.findByAccountId(UUID.randomUUID()).isEmpty());
    }

}