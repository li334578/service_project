package com.example.aop_project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.aop_project.enums.GenderEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * (Person)表实体类
 *
 * @author liwenbo
 * @since 2023-04-10 14:35:12
 */
@Data
@TableName("tb_person")
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    //自增id
    @TableId(type = IdType.AUTO)
    private Long id;
    //姓名
    private String name;
    //性别 1 男 2 女 3未知
    private GenderEnum gender;
    //手机号
    private String phone;
}

