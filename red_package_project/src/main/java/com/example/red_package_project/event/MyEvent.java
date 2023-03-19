package com.example.red_package_project.event;

import org.springframework.context.ApplicationEvent;

/**
 * @ClassName MyEvent
 * @Description 自定义事件
 * @Author Wenbo Li
 * @Date 19/3/2023 上午 11:04
 * @Version 1.0
 */
public class MyEvent extends ApplicationEvent {

    private String name;

    public MyEvent(Object source) {
        super(source);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
