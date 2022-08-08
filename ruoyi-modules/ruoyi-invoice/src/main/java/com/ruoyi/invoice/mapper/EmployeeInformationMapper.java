package com.ruoyi.invoice.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.ruoyi.invoice.pojo.EmployeeInformation;
import com.ruoyi.invoice.pojo.SysUser;
import com.ruoyi.invoice.vo.SysUserVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EmployeeInformationMapper extends MPJBaseMapper<EmployeeInformation> {
    @Select("SELECT B.post_name,t.* FROM employee_information t JOIN sys_user_post A on t.user_id=A.user_id  JOIN sys_post B on A.post_id=B.post_id WHERE  t.user_id=#{userId}")
    EmployeeInformation getPost(@Param("userId") Integer userId);
}
