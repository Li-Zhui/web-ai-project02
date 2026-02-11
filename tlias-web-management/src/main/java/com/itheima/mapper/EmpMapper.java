package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

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



    /**
     * 新增员工基本信息
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    //获取到生成的主键 -- 主键返回
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date," +
            " dept_id, create_time, update_time)\n" +
            "    values (#{username},#{name}, #{gender},#{phone}, #{job}, #{salary},#{image},#{entryDate}," +
            "#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    /**
     * 批量删除员工
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据id查询员工信息及工作经历信息
     */
    Emp getById(Integer id);

    /**
     * 根据ID修改员工基本信息
     */
    void updateById(Emp emp);

    /**
     * 统计员工职位人数
     */
    @MapKey("pos")
    List<Map<String,Object>> countEmpJobData();

    /**
     * 统计员工性别人数
     */
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    /**
     *根据用户名和密码查询员工信息
     */
    @Select("select id, username, name from emp where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
