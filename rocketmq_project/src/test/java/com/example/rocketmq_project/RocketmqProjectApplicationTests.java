package com.example.rocketmq_project;

import com.example.rocketmq_project.component.MQSendUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

@SpringBootTest
@Slf4j
class RocketmqProjectApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    private MQSendUtils mqSendUtils;

    @Test
    public void testMethod1() {
        SendResult s1 = mqSendUtils.sendMessage("my-topic", "hello world1");
        SendResult s2 = mqSendUtils.sendMessage("my-topic", "tagA", "hello world2");
        log.info("s1:{},s2:{}", s1, s2);
    }

    @Test
    public void testMethod2() {
        CountDownLatch latch = new CountDownLatch(2);
        SendCallback callback = new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                latch.countDown();
                log.info("消息发送成功{}", sendResult);
            }

            @Override
            public void onException(Throwable e) {
                latch.countDown();
                log.error("消息发送失败 {}", e);
            }
        };
        mqSendUtils.sendAsyncMessage("my-topic", "hello world3", callback);
        mqSendUtils.sendAsyncMessage("my-topic", "tagA", "hello world4", callback);
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("消息发送完成");
    }

    @Test
    public void testMethod3() {
        SendResult s1 = mqSendUtils.sendBatchMessage("my-topic", Arrays.asList("hello world5", "hello world6"));
        SendResult s2 = mqSendUtils.sendBatchMessage("my-topic", "tagA", Arrays.asList("hello world7", "hello world8"));
        log.info("s1:{},s2:{}", s1, s2);
    }

    @Test
    public void testMethod4() {
        SendResult s1 = mqSendUtils.sendMessage("my-topic", "tagA", "hello world1");
        SendResult s2 = mqSendUtils.sendMessage("my-topic", "tagB", "hello world2");
        SendResult s3 = mqSendUtils.sendMessage("my-topic", "tagC", "hello world3");
        log.info("s1:{},s2:{},s3:{}", s1, s2, s3);
    }
}
