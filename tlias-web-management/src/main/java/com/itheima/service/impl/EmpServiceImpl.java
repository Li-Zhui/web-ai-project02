package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;


    /**
     * 原始分页查询
     * @param page 页码
     * @param pageSize 每页记录数
     */
    /*@Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        //1.调用Mapper接口，查询总记录数
        Long total = empMapper.count();

        //2.调用Mapper接口，查询结果列表
        Integer start = (page - 1) * pageSize;
        List<Emp> rows = empMapper.list(start, pageSize);

        //3.封装结果PageResult<Emp>
        return new PageResult<Emp>(total, rows);
    }*/


    /**
     * PageHelper分页查询
     */
    /*@Override
    public PageResult<Emp> page(Integer page,
                                Integer pageSize,
                                String name ,
                                Integer gender,
                                LocalDate begin,
                                LocalDate end) {
        //1.设置分页参数（页码、每页记录数），需要用到这个插件中提供的PageHelper类
        PageHelper.startPage(page, pageSize);

        //2.执行查询
        List<Emp> empList = empMapper.list(name,gender,begin,end);


        //3.解析查询结果，冰封装数据
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal() , p.getResult());

    }*/


    /**
     * PageHelper分页查询
     */
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1.设置分页参数（页码、每页记录数），需要用到这个插件中提供的PageHelper类
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        //2.执行查询
        List<Emp> empList = empMapper.list(empQueryParam);


        //3.解析查询结果，冰封装数据
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal() , p.getResult());

    }


    /**
     * 新增员工
     */
    @Override
    public void save(Emp emp) {
        //1.保存员工基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        //2.批量保存员工工作经历

    }

}
