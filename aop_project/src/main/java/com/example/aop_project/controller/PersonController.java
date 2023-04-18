package com.example.aop_project.controller;


import com.example.aop_project.entity.Person;
import com.example.aop_project.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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

    @PostMapping("addPerson")
    public void addPerson(@RequestBody Person person) {
        log.info(person.toString());
        personService.save(person);
    }

    @GetMapping("getAll")
    public List<Person> getAll() {
        return personService.list().stream().peek(item -> item.setSex(item.getGender().toString())).collect(Collectors.toList());
    }

    @GetMapping("/testGetRequestHeader")
    public String testGetRequestHeader(@RequestHeader("userId") Long userId) {
        log.info("userId={}", userId);
        return "OK";
    }
}

