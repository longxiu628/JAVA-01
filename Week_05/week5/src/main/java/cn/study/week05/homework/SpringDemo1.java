package cn.study.week05.homework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDemo1 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("homeworkContext.xml");
        StudentXml alice = (StudentXml) context.getBean("alice");
        StudentXml bob = (StudentXml) context.getBean("bob");
        System.out.println(alice);
        System.out.println(bob);
        StudentConfig liudehua = (StudentConfig) context.getBean("liudehua");
        liudehua.sayHi();
        StudentAnnotation vencent = context.getBean(StudentAnnotation.class);
        vencent.sayHi();
        System.out.println(String.join(",", context.getBeanDefinitionNames()));
    }
}
