package com.springboot.prod_ready_features.clients.impl;

import com.springboot.prod_ready_features.advice.ApiResponse;
import com.springboot.prod_ready_features.clients.EmployeeClient;
import com.springboot.prod_ready_features.dto.EmployeeDTO;
import com.springboot.prod_ready_features.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;

    Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);

    @Override
    public List<EmployeeDTO> getAllEmployees() {

        log.trace("Fetching all employees");
        try {


            List<EmployeeDTO> employeeDTOList = restClient
                    .get()
                    .uri("employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.error("Error occurred: {}", new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Failed to fetch employee by id: Employee not found");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });

            log.debug("Employees retrieved successfully");
            log.trace("Employee List: {}", employeeDTOList);
            return employeeDTOList;
        } catch (Exception e) {
            log.error("Exception occurred while getting employees", e);
            throw new RuntimeException("Failed to fetch employees", e);
        }

    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        log.trace("Fetching employee with id: {}", id);
        try {
            ApiResponse<EmployeeDTO> employeeDTOApiResponse = restClient
                    .get()
                    .uri("employees/{employeeId}", id)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.error("Error occurred: {}", new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Failed to fetch employee by id: Employee not found");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeDTOApiResponse.getData();
        } catch (Exception e) {
            log.error("Exception occurred while getting employee by id", e);
            throw new RuntimeException("Failed to fetch employee by id", e);
        }
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        try {
            ApiResponse<EmployeeDTO> employeeDTOApiResponse = restClient
                    .post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        System.out.println("Error occurred: " + new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Failed to create employee: ");
                    })
                    .onStatus(HttpStatusCode::is5xxServerError, (req, res) -> {
                        System.out.println("Error occurred: " + new String(res.getBody().readAllBytes()));
                        throw new RuntimeException("Failed to create employee: Server error");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeDTOApiResponse.getData();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create employee", e);
        }
    }
}
