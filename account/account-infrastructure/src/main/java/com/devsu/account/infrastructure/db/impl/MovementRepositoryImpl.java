package com.devsu.account.infrastructure.db.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.devsu.account.domain.model.Movement;
import com.devsu.account.domain.repository.MovementRepository;
import com.devsu.account.infrastructure.db.jpa.entities.MovementEntity;
import com.devsu.account.infrastructure.db.jpa.repository.JpaMovementRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class MovementRepositoryImpl implements MovementRepository {

    private final JpaMovementRepository jpaMovementRepository;

    @Override
    public Movement save(Movement movement) {

        MovementEntity movementEntity = MovementEntity.fromDomain(movement);

        return jpaMovementRepository.save(movementEntity).toDomain();
    }

    @Override
    public Optional<Movement> findById(UUID id) {

        return jpaMovementRepository.findById(id).map(MovementEntity::toDomain);
    }

    @Override
    public List<Movement> findAll() {

        return jpaMovementRepository.findAll().stream().map(MovementEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Movement> findByAccountId(UUID accountId) {

        return jpaMovementRepository.findByAccountId(accountId).stream().map(MovementEntity::toDomain)
                .collect(Collectors.toList());
    }

}
