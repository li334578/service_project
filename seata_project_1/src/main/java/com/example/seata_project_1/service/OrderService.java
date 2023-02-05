package com.example.seata_project_1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.seata_project_1.dto.ObjectResponse;
import com.example.seata_project_1.dto.OrderDTO;
import com.example.seata_project_1.pojo.Order;

/**
 * @ClassName OrderService
 * @Description TODO
 * @Author Wenbo Li
 * @Date 5/2/2023 上午 10:38
 * @Version 1.0
 */
public interface OrderService extends IService<Order> {
    /**
     * 创建订单
     */
    ObjectResponse<OrderDTO> createOrder(OrderDTO orderDTO);
}
