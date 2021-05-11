package com.atguigu.controller;

import com.atguigu.conf.RabbitmqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 生产者
 */
@Component
@Slf4j
public class MsgProducer implements RabbitTemplate.ConfirmCallback {

    //由于rabbitTemplate的scope属性设置为ConfigurableBeanFactory.SCOPE_PROTOTYPE，所以不能自动注入
    private RabbitTemplate rabbitTemplate;

    /**
     * 构造方法注入rabbitTemplate
     */
    @Autowired
    public MsgProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this);//rabbitTemplate如果为单例的话，那回调就是最后设置的内容
    }

    public void sendMsg(String content){
        for (int i=0;i<5;i++){
            CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
            //报消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
            rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_A,RabbitmqConfig.ROUTINGKEY_A,content,correlationId);
        }

    }

    /**
     * 回调
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("回调id："+correlationData);
        if (ack){
            log.info("消息消费成功");
        }else{
            log.info("消息消费失败："+cause);
        }
    }
}
