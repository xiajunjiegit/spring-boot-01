package com.atguigu.controller;


import com.atguigu.conf.RabbitmqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/*@Component
@RabbitListener(queues = RabbitmqConfig.QUEUE_A)*/
@Slf4j
public class MsgReceiverC_two {

    //@RabbitHandler
    public void process(String content) {
        log.info("处理器two接收处理队列A当中的消息： " + content);
    }
}
