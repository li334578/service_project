package com.example.red_package_project;

import cn.hutool.core.date.DateUtil;
import com.example.red_package_project.pojo.DelayTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
class RedPackageProjectApplicationTests {

    @Resource
    private ExecutorService executorService;

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

    @Test
    public void testMethod2() {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start("task1");
        List<Integer> collect = IntStream.range(0, 1000).parallel().map(this::mockOperation).boxed()
                .collect(Collectors.toList());
        log.info("size1:{}", collect.size());
        stopWatch.stop();

        stopWatch.start("task2");
//        List<Integer> collect1 = Collections.synchronizedList(new ArrayList<>());
//        CompletableFuture[] completableFutures = IntStream.range(0, 1000).boxed().map(item ->
//                CompletableFuture.supplyAsync(() -> this.mockOperation(item), executorService).whenComplete((result, e) -> {
//                    if (e != null) {
//                        log.error("出现异常");
//                    } else {
//                        collect1.add(result);
//                    }
//                })).toArray(CompletableFuture[]::new);
//        CompletableFuture.allOf(completableFutures).join();


//        List<CompletableFuture<Integer>> completableFutures = IntStream.range(0, 1000).boxed().map(item ->
//                CompletableFuture.supplyAsync(() -> this.mockOperation(item), executorService)).collect(Collectors.toList());
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        log.info("我取获取结果了！！！！");
//        List<Integer> collect1 = completableFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());


        List<Integer> collect1 = IntStream.range(0, 1000).boxed().map(item -> CompletableFuture.supplyAsync(() ->
                this.mockOperation(item), executorService).join()).collect(Collectors.toList());

        log.info("size2:{}", collect1.size());
        stopWatch.stop();
        // stopWatchStopWatch '': running time = 94757522100 ns; [task1] took 64232524700 ns = 68%; [task2] took 30524997400 ns = 32%
        // stopWatchStopWatch '': running time = 94140481500 ns; [task1] took 63786134100 ns = 68%; [task2] took 30354347400 ns = 32%
        // stopWatchStopWatch '': running time = 574377249700 ns; [task1] took 64254966500 ns = 11%; [task2] took 510122283200 ns = 89%
        // stopWatchStopWatch '': running time = 573664965300 ns; [task1] took 64581624600 ns = 11%; [task2] took 509083340700 ns = 89%
        // stopWatchStopWatch '': running time = 96532801300 ns; [task1] took 64327235000 ns = 67%; [task2] took 32205566300 ns = 33%

        log.info("stopWatch{}", stopWatch);
    }

    private Integer mockOperation(Integer i) {
//        log.info("我执行了{}", i);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return i * 2;
    }
}
