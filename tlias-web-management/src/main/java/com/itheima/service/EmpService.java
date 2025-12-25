package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface EmpService {

    /**
     *分页查询
     */
   /*PageResult<Emp> page(Integer page,
                         Integer pageSize,
                         String name ,
                         Integer gender,
                         LocalDate begin,
                         LocalDate end);*/

    /**
     *分页查询
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);


    /**
     * 新增员工
     */
    void save(Emp emp);

    void delete(List<Integer> ids);
}

