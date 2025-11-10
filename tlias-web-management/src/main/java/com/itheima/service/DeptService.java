package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询全部部门数据
     */
    List<Dept> findAll();

    /**
     * 根据ID删除部门
     */
    void deleteById(Integer id);

    /**
     * 添加部门
     */
    void add(Dept dept);

    Dept getById(Integer id);
}
