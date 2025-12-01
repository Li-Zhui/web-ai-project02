package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
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
     */
//    @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id " +
//            "order by e.update_time desc limit #{start},#{pageSize}")
//    List<Emp> list(Integer start, Integer pageSize);

    /**
     * 使用插件后的分页查询
     */
    //@Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc")

    /**
     *使用xml和插件的分页查询
     */
    /*List<Emp> list(String name ,
                   Integer gender,
                   LocalDate begin,
                   LocalDate end);*/

    /**
     *使用xml和插件的分页查询（优化）
     */
    List<Emp> list(EmpQueryParam empQueryParam);
}
