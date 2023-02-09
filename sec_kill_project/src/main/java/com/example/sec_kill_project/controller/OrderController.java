package com.example.sec_kill_project.controller;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.util.concurrent.RateLimiter;
import org.redisson.Redisson;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Date 9/2/2023 0009 下午 5:07
 * @Description 订单controller
 * @Version 1.0.0
 * @Author liwenbo
 */
@RestController
public class OrderController {

    @Resource
    private Redisson redisson;

    /**
     * 基于令牌桶算法的限流实现类
     */
    RateLimiter rateLimiter = RateLimiter.create(10);

    /**
     * guava 缓存层
     */
    Cache<Object, Object> cache = CacheBuilder.newBuilder().build();


}
