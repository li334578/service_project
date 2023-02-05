package com.example.seata_project_2.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName Account
 * @Description TODO
 * @Author Wenbo Li
 * @Date 5/2/2023 上午 11:04
 * @Version 1.0
 */
@Data
@TableName("account_tbl")
public class Account {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String userId;

    private Integer money;
}
