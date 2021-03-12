package pers.peixinyi.work.tcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * @author: peixinyi
 * @version: V1.0.0.0
 * @date: 2021-03-11 09:23
 */
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class RunApplication {
    public static void main(String[] args) {
        SpringApplication.run(RunApplication.class, args);
    }
}
