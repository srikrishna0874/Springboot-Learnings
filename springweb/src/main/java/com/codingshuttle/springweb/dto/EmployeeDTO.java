package com.codingshuttle.springweb.dto;

import com.codingshuttle.springweb.annotations.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "Name of the employee shouldn't be blank")
    @Size(min = 3, max = 10, message = "Number of characters in name should be in range [3, 10]")
    private String name;

    @NotBlank(message = "The email of an employee cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Age of an employee should not be blank")
    @Max(value = 80, message = "Age of an employee cannot be greater than 80")
    @Min(value = 18, message = "Age of an employee cannot be less than 18")
    private Integer age;

    @NotNull(message = "Salary of the employee should not be empty")
    @Positive(message = "Salary of the employee should be positive")
    @Digits(integer = 6, fraction = 2, message = "Salary of employee can only be in the form XXXXXX.YY")
    @DecimalMin(value = "100.50", message = "Salary should be greater than 100.50")
    @DecimalMax(value = "100000.99", message = "Salary should be less than 100000.99")
    private Double salary;

    @NotBlank(message = "The role of an employee cannot be blank")
//    @Pattern(regexp = "^(ADMIN|USER)$", message = "The role of an employee can be ADMIN / USER")
    @EmployeeRoleValidation
    private String role;

    @PastOrPresent(message = "Date of joining of employee cannot be the future date.")
    private LocalDate dateOfJoining;

    @JsonProperty("isActive")
    @AssertTrue(message = "Employee should be active.")
    private Boolean isActive;


}
