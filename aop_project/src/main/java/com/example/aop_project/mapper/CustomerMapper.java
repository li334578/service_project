package com.example.aop_project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.aop_project.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户表(Customer)表数据库访问层
 *
 * @author liwenbo
 * @since 2023-04-04 10:21:26
 */
@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {

}

