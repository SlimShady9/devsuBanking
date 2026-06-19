package com.devsu.account.instrastructure.db.jpa;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.devsu.account.infrastructure.db.jpa.entities.AccountEntity;
import com.devsu.account.infrastructure.db.jpa.repository.JpaAccountRepository;
import com.devsu.account.instrastructure.config.ConfigurationTest;

import jakarta.persistence.EntityManager;

@Transactional
public class JpaAccountRepositoryTest extends ConfigurationTest {

    @Autowired
    private JpaAccountRepository accountRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void testFindByAccountNumberWhenExists() {
        AccountEntity account = new AccountEntity();
        account.setAccountNumber("123456789");
        account.setAccountType("SAVINGS");
        account.setState(true);
        account.setBalance(0.0);
        entityManager.persist(account);
        entityManager.flush();

        assertTrue(accountRepository.findByAccountNumber("123456789").isPresent());
    }

    @Test
    void testFindByAccountNumberWhenNotExists() {
        assertFalse(accountRepository.findByAccountNumber("999999999").isPresent());
    }

}
