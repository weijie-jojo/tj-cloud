package com.ruoyi.invoice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.invoice.mapper.SysUserMapper;
import com.ruoyi.invoice.pojo.SysUser;
import com.ruoyi.invoice.vo.SysUserVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser selectByCompany(Integer userId) {
        QueryWrapper<SysUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        return sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public List<SysUserVo> getRoleByUserId(Long userId) {
        return sysUserMapper.getRoleByUserId(userId);
    }

    @Override
    public SysUserVo getDeptByRoleId(int userId) {
        return sysUserMapper.getDeptByRoleId(userId);
    }

    @Override
    public List<SysUserVo> getUserByDeptId(int deptId) {
        return sysUserMapper.getUserByDeptId(deptId);
    }


    @Override
    public List<SysUser> selectAll() {
        QueryWrapper<SysUser> queryWrapper=new QueryWrapper<>();
        return sysUserMapper.selectList(queryWrapper);
    }
}
