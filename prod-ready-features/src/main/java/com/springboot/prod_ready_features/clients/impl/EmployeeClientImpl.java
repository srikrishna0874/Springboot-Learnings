package com.springboot.prod_ready_features.clients.impl;

import com.springboot.prod_ready_features.advice.ApiResponse;
import com.springboot.prod_ready_features.clients.EmployeeClient;
import com.springboot.prod_ready_features.dto.EmployeeDTO;
import com.springboot.prod_ready_features.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;

    @Override
    public List<EmployeeDTO> getAllEmployees() {

        try {
            ApiResponse<List<EmployeeDTO>> response = restClient
                    .get()
                    .uri("employees")
                    .retrieve()
                    .body(new ParameterizedTypeReference<ApiResponse<List<EmployeeDTO>>>() {
                    });

            return response.getData();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch employees", e);
        }

    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        try {
            ApiResponse<EmployeeDTO> employeeDTOApiResponse = restClient
                    .get()
                    .uri("employees/{employeeId}", id)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeDTOApiResponse.getData();
        } catch (Exception e) {
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
