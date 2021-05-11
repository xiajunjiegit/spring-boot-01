package com.atguigu.service;

import com.atguigu.model.auto.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-04-01
 */
public interface ISysUserService extends IService<SysUser> {

    List<SysUser> getUser();

    SysUser findUserById(int id);

    int updateSysUserById(SysUser user);

    int deleteSysUserById(int id);

    int addUser(SysUser user);

    void wrapperTest();
}
