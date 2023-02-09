package com.example.sec_kill_project.dto;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO<T> {

    private Integer code;

    private String msg;

    private T data;

    public static <T> ResponseDTO<T> ok() {
        return build(null, ErrorCode.SUCCESS);
    }

    public static <T> ResponseDTO<T> ok(T data) {
        return build(data, ErrorCode.SUCCESS);
    }

    public static <T> ResponseDTO<T> fail() {
        return build(null, ErrorCode.FAIL);
    }

    public static <T> ResponseDTO<T> fail(T data) {
        return build(data, ErrorCode.FAIL);
    }

    public static <T> ResponseDTO<T> fail(ErrorCode code) {
        return build(null, code);
    }

    public static <T> ResponseDTO<T> fail(ErrorCode code, Object... args) {
        return build(null, code, args);
    }


    public static <T> ResponseDTO<T> build(Integer code, String msg) {
        return new ResponseDTO<>(code, msg, null);
    }

    public static <T> ResponseDTO<T> build(Integer code, String msg, T data) {
        return new ResponseDTO<>(code, msg, data);
    }

    public static <T> ResponseDTO<T> build(T data, ErrorCode code, Object... args) {
        return new ResponseDTO<>(code.getCode(), StrUtil.format(code.getMsg(), args), data);
    }

}