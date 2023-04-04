package com.example.aop_project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.aop_project.entity.Customer;

import java.io.IOException;
import java.util.List;

/**
 * 客户表(Customer)表服务接口
 *
 * @author liwenbo
 * @since 2023-04-04 10:21:27
 */
public interface CustomerService extends IService<Customer> {

    List<Customer> queryAllCustomer();

    void addCustomer(Customer customer) throws IOException;
}

