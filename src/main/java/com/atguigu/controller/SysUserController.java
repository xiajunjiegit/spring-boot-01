package com.atguigu.controller;


import com.atguigu.model.auto.SysUser;
import com.atguigu.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2021-04-01
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    ISysUserService userService;

    @PostMapping("/getUser")
    public SysUser getUser(){
        return userService.getById(1);
    }

    @GetMapping("/getUser1")
    public List<SysUser> getUser1(){
        return userService.getUser();
    }


    @GetMapping("/findUserById")
    public Map<String, Object> findUserById(@RequestParam int id){
        SysUser user = userService.findUserById(id);
        Map<String, Object> result = new HashMap<>();
        result.put("id", user.getId());
        result.put("userName", user.getLoginName());
        result.put("pwd", user.getPassword());
        return result;
    }

    @RequestMapping("/updateUser")
    public String updateUser(){
        SysUser user = new SysUser();
        user.setId(5);
        user.setLoginName("AAAAA");
        user.setPassword("123456");

        int result = userService.updateSysUserById(user);

        if(result != 0){
            return "update user success";
        }

        return "fail";
    }

    @RequestMapping("/deleteUserById")
    public String deleteUserById(@RequestParam int id){
        int result = userService.deleteSysUserById(id);
        if(result != 0){
            return "delete success";
        }
        return "delete fail";
    }

    @RequestMapping("/addUser")
    public String addUser(){
        SysUser user = new SysUser();
        user.setLoginName("bb");
        user.setPassword("bb33");
        int result = userService.addUser(user);
        if(result != 0){
            return "add user success";
        }
        return "add user fail";
    }


    @RequestMapping("/wrapperTest")
    public void wrapperTest(){
        userService.wrapperTest();
    }

}
