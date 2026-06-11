package com.springboot.prod_ready_features.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    private String name;

    private String email;

    private Integer age;

    private Double salary;

    private String role;

    private LocalDate dateOfBirth;

    private LocalDate dateOfJoining;

    private Double experienceInYears;

    private Boolean isActive;

    private Boolean isRetired;


}
