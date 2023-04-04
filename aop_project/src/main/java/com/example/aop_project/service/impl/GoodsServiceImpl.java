package com.example.aop_project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.aop_project.mapper.GoodsMapper;
import com.example.aop_project.entity.Goods;
import com.example.aop_project.service.GoodsService;
import org.springframework.stereotype.Service;

/**
 * 货物清单表(Goods)表服务实现类
 *
 * @author liwenbo
 * @since 2023-04-04 10:21:27
 */
@Service("goodsService")
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

}

