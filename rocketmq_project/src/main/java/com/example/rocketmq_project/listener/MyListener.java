package com.example.rocketmq_project.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Date 2/2/2023 0002 下午 2:01
 * @Description TODO
 * @Version 1.0.0
 * @Author liwenbo
 */
@Component
@RocketMQMessageListener(topic = "my-topic", consumerGroup = "my-group", consumeMode = ConsumeMode.ORDERLY)
@Slf4j
public class MyListener implements RocketMQListener<String> {


    /*
    *
        consumeMode - 消费模式
         默认值：ConsumeMode.CONCURRENTLY并行接受
         ConsumeMode.ORDERLY每个队列使用一个线程按顺序接收
        messageModel - 消息模式
         默认值：MessageModel.CLUSTERING集群模式
         MessageModel.BROADCASTING广播模式
    *
    * */

    @Override
    public void onMessage(String message) {
        log.info("=== 收到消息了{}", message);
    }
}
