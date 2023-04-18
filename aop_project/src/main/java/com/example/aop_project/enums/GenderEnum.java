package com.example.aop_project.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @Date 10/4/2023 0010 下午 2:37
 * @Description TODO
 * @Version 1.0.0
 * @Author liwenbo
 */
@JsonDeserialize(using = IEnumDeserializer.class)
@JsonSerialize(using = IEnumSerializer.class)
public enum GenderEnum implements IEnum<Integer> {

    MAN(1, "男"),
    WOMAN(2, "女"),
    UN_KNOWN(3, "未知");

    /**
     * value
     */
    private final int value;

    /**
     * desc
     */
    private final String desc;

    GenderEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return desc;
    }
}
