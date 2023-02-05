package com.example.seata_project_2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.seata_project_2.dto.AccountDTO;
import com.example.seata_project_2.mapper.AccountMapper;
import com.example.seata_project_2.pojo.Account;
import com.example.seata_project_2.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @ClassName AccountServiceImpl
 * @Description TODO
 * @Author Wenbo Li
 * @Date 5/2/2023 上午 11:06
 * @Version 1.0
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
    /**
     * 扣减账户余额
     *
     * @param accountDTO 账户信息
     * @return 扣减结果 1 扣减成功 2 扣减失败 余额不足 3 扣减失败 其他异常
     */
    @Override
    public Integer decreaseAccount(AccountDTO accountDTO) {
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", accountDTO.getUserId());
        Account dbAccount = this.getOne(queryWrapper);
        if (Objects.isNull(dbAccount)) {
            // 账户不存在
            return 3;
        }
        if (dbAccount.getMoney() < accountDTO.getAmount()) {
            return 2;
        }
        dbAccount.setMoney(dbAccount.getMoney() - accountDTO.getAmount());
        this.updateById(dbAccount);
        return 1;
    }
}
