package com.springboot.security_app.SecurityApplication.dto;

import com.springboot.security_app.SecurityApplication.entities.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpDto {
    private String email;

    private String password;

    private String name;

    private Set<Role> roles;
}
