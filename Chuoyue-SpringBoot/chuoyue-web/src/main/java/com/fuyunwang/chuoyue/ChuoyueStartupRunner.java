package com.fuyunwang.chuoyue;

import com.fuyunwang.chuoyue.quartz.SampleScheduler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Order(value = 1)
public class ChuoyueStartupRunner implements CommandLineRunner {
    @Autowired
    public SampleScheduler scheduleJobs;
    @Override
    public void run(String... args) throws Exception {
        scheduleJobs.scheduleJobs();
        log.info(">>>>>>>>>>>>>>>定时任务开始执行<<<<<<<<<<<<<");
    }
}
