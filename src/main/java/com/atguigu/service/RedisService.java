package com.atguigu.service;


import com.atguigu.model.auto.SysUser;

public interface RedisService {

    boolean setUser(SysUser user);

    SysUser getUser(String name);
}
