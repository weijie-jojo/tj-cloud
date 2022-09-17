package com.ruoyi.company.mapper;

import com.ruoyi.company.domain.SelfApplicationInfo;

import java.util.List;

/**
 * 个体户注册申请信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-06-17
 */
public interface SelfApplicationInfoTcMapper
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
     * 删除个体户注册申请信息
     * 
     * @param id 个体户注册申请信息主键
     * @return 结果
     */
    public int deleteSelfApplicationInfoById(Long id);

    /**
     * 批量删除个体户注册申请信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSelfApplicationInfoByIds(Long[] ids);
}
