package com.example.seata_project_2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.seata_project_2.pojo.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName AccountMapper
 * @Description TODO
 * @Author Wenbo Li
 * @Date 5/2/2023 上午 11:06
 * @Version 1.0
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
