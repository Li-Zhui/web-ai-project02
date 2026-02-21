package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 默认Bean是单例的 -singleton ，默认单例的Bean在项目启动时创建，创建完毕后将该Bean保存在IOC容器中。
 */
//@Lazy  //延迟初始话 -> 会延迟到第一此使用时再来创建这个Bean
@Scope("prototype") //多例
@RestController
public class DeptController {

    public DeptController(){
        System.out.println("创建Controller对象");
    }


    @Autowired
    private DeptService deptService;

    /**
     * 查询全部部门
     */
    @GetMapping("/depts")
    public Result findAll(){
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /**
     * 根据ID删除部门
     */
    @DeleteMapping("/depts")
    public Result delete(Integer id){
        System.out.println("根据ID删除部门数据: " + id);
        deptService.delete(id);
        return Result.success();
    }

    /**
     * 新增部门
     */
    @PostMapping("/depts")
    public Result save(@RequestBody Dept dept){
        System.out.println("新增部门数据: " + dept);
        deptService.save(dept);
        return Result.success();
    }

    /**
     * 根据ID查询部门信息
     */
    @GetMapping("/depts/{id}")
    public Result getInfo(@PathVariable Integer id){
        System.out.println("根据ID查询部门数据: " + id);
        Dept dept = deptService.getInfo(id);
        return Result.success(dept);
    }

    /**
     * 修改部门
     */
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        System.out.println("修改部门数据: " + dept);
        deptService.update(dept);
        return Result.success();
    }
}
