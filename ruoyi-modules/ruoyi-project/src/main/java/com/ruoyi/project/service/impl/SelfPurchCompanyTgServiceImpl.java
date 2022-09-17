package com.ruoyi.project.service.impl;

import com.ruoyi.project.domain.SelfPurchCompany;
import com.ruoyi.project.mapper.SelfPurchCompanyMapper;
import com.ruoyi.project.service.ISelfPurchCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 甲方信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-09-01
 */
@Service
public class SelfPurchCompanyTgServiceImpl implements ISelfPurchCompanyService
{
    @Autowired
    private SelfPurchCompanyMapper selfPurchCompanyMapper;

    /**
     * 查询甲方信息
     *
     * @param purchId 甲方信息主键
     * @return 甲方信息
     */
    @Override
    public SelfPurchCompany selectSelfPurchCompanyByPurchId(String purchId)
    {
        return selfPurchCompanyMapper.selectSelfPurchCompanyByPurchId(purchId);
    }

    /**
     * 查询甲方信息列表
     *
     * @param selfPurchCompany 甲方信息
     * @return 甲方信息
     */
    @Override
    public List<SelfPurchCompany> selectSelfPurchCompanyList(SelfPurchCompany selfPurchCompany)
    {
        return selfPurchCompanyMapper.selectSelfPurchCompanyList(selfPurchCompany);
    }

    /**
     * 新增甲方信息
     *
     * @param selfPurchCompany 甲方信息
     * @return 结果
     */
    @Override
    public int insertSelfPurchCompany(SelfPurchCompany selfPurchCompany)
    {
        return selfPurchCompanyMapper.insertSelfPurchCompany(selfPurchCompany);
    }

    /**
     * 修改甲方信息
     *
     * @param selfPurchCompany 甲方信息
     * @return 结果
     */
    @Override
    public int updateSelfPurchCompany(SelfPurchCompany selfPurchCompany)
    {
        return selfPurchCompanyMapper.updateSelfPurchCompany(selfPurchCompany);
    }

    /**
     * 批量删除甲方信息
     *
     * @param purchIds 需要删除的甲方信息主键
     * @return 结果
     */
    @Override
    public int deleteSelfPurchCompanyByPurchIds(String[] purchIds)
    {
        return selfPurchCompanyMapper.deleteSelfPurchCompanyByPurchIds(purchIds);
    }

    /**
     * 删除甲方信息信息
     *
     * @param purchId 甲方信息主键
     * @return 结果
     */
    @Override
    public int deleteSelfPurchCompanyByPurchId(String purchId)
    {
        return selfPurchCompanyMapper.deleteSelfPurchCompanyByPurchId(purchId);
    }
}
