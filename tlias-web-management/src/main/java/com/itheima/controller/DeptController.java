package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RestController == @Controller + @ResponseBody
//@Controller：表示将控制层的这个类交给IOC容器管理
//@ResponseBody：表示将方法的返回值直接作为响应数据响应给前端。如果返回值是一个对象或集合，先会转成JSON再响应给前端。

public class DeptController {

    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts", method = RequestMethod.GET)
    //method : 指定请求的方式
    @GetMapping("/depts")
    //代表请求方式必须为 GET
    public Result list(){
        System.out.println("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }
}
