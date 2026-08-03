package com.springboot.cachingapp.services.impl;

import com.springboot.cachingapp.entities.Employee;
import com.springboot.cachingapp.entities.SalaryAccount;
import com.springboot.cachingapp.repositories.SalaryAccountRepository;
import com.springboot.cachingapp.services.SalaryAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
public class SalaryAccountServiceImpl implements SalaryAccountService {

    private final SalaryAccountRepository salaryAccountRepository;

    @Override
    public void createSalaryAccount(Employee employee) {

        if (employee.getName().equals("Krishna2")) {
            throw new RuntimeException("Krishna2 is not allowed");
        }

        SalaryAccount salaryAccount = SalaryAccount.builder()
                .employee(employee)
                .balance(BigDecimal.ZERO)
                .build();

        salaryAccountRepository.save(salaryAccount);
    }

    @Override
    public SalaryAccount incrementBalance(Long accountId) {

        SalaryAccount salaryAccount = salaryAccountRepository.findById(accountId).orElseThrow(() ->
                new RuntimeException("SalaryAccount not found"));

        BigDecimal previousBalance = salaryAccount.getBalance();
        BigDecimal newBalance = salaryAccount.getBalance().add(BigDecimal.valueOf(1L));

        salaryAccount.setBalance(newBalance);

        return salaryAccountRepository.save(salaryAccount);
    }
}