package com.example.aop_project;

import com.example.aop_project.entity.Person;
import com.example.aop_project.enums.GenderEnum;
import com.example.aop_project.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class AopProjectApplicationTests {

    @Resource
    private PersonService personService;

    @Test
    void contextLoads() {
    }


    @Test
    public void testMethod1() {
        Person p = new Person();
        p.setName("张三");
        p.setPhone("18888888888");
        p.setGender(GenderEnum.MAN);

        Person p2 = new Person();
        p2.setName("李四");
        p2.setPhone("18888888888");
        p2.setGender(GenderEnum.WOMAN);

        Person p3 = new Person();
        p3.setName("王五");
        p3.setPhone("18888888888");
        p3.setGender(GenderEnum.UN_KNOWN);

        personService.saveBatch(Arrays.asList(p, p2, p3));
        List<Person> personList = personService.list();
        System.out.println(personList);
    }
}
