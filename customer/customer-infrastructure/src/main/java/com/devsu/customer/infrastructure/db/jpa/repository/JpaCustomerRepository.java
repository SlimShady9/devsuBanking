package com.devsu.customer.infrastructure.db.jpa.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.devsu.customer.infrastructure.db.jpa.entities.CustomerEntity;

@Repository
public interface JpaCustomerRepository extends JpaRepository<CustomerEntity, UUID> {

    @NonNull
    Optional<CustomerEntity> findById(UUID id);

    Optional<CustomerEntity> findByName(String name);

}
