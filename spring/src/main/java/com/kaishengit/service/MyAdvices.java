package com.kaishengit.service;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import javax.inject.Named;

@Aspect
@Named
public class MyAdvices {

    @Pointcut("execution(* com.kaishengit.dao..*.*(..))")
    public void pointCut(){}

    //@Before("pointCut()")
    public void beforeAdvice() {

        System.out.println("前置通知...");
    }

    //@AfterReturning(value = "pointCut()",returning = "result")
    public void afterRetuingAdvice(Object result) {

        System.out.println("后置通知...result:" + result);
    }

    //@AfterThrowing(value = "pointCut()",throwing = "ex")
    public void throwingAdvice(Exception ex) {

        System.out.println("异常通知...msg:" + ex.getMessage());
    }

    //@After("pointCut()")
    public void afterAdvice() {
        System.out.println("最终通知...");
    }

    //@Around("pointCut()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("前置通知&&&&");
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
            System.out.println("后置通知&&&& result:" + result);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("异常通知&&&&&");
        } finally {
            System.out.println("最终通知&&&&&");
        }
        return result;
    }
}
