package com.ruoyi.invoice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.constant.CacheConstants;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.invoice.mapper.SysUserMapper;
import com.ruoyi.invoice.pojo.SysUser;
import com.ruoyi.invoice.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl implements SysUserService {
    private final static long expireTime = CacheConstants.EXPIRATION;

    @Resource
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisService redisService;
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
    public SysUserVo getDeptByUserId(Long userId) {
        return sysUserMapper.getDeptByUserId(userId);
    }

    @Override
    public SysUserVo getUserByUserName(String userName) {
        return sysUserMapper.getUserByUserName(userName);
    }


    @Override
    public List<SysUser> selectAll() {
        QueryWrapper<SysUser> queryWrapper=new QueryWrapper<>();
        if (redisService.getCacheObject("users")==null){
            List<SysUser> list = sysUserMapper.selectList(queryWrapper);
            redisService.setCacheObject("users", list, expireTime, TimeUnit.MINUTES);
        }
        return redisService.getCacheObject("users");
    }
}
