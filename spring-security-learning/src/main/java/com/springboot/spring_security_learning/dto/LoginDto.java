package com.springboot.spring_security_learning.dto;

import lombok.Data;

@Data
public class LoginDto {

    private String email;

    private String password;
}
