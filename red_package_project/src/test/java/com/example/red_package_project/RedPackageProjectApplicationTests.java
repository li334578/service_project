package com.example.red_package_project;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.example.red_package_project.pojo.DelayTask;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.function.Function;
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

    volatile Queue<MockUser> userQueue = new LinkedBlockingQueue<>(10);
    volatile Queue<MockCar> carQueue = new LinkedBlockingQueue<>(15);

    @Test
    public void testMethod3() {
        MockUserService mockUserService = new MockUserService();
        MockCarService mockCarService = new MockCarService();

        new Thread(() -> {
            int k = 1;
            for (int i = 1; i <= 100; i++) {
                log.info("入队了 i= {}", i);
                while (!userQueue.offer(new MockUser(i, "用户" + i, RandomUtil.randomInt(100)))) {
                    try {
                        // 入队失败休息一下
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                // 随机 0-3 车
                for (int j = 0; j < RandomUtil.randomInt(0, 3); j++) {
                    while (!carQueue.offer(new MockCar(++k, "宝马" + k, "红色", i))) {
                        // 入队失败休息一下
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }).start();

        new Thread(() -> {
            for (; ; ) {
                // 出队保存
                MockUser user = userQueue.poll();
                if (Objects.nonNull(user) && !batchProcess(mockUserService::save, user)) {
                    // 重新入队
                    userQueue.offer(user);
                }
                // 出队保存
                MockCar car = carQueue.poll();
                if (Objects.nonNull(car) && batchProcess(mockCarService::save, car)) {
                    carQueue.offer(car);
                }
            }

        }).start();


        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    public <T, R> R batchProcess(Function<T, R> function, T t) {
        return function.apply(t);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class MockUser {
    private Integer id;
    private String name;
    private Integer age;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class MockCar {
    private Integer id;
    private String name;
    private String color;
    private Integer userId;
}

@Slf4j
class MockUserService implements MockInterface<MockUser> {
    @Override
    public Boolean save(MockUser user) {
        // 随机数判断奇偶数决定成功or失败
        int value = RandomUtil.randomInt(0, 10);
        if (value >>> 1 << 1 == value) {
            // 保存成功
            log.info("保存成功{}", user);
            return true;
        } else {
            // 保存失败
            log.error("保存失败{}", user);
            return false;
        }
    }

    @Override
    public Boolean saveBatch(Collection<MockUser> users) {
        int value = RandomUtil.randomInt(0, 10);
        if (value >>> 1 << 1 == value) {
            // 保存成功
            log.info("保存成功{}", users);
            return true;
        } else {
            // 保存失败
            log.error("保存失败{}", users);
            return false;
        }
    }
}

@Slf4j
class MockCarService implements MockInterface<MockCar> {
    @Override
    public Boolean save(MockCar car) {
        // 随机数判断奇偶数决定成功or失败
        int value = RandomUtil.randomInt(0, 10);
        if (value >>> 1 << 1 == value) {
            // 保存成功
            log.info("保存成功{}", car);
            return true;
        } else {
            // 保存失败
            log.error("保存失败{}", car);
            return false;
        }
    }

    @Override
    public Boolean saveBatch(Collection<MockCar> cars) {
        int value = RandomUtil.randomInt(0, 10);
        if (value >>> 1 << 1 == value) {
            // 保存成功
            log.info("保存成功{}", cars);
            return true;
        } else {
            // 保存失败
            log.error("保存失败{}", cars);
            return false;
        }
    }
}


interface MockInterface<T> {
    Boolean saveBatch(Collection<T> data);

    Boolean save(T data);
}