package com.springboot.cachingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class CachingappApplication {

    public static void main(String[] args) {
        SpringApplication.run(CachingappApplication.class, args);
    }

}
