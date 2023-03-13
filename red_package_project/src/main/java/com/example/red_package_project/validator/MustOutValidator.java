package com.example.red_package_project.validator;

import com.example.red_package_project.anno.MustOut;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Date 13/3/2023 0013 下午 4:03
 * @Description 必须不在列表中
 * @Version 1.0.0
 * @Author liwenbo
 */
public class MustOutValidator implements ConstraintValidator<MustOut, String> {

    private Set<String> scopeList;

    @Override
    public void initialize(MustOut constraintAnnotation) {
        this.scopeList = new HashSet<>(Arrays.asList(constraintAnnotation.scopeList()));
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Objects.nonNull(value) && !this.scopeList.contains(value);
    }
}
