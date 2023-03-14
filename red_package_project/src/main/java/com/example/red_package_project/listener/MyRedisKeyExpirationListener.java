package com.example.red_package_project.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @Date 14/3/2023 0014 上午 9:58
 * @Description redis key 失效监听器
 * @Version 1.0.0
 * @Author liwenbo
 */
@Component
@Slf4j
public class MyRedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    public MyRedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        //获取过期的key
        String expireKey = message.toString();
        log.info("key is:" + expireKey);
    }
}
