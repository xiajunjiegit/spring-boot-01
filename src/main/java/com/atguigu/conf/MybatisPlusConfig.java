package com.atguigu.conf;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;

/**
 * 配置分页插件
 */
//@Configuration
public class MybatisPlusConfig {

    //@Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
