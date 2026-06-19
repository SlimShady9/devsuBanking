package com.devsu.account.infrastructure.db.jpa.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.devsu.account.infrastructure.db.jpa.entities.MovementEntity;

@Repository
public interface JpaMovementRepository extends JpaRepository<MovementEntity, UUID> {

    @NonNull
    Optional<MovementEntity> findById(@NonNull UUID id);

    List<MovementEntity> findByAccountId(@NonNull UUID accountId);

}
