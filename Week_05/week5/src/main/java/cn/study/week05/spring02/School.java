package cn.study.week05.spring02;

import cn.study.week05.aop.ISchool;
import cn.study.week05.spring01.Student;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@Data
@ToString
public class School implements ISchool {
    @Autowired
    private Klass klass;
    @Resource(name = "student-zhangsan")
    Student student;

    @Override
    public void ding() {
        System.out.println("Class1 have " + this.klass.getStudents().size() + " students and one is " + this.student);
    }
}
