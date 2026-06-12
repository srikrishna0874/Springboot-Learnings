package com.codingshuttle.springweb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private Integer age;

    private String role;

    private Double salary;

    private LocalDate dateOfBirth;

    private LocalDate dateOfJoining;

    private Double experienceInYears;

    @JsonProperty("isActive")
    private Boolean isActive;

    @JsonProperty("isRetired")
    private Boolean isRetired;

}
