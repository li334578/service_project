package com.example.aop_project.controller;


import com.example.aop_project.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 订单表(Order)表控制层
 *
 * @author liwenbo
 * @since 2023-04-04 10:21:28
 */
@RestController
@RequestMapping("/order/")
@Slf4j
public class OrderController {
    /**
     * 服务对象
     */
    @Resource
    private OrderService orderService;
}

