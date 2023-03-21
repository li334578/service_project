package com.example.red_package_project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RedPackageProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedPackageProjectApplication.class, args);
    }

    @Bean
    CommandLineRunner init() {
        return (args) -> {
            System.out.println("initial");
            System.out.println("initial step 2");
        };
    }

}
