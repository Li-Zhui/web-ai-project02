package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询全部部门数据
     */
    List<Dept> findAll();
}
