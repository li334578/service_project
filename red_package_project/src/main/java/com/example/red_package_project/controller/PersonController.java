package com.example.red_package_project.controller;

import com.example.red_package_project.event.MyEvent;
import com.example.red_package_project.pojo.Person;
import com.example.red_package_project.pojo.SingletonEhan;
import com.example.red_package_project.pojo.SingletonInnerStatic;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @Resource
    private ApplicationEventPublisher publisher;

    @PostMapping("/add")
    public void addPerson(@RequestBody @Valid Person person) {
        System.out.println(person);
    }

    @GetMapping("/publish/{name}")
    public String publishEvent(@PathVariable("name") String name) {
        Person person = new Person();
        MyEvent event = new MyEvent(person);
        event.setName(name);
        publisher.publishEvent(event);
        return "OK";
    }

    @GetMapping("/testInstance")
    public String testInstance() {
        SingletonEhan instance = SingletonEhan.getInstance();
        SingletonInnerStatic instance1 = SingletonInnerStatic.getInstance();
        return "OK";
    }

    @GetMapping("/testInstanceMyMethod")
    public String testInstanceMyMethod() {
        SingletonEhan.myMethod();
        SingletonInnerStatic.myMethod();
        return "OK";
    }
}
