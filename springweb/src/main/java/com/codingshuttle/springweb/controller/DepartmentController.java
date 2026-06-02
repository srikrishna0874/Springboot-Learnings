package com.codingshuttle.springweb.controller;

import com.codingshuttle.springweb.dto.DepartmentDTO;
import com.codingshuttle.springweb.dto.EmployeeDTO;
import com.codingshuttle.springweb.entity.DepartmentEntity;
import com.codingshuttle.springweb.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable("departmentId") Long departmentId) {
        return ResponseEntity.ok(departmentService.getDepartmentById(departmentId));
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO savedDepartment = departmentService.createDepartment(departmentDTO);

        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO> updateEmployeeById(@PathVariable(name = "departmentId") Long id,
                                                          @RequestBody @Valid DepartmentDTO departmentDTO) {
        return ResponseEntity.ok(departmentService.updateDepartmentById(id, departmentDTO));
    }

    @DeleteMapping(path = "/{departmentId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable(name = "departmentId") Long id) {
        Boolean isDeleted = departmentService.deleteDepartmentById(id);

        if (isDeleted) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }
}
