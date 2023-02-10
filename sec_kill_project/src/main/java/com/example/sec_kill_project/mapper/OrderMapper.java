package com.example.sec_kill_project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sec_kill_project.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Date 10/2/2023 0010 上午 9:37
 * @Description 订单mapper
 * @Version 1.0.0
 * @Author liwenbo
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
