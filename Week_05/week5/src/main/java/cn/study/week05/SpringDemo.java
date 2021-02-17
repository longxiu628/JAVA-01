package cn.study.week05;

import cn.study.week05.aop.ISchool;
import cn.study.week05.spring01.Student;
import com.sun.xml.internal.bind.v2.schemagen.episode.Klass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDemo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student zhangsan = (Student) context.getBean("student-zhangsan");
        zhangsan.print();
        System.out.println(zhangsan.toString());
        Student lisi = (Student) context.getBean("student-lisi");
        lisi.print();
        Klass klass = context.getBean(Klass.class);
        klass.dong();
        System.out.println("Klass对象Aop代理后的实际类型：" + klass.getClass());


        ISchool school = context.getBean(ISchool.class);
        System.out.println(school);
        System.out.println("Ischool接口AOP代理后的实际类型：" + school.getClass());
        school.ding();
    }
}
