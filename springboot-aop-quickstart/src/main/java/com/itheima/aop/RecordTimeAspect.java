package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect //标识当前为一个切面类
@Slf4j
public class RecordTimeAspect {

    @Around("execution(* com.itheima.controller.*.*(..))")
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {
        //1.获取开始时间
        long begin = System.currentTimeMillis();

        //2.执行原始方法
        Object result = pjp.proceed();

        //3.记录方法运行结束时间，计算耗时
        long end = System.currentTimeMillis();
        log.info("方法运行耗时：{}ms", end - begin);

        return result;
    }
}
