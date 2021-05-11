/*
package com.atguigu.controller;

import com.atguigu.conf.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private WebSocketServer webSocketServer;

    @GetMapping("index")
    public ResponseEntity<String> index(){
        return ResponseEntity.ok("请求成功");
    }

    @GetMapping("/page")
    public String page(){
        //return new ModelAndView("websocket");
        return "websocket";
    }

    @RequestMapping("/push/{toUserId}")
    public ResponseEntity<String> pushToWeb(String message, @PathVariable String toUserId) throws IOException {
        WebSocketServer.sendInfo(message,toUserId);
        return ResponseEntity.ok("MSG SEND SUCCESS");
    }

    */
/**
     * 测试webSocket
     *//*

    @GetMapping("/testWebSocekt")
    public void TestWebSocekt() throws IOException {
        for(int i=1;i<5;i++){
            webSocketServer.sendMessage("我是一个测试ws接口,编号："+i);
        }
    }
}
*/
