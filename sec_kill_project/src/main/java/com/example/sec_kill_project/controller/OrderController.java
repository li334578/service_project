package com.example.sec_kill_project.controller;

import com.example.sec_kill_project.dto.ErrorCode;
import com.example.sec_kill_project.dto.PurchaseDto;
import com.example.sec_kill_project.dto.ResponseDTO;
import com.example.sec_kill_project.service.GoodsService;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.redisson.Redisson;
import org.redisson.api.BatchOptions;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Date 9/2/2023 0009 下午 5:07
 * @Description 订单controller
 * @Version 1.0.0
 * @Author liwenbo
 */
@RestController
@RequestMapping("/order/")
@Slf4j
public class OrderController {

    @Resource
    private Redisson redisson;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    private static final String MY_TOPIC = "order-topic";

    /**
     * 基于令牌桶算法的限流实现类
     */
    RateLimiter rateLimiter = RateLimiter.create(10);

    /**
     * guava 缓存层
     */
    Cache<String, Boolean> goodsStockCache = CacheBuilder.newBuilder().build();

    private static final String GOODS_PREFIX = "sec:kill:goods:key:";

    @GetMapping("initial")
    public ResponseDTO<Void> initialGoodsInfo(@RequestParam("number") Integer number,
                                              @RequestParam("stocks") Integer... stocks) {
        if (!Objects.equals(number, stocks.length)) {
            return ResponseDTO.fail(ErrorCode.ILLEGAL_ARGUMENT);
        }
        for (int i = 1; i <= number; i++) {
            String key = GOODS_PREFIX + i;
            RBucket<Object> bucket = redisson.getBucket(key);
            bucket.set(stocks[i - 1]);
            // 内存标记 减少redis访问
            goodsStockCache.put(key, false);
        }
        return ResponseDTO.ok();
    }

    @GetMapping("clear")
    public ResponseDTO<Void> clearGoodsInfo() {
        goodsStockCache.invalidateAll();
        redisson.getKeys().deleteByPattern(GOODS_PREFIX + "\\d+");
        return ResponseDTO.ok();
    }

    public boolean method(String key) throws InterruptedException {
        RLock lock = redisson.getLock("lock:" + key);
        try {
            if (!goodsStockCache.get(key, () -> true) && lock.tryLock(1000, TimeUnit.MILLISECONDS) && !goodsStockCache.get(key, () -> true)) {
                RBucket<Integer> stockBucket = redisson.getBucket(key);
                int value = stockBucket.get() - 1;
                stockBucket.set(value);
                if (value <= 0) {
                    goodsStockCache.put(key, true);
                }
                return true;
            }
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return false;
    }

    @PutMapping("purchase")
    public ResponseDTO purchaseGoods(@RequestBody PurchaseDto purchaseDto) throws ExecutionException, InterruptedException {
        // 购买商品
        if (!rateLimiter.tryAcquire(1000, TimeUnit.MILLISECONDS)) {
            return ResponseDTO.fail(ErrorCode.TIME_OUT);
        }
        String key = GOODS_PREFIX + purchaseDto.getGoodsId();
        if (goodsStockCache.get(key, () -> false)) {
            // 商品已经没有库存
            return ResponseDTO.fail(ErrorCode.SALE_EMPTY);
        }
        // 走到这说明有库存  抢购
        // 1.预减库存
        int count = 0;
        while (count < 5 && !method(key)) {
            count++;
            TimeUnit.MILLISECONDS.sleep(120);
        }
        if (count == 5) {
            // 自旋五次还没成功
            return ResponseDTO.fail(ErrorCode.TIME_OUT);
        }

        SendCallback back = new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("发送成功");
            }

            @Override
            public void onException(Throwable e) {
                // 发送失败 重试
                log.error("消息发送失败了,请手动处理====", e);
            }
        };
        // 通知MQ处理成订单信息落库
        // 异步发送
        rocketMQTemplate.asyncSend(MY_TOPIC, purchaseDto, back);
        return ResponseDTO.ok(purchaseDto);
    }

}
