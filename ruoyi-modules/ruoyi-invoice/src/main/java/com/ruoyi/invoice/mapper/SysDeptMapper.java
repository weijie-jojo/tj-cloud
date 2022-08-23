package com.ruoyi.invoice.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.ruoyi.invoice.pojo.SysDept;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysDeptMapper extends MPJBaseMapper<SysDept> {
    @Select("select dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, del_flag, create_by, create_time, update_by, update_time from sys_dept")
    List<SysDept> selectAllDept();
}
