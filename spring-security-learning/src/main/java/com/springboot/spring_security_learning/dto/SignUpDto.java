package com.springboot.spring_security_learning.dto;

import lombok.Data;

@Data
public class SignUpDto {
    private String email;

    private String password;

    private String name;
}
