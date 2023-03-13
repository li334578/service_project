package com.example.red_package_project.validator;

/**
 * @Date 13/3/2023 0013 下午 3:43
 * @Description TODO
 * @Version 1.0.0
 * @Author liwenbo
 */

import com.example.red_package_project.anno.Gender;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 实现ConstraintValidator接口并提供校验规则
 */
public class GenderValidator implements ConstraintValidator<Gender, String> {

    //初始化校验值
    @Override
    public void initialize(Gender constraintAnnotation) {

    }

    //检验规则
    @Override
    public boolean isValid(String value, ConstraintValidatorContext content) {
        return "男".equals(value) || "女".equals(value);
    }
}