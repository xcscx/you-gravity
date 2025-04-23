package com.itegg.yougravitybackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.itegg.yougravitybackend.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class YouGravityBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(YouGravityBackendApplication.class, args);
    }

}
