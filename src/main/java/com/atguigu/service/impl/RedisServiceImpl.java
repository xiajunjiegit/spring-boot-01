package com.atguigu.service.impl;

import com.atguigu.model.auto.SysUser;
import com.atguigu.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public boolean setUser(SysUser user) {
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set(user.getNickName(),user);
        log.info("{}",user.toString());

        return true;
    }

    public SysUser getUser(String name) {
        ValueOperations ops = redisTemplate.opsForValue();
        return (SysUser) ops.get(name);
    }
}
