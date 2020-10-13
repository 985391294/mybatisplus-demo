package com.tqz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * author: tian
 * date: 2019-12-28 12:36
 * desc:
 **/
@SpringBootApplication
@MapperScan("com.tqz.dao")
public class BaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class,args);
    }
}
