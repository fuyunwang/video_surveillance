package com.fuyunwang.chuoyue;

import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: FuyunWang
 * @time: 2020/7/22 23:26
 */
@Slf4j
//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChuoyueTestApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;


//    @Test
    public void generatePassword(){
        String wangxiaohao = passwordEncoder.encode("wangxiaohao");
        log.info(wangxiaohao);
    }
}
