package com.example.aop_project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单表(Order)表实体类
 *
 * @author liwenbo
 * @since 2023-04-04 10:21:28
 */
@Data
@TableName("tb_order")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    //自增id
    @TableId(type = IdType.AUTO)
    private Long id;
    //客户id
    private Long customerId;
    //客户名称
    private String customerName;
    //客户手机号
    private String customerPhone;
    //客户地址
    private String customerAddress;
    //订单编号
    private String orderNumber;
    //制单人
    private String createUserName;
    //订单日期
    private Date createTime;
    //金额
    private Double money;
    //已收款金额
    private Double receiveMoney;
    //总面积
    private Double totalArea;
    //备注
    private String remark;
    //付款状态
    private Integer payStatus;
    //发货状态
    private Integer sendStatus;
}

