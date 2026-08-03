package com.springboot.cachingapp.repositories;

import com.springboot.cachingapp.entities.SalaryAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryAccountRepository extends JpaRepository<SalaryAccount, Long> {
}