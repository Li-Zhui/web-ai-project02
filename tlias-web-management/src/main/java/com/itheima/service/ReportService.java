package com.itheima.service;

import com.itheima.pojo.JobOption;

public interface ReportService {

    /**
     * 统计员工职位人数
     */
    JobOption getEmpJobData();
}
