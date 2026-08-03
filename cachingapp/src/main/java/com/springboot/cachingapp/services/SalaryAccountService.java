package com.springboot.cachingapp.services;

import com.springboot.cachingapp.entities.Employee;
import com.springboot.cachingapp.entities.SalaryAccount;

public interface SalaryAccountService {

    void createSalaryAccount(Employee employee);

    SalaryAccount incrementBalance(Long accountId);
}
