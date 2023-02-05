package com.example.seata_project_1.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName Order
 * @Description TODO
 * @Author Wenbo Li
 * @Date 5/2/2023 上午 10:34
 * @Version 1.0
 */
@Data
@TableName("order_tbl")
public class Order {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String userId;
    private String commodityCode;
    private Integer count;
    private Integer money;

}
