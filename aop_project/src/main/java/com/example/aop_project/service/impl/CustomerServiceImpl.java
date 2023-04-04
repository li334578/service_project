package com.example.aop_project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.aop_project.entity.Customer;
import com.example.aop_project.entity.Order;
import com.example.aop_project.mapper.CustomerMapper;
import com.example.aop_project.service.CustomerService;
import com.example.aop_project.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * 客户表(Customer)表服务实现类
 *
 * @author liwenbo
 * @since 2023-04-04 10:21:27
 */
@Service("customerService")
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Resource
    private OrderService orderService;

    @Override
    public List<Customer> queryAllCustomer() {
        return this.list();
    }

    @Override
    public void addCustomer(Customer customer) throws IOException {
        this.save(customer);
        if (1 == 1) {
            int a = 1 / 0;
            throw new IOException("io");
        }
        Order order = new Order();
        order.setCustomerId(customer.getId());
        order.setCustomerName(customer.getCustomerName());
        order.setCustomerPhone(customer.getCustomerPhone());
        order.setOrderNumber("测试单号" + customer.getId());
        orderService.save(order);
    }
}

