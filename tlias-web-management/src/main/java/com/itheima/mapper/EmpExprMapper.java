package com.itheima.mapper;

import com.itheima.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 操作员工工作经历信息
 */
@Mapper
public interface EmpExprMapper {

    /**
     * 批量保存员工工作经历信息
     */
    void insertBatch(List<EmpExpr> exprList);


    /**
     * 根据员工ID批量删除员工工作经历信息
     */
    void deleteExprByIds(List<Integer> empIds);
}
