package com.example.seata_project_1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.seata_project_1.dto.OrderDTO;
import com.example.seata_project_1.mapper.StorageMapper;
import com.example.seata_project_1.pojo.Storage;
import com.example.seata_project_1.service.StorageService;
import org.springframework.stereotype.Service;

/**
 * @ClassName StorageServiceImpl
 * @Description TODO
 * @Author Wenbo Li
 * @Date 5/2/2023 上午 10:40
 * @Version 1.0
 */
@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements StorageService {
    @Override
    public Storage getStorageByCommodityCode(OrderDTO orderDTO) {
        QueryWrapper<Storage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("commodity_code", orderDTO.getCommodityCode());
        return getOne(queryWrapper);
    }
}
