package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.system.domain.EmployeeInformation;
import com.ruoyi.system.mapper.EmployeeInformationMapper;
import com.ruoyi.system.service.IEmployeeInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工信息Service业务层处理
 * 
 * @author employeeInformation
 * @date 2022-06-17
 */
@Service
public class EmployeeInformationServiceImpl implements IEmployeeInformationService
{
    @Autowired
    private EmployeeInformationMapper employeeInformationMapper;

    /*
     * 查询数据库里面最后一条数据
     *
     * */
    @Override
    public  EmployeeInformation selectMaxCode(){
        return employeeInformationMapper.selectMaxCode();
    }
    /**
     * 查询员工信息（不分页）
     *
     * @return 员工信息
     */
    @Override
    public List<EmployeeInformation> selectEmployeeInformation() {
        return employeeInformationMapper.selectEmployeeInformation();
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
