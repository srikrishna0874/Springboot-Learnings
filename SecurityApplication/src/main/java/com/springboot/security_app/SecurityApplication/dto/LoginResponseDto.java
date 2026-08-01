package com.springboot.security_app.SecurityApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {

    Long id;

    String accessToken;

    String refreshToken;
}
