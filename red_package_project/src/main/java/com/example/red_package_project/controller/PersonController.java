package com.example.red_package_project.controller;

import com.example.red_package_project.pojo.Person;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Date 13/3/2023 0013 下午 3:22
 * @Description 测试validation
 * @Version 1.0.0
 * @Author liwenbo
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    @PostMapping("/add")
    public void addPerson(@RequestBody @Valid Person person) {
        System.out.println(person);
    }
}
