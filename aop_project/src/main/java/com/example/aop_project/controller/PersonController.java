package com.example.aop_project.controller;


import com.example.aop_project.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Person)表控制层
 *
 * @author liwenbo
 * @since 2023-04-10 14:35:12
 */
@RestController
@RequestMapping("/person/")
@Slf4j
public class PersonController {
    /**
     * 服务对象
     */
    @Resource
    private PersonService personService;
}

