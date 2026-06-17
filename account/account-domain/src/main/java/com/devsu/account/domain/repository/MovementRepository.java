package com.devsu.account.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.devsu.account.domain.model.Movement;

public interface MovementRepository {
    Movement save(Movement movement);

    Optional<Movement> findById(UUID id);

    List<Movement> findAll();

    List<Movement> findByAccountId(UUID accountId);
}
