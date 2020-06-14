package com.softwork.freshmarket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@MapperScan("com.softwork.freshmarket.mapper")
@Component
public class FreshmarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreshmarketApplication.class, args);
    }

}
