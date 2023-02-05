package com.example.seata_project_1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.seata_project_1.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName OrderMapper
 * @Description TODO
 * @Author Wenbo Li
 * @Date 5/2/2023 上午 10:38
 * @Version 1.0
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
