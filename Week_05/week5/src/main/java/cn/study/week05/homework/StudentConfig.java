package cn.study.week05.homework;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StudentConfig {
    private int id;
    private String name;

    public void init() {
        System.out.println("init method StudentConfig.......");
    }

    public void sayHi() {
        System.out.println("hello, my class is " + this.getClass() + " and my name is:" + name);
    }
}
