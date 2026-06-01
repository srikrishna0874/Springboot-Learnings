package com.codingshuttle.springweb.controller;

import com.codingshuttle.springweb.dto.EmployeeDTO;
import com.codingshuttle.springweb.entity.EmployeeEntity;
import com.codingshuttle.springweb.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @GetMapping("/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @GetMapping()
    public List<EmployeeEntity> getAllEmployees(@RequestParam(required = false, name = "age") Integer age,
                                                @RequestParam(required = false) String sortBy) {
        return employeeRepository.findAll();
    }

    @PostMapping
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }

    @PutMapping
    public String updateEmployeeById() {
        return "Hello from PUT";
    }

}
