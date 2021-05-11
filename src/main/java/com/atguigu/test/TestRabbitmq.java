package com.atguigu.test;

import com.atguigu.controller.MsgProducer;
import com.atguigu.controller.MsgReceiver;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestRabbitmq {
    @Autowired
    private MsgProducer msgProducer;
    /*@Autowired
    private MsgReceiver msgReceiver;*/

    @RequestMapping("/mq")
    public void test(){
        msgProducer.sendMsg("我发送的是多个消息》》》");

        //msgReceiver.process("我发的是单个消息====");
    }
}
