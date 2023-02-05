package com.example.seata_project_1.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName storage
 * @Description TODO
 * @Author Wenbo Li
 * @Date 5/2/2023 上午 10:37
 * @Version 1.0
 */
@Data
@TableName("storage_tbl")
public class Storage {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String commodityCode;
    private Integer count;

}
