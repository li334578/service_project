package com.example.aop_project.service;

/**
 * @Date 21/4/2023 0021 上午 9:05
 * @Description TODO
 * @Version 1.0.0
 * @Author liwenbo
 */
public interface RetryService {

    String mockRetry(String param);

    String mockRetry(String param, Integer numberParam);
}
