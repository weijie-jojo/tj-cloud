package com.ruoyi.company.service;

import com.ruoyi.company.domain.SelfApplicationInfo;

import java.util.List;

/**
 * 个体户注册申请信息Service接口
 * 
 * @author ruoyi
 * @date 2022-06-17
 */
public interface ISelfApplicationInfoTcService
{
    /**
     * 查询个体户注册申请信息
     * 
     * @param id 个体户注册申请信息主键
     * @return 个体户注册申请信息
     */
    public SelfApplicationInfo selectSelfApplicationInfoById(Long id);

    /**
     * 查询个体户注册申请信息列表
     * 
     * @param selfApplicationInfo 个体户注册申请信息
     * @return 个体户注册申请信息集合
     */
    public List<SelfApplicationInfo> selectSelfApplicationInfoList(SelfApplicationInfo selfApplicationInfo);

    /**
     * 新增个体户注册申请信息
     * 
     * @param selfApplicationInfo 个体户注册申请信息
     * @return 结果
     */
    public int insertSelfApplicationInfo(SelfApplicationInfo selfApplicationInfo);

    /**
     * 修改个体户注册申请信息
     * 
     * @param selfApplicationInfo 个体户注册申请信息
     * @return 结果
     */
    public int updateSelfApplicationInfo(SelfApplicationInfo selfApplicationInfo);

    /**
     * 批量删除个体户注册申请信息
     * 
     * @param ids 需要删除的个体户注册申请信息主键集合
     * @return 结果
     */
    public int deleteSelfApplicationInfoByIds(Long[] ids);

    /**
     * 删除个体户注册申请信息信息
     * 
     * @param id 个体户注册申请信息主键
     * @return 结果
     */
    public int deleteSelfApplicationInfoById(Long id);
}
