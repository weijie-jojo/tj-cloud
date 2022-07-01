package com.ruoyi.invoice.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.ruoyi.invoice.pojo.SysUser;
import com.ruoyi.invoice.vo.SysUserVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysUserMapper extends MPJBaseMapper<SysUser> {
    @Select("SELECT * FROM sys_user_role where user_id=#{userId}")
    List<SysUserVo> getRoleByUserId(@Param("userId") Long userId);
    @Select("SELECT * FROM sys_user where user_id=#{user_id}")
    SysUserVo getDeptByUserId(@Param("userId") Long userId);
}
