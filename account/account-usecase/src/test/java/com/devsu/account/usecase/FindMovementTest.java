package com.devsu.account.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.devsu.account.domain.model.Account;
import com.devsu.account.domain.model.Movement;
import com.devsu.account.domain.repository.AccountRepository;
import com.devsu.account.domain.repository.MovementRepository;
import com.devsu.account.dto.MovementResponse;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FindMovementTest {
    private MovementRepository movementRepository;
    private FindMovement useCase;

    @BeforeEach
    void setUp() {
        movementRepository = mock(MovementRepository.class);
        useCase = new FindMovement(movementRepository);

    }

    @Test
    void should_return_movement_when_exists() {

        UUID id = UUID.randomUUID();
        Movement movement = new Movement(id, UUID.randomUUID(), 200.0);
        when(movementRepository.findById(id)).thenReturn(Optional.of(movement));
        MovementResponse response = useCase.execute(id);
        assertEquals(MovementResponse.fromDomain(movement), response);
    }

    void should_thow_error_when_movement_not_exists() {

        UUID id = UUID.randomUUID();
        when(movementRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> useCase.execute(id));
    }
}