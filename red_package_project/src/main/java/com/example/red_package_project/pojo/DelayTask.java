package com.example.red_package_project.pojo;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Date 15/3/2023 0015 上午 9:11
 * @Description 延时队列的任务
 * @Version 1.0.0
 * @Author liwenbo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DelayTask implements Delayed {

    private Integer id;

    private String orderNumber;

    private Date createTime;
    /**
     * 延时时间 单位 s
     */
    private Integer delaySeconds;

    @Override
    public long getDelay(TimeUnit unit) {
        long between = DateUtil.between(this.createTime, DateUtil.date(), DateUnit.SECOND);
        return unit.convert(delaySeconds - between, TimeUnit.SECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.SECONDS) - o.getDelay(TimeUnit.SECONDS));
    }
}
