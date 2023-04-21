package com.example.aop_project.service.impl;

import com.example.aop_project.service.RetryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * @Date 21/4/2023 0021 上午 9:05
 * @Description TODO
 * @Version 1.0.0
 * @Author liwenbo
 */
@Service
@Slf4j
public class RetryServiceImpl implements RetryService {

    /*
    几个参数的含义：
    value：抛出指定异常才会重试
    include：和value一样，默认为空，当exclude也为空时，默认所有异常
    exclude：指定不处理的异常
    maxAttempts：最大重试次数，默认3次
    backoff：重试等待策略，默认使用@Backoff，@Backoff的value默认为1000L，我们设置为2000L；
    multiplier（指定延迟倍数）默认为0，表示固定暂停1秒后进行重试，
    如果把multiplier设置为1.5，则第一次重试为2秒，第二次为3秒，第三次为4.5秒。
    * */

    @Override
    @Retryable(value = Exception.class, maxAttempts = 2, backoff = @Backoff(delay = 2000, multiplier = 1.5))
    public String mockRetry(String param) {
        log.info("mockRetry execute");
        int a = 1 / 0;
        return "Very OK";
    }

    @Override
    @Retryable(value = Exception.class, maxAttempts = 2, backoff = @Backoff(delay = 2000, multiplier = 1.5))
    public String mockRetry(String param, Integer numberParam) {
        log.info("mockRetry2 execute");
        int a = 1 / 0;
        return "Very OK 2";
    }

    /*
    传参里面写的是 Exception e，这个是作为回调的接头暗号（重试次数用完了，还是失败，我们抛出这个Exception e通知触发这个回调方法）。
    对于@Recover注解的方法，需要特别注意的是：
    方法的返回值必须与@Retryable方法一致
    方法的第一个参数，必须是Throwable类型的，建议是与@Retryable配置的异常一致，
    其他的参数，需要哪个参数，写进去就可以了（@Recover方法中有的）
    该回调方法与重试方法写在同一个实现类里面
    ---
    由于是基于AOP实现，所以不支持类里自调用方法
    如果重试失败需要给@Recover注解的方法做后续处理，那这个重试的方法不能有返回值，只能是void
    方法内不能使用try catch，只能往外抛异常
    @Recover注解来开启重试失败后调用的方法(注意,需跟重处理方法在同一个类中)，
    此注解注释的方法参数一定要是@Retryable抛出的异常，否则无法识别，可以在该方法中进行日志处理。
     * */
    @Recover
    public String mockRecover(Exception e, String param) {
        log.info("mockRecover execute");
        log.info("msg {} p {}", e.getMessage(), param);
        return "FAKE SUCCESS";
    }

    @Recover
    public String mockRecover(Exception e, String param, Integer numberParam) {
        log.info("mockRecover2 execute");
        log.info("msg {} p {} np {}", e.getMessage(), param, numberParam);
        return "FAKE SUCCESS 2";
    }
}
