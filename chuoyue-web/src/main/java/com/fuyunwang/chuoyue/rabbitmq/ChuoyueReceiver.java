package com.fuyunwang.chuoyue.rabbitmq;

import com.fuyunwang.chuoyue.common.base.GlobalConstant;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@Slf4j
//@Component
public class ChuoyueReceiver {

   /* @RabbitListener(queues = GlobalConstant.RabbitMQ.QUEUE_NAME_CHUOYUE)
    public void process(String msg, Message message, Channel channel){
        if (StringUtils.isNotBlank(msg)){
            try{
                log.info(String.format("chuoyue queue receive: %s", msg));
            }finally {
                try {
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/

}
