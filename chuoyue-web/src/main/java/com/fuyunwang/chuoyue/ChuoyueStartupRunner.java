package com.fuyunwang.chuoyue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Order(value = 1)
public class ChuoyueStartupRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("初始化任务");
    }
}
