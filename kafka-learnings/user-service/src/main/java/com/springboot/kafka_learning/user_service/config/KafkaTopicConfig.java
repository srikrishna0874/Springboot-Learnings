package com.springboot.kafka_learning.user_service.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic.user-random-topic}")
    private String KAFKA_TOPIC_USER_RANDOM;

    @Bean
    NewTopic userRandomTopic() {
        return new NewTopic(KAFKA_TOPIC_USER_RANDOM, 3, (short) 1);
    }
}
