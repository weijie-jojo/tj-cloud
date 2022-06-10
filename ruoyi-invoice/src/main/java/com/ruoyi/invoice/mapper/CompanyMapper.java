package com.ruoyi.invoice.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.ruoyi.invoice.pojo.sysGroup;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CompanyMapper extends MPJBaseMapper<sysGroup> {
    @Select("SELECT *FROM sys_group where is_deleted=0")
    List<sysGroup> selectAllCompany();
}
