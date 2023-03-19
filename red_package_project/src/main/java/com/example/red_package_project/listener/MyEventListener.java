package com.example.red_package_project.listener;

import com.example.red_package_project.event.MyEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName MyEventListener
 * @Description 自定义事件监听器
 * @Author Wenbo Li
 * @Date 19/3/2023 上午 11:04
 * @Version 1.0
 */
@Slf4j
@Component
public class MyEventListener implements ApplicationListener<MyEvent> {

    @Override
    public void onApplicationEvent(MyEvent event) {
        Object source = event.getSource();
        log.info("source is {} source class is {}", source, source.getClass());
        log.info("事件成功监听 --> {}", event.getName());
    }
}
