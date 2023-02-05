package com.example.seata_project_1.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountDTO implements Serializable {

    private String userId;

    private Integer amount;
}
