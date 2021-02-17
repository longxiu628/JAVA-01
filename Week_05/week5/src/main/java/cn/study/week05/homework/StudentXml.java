package cn.study.week05.homework;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StudentXml {
    private int id;
    private String name;

    public void init() {
        System.out.println("init method studentxml.......");
    }

    public void sayHi() {
        System.out.println("hello, my class is " + this.getClass() + " and my name is:" + name);
    }

}
