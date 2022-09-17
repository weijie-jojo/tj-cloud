package com.ruoyi.project.service;

import com.ruoyi.project.domain.SelfPurchCompany;

import java.util.List;

/**
 * 甲方信息Service接口
 *
 * @author ruoyi
 * @date 2022-09-01
 */
public interface ISelfPurchCompanyTcService
{
    /**
     * 查询甲方信息
     *
     * @param purchId 甲方信息主键
     * @return 甲方信息
     */
    public SelfPurchCompany selectSelfPurchCompanyByPurchId(String purchId);

    /**
     * 查询甲方信息列表
     *
     * @param selfPurchCompany 甲方信息
     * @return 甲方信息集合
     */
    public List<SelfPurchCompany> selectSelfPurchCompanyList(SelfPurchCompany selfPurchCompany);

    /**
     * 新增甲方信息
     *
     * @param selfPurchCompany 甲方信息
     * @return 结果
     */
    public int insertSelfPurchCompany(SelfPurchCompany selfPurchCompany);

    /**
     * 修改甲方信息
     *
     * @param selfPurchCompany 甲方信息
     * @return 结果
     */
    public int updateSelfPurchCompany(SelfPurchCompany selfPurchCompany);

    /**
     * 批量删除甲方信息
     *
     * @param purchIds 需要删除的甲方信息主键集合
     * @return 结果
     */
    public int deleteSelfPurchCompanyByPurchIds(String[] purchIds);

    /**
     * 删除甲方信息信息
     *
     * @param purchId 甲方信息主键
     * @return 结果
     */
    public int deleteSelfPurchCompanyByPurchId(String purchId);
}
