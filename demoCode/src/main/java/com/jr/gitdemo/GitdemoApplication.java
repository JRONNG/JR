package com.jr.gitdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.jr.gitdemo.dao") //扫描的mapper("com.jr.gitdemo.dao") //扫描的mapper
public class GitdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GitdemoApplication.class, args);
    }

}
