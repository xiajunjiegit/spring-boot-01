package com.atguigu.test;

import com.atguigu.model.auto.SysUser;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * redis 提供5种数据类型的操作
 * String ,hash ,list , set , zset
 */
public class UserRedis {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public void addUser(String key,Long time,SysUser user){
        Gson gson = new Gson();
        redisTemplate.opsForValue().set(key,gson.toJson(user),time,TimeUnit.MINUTES);
    }

    public void addUserList(String key,Long time,List<SysUser> userList){
        Gson gson = new Gson();
        redisTemplate.opsForValue().set(key,gson.toJson(userList),time,TimeUnit.MINUTES);
    }

    public SysUser getUserByKey(String key){
        Gson gson = new Gson();
        SysUser su = null;
        String userJson = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotEmpty(userJson)){
            su = gson.fromJson(userJson,SysUser.class);
        }
        return su;
    }
    public List<SysUser> getUserListByKey(String key){
        Gson gson = new Gson();
        List<SysUser> userList = null;
        String userJson = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotEmpty(userJson)){
            userList = gson.fromJson(userJson,new TypeToken<List<SysUser>>(){}.getType());
        }
        return userList;
    }

    public void deleteByKey(String key){
        redisTemplate.opsForValue().getOperations().delete(key);
    }














}
