package com.example.red_package_project.exception;

import com.example.red_package_project.pojo.BaseResponse;
import com.example.red_package_project.pojo.ErrorCode;
import com.example.red_package_project.pojo.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("businessException: " + e.getMessage(), e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("runtimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, e.getMessage());
    }

    /**
     * @Null：元素为null
     * @NotNull：元素不为null
     * @AssertTrue：元素为true
     * @AssertFalse：元素为false
     * @Min(value)：数字的值大于等于指定的最小值
     * @Max(value)：数字的值小于等于指定的最大值
     * @DecimalMin(value)：大数值大于等于指定的最小值
     * @DecimalMax(value)：大数值小于等于指定的最大值
     * @Size(max=,min=)：元素的大小在指定的范围内
     * @Digits(integer,fraction):元素是一个数字，其值必须在可接受的范围内
     * @Past:一个过去的日期
     * @Future:一个将来的日期
     * @Pattern(regex=,flag=):指定的正则表达式
     * @URL:必须是一个URL
     * @Email：必须是email格式
     * @NotBlank:字符串不能为空
     * @NotEmpty：集合不能为空
     * @Length：长度必须在指定范围内
     * @Valid：对实体类进行校验
     */

    /**
     * 捕获方法参数校验异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public BaseResponse<?> constraintViolationExceptionHandler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> message = e.getConstraintViolations();
        HashMap<String, Object> map = new HashMap<>();
        message.forEach(msg -> {
            String path = msg.getPropertyPath().toString();
            String field = path.substring(path.indexOf(".") + 1);
            map.put(field, msg.getMessageTemplate());
        });
        return ResultUtils.error(ErrorCode.PARAMS_ERROR, map.toString());
    }

    /**
     * 捕获实体参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<?> resolveMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        HashMap<String, Object> map = new HashMap<>();
        allErrors.forEach(error -> {
            FieldError fieldError = (FieldError) error;
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return ResultUtils.error(ErrorCode.PARAMS_ERROR, map.toString());
    }
}