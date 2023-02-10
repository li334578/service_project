package com.example.sec_kill_project.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Date 10/2/2023 0010 上午 9:15
 * @Description 商品信息
 * @Version 1.0.0
 * @Author liwenbo
 */
@Data
@TableName("tbl_goods")
public class Goods {
    /**
     * 商品id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品标题
     */
    private String goodsTitle;
    /**
     * 商品日常价格
     */
    private BigDecimal goodsPrice;
    /**
     * 商品秒杀价格
     */
    private BigDecimal secKillPrice;
    /**
     * 商品库存数量
     */
    private Integer stockCount;
}
