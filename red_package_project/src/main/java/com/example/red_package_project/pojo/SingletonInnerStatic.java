package com.example.red_package_project.pojo;

import lombok.extern.slf4j.Slf4j;

/**
 * @Date 21/3/2023 0021 上午 10:56
 * @Description 内部类单例
 * @Version 1.0.0
 * @Author liwenbo
 */
@Slf4j
public class SingletonInnerStatic {
    private SingletonInnerStatic() {
        log.info("外部函数的构造执行了");
    }

    private static class SingletonHolder {

        private SingletonHolder() {
            log.info("内部函数的构造执行了");
        }

        private static final SingletonInnerStatic INSTANCE = new SingletonInnerStatic();
    }

    public static SingletonInnerStatic getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static void myMethod() {
        log.info("调用静态方法了");
    }
}
