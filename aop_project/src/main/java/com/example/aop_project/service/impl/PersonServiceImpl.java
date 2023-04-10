package com.example.aop_project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.aop_project.mapper.PersonMapper;
import com.example.aop_project.entity.Person;
import com.example.aop_project.service.PersonService;
import org.springframework.stereotype.Service;

/**
 * (Person)表服务实现类
 *
 * @author liwenbo
 * @since 2023-04-10 14:35:13
 */
@Service("personService")
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

}

