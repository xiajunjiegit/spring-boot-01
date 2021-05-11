package com.atguigu.controller;

import com.atguigu.model.auto.SysUser;
import com.atguigu.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisService redisService;

    @RequestMapping("/getUser")
    public SysUser getUser(){
        String name = "test";
        return redisService.getUser(name);
    }

    @RequestMapping("/setUser")
    public String setUser(){
        /*SysUser user = new SysUser(1, "admin3", "test", "123456", "8520147", "921723915@qq.com");
        redisService.setUser(user);*/
        return "添加成功";
    }

}
