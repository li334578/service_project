package com.example.red_package_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Date 15/3/2023 0015 上午 10:55
 * @Description 线程池配置
 * @Version 1.0.0
 * @Author liwenbo
 */
@Configuration
public class ThreadPoolConfig {


    @Bean
    public ExecutorService executorService() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        int poolSize = Runtime.getRuntime().availableProcessors() * 2;
        taskExecutor.setCorePoolSize(poolSize);
        taskExecutor.setMaxPoolSize(poolSize);
        taskExecutor.setQueueCapacity(poolSize * 10);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.setThreadNamePrefix("async-");
        taskExecutor.initialize();
        return taskExecutor.getThreadPoolExecutor();
    }
}
