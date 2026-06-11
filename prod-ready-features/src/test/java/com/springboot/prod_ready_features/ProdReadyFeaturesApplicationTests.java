package com.springboot.prod_ready_features;

import com.springboot.prod_ready_features.clients.EmployeeClient;
import com.springboot.prod_ready_features.dto.EmployeeDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProdReadyFeaturesApplicationTests {

    @Autowired
    private EmployeeClient employeeClient;


    @Test
    @Order(3)
    void getAllEmployees() {
        System.out.println(employeeClient.getAllEmployees());
    }

    @Test
    @Order(2)
    void getEmployeeById() {
        System.out.println(employeeClient.getEmployeeById(1L));
    }

    @Test
    @Order(1)
    void createEmployee() {
        EmployeeDTO employeeDTO = new EmployeeDTO(
                1L,
                "Krishna",
                "krishna@example.com",
                2,
                50000.50,
                "ADMIN",
                LocalDate.of(2001, 5, 15),
                LocalDate.of(2024, 1, 10),
                2.5,
                true,
                false
        );
        System.out.println(employeeClient.createEmployee(employeeDTO));
    }


}
