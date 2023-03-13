package com.example.red_package_project.anno;

import com.example.red_package_project.validator.GenderValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 定义校验注解
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = GenderValidator.class)
@Documented
public @interface Gender {

    String message() default "性别为男或者女";

    Class<?>[] groups() default {};

    Class<? extends Payload[]>[] payload() default {};

}