package com.ruoyi.company.service.impl;

import com.ruoyi.company.domain.SelfApplicationInfo;
import com.ruoyi.company.mapper.SelfApplicationInfoTgMapper;
import com.ruoyi.company.service.ISelfApplicationInfoTgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 个体户注册申请信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-06-17
 */
@Service
public class SelfApplicationInfoTgServiceImpl implements ISelfApplicationInfoTgService
{
    @Autowired
    private SelfApplicationInfoTgMapper selfApplicationInfoMapper;

    /**
     * 查询个体户注册申请信息
     * 
     * @param id 个体户注册申请信息主键
     * @return 个体户注册申请信息
     */
    @Override
    public SelfApplicationInfo selectSelfApplicationInfoById(Long id)
    {
        return selfApplicationInfoMapper.selectSelfApplicationInfoById(id);
    }

    /**
     * 查询个体户注册申请信息列表
     * 
     * @param selfApplicationInfo 个体户注册申请信息
     * @return 个体户注册申请信息
     */
    @Override
    public List<SelfApplicationInfo> selectSelfApplicationInfoList(SelfApplicationInfo selfApplicationInfo)
    {
        return selfApplicationInfoMapper.selectSelfApplicationInfoList(selfApplicationInfo);
    }

    /**
     * 新增个体户注册申请信息
     * 
     * @param selfApplicationInfo 个体户注册申请信息
     * @return 结果
     */
    @Override
    public int insertSelfApplicationInfo(SelfApplicationInfo selfApplicationInfo)
    {
        return selfApplicationInfoMapper.insertSelfApplicationInfo(selfApplicationInfo);
    }

    /**
     * 修改个体户注册申请信息
     * 
     * @param selfApplicationInfo 个体户注册申请信息
     * @return 结果
     */
    @Override
    public int updateSelfApplicationInfo(SelfApplicationInfo selfApplicationInfo)
    {
        return selfApplicationInfoMapper.updateSelfApplicationInfo(selfApplicationInfo);
    }

    /**
     * 批量删除个体户注册申请信息
     * 
     * @param ids 需要删除的个体户注册申请信息主键
     * @return 结果
     */
    @Override
    public int deleteSelfApplicationInfoByIds(Long[] ids)
    {
        return selfApplicationInfoMapper.deleteSelfApplicationInfoByIds(ids);
    }

    /**
     * 删除个体户注册申请信息信息
     * 
     * @param id 个体户注册申请信息主键
     * @return 结果
     */
    @Override
    public int deleteSelfApplicationInfoById(Long id)
    {
        return selfApplicationInfoMapper.deleteSelfApplicationInfoById(id);
    }
}
