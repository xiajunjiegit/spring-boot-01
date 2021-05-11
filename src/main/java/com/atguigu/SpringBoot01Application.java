package com.atguigu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.atguigu.mapper.auto")
@SpringBootApplication
public class SpringBoot01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot01Application.class, args);
    }

}
