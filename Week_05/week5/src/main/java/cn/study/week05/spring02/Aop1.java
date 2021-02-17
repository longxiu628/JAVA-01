package cn.study.week05.spring02;

import org.aspectj.lang.ProceedingJoinPoint;

public class Aop1 {
    public void startTransaction() {
        System.out.println("======>begin ding......");
    }

    public void commitTransaction() {
        System.out.println("=======>end ding..........");
    }

    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("------>around begin ding..............");
        joinPoint.proceed();
        System.out.println("----------->around end ding..............");
    }
}
