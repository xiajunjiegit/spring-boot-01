package com.atguigu.controller;

import com.atguigu.conf.RabbitmqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 * 一个生产者，一个消费者
 */
/*@Component
@RabbitListener(queues = RabbitmqConfig.QUEUE_A)*/
@Slf4j
public class MsgReceiver {

    //@RabbitHandler
    public void process(String content){
        log.info("接收处理队列A当中的消息："+content);
    }
}
