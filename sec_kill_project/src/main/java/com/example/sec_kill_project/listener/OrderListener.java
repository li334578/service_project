package com.example.sec_kill_project.listener;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.example.sec_kill_project.dto.PurchaseDto;
import com.example.sec_kill_project.pojo.Goods;
import com.example.sec_kill_project.pojo.Order;
import com.example.sec_kill_project.service.GoodsService;
import com.example.sec_kill_project.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Date 10/2/2023 0010 上午 9:42
 * @Description 订单mq监听器
 * @Version 1.0.0
 * @Author liwenbo
 */
@Component
@RocketMQMessageListener(topic = "order-topic", consumerGroup = "my-group", consumeMode = ConsumeMode.ORDERLY, selectorExpression = "*")
@Slf4j
public class OrderListener implements RocketMQListener<PurchaseDto> {

    @Resource
    private OrderService orderService;
    @Resource
    private GoodsService goodsService;
    @Resource
    private Redisson redisson;

    private static final String ORDER_NUMBER_KEY = "order:number:key";

    private static final String ORDER_NUMBER_PREFIX = "DD";

    @Override
    public void onMessage(PurchaseDto purchaseDto) {
        // 处理抢购信息成订单
        Order order = new Order();
        // 默认已付款
        order.setStatus(2);
        Goods goods = goodsService.getById(purchaseDto.getGoodsId());
        if (Objects.nonNull(goods)) {
            order.setTotalMoney(goods.getSecKillPrice());
        }
        order.setUserId(purchaseDto.getUserId());
        order.setCreateTime(DateUtil.date());
        order.setUpdateTime(DateUtil.date());
        // 订单号
        RBucket<String> orderNumberBucket = redisson.getBucket(ORDER_NUMBER_KEY);
        String orderNumberPrefix = ORDER_NUMBER_PREFIX + DateUtil.format(DateUtil.date(), DatePattern.PURE_DATE_PATTERN);
        String regexString = orderNumberPrefix + "(\\d{3})";
        String orderNumberValue = orderNumberBucket.get();
        String orderNumber;
        if (StrUtil.isEmpty(orderNumberValue) || !ReUtil.isMatch(regexString, orderNumberValue)) {
            // 生成新的订单号
            orderNumber = orderNumberPrefix + "001";
        } else {
            // 累加
            String g1 = ReUtil.getGroup1(regexString, orderNumberValue);
            String number = NumberUtil.decimalFormat("000", Integer.parseInt(g1) + 1);
            orderNumber = orderNumberPrefix + number;
        }
        orderNumberBucket.set(orderNumber);
        order.setOrderNum(orderNumber);
        log.info("收到消息处理完毕--{}", orderNumber);
        orderService.save(order);
    }
}
