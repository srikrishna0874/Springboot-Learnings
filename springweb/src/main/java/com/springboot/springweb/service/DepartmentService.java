package com.codingshuttle.springweb.service;

import com.codingshuttle.springweb.dto.DepartmentDTO;
import com.codingshuttle.springweb.dto.EmployeeDTO;
import com.codingshuttle.springweb.entity.DepartmentEntity;
import com.codingshuttle.springweb.exceptions.ResourceNotFoundException;
import com.codingshuttle.springweb.repository.DepartmentRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository,
                             ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    void isDepartmentExistsById(Long departmentId) {
        boolean isDepartmentExists = departmentRepository.existsById(departmentId);

        if (!isDepartmentExists) {
            throw new ResourceNotFoundException("Department with id " + departmentId + " not found");
        }
    }

    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();

        return departmentEntities
                .stream()
                .map((departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class)))
                .toList();
    }

    public DepartmentDTO getDepartmentById(Long departmentId) {

        DepartmentEntity departmentEntity = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department with id " + departmentId + " not found"));

        return modelMapper.map(departmentEntity, DepartmentDTO.class);
    }

    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        DepartmentEntity savedDepartment =
                departmentRepository.save(modelMapper.map(departmentDTO, DepartmentEntity.class));
        return modelMapper.map(savedDepartment, DepartmentDTO.class);
    }

    public DepartmentDTO updateDepartmentById(Long id, DepartmentDTO departmentDTO) {
        isDepartmentExistsById(id);

        DepartmentEntity updatedDepartmentEntity =
                departmentRepository.save(modelMapper.map(departmentDTO, DepartmentEntity.class));

        return modelMapper.map(updatedDepartmentEntity, DepartmentDTO.class);

    }

    public Boolean deleteDepartmentById(Long id) {
        isDepartmentExistsById(id);

        departmentRepository.deleteById(id);
        return true;
    }
}
