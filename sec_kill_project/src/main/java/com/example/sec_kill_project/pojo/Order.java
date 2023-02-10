package com.example.sec_kill_project.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Date 10/2/2023 0010 上午 9:28
 * @Description 订单信息
 * @Version 1.0.0
 * @Author liwenbo
 */
@Data
@TableName("tbl_order")
public class Order {
    /**
     * 订单id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 订单所属用户
     */
    private Long userId;
    /**
     * 订单编号
     */
    private String orderNum;

    /**
     * 订单创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 订单更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    /**
     * 订单状态 1 待付款 2 待发货 3 待收货 4 待评价 5 已完成
     */
    private Integer status;

    /**
     * 订单实付金额
     */
    private BigDecimal totalMoney;
}
