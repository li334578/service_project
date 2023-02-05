package com.example.seata_project_2;

import cn.hutool.core.util.IdUtil;
import com.example.seata_project_2.pojo.Account;
import com.example.seata_project_2.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class SeataProject2ApplicationTests {

    @Resource
    private AccountService accountService;

    @Test
    void contextLoads() {
    }


    @Test
    public void testMethod1() {
        Account account = new Account();
        account.setUserId(IdUtil.fastSimpleUUID());
        account.setMoney(100);
        accountService.save(account);
    }
}
