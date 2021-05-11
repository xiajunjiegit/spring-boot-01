package com.atguigu.service.impl;

import com.atguigu.model.auto.SysUser;
import com.atguigu.mapper.auto.SysUserMapper;
import com.atguigu.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-04-01
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<SysUser> getUser() {
        log.info("=================我是一个测试日志===============");
        return baseMapper.getUser();
    }

    /**
     * 获取用户策略：先从缓存中获取，没有则在数据库读取，再写入缓存
     */
    @Override
    public SysUser findUserById(int id){
        String key = "user_"+id;
        ValueOperations<String,SysUser> vo = redisTemplate.opsForValue();
        //判断redis中是否存在键为key的缓存
        Boolean haskey = redisTemplate.hasKey(key);
        if(haskey){
            SysUser sysUser = vo.get(key);
            System.out.println("从缓存中获取数据："+sysUser.getLoginName());
            System.out.println("--------------------------");
            return sysUser;
        }else{
            SysUser sysUser = baseMapper.selectById(id);
            System.out.println("查询数据库获取数据："+sysUser.getLoginName());
            System.out.println("--------------------------");
            //写入缓存
            vo.set(key,sysUser,5, TimeUnit.HOURS);
            return sysUser;
        }
    }

    //删除用户：删除数据库中数据，删除缓存中数据
    @Override
    public int deleteSysUserById(int id){
        int result = baseMapper.deleteById(id);
        String key = "user_"+id;
        if(result != 0){
            Boolean hasKey = redisTemplate.hasKey(key);
            if (hasKey){
                redisTemplate.delete(key);
                System.out.println("删除了缓存中的key："+key);
            }
        }
        return result;
    }

    /**
     * 更新：先更新数据库表数据，然后删除原来的缓存，再更新缓存
     */
    @Override
        public int updateSysUserById(SysUser user){
        ValueOperations vo = redisTemplate.opsForValue();
        int result = baseMapper.updateById(user);
        if (result != 0){
            String key = "user_"+user.getId();
            Boolean hasKey = redisTemplate.hasKey(key);
            if (hasKey){
                redisTemplate.delete(key);
                System.out.println("删除缓存中的key，------------》"+key);
            }
            //再将更新后的数据加入缓存
            SysUser sysUser = baseMapper.selectById(user.getId());
            if(sysUser != null){
                vo.set(key,sysUser,3,TimeUnit.HOURS);
            }
        }
        return result;
    }

    @Override
    public int addUser(SysUser user){
        QueryWrapper<SysUser> w = new QueryWrapper();
        w.eq("login_name",user.getLoginName());

        SysUser sysUser = baseMapper.selectOne(w);
        int result;
        if(sysUser != null){
            result = 0;
            return result;
        }else{
            ValueOperations vo = redisTemplate.opsForValue();
            result = baseMapper.insert(user);
            if (result != 0){
                String key = "user_"+user.getId();
                Boolean hasKey = redisTemplate.hasKey(key);
                if (hasKey){
                    redisTemplate.delete(key);
                }
                if (user != null){
                    vo.set(key,sysUser,3,TimeUnit.HOURS);
                }
            }
        }

        return result;
    }

    @Override
    public void wrapperTest(){
        QueryWrapper<SysUser> qw = new QueryWrapper<>();
        qw.eq("login_name","AAAAA");
        qw.between("id",1,2);
        qw.like("login_name","A");
        qw.orderByDesc("id");

        baseMapper.selectList(qw);

        SysUser s= baseMapper.selectOne(qw);
        System.out.println(s);
    }

}
