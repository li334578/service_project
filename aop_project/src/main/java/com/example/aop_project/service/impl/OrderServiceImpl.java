package com.example.aop_project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.aop_project.mapper.OrderMapper;
import com.example.aop_project.entity.Order;
import com.example.aop_project.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * 订单表(Order)表服务实现类
 *
 * @author liwenbo
 * @since 2023-04-04 10:21:28
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}

