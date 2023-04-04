package com.example.aop_project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 客户表(Customer)表实体类
 *
 * @author liwenbo
 * @since 2023-04-04 10:21:26
 */
@Data
@TableName("tb_customer")
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    //自增id
    @TableId(type = IdType.AUTO)
    private Long id;
    //客户名称
    private String customerName;
    //客户手机号
    private String customerPhone;
    //客户地址
    private String customerAddress;
}

