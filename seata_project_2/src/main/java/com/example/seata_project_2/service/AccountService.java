package com.example.seata_project_2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.seata_project_2.dto.AccountDTO;
import com.example.seata_project_2.pojo.Account;

/**
 * @ClassName AccountService
 * @Description TODO
 * @Author Wenbo Li
 * @Date 5/2/2023 上午 11:05
 * @Version 1.0
 */
public interface AccountService extends IService<Account> {

    /**
     * 扣减账户余额
     *
     * @param accountDTO 账户信息
     * @return 扣减结果 1 扣减成功 2 扣减失败 余额不足 3 扣减失败 其他异常
     */
    Integer decreaseAccount(AccountDTO accountDTO);
}
