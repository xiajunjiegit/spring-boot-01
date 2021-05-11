package com.atguigu.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/api/ws")
public class WebSocketController {

    @GetMapping("/page")
    public String page(){
        //return new ModelAndView("websocket");
        return "index";
    }

    /**
     * 群发消息内容
     */
    @RequestMapping(value="/sendAll",method = RequestMethod.GET)
    public String sendAllMessage(@RequestParam(required = true) String message){
        WebSocketServer.BroadCastInfo(message);

        return "ok";
    }

    /**
     * 指定会话ID发消息
     * @param message 消息内容
     * @param id 连接会话ID
     * @return
     */
    @RequestMapping(value="/sendOne", method=RequestMethod.GET)
    public String sendOneMessage(@RequestParam(required=true) String message,@RequestParam(required=true) String id){
        WebSocketServer.SendMesage(message,id);

        return "ok";
    }
}
