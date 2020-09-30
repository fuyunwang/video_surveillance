package com.fuyunwang.chuoyue;

import com.fuyunwang.chuoyue.common.base.GlobalConstant;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @description:
 * @author: FuyunWang
 * @time: 2020/7/22 23:26
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChuoyueTestApplication {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
    public void generatePassword(){
        String wangxiaohao = passwordEncoder.encode("password");
        log.info(wangxiaohao);
    }

    @Test
    public void usePython(){
        Process proc;
        try {
            proc = Runtime.getRuntime().exec("python F:\\work\\laboratory\\tasks\\video_sub\\yolov4-pytorch\\demo1.py");// 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testRabbitmqChuoyueQueue(){
        String topic="topic.chuoyue";
        String message="发送chuoyue消息";
        this.rabbitTemplate.convertAndSend(GlobalConstant.RabbitMQ.EXCHANGE_NAME_TOPIC,topic,message);
    }
}
