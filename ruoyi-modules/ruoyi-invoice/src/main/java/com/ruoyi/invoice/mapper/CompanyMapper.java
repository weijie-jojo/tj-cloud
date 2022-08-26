package com.ruoyi.invoice.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.ruoyi.invoice.pojo.sysGroup;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CompanyMapper extends MPJBaseMapper<sysGroup> {
    @Select("select group_Id, group_code, group_name, group_address, group_regist_address, group_open_bank, group_bank_account, group_credit_code, group_status, create_time, is_deleted from sys_group where is_deleted=0")
    List<sysGroup> selectAllCompany();
}
