package com.devsu.account.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.devsu.account.domain.model.Account;
import com.devsu.account.domain.model.AccountType;
import com.devsu.account.domain.model.Movement;
import com.devsu.account.domain.repository.AccountRepository;
import com.devsu.account.domain.repository.MovementRepository;
import com.devsu.account.dto.MovementResponse;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FindMovementsTest {
    private AccountRepository accountRepository;
    private MovementRepository movementRepository;
    private FindMovements findMovements;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);
        movementRepository = mock(MovementRepository.class);
        findMovements = new FindMovements(accountRepository, movementRepository);

    }

    @Test
    void should_find_movements_by_account_number_return_list_of_movements() {

        UUID accountId = UUID.randomUUID();
        Account account = new Account(accountId, "1234567890", AccountType.SAVINGS, 1000.0, true, UUID.randomUUID());
        List<Movement> movements = List.of(
                new Movement(UUID.randomUUID(), accountId, 100.0),
                new Movement(UUID.randomUUID(), accountId, 200.0));

        when(movementRepository.findByAccountId(accountId)).thenReturn(movements);
        when(accountRepository.findByAccountNumber("1234567890")).thenReturn(Optional.of(account));

        List<MovementResponse> result = findMovements.execute("1234567890");
        assertEquals(2, result.size());
    }

    @Test
    void should_return_empty_list_when_account_not_found() {
        when(accountRepository.findByAccountNumber("nonexistent")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> findMovements.execute("nonexistent"));

    }
}