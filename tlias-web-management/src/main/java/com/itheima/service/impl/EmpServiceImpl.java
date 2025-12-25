package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;


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


        //3.解析查询结果，并封装数据
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal() , p.getResult());

    }


    /**
     * 新增员工
     */
    @Transactional
    //事务管理的注解
    @Override
    public void save(Emp emp) {
        //1.保存员工基本信息
        emp.setCreateTime(LocalDateTime.now());
        try {
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            //2.批量保存员工工作经历
            List<EmpExpr> exprList = emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                //遍历集合，为exprList中的每个EmpExpr对象设置empId（补全字段）
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //3.记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工：" + emp);
            empLogService.insertLog(empLog);
        }

    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        //1.删除员工基本信息
        empMapper.deleteByIds(ids);

        //2.删除员工工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    /**
     * 查询员工
     */
    @Override
    public Emp getInfo(Integer id) {
       return empMapper.getById(id);
    }

    /**
     * 修改员工
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        //1.根据id修改员工的基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //2.根据ID批量修改员工工作经历
            //2.1 先根据ID删除原有的工作经历
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
            //2.2 再添加新的工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }

}
