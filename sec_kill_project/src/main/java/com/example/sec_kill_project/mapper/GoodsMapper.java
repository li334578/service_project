package com.example.sec_kill_project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sec_kill_project.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Date 10/2/2023 0010 上午 9:36
 * @Description 商品mapper
 * @Version 1.0.0
 * @Author liwenbo
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
}
