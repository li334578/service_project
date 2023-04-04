package com.example.aop_project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.aop_project.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单表(Order)表数据库访问层
 *
 * @author liwenbo
 * @since 2023-04-04 10:21:28
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}

