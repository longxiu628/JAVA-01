package cn.study.week05.spring02;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class Aop2 {
    @Pointcut(value = "execution(* cn.study.week05.spring02.Klass.*dong(..))")
    public void point() {

    }

    @Before(value = "point()")
    public void before() {
        System.out.println("begin Klass dong......");
    }

    @AfterReturning(value = "point()")
    public void after() {
        System.out.println("end Klass dong........");
    }

    @Around(value = "point()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around begin Klass dong.......");
        joinPoint.proceed();
        System.out.println("around end Klass dong.......");
    }
}
