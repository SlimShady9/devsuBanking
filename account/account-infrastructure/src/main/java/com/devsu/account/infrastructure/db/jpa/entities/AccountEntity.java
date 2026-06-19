package com.devsu.account.infrastructure.db.jpa.entities;

import java.time.Instant;
import java.util.UUID;

import com.devsu.account.domain.model.Account;
import com.devsu.account.domain.model.AccountType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "account_type")
    private String accountType;
    @Column(name = "balance")
    private double balance;
    @Column(name = "state")
    private Boolean state;
    @Column(name = "customer_id")
    private UUID customerId;
    @Column(name = "creation_date")
    private Instant creationDate;

    public static AccountEntity fromDomain(Account account) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(account.getId());
        accountEntity.setAccountNumber(account.getAccountNumber());
        accountEntity.setAccountType(account.getAccountType().name());
        accountEntity.setBalance(account.getBalance());
        accountEntity.setState(account.getState());
        accountEntity.setCustomerId(account.getCustomerId());
        accountEntity.setCreationDate(account.getCreationDate());
        return accountEntity;
    }

    public Account toDomain() {

        Account newAccount = new Account();
        newAccount.setId(this.id);
        newAccount.setAccountNumber(this.getAccountNumber());
        newAccount.setAccountType(AccountType.fromString(this.accountType));
        newAccount.setBalance(this.getBalance());
        newAccount.setState(this.getState());
        newAccount.setCustomerId(this.getCustomerId());
        newAccount.setCreationDate(this.getCreationDate());

        return newAccount;
    }

}
