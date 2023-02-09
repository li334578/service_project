package com.example.sec_kill_project.config;

import cn.hutool.core.util.StrUtil;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Date 9/2/2023 0009 下午 4:42
 * @Description redisson 配置文件
 * @Version 1.0.0
 * @Author liwenbo
 */
@Configuration
public class RedissonConfig {
    private static final String redisConfTemp = "redis://{}:{}";

    @Resource
    RedisConfig redisConfig;

    @Bean
    public Redisson redisson() {
        Config config = new Config();
        // 使用单机模式 设置地址 密码 和所用数据库
        config.useSingleServer()
                .setAddress(StrUtil.format(redisConfTemp, redisConfig.getHost(), redisConfig.getPort()))
                .setPassword(redisConfig.getPassword());
        return (Redisson) Redisson.create(config);
    }
}
