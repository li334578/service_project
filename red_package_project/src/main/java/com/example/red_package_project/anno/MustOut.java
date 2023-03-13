package com.example.red_package_project.anno;

import com.example.red_package_project.validator.MustOutValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Date 13/3/2023 0013 下午 4:02
 * @Description 必须不在列表中
 * @Version 1.0.0
 * @Author liwenbo
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = MustOutValidator.class)
@Documented
public @interface MustOut {
    String message() default "必须不在列表中";

    Class<?>[] groups() default {};

    Class<? extends Payload[]>[] payload() default {};

    String[] scopeList();
}
