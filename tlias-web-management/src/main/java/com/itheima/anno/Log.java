package com.itheima.anno;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) //指定注解的作用目标
@Retention(RetentionPolicy.RUNTIME) //指定注解的生效时刻
public @interface Log {
}
