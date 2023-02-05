package com.example.seata_project_1.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName OrderDTO
 * @Description 订单信息
 * @Author Wenbo Li
 * @Date 5/2/2023 上午 10:43
 * @Version 1.0
 */
@Data
public class OrderDTO implements Serializable {
    private String orderNo;

    private String userId;

    private String commodityCode;

    private Integer orderCount;

    private Integer orderAmount;
}
