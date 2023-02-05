package com.example.seata_project_2.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AccountDTO implements Serializable {

    private Integer id;

    private String userId;

    private Integer amount;
}
