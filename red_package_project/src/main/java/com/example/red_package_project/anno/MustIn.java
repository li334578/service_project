package com.example.red_package_project.anno;

import com.example.red_package_project.validator.MustInValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Date 13/3/2023 0013 下午 3:48
 * @Description 必须在列表中
 * @Version 1.0.0
 * @Author liwenbo
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = MustInValidator.class)
@Documented
public @interface MustIn {
    String message() default "必须在列表中";

    Class<?>[] groups() default {};

    Class<? extends Payload[]>[] payload() default {};

    String[] scopeList();
}
