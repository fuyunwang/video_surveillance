package com.fuyunwang.chuoyue;

//import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @description:
 * @author: FuyunWang
 * @time: 2020/7/21 21:06
 */
//@EnableAdminServer
@SpringBootApplication
public class ChuoyueApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChuoyueApplication.class,args);
    }
}
