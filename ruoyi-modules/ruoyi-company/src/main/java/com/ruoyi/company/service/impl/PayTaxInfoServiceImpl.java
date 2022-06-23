package com.ruoyi.company.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.company.mapper.PayTaxInfoMapper;
import com.ruoyi.company.domain.PayTaxInfo;
import com.ruoyi.company.service.IPayTaxInfoService;

/**
 * 纳税账户
 Service业务层处理
 *
 * @author ruoyi
 * @date 2022-06-23
 */
@Service
public class PayTaxInfoServiceImpl implements IPayTaxInfoService
{
    @Autowired
    private PayTaxInfoMapper payTaxInfoMapper;

    /*
     *
     * 查询所有（不分页）
     *
     * */
    @Override
    public List<PayTaxInfo> selectPayTaxInfoAll() {
        return payTaxInfoMapper.selectPayTaxInfoAll();
    }

    /**
     * 查询纳税账户

     *
     * @param taxId 纳税账户
    主键
     * @return 纳税账户

     */
    @Override
    public PayTaxInfo selectPayTaxInfoByTaxId(Long taxId)
    {
        return payTaxInfoMapper.selectPayTaxInfoByTaxId(taxId);
    }

    /**
     * 查询纳税账户
     列表
     *
     * @param payTaxInfo 纳税账户

     * @return 纳税账户

     */
    @Override
    public List<PayTaxInfo> selectPayTaxInfoList(PayTaxInfo payTaxInfo)
    {
        return payTaxInfoMapper.selectPayTaxInfoList(payTaxInfo);
    }

    /**
     * 新增纳税账户

     *
     * @param payTaxInfo 纳税账户

     * @return 结果
     */
    @Override
    public int insertPayTaxInfo(PayTaxInfo payTaxInfo)
    {
        payTaxInfo.setCreateTime(DateUtils.getNowDate());
        return payTaxInfoMapper.insertPayTaxInfo(payTaxInfo);
    }

    /**
     * 修改纳税账户

     *
     * @param payTaxInfo 纳税账户

     * @return 结果
     */
    @Override
    public int updatePayTaxInfo(PayTaxInfo payTaxInfo)
    {
        payTaxInfo.setUpdateTime(DateUtils.getNowDate());
        return payTaxInfoMapper.updatePayTaxInfo(payTaxInfo);
    }

    /**
     * 批量删除纳税账户

     *
     * @param taxIds 需要删除的纳税账户
    主键
     * @return 结果
     */
    @Override
    public int deletePayTaxInfoByTaxIds(Long[] taxIds)
    {
        return payTaxInfoMapper.deletePayTaxInfoByTaxIds(taxIds);
    }

    /**
     * 删除纳税账户
     信息
     *
     * @param taxId 纳税账户
    主键
     * @return 结果
     */
    @Override
    public int deletePayTaxInfoByTaxId(Long taxId)
    {
        return payTaxInfoMapper.deletePayTaxInfoByTaxId(taxId);
    }
}
