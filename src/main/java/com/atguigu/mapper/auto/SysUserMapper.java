package com.atguigu.mapper.auto;

import com.atguigu.model.auto.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *  在对应的mapper接口上继承BaseMapper类，即可完成所有的crud操作
 * @author astupidcoder
 * @since 2021-04-01
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUser> getUser();

}
