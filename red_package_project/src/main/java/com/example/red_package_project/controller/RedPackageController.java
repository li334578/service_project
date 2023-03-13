package com.example.red_package_project.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.example.red_package_project.pojo.BaseResponse;
import com.example.red_package_project.pojo.ErrorCode;
import com.example.red_package_project.pojo.ResultUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Date 8/3/2023 0008 下午 2:13
 * @Description 红包controller
 * @Version 1.0.0
 * @Author liwenbo
 */
@RestController
@AllArgsConstructor
@Slf4j
public class RedPackageController {

    private RedisTemplate redisTemplate;

    private static final String RED_PACKAGE_KEY = "readpackage:";
    private static final String RED_PACKAGE_CONSUME_KEY = "readpackage:consume:";

    @GetMapping("/sendRedPackage")
    public BaseResponse<String> sendRedPackage(Integer totalMoney, Integer count) {
        // 对红包进行拆分
        Integer[] arr = splitRedPackage(totalMoney, count);
        for (Integer integer : arr) {
            log.info(String.valueOf(integer));
        }
        String uuid = IdUtil.fastSimpleUUID();
        // 发送人的useId
        redisTemplate.opsForList().leftPushAll(RED_PACKAGE_KEY + uuid, arr);
        redisTemplate.expire(RED_PACKAGE_KEY + uuid, 1, TimeUnit.DAYS);
        return ResultUtils.success(uuid);
    }

    @GetMapping("/robRedPackage")
    public BaseResponse<Object> robRedPackage(String uuid, String userId) {
        // 先看看有没有抢过
        Object o = redisTemplate.opsForHash().get(RED_PACKAGE_CONSUME_KEY + uuid, userId);
        if (Objects.isNull(o)) {
            Object money = redisTemplate.opsForList().leftPop(RED_PACKAGE_KEY + uuid);
            if (Objects.nonNull(money)) {
                // 抢到红包了
                redisTemplate.opsForHash().put(RED_PACKAGE_CONSUME_KEY + uuid, userId, uuid);
                return ResultUtils.success(money, "抢红包成功");
            }else {
                // 抢红包失败
                return ResultUtils.error(ErrorCode.NOT_FOUND_ERROR, "红包已经抢完了");
            }
        }
        return ResultUtils.error(ErrorCode.FORBIDDEN_ERROR, "您已经抢过红包了");
    }

    private static Integer[] splitRedPackage(Integer totalMoney, Integer count) {
        Integer[] result = new Integer[count];
        Integer useMoney = 0;
        for (Integer i = 0; i < count; i++) {
            if (Objects.equals(i, count - 1)) {
                // 最后一个 剩余多少给多少
                result[i] = totalMoney - useMoney;
            } else {
                result[i] = RandomUtil.randomInt(0, ((totalMoney - useMoney) / (count - i)) * 2 - 1) + 1;
                useMoney += result[i];
            }
        }
        return result;
    }

}
