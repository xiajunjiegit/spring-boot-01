package com.atguigu.model.auto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author astupidcoder
 * @since 2021-04-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
public class SysUser extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 昵称
     */
    private String nickName;

    private String icon;

    /**
     * 密码
     */
    private String password;

    /**
     * shiro加密盐
     */
    private String salt;

    /**
     * 手机号码
     */
    private String tel;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 是否锁定
     */
    private Integer locked;

    private String createBy;

    private String updateBy;

    private String remarks;

    private Integer delFlag;

    private Integer isAdmin;

    /*public SysUser(Integer id, String loginName, String nickName, String password, String tel, String email) {
        this.id = id;
        this.loginName = loginName;
        this.nickName = nickName;
        this.password = password;
        this.tel = tel;
        this.email = email;
    }

    public SysUser() {

    }*/
}
