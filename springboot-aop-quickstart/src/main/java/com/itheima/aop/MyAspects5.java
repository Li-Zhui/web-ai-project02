package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
//@Aspect
public class MyAspects5 {
    //前置通知
    //aBefore("execution(public void com.itheima.service.impl.DeptServiceImpl.delete(java.lang.Integer))")
    //@Before("execution(void com.itheima.service.impl.DeptServiceImpl.delete(java.lang.Integer))")
//    @Before("execution(void delete(java.lang.Integer))")  //包名.类名 强烈不建议省略

    //匹配impl下的delete和list方法
//    @Before("execution(* com.itheima.service.impl.DeptServiceImpl.list(.. )) ||" +
//            "execution(* com.itheima.service.impl.DeptServiceImpl.delete(.. ))")

    @Before("@annotation(com.itheima.anno.LogOperation)")
    public void before(){
        log.info("MyAspect5 -> before ...");
    }

}
