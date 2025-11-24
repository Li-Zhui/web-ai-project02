package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

/**
 * 操作员工信息
 */
@Mapper
public interface EmpMapper {
    // ============================= 原始方式 ==============================

    /**
     *分页查询
     字符串字面量
     */
//    @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id " +
//            "order by e.update_time desc limit #{start},#{pageSize}")
//    List<Emp> list(Integer start, Integer pageSize);

    //@Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc")
    List<Emp> list(String name ,
                   Integer gender,
                   LocalDate begin,
                   LocalDate end);
}
