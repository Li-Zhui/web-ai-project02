package com.itheima.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
//@RestController == @Controller + @ResponseBody
//@Controller：表示将控制层的这个类交给IOC容器管理
//@ResponseBody：表示将方法的返回值直接作为响应数据响应给前端。如果返回值是一个对象或集合，先会转成JSON再响应给前端。

public class DeptController {
}
