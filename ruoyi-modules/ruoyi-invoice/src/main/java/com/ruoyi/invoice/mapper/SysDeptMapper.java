package com.ruoyi.invoice.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.ruoyi.invoice.pojo.SysDept;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysDeptMapper extends MPJBaseMapper<SysDept> {
    @Select("SELECT *FROM sys_dept")
    List<SysDept> selectAllDept();
}
