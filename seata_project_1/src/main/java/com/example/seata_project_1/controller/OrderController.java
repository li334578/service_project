package com.example.seata_project_1.controller;

import com.example.seata_project_1.dto.ObjectResponse;
import com.example.seata_project_1.dto.OrderDTO;
import com.example.seata_project_1.dto.RspStatusEnum;
import com.example.seata_project_1.pojo.Order;
import com.example.seata_project_1.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @GetMapping("list")
    public ObjectResponse<List<Order>> listOrder() {
        ObjectResponse<List<Order>> response = new ObjectResponse<>();
        response.setStatus(RspStatusEnum.SUCCESS.getCode());
        response.setData(orderService.list());
        return response;
    }

    @PostMapping("add")
    public ObjectResponse<OrderDTO> addOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }
}
