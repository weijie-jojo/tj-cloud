package com.ruoyi.project.mapper;

import com.ruoyi.project.domain.SelfIndustryRate;

import java.util.List;

/**
 * 个体户行业类型税率Mapper接口
 * 
 * @author ruoyi
 * @date 2022-06-17
 */
public interface SelfIndustryRateMapper 
{
    /**
     * 查询个体户行业类型税率列表(不分页)
     *
     * @return 个体户行业类型税率集合
     */
    public List<SelfIndustryRate> selectSelfIndustryRate();

    /**
     * 查询个体户行业类型税率
     * 
     * @param industryId 个体户行业类型税率主键
     * @return 个体户行业类型税率
     */
    public SelfIndustryRate selectSelfIndustryRateByIndustryId(String industryId);

    /**
     * 查询个体户行业类型税率列表
     * 
     * @param selfIndustryRate 个体户行业类型税率
     * @return 个体户行业类型税率集合
     */
    public List<SelfIndustryRate> selectSelfIndustryRateList(SelfIndustryRate selfIndustryRate);

    /**
     * 新增个体户行业类型税率
     * 
     * @param selfIndustryRate 个体户行业类型税率
     * @return 结果
     */
    public int insertSelfIndustryRate(SelfIndustryRate selfIndustryRate);

    /**
     * 修改个体户行业类型税率
     * 
     * @param selfIndustryRate 个体户行业类型税率
     * @return 结果
     */
    public int updateSelfIndustryRate(SelfIndustryRate selfIndustryRate);

    /**
     * 删除个体户行业类型税率
     * 
     * @param industryId 个体户行业类型税率主键
     * @return 结果
     */
    public int deleteSelfIndustryRateByIndustryId(String industryId);

    /**
     * 批量删除个体户行业类型税率
     * 
     * @param industryIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSelfIndustryRateByIndustryIds(String[] industryIds);
}
