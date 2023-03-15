package com.example.red_package_project;

import cn.hutool.core.date.DateUtil;
import com.example.red_package_project.pojo.DelayTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
class RedPackageProjectApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testMethod1() {
        // delayQueue延迟队列
        Queue<DelayTask> queue = new DelayQueue<>();
        queue.offer(new DelayTask(1, "A001", DateUtil.date(), 5));
        queue.offer(new DelayTask(2, "A002", DateUtil.date(), 10));
        queue.offer(new DelayTask(3, "A003", DateUtil.date(), 3));
        queue.offer(new DelayTask(4, "A004", DateUtil.date(), 7));
        log.info("size is {}", queue.size());
        CountDownLatch countDownLatch = new CountDownLatch(4);

        new Thread(() -> {
            for (; ; ) {
                DelayTask poll = queue.poll();
                if (Objects.isNull(poll)) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    // 取出任务成功
                    log.info("{}订单，检查数据库是否已经支付，未支付跳转到已取消，并归还库存", poll.getOrderNumber());
                    countDownLatch.countDown();
                }
            }
        }).start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
