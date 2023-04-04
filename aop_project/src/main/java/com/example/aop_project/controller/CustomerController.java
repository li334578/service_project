package com.example.aop_project.controller;


import com.example.aop_project.entity.Customer;
import com.example.aop_project.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * 客户表(Customer)表控制层
 *
 * @author liwenbo
 * @since 2023-04-04 10:21:26
 */
@RestController
@RequestMapping("/customer/")
@Slf4j
public class CustomerController {
    /**
     * 服务对象
     */
    @Resource
    private CustomerService customerService;

    @GetMapping("list")
    public String queryAllCustomer() {
        List<Customer> customers = customerService.queryAllCustomer();
        customers.forEach(System.out::println);
        return "OK";
    }

    @PostMapping("add")
    public String addCustomer(@RequestBody Customer customer) {
        try {
            customerService.addCustomer(customer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "OK";
    }
}

