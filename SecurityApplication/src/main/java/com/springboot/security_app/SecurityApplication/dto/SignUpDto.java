package com.springboot.security_app.SecurityApplication.dto;

import lombok.Data;

@Data
public class SignUpDto {
    private String email;

    private String password;

    private String name;
}
