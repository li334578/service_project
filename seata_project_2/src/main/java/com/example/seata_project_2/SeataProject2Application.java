package com.example.seata_project_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SeataProject2Application {

    public static void main(String[] args) {
        SpringApplication.run(SeataProject2Application.class, args);
    }

}
