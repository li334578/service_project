package com.example.seata_project_1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.seata_project_1.dto.AccountDTO;
import com.example.seata_project_1.dto.ObjectResponse;
import com.example.seata_project_1.dto.OrderDTO;
import com.example.seata_project_1.dto.RspStatusEnum;
import com.example.seata_project_1.feign.AccountService;
import com.example.seata_project_1.mapper.OrderMapper;
import com.example.seata_project_1.pojo.Order;
import com.example.seata_project_1.pojo.Storage;
import com.example.seata_project_1.service.OrderService;
import com.example.seata_project_1.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @Author Wenbo Li
 * @Date 5/2/2023 上午 10:39
 * @Version 1.0
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    /**
     * 创建订单
     *
     * @Param: OrderDTO  订单对象
     * @Return: OrderDTO  订单对象
     */
    @Override
    @GlobalTransactional
    public ObjectResponse<OrderDTO> createOrder(OrderDTO orderDTO) {
        ObjectResponse<OrderDTO> response = new ObjectResponse<>();
        //扣减用户账户
        ObjectResponse objectResponse = decreaseAccount(orderDTO);
        if (objectResponse.getStatus() != 200) {
            throw new RuntimeException("扣减金额失败");
//            response.setStatus(RspStatusEnum.FAIL.getCode());
//            response.setMessage(RspStatusEnum.FAIL.getMessage());
//            return response;
        }
        // 扣减库存 失败回滚
        Storage storage = storageService.getStorageByCommodityCode(orderDTO);
        int a = 1/0;
        if (storage.getCount() < orderDTO.getOrderCount()) {
            // 库存不够
            throw new RuntimeException("库存不足");
//            response.setStatus(RspStatusEnum.FAIL.getCode());
//            response.setMessage(RspStatusEnum.FAIL.getMessage());
//            return response;
        }
        storage.setCount(storage.getCount() - orderDTO.getOrderCount());
        storageService.updateById(storage);

        //生成订单号
        orderDTO.setOrderNo(UUID.randomUUID().toString().replace("-", ""));
        //生成订单
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO, order);
        order.setCount(orderDTO.getOrderCount());
        order.setMoney(orderDTO.getOrderAmount());
        try {
            this.save(order);
        } catch (Exception e) {
            response.setStatus(RspStatusEnum.FAIL.getCode());
            response.setMessage(RspStatusEnum.FAIL.getMessage());
            return response;
        }
        response.setStatus(RspStatusEnum.SUCCESS.getCode());
        response.setMessage(RspStatusEnum.SUCCESS.getMessage());
        return response;
    }

    private ObjectResponse decreaseAccount(OrderDTO orderDTO) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUserId(orderDTO.getUserId());
        accountDTO.setAmount(orderDTO.getOrderAmount());
        return accountService.decreaseAccount(accountDTO);
    }
}
