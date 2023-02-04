package com.example.rocketmq_project.controller;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Date 2/2/2023 0002 下午 1:59
 * @Description 测试rocketMQ
 * @Version 1.0.0
 * @Author liwenbo
 */
@RestController
@RequestMapping("/mq/")
public class RocketController {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    private static final String MY_TOPIC = "my-topic";

    @GetMapping("send")
    public String sendMsg(@RequestParam("msg") String msg) {
        // 同步发送 拿不到回执
        rocketMQTemplate.convertAndSend(MY_TOPIC, msg);
        // 同步发送 有回执
        // SendResult sendResult = rocketMQTemplate.syncSend(MY_TOPIC, msg);
//        SendCallback callback = new SendCallback() {
//            @Override
//            public void onSuccess(SendResult sendResult) {
//                System.out.println("send msg success");
//            }
//
//            @Override
//            public void onException(Throwable throwable) {
//                System.out.println("send msg fail");
//            }
//        };
        // 异步发送 有回执
        // rocketMQTemplate.asyncSend(MY_TOPIC, msg, callback);

        // 与UDP 类似，不会等待broker的确认后再返回。显然，它具有最大的吞吐量，但有可能丢失消息。单向传输用于需要中等可靠性的情况，例如日志收集。
        // 单向消息
        // rocketMQTemplate.sendOneWay(MY_TOPIC, msg);
        return "OK";
    }


}
