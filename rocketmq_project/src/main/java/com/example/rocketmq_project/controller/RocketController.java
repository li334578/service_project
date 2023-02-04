package com.example.rocketmq_project.controller;

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


    @GetMapping("send")
    public String sendMsg(@RequestParam("msg") String msg) {
        // 同步发送 拿不到回执
        rocketMQTemplate.convertAndSend("my-topic", msg);
        return "OK";
    }


}
