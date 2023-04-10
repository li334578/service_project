package com.example.aop_project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.aop_project.entity.Person;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Person)表数据库访问层
 *
 * @author liwenbo
 * @since 2023-04-10 14:35:12
 */
@Mapper
public interface PersonMapper extends BaseMapper<Person> {

}

