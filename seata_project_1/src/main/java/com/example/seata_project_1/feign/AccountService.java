package com.example.seata_project_1.feign;

import com.example.seata_project_1.dto.AccountDTO;
import com.example.seata_project_1.dto.ObjectResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @ClassName AccountService
 * @Description TODO
 * @Author Wenbo Li
 * @Date 5/2/2023 上午 11:11
 * @Version 1.0
 */
@FeignClient(name = "seata-project-2")
public interface AccountService {

    @PostMapping("/account/decrease")
    ObjectResponse decreaseAccount(AccountDTO accountDTO);

}
