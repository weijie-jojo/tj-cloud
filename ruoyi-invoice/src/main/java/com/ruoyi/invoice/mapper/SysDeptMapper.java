package com.ruoyi.invoice.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.ruoyi.invoice.pojo.SysDept;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysDeptMapper extends MPJBaseMapper<SysDept> {
    @Select("CALL selectAllDept")
    List<SysDept> selectAllDept();
}
