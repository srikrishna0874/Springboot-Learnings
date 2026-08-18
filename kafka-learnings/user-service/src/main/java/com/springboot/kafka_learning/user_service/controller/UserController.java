package com.springboot.kafka_learning.user_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.user-random-topic}")
    private String KAFKA_TOPIC_USER_RANDOM;

    @PostMapping(path = "/{message}")
    public ResponseEntity<String> sendMessage(@PathVariable String message) {

        for(int i=1; i<=1000; i++)
            kafkaTemplate.send(KAFKA_TOPIC_USER_RANDOM, ""+i%2, message+i);

        return ResponseEntity.ok("Message queued");
    }
}
