package com.example.sec_kill_project.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Date 9/2/2023 0009 下午 4:44
 * @Description redis配置文件
 * @Version 1.0.0
 * @Author liwenbo
 */
@Data
@ConfigurationProperties(prefix = "redis")
@Configuration
public class RedisConfig {
    private String host;
    private Integer port;
    private String password;
}
