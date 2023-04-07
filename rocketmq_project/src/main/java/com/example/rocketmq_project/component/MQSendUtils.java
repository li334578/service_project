package com.example.rocketmq_project.component;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Date 7/4/2023 0007 下午 1:14
 * @Description MQ 消息发送工具类
 * @Version 1.0.0
 * @Author liwenbo
 */
@Component
public class MQSendUtils<T> {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送同步消息
     */
    public SendResult sendMessage(String topic, T message) {
        return rocketMQTemplate.syncSend(topic, message);
    }

    /**
     * 发送同步消息 带tag
     */
    public SendResult sendMessage(String topic, String tag, T message) {
        // topic:tag
        return sendMessage(topic + ":" + tag, message);
    }

    /**
     * 发送异步消息
     */
    public void sendAsyncMessage(String topic, T message, SendCallback callback) {
        rocketMQTemplate.asyncSend(topic, message, callback);
    }

    /**
     * 发送异步消息
     */
    public void sendAsyncMessage(String topic, String tag, T message, SendCallback callback) {
        sendAsyncMessage(topic + ":" + tag, message, callback);
    }

    /**
     * 发送同步的顺序消息，保证一类消息发送到同一个MessageQueue中
     */
    public SendResult sendOrderlyMessage(String topic, T msg, String sortId) {
        Message<T> message = MessageBuilder.withPayload(msg).setHeader(RocketMQHeaders.KEYS, sortId).build();
        return rocketMQTemplate.syncSendOrderly(topic, message, sortId);
    }

    /**
     * 发送同步的顺序消息，保证一类消息发送到同一个MessageQueue中
     */
    public SendResult sendOrderlyMessage(String topic, String tag, T msg, String sortId) {
        return sendOrderlyMessage(topic + ":" + tag, msg, sortId);
    }

    /**
     * 发送延迟消息
     * messageDelayLevel=1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     */
    public SendResult sendDelayedMessage(String topic, T msg, int delayLevel) {
        Message<T> message = MessageBuilder.withPayload(msg).build();
        // 超时时间
        return rocketMQTemplate.syncSend(topic, message, 2000L, delayLevel);
    }

    /**
     * 发送延迟消息
     * messageDelayLevel=1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     */
    public SendResult sendDelayedMessage(String topic, String tag, T msg, int delayLevel) {
        return sendDelayedMessage(topic + ":" + tag, msg, delayLevel);
    }

    /**
     * 批量消息发送
     */
    public SendResult sendBatchMessage(String topic, List<T> msgList) {
        return rocketMQTemplate.syncSend(topic, msgList.stream().map(s -> MessageBuilder.withPayload(s).build()).collect(Collectors.toList()));
    }

    /**
     * 批量消息发送
     */
    public SendResult sendBatchMessage(String topic, String tag, List<T> msgList) {
        return sendBatchMessage(topic + ":" + tag, msgList);
    }
}
