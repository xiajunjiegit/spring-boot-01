package com.atguigu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = SpringBoot01Application.class)
@RunWith(SpringRunner.class)
public class TestRabbitmq {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //topic
    @Test
    public void topic(){
        rabbitTemplate.convertAndSend("topics","order.save","topic模型消息");
    }
}
