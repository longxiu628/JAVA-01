package com.hf.mybootDbJdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author hfzhang
 * @date 2021/3/3
 */
@SpringBootApplication
@EnableSwagger2
public class JdbcApp {
    public static void main(String[] args) {
        SpringApplication.run(JdbcApp.class, args);
    }
}
