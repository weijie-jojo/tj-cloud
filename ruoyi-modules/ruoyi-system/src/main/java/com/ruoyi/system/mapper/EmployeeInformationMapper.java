package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.EmployeeInformation;

import java.util.List;

/**
 * 员工信息Mapper接口
 * 
 * @author employeeInformation
 * @date 2022-06-17
 */
public interface EmployeeInformationMapper {

    /**
     * 查询数据库里面最后一条数据
     *
     * @return 用户集合
     */
    public EmployeeInformation selectMaxCode();


    /**
     * 查询员工信息（不分页）
     *
     * @return 员工信息
     */
    public List<EmployeeInformation> selectEmployeeInformation();

    /**
     * 查询员工信息
     * 
     * @param userId 员工信息主键
     * @return 员工信息
     */
    public EmployeeInformation selectEmployeeInformationByUserId(Long userId);

    /**
     * 查询员工信息列表
     * 
     * @param employeeInformation 员工信息
     * @return 员工信息集合
     */
    public List<EmployeeInformation> selectEmployeeInformationList(EmployeeInformation employeeInformation);

    /**
     * 新增员工信息
     * 
     * @param employeeInformation 员工信息
     * @return 结果
     */
    public int insertEmployeeInformation(EmployeeInformation employeeInformation);

    /**
     * 修改员工信息
     * 
     * @param employeeInformation 员工信息
     * @return 结果
     */
    public int updateEmployeeInformation(EmployeeInformation employeeInformation);

    /**
     * 删除员工信息
     * 
     * @param userId 员工信息主键
     * @return 结果
     */
    public int deleteEmployeeInformationByUserId(Long userId);

    /**
     * 批量删除员工信息
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEmployeeInformationByUserIds(Long[] userIds);
}
