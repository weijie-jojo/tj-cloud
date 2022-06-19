package com.ruoyi.company.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.company.mapper.SelfIndustryRateMapper;
import com.ruoyi.company.domain.SelfIndustryRate;
import com.ruoyi.company.service.ISelfIndustryRateService;

/**
 * 个体户行业类型税率Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-06-17
 */
@Service
public class SelfIndustryRateServiceImpl implements ISelfIndustryRateService 
{
    @Autowired
    private SelfIndustryRateMapper selfIndustryRateMapper;

    /**
     * 查询个体户行业类型税率列表(不分页)
     *
     * @return 个体户行业类型税率
     */
    @Override
    public List<SelfIndustryRate> selectSelfIndustryRate()
    {
        return selfIndustryRateMapper.selectSelfIndustryRate();
    }

    /**
     * 查询个体户行业类型税率
     * 
     * @param industryId 个体户行业类型税率主键
     * @return 个体户行业类型税率
     */
    @Override
    public SelfIndustryRate selectSelfIndustryRateByIndustryId(String industryId)
    {
        return selfIndustryRateMapper.selectSelfIndustryRateByIndustryId(industryId);
    }

    /**
     * 查询个体户行业类型税率列表
     * 
     * @param selfIndustryRate 个体户行业类型税率
     * @return 个体户行业类型税率
     */
    @Override
    public List<SelfIndustryRate> selectSelfIndustryRateList(SelfIndustryRate selfIndustryRate)
    {
        return selfIndustryRateMapper.selectSelfIndustryRateList(selfIndustryRate);
    }

    /**
     * 新增个体户行业类型税率
     * 
     * @param selfIndustryRate 个体户行业类型税率
     * @return 结果
     */
    @Override
    public int insertSelfIndustryRate(SelfIndustryRate selfIndustryRate)
    {
        selfIndustryRate.setCreateTime(DateUtils.getNowDate());
        return selfIndustryRateMapper.insertSelfIndustryRate(selfIndustryRate);
    }

    /**
     * 修改个体户行业类型税率
     * 
     * @param selfIndustryRate 个体户行业类型税率
     * @return 结果
     */
    @Override
    public int updateSelfIndustryRate(SelfIndustryRate selfIndustryRate)
    {
        selfIndustryRate.setUpdateTime(DateUtils.getNowDate());
        return selfIndustryRateMapper.updateSelfIndustryRate(selfIndustryRate);
    }

    /**
     * 批量删除个体户行业类型税率
     * 
     * @param industryIds 需要删除的个体户行业类型税率主键
     * @return 结果
     */
    @Override
    public int deleteSelfIndustryRateByIndustryIds(String[] industryIds)
    {
        return selfIndustryRateMapper.deleteSelfIndustryRateByIndustryIds(industryIds);
    }

    /**
     * 删除个体户行业类型税率信息
     * 
     * @param industryId 个体户行业类型税率主键
     * @return 结果
     */
    @Override
    public int deleteSelfIndustryRateByIndustryId(String industryId)
    {
        return selfIndustryRateMapper.deleteSelfIndustryRateByIndustryId(industryId);
    }
}
