package com.springboot.cachingapp.repositories;

import com.springboot.cachingapp.entities.SalaryAccount;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface SalaryAccountRepository extends JpaRepository<SalaryAccount, Long> {

    @Override
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    Optional<SalaryAccount> findById(Long id);
}