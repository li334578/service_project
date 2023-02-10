package com.example.sec_kill_project.dto;

import lombok.Data;

/**
 * @Date 10/2/2023 0010 上午 10:35
 * @Description 商品请求
 * @Version 1.0.0
 * @Author liwenbo
 */
@Data
public class PurchaseDto {
    /**
     * 用户购买的商品id
     */
    private Long goodsId;
    /**
     * 用户id
     */
    private Long userId;
}
