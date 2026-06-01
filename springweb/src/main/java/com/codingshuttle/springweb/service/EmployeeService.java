package com.codingshuttle.springweb.service;

import com.codingshuttle.springweb.dto.EmployeeDTO;
import com.codingshuttle.springweb.entity.EmployeeEntity;
import com.codingshuttle.springweb.repository.EmployeeRepository;
import org.apache.el.util.ReflectionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        return employeeRepository
                .findById(id)
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();

        return employeeEntityList
                .stream()
                .map((employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class)))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);

        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);

        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(Long id, EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(id);

        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);

        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public boolean isEmployeeIdExists(Long id) {
        return employeeRepository.existsById(id);
    }

    public boolean deleteEmployeeById(Long id) {
        boolean isExists = isEmployeeIdExists(id);

        if (!isExists)
            return false;

        employeeRepository.deleteById(id);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Long id, Map<String, Object> updateData) {
        boolean isExists = isEmployeeIdExists(id);

        if (!isExists)
            return null;

        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();

        updateData.forEach((key, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class, key);
            fieldToBeUpdated.setAccessible(true);

            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
        });

        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }
}
