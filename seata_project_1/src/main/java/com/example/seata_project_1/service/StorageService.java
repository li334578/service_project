package com.example.seata_project_1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.seata_project_1.dto.OrderDTO;
import com.example.seata_project_1.pojo.Storage;

/**
 * @ClassName StorageService
 * @Description TODO
 * @Author Wenbo Li
 * @Date 5/2/2023 上午 10:40
 * @Version 1.0
 */
public interface StorageService extends IService<Storage> {

    Storage getStorageByCommodityCode(OrderDTO orderDTO);
}
