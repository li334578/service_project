package com.example.seata_project_1.dto;

import java.io.Serializable;

public class ObjectResponse<T> extends BaseResponse implements Serializable {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}