package com.example.sec_kill_project.dto;

public enum ErrorCode {

    /**
     * 错误码集合
     * 1~9999 为保留错误码 或者 常用错误码
     * 10000~19999 为内部错误码
     * 20000~29999 客户端错误码 （客户端异常调用之类的错误）
     * 30000~39999 为第三方错误码 （代码正常，但是第三方异常）
     * 40000~49999 为业务逻辑 错误码 （无异常，代码正常流转，并返回提示给用户）
     * 由于系统内的错误码都是独一无二的，所以错误码应该放在common包集中管理
     */
    // -------------- 普通错误码 及保留错误码 ---------------
    SUCCESS(0, "操作成功"),
    FAIL(9999, "操作失败"),
    ILLEGAL_ARGUMENT(100, "非法参数异常"),
    TIME_OUT(101, "请求超时，请重新请求"),
    SALE_EMPTY(102, "商品已经售空"),


    UNKNOWN_ERROR(99999, "未知错误");

    private final int code;
    private final String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 40000~49999 为业务逻辑 错误码 （无代码异常，代码正常流转，并返回提示给用户）
     */
    public enum Business {

    }

}
