package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//@Aspect
@Slf4j
@Component
public class MyAspect1 {

    //抽取切入点表达式
    @Pointcut("execution(* com.itheima.service.impl.DeptServiceImpl.*(..))")
    public void pt(){}

    //前置通知 - 在目标方法运行之前运行
    @Before("pt()")
    public void before(){
        log.info("MyAspect1.before()");
    }

    //环绕通知 - 在目标方法运行之前和之后运行
    @Around("execution(* com.itheima.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("around .... before ....");

        Object result = pjp.proceed();

        log.info("around .... before ....");
        return result;
    }

    //后置通知 - 在目标方法运行之后运行，无论是否出现异常都运行
    @After("execution(* com.itheima.service.impl.*.*(..))")
    public void after(){
        log.info("MyAspect1.after()");
    }

    //返回通知 - 在目标方法正常返回时运行
    @AfterReturning("execution(* com.itheima.service.impl.*.*(..))")
    public void afterReturning(){
        log.info("MyAspect1.afterReturning()");
    }

    //异常通知 - 在目标方法抛出异常时运行
    @AfterThrowing("execution(* com.itheima.service.impl.*.*(..))")
    public void afterThrowing(){
        log.info("MyAspect1.afterThrowing()");
    }


}
