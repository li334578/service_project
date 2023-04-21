package com.example.aop_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class AopProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopProjectApplication.class, args);
    }

}
