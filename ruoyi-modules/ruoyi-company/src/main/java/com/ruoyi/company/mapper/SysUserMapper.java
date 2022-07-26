package com.ruoyi.company.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.ruoyi.company.domain.vo.SysUserVo;
import com.ruoyi.system.api.domain.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysUserMapper extends MPJBaseMapper<SysUser> {
    @Select("SELECT * FROM sys_user_role where user_id=#{userId}")
    List<SysUserVo> getRoleByUserId(@Param("userId") Long userId);
    @Select("SELECT * FROM sys_user where user_id=#{userId}")
    SysUserVo getDeptByUserId(@Param("userId") Long userId);
    @Select("SELECT * FROM sys_user where dept_id=#{deptId}")
    List<SysUserVo> getUserByDeptId(@Param("deptId") Integer deptId);
}
