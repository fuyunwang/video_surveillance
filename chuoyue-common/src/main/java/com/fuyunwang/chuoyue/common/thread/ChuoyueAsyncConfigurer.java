package com.fuyunwang.chuoyue.common.thread;

import com.fuyunwang.chuoyue.common.prop.AsyncTaskProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description: 线程池    https://www.cnblogs.com/sbj-dawn/p/9075573.html
 * @author: FuyunWang
 * @time: 2020/7/25 17:20
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(value = {AsyncTaskProperties.class})
public class ChuoyueAsyncConfigurer implements AsyncConfigurer {
    /** 注入配置类 */
    @Autowired
    private AsyncTaskProperties config;

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程池大小
        executor.setCorePoolSize(config.getCorePoolSize());
        //最大线程数
        executor.setMaxPoolSize(config.getMaxPoolSize());
        //队列容量
        executor.setQueueCapacity(config.getQueueCapacity());
        //活跃时间
        executor.setKeepAliveSeconds(config.getKeepAliveSeconds());
        executor.setWaitForTasksToCompleteOnShutdown(true);//等待任务在关机时完成--表明等待所有线程执行完
        //线程名字前缀
        executor.setThreadNamePrefix("chuoyue-async-");
        // setRejectedExecutionHandler：当pool已经达到max size的时候，如何处理新任务
        // CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) -> {
            log.error("===="+throwable.getMessage()+"====", throwable);
            log.error("exception method:"+method.getName());
        };
    }
}
