package com.springboot.kafka_learning.user_service.dto;

import lombok.Data;

@Data
public class CreateUserRequestDto {

    private Long id;

    private String name;

    private String email;
}
