package com.codingshuttle.springweb.controller;

import com.codingshuttle.springweb.dto.EmployeeDTO;
import com.codingshuttle.springweb.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);

        return employeeDTO
                .map((employeeDTO1 -> ResponseEntity.ok(employeeDTO1)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false, name = "age") Integer age,
                                                             @RequestParam(required = false) String sortBy) {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        EmployeeDTO savedEmployeeDto = employeeService.createEmployee(employeeDTO);

        return new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@PathVariable(name = "employeeId") Long id,
                                                          @RequestBody @Valid EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.updateEmployeeById(id, employeeDTO));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable(name = "employeeId") Long id) {
        Boolean isDeleted = employeeService.deleteEmployeeById(id);

        if (isDeleted) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@PathVariable(name = "employeeId") Long id,
                                                                 @RequestBody Map<String, Object> updateData) {
        EmployeeDTO updatedEmployeeDto = employeeService.updatePartialEmployeeById(id, updateData);

        if (updatedEmployeeDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEmployeeDto);
    }

}
