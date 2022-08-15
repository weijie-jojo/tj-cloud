package com.ruoyi.invoice.service;

import com.ruoyi.invoice.pojo.SysUser;
import com.ruoyi.invoice.vo.SysUserVo;

import java.util.List;


public interface SysUserService {
    List<SysUser> selectAll();
    SysUser selectByCompany(Integer userId);
    List<SysUserVo> getRoleByUserId(Long userId);
    SysUserVo getDeptByUserId(Long userId);
    SysUserVo getUserByUserName(String userName);
}
