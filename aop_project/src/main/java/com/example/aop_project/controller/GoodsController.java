package com.example.aop_project.controller;


import com.example.aop_project.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 货物清单表(Goods)表控制层
 *
 * @author liwenbo
 * @since 2023-04-04 10:21:27
 */
@RestController
@RequestMapping("/goods/")
@Slf4j
public class GoodsController {
    /**
     * 服务对象
     */
    @Resource
    private GoodsService goodsService;
}

