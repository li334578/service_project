package com.example.seata_project_2.controller;

import cn.hutool.core.util.IdUtil;
import com.example.seata_project_2.dto.AccountDTO;
import com.example.seata_project_2.dto.ObjectResponse;
import com.example.seata_project_2.dto.RspStatusEnum;
import com.example.seata_project_2.pojo.Account;
import com.example.seata_project_2.service.AccountService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName AccountController
 * @Description TODO
 * @Author Wenbo Li
 * @Date 5/2/2023 上午 11:14
 * @Version 1.0
 */
@RestController
@RequestMapping("/account/")
public class AccountController {

    @Resource
    AccountService accountService;

    @GetMapping("/add/{money}")
    public void addAccount(@PathVariable("money") Integer money) {
        Account account = new Account();
        account.setUserId(IdUtil.fastSimpleUUID());
        account.setMoney(money);
        accountService.save(account);
    }

    @PostMapping("decrease")
    public ObjectResponse decreaseAccount(@RequestBody AccountDTO accountDTO) {
        ObjectResponse response = new ObjectResponse();
        Integer resultCode = accountService.decreaseAccount(accountDTO);
        if (resultCode == 1) {
            response.setMessage("扣减成功");
            response.setStatus(RspStatusEnum.SUCCESS.getCode());
        } else {
            response.setMessage(resultCode == 2 ? "余额不足扣减失败" : "账户不存在或其他异常");
            response.setStatus(RspStatusEnum.FAIL.getCode());
        }
        return response;
    }
}
