package com.ruoyi.company.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.ruoyi.common.core.constant.CacheConstants;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.company.mapper.EmployeeInformationMapper;
import com.ruoyi.company.domain.EmployeeInformation;
import com.ruoyi.company.service.IEmployeeInformationService;

/**
 * 员工信息Service业务层处理
 * 
 * @author employeeInformation
 * @date 2022-06-17
 */
@Service
public class EmployeeInformationServiceImpl implements IEmployeeInformationService 
{
    private final static long expireTime = CacheConstants.EXPIRATION;

    @Autowired
    private EmployeeInformationMapper employeeInformationMapper;

    @Autowired
    private RedisService redisService;

    /**
     * 查询员工信息（不分页）
     *
     * @return 员工信息
     */
    @Override
    public List<EmployeeInformation> selectEmployeeInformation() {
        if (redisService.getCacheObject("employeeInformation")==null){
            List<EmployeeInformation> list = employeeInformationMapper.selectEmployeeInformation();
            redisService.setCacheObject("employeeInformation", list, expireTime, TimeUnit.MINUTES);
        }
        return redisService.getCacheObject("employeeInformation");
    }

    /**
     * 查询员工信息
     * 
     * @param userId 员工信息主键
     * @return 员工信息
     */
    @Override
    public EmployeeInformation selectEmployeeInformationByUserId(Long userId)
    {
        return employeeInformationMapper.selectEmployeeInformationByUserId(userId);
    }

    /**
     * 查询员工信息列表
     * 
     * @param employeeInformation 员工信息
     * @return 员工信息
     */
    @Override
    public List<EmployeeInformation> selectEmployeeInformationList(EmployeeInformation employeeInformation) {
        return employeeInformationMapper.selectEmployeeInformationList(employeeInformation);
    }

    /**
     * 新增员工信息
     * 
     * @param employeeInformation 员工信息
     * @return 结果
     */
    @Override
    public int insertEmployeeInformation(EmployeeInformation employeeInformation)
    {
        employeeInformation.setCreateTime(DateUtils.getNowDate());
        return employeeInformationMapper.insertEmployeeInformation(employeeInformation);
    }

    /**
     * 修改员工信息
     * 
     * @param employeeInformation 员工信息
     * @return 结果
     */
    @Override
    public int updateEmployeeInformation(EmployeeInformation employeeInformation)
    {
        employeeInformation.setUpdateTime(DateUtils.getNowDate());
        return employeeInformationMapper.updateEmployeeInformation(employeeInformation);
    }

    /**
     * 批量删除员工信息
     * 
     * @param userIds 需要删除的员工信息主键
     * @return 结果
     */
    @Override
    public int deleteEmployeeInformationByUserIds(Long[] userIds)
    {
        return employeeInformationMapper.deleteEmployeeInformationByUserIds(userIds);
    }

    /**
     * 删除员工信息信息
     * 
     * @param userId 员工信息主键
     * @return 结果
     */
    @Override
    public int deleteEmployeeInformationByUserId(Long userId)
    {
        return employeeInformationMapper.deleteEmployeeInformationByUserId(userId);
    }
}
