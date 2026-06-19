package com.devsu.account.usecase;

import com.devsu.account.domain.model.Movement;
import com.devsu.account.domain.repository.MovementRepository;
import com.devsu.account.dto.MovementResponse;

import java.util.MissingResourceException;
import java.util.UUID;

public class FindMovement {

    private final MovementRepository movementRepository;

    public FindMovement(MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    public MovementResponse execute(UUID id) {
        Movement movement = movementRepository.findById(id)
                .orElseThrow(() -> new MissingResourceException("Movement not found", Movement.class.getName(),
                        id.toString()));
        return MovementResponse.fromDomain(movement);
    }
}
