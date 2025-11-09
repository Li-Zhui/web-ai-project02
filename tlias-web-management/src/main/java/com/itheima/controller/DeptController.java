package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 删除部门 - 方式一：基于 HttpServletRequest 获取请求参数
     */
//    @DeleteMapping("/depts")
//    public Result delete(HttpServletRequest request){
//        String idString = request.getParameter("id");
//        Integer id = Integer.parseInt(idString);
//        System.out.println("根据ID删除部门：" + id);
//        return Result.success();
//    }

    /**
     * 删除部门 - 方式二：通过spring提供的 @RequestParam 注解，将请求参数绑定给方法参数
     * 注意事项：一旦声明了 @RequestParam，该参数在请求时必须传递，如果不传递将会报错。（默认 required 为true）
     */
//    @DeleteMapping("/depts")
//    public Result delete(@RequestParam(value = "id",required = false) Integer deptId){
//        System.out.println("根据ID删除部门：" + deptId);
//        return Result.success();
//    }

    /**
     * 删除部门 - 方式三：如果请求参数名与形参变量名相同，直接定义方法形参即可接收。（省略aRequestParam）
     */
    @DeleteMapping("/depts")
    public Result delete(Integer id){
        System.out.println("根据ID删除部门：" + id);
        deptService.deleteById(id);
        return Result.success();
    }
}
