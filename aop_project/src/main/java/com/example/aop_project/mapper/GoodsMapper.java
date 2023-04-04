package com.example.aop_project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.aop_project.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

/**
 * 货物清单表(Goods)表数据库访问层
 *
 * @author liwenbo
 * @since 2023-04-04 10:21:27
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

}

