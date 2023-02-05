package com.example.seata_project_1.controller;

import com.example.seata_project_1.dto.ObjectResponse;
import com.example.seata_project_1.dto.OrderDTO;
import com.example.seata_project_1.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author Wenbo Li
 * @Date 5/2/2023 上午 11:28
 * @Version 1.0
 */
@RestController
@RequestMapping("/order/")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("add")
    public ObjectResponse<OrderDTO> addOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }
}
