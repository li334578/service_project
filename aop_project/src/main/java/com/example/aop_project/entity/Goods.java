package com.example.aop_project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 货物清单表(Goods)表实体类
 *
 * @author liwenbo
 * @since 2023-04-04 10:21:27
 */
@Data
@TableName("tb_goods")
public class Goods implements Serializable {
    private static final long serialVersionUID = 1L;
    //自增id
    @TableId(type = IdType.AUTO)
    private Long id;
    //客户名称
    private String goodsName;
    //宽度 单位mm
    private Double goodsWidth;
    //长度 单位mm
    private Double goodsLength;
    //总数
    private Double total;
    //面积 单位平方米
    private Double area;
    //单价
    private Double goodsPrice;
    //加工需求
    private String processingRequirements;
    //加工费用
    private Double processingExpenses;
    //总金额
    private Double totalMoney;
    //备注
    private String remark;
    //生产流程
    private String productionProcess;
    //关联订单id
    private Long orderId;
}

