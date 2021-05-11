package com.atguigu.topic;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicCustomer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//默认不写队列名称就是创建一个临时队列
                    exchange = @Exchange(type = "topic",name = "topics"),
                    key = {"user.save","user.*"}
            )
    })
    public void receivel(String msg){
        System.out.println("\033[31;0m" + "消费者1：" + "\033[0m"+msg);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//默认不写队列名称就是创建一个临时队列
                    exchange = @Exchange(type = "topic",name = "topics"),
                    key = {"order.#"}
            )
    })
    public void receive2(String msg){
        System.out.println("\033[31;0m" + "消费者2：" + "\033[0m"+msg);
    }
}
