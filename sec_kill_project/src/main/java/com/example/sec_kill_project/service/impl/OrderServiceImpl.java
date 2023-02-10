package com.example.sec_kill_project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sec_kill_project.mapper.OrderMapper;
import com.example.sec_kill_project.pojo.Order;
import com.example.sec_kill_project.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @Date 10/2/2023 0010 上午 9:38
 * @Description 订单service实现类
 * @Version 1.0.0
 * @Author liwenbo
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
}
