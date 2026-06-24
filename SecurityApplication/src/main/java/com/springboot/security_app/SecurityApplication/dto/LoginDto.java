package com.springboot.security_app.SecurityApplication.dto;

import lombok.Data;

@Data
public class LoginDto {

    private String email;

    private String password;
}
