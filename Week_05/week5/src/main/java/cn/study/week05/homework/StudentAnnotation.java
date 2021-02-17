package cn.study.week05.homework;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component(value = "vencent")
public class StudentAnnotation implements InitializingBean {
    private int id;
    private String name;

    public void init() {
        System.out.println("init method StudentAnnotation.......");
    }

    public void sayHi() {
        System.out.println("hello, my class is " + this.getClass() + " and my name is:" + name);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.id = 1002;
        this.name = "vencent";
    }
}
