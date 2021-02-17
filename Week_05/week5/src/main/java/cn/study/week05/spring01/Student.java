package cn.study.week05.spring01;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student implements Serializable, BeanNameAware, ApplicationContextAware {
    private int id;
    private String name;
    private String beanName;
    private ApplicationContext applicationContext;

    public void init() {
        System.out.println("hello init method......");
    }

    public void print() {
        System.out.println(this.beanName + "=======applicationContext.getBeanDefinitionNames======" + String.join(",", applicationContext.getBeanDefinitionNames()));

    }
}
