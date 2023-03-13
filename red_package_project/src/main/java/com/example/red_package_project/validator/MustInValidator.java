package com.example.red_package_project.validator;

import com.example.red_package_project.anno.MustIn;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Date 13/3/2023 0013 下午 3:56
 * @Description 必须在列表中
 * @Version 1.0.0
 * @Author liwenbo
 */
public class MustInValidator implements ConstraintValidator<MustIn, String> {

    private Set<String> scopeList;

    @Override
    public void initialize(MustIn constraintAnnotation) {
        this.scopeList = new HashSet<>(Arrays.asList(constraintAnnotation.scopeList()));
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.scopeList.contains(value);
    }
}
