package cn.study.week05.homework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {
    @Bean(name = "liudehua", initMethod = "init")
    public StudentConfig createStudent() {
        return new StudentConfig(1001, "liudehua");
    }
}
