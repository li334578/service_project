package com.example.red_package_project.pojo;

import lombok.extern.slf4j.Slf4j;

/**
 * @Date 21/3/2023 0021 上午 10:59
 * @Description 饿汉式
 * @Version 1.0.0
 * @Author liwenbo
 */
@Slf4j
public class SingletonEhan {
    private static final SingletonEhan singletonEhan = new SingletonEhan();

    private SingletonEhan() {
        log.info("我被调用了");
    }

    public static SingletonEhan getInstance() {
        return singletonEhan;
    }

    public static void myMethod() {
        log.info("调用静态方法了");
    }
}
