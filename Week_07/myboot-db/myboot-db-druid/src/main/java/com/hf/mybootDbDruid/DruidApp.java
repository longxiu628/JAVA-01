package com.hf.mybootDbDruid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;

/**
 * @author hfzhang
 * @date 2021/3/4
 */
@SpringBootApplication
@EnableSwagger2
public class DruidApp implements CommandLineRunner {

    @Autowired
    DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(DruidApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception{
        System.out.println(dataSource.getClass().getName());
    }
}
