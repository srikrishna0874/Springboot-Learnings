package com.codingshuttle.springweb.controller;

import com.codingshuttle.springweb.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

//    @GetMapping(path = "/get-secret-message")
//    public String getMySuperSecretMessage() {
//        return "Secret message : sfknksdf232kjkj";
//    }

    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        return new EmployeeDTO(
                id,
                "Krishna",
                "krishna@gmail.com",
                23,
                LocalDate.of(2024, 1, 2),
                true
        );
    }

    @GetMapping()
    public String getAllEmployees(@RequestParam(required = false, name = "age") Integer age,
                                  @RequestParam(required = false) String sortBy) {
        return "Hi age " + age + " " + sortBy;
    }

    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeDTO.setId(100L);
        return employeeDTO;
    }

    @PutMapping
    public String updateEmployeeById() {
        return "Hello from PUT";
    }

}
