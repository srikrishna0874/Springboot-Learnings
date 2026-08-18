package com.springboot.kafka_learning.user_service.controller;

import com.springboot.kafka_learning.user_service.dto.CreateUserRequestDto;
import com.springboot.kafka_learning.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.user-random-topic}")
    private String KAFKA_TOPIC_USER_RANDOM;

    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
        userService.createNewUser(createUserRequestDto);

        return ResponseEntity.ok("User is created successfully");
    }

    @PostMapping(path = "/{message}")
    public ResponseEntity<String> sendMessage(@PathVariable String message) {

        for(int i=1; i<=1000; i++)
            kafkaTemplate.send(KAFKA_TOPIC_USER_RANDOM, ""+i%2, message+i);

        return ResponseEntity.ok("Message queued");
    }
}
