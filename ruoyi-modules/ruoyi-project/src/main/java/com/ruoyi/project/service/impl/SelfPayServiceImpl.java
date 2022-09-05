package com.ruoyi.project.service.impl;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.project.domain.SelfPay;
import com.ruoyi.project.domain.SelfPayReceive;
import com.ruoyi.project.mapper.SelfPayMapper;
import com.ruoyi.project.service.ISelfPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 出款信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-09-05
 */
@Service
public class SelfPayServiceImpl implements ISelfPayService
{
    @Autowired
    private SelfPayMapper selfPayMapper;

    /**
     * 查询出款信息
     *
     * @param payId 出款信息主键
     * @return 出款信息
     */
    @Override
    public SelfPay selectSelfPayByPayId(String payId)
    {
        return selfPayMapper.selectSelfPayByPayId(payId);
    }

    /**
     * 查询
     *
     * @param projectCode
     * @return
     */
    @Override
    public List<SelfPay> selectSelfPayByProjectCode(String projectCode) {
        return selfPayMapper.selectSelfPayByProjectCode(projectCode);
    }

    /**
     * 查询出款信息列表
     *
     * @param selfPay 出款信息
     * @return 出款信息
     */
    @Override
    public List<SelfPay> selectSelfPayList(SelfPay selfPay)
    {
        return selfPayMapper.selectSelfPayList(selfPay);
    }

    /**
     * 新增出款信息
     *
     * @param selfPay 出款信息
     * @return 结果
     */
    @Override
    public int insertSelfPay(SelfPay selfPay)
    {
        selfPay.setCreateTime(DateUtils.getNowDate());
        return selfPayMapper.insertSelfPay(selfPay);
    }

    /**
     * 修改出款信息
     *
     * @param selfPay 出款信息
     * @return 结果
     */
    @Override
    public int updateSelfPay(SelfPay selfPay)
    {
        selfPay.setUpdateTime(DateUtils.getNowDate());
        return selfPayMapper.updateSelfPay(selfPay);
    }

    /**
     * 批量删除出款信息
     *
     * @param payIds 需要删除的出款信息主键
     * @return 结果
     */
    @Override
    public int deleteSelfPayByPayIds(String[] payIds)
    {
        return selfPayMapper.deleteSelfPayByPayIds(payIds);
    }

    /**
     * 删除出款信息信息
     *
     * @param payId 出款信息主键
     * @return 结果
     */
    @Override
    public int deleteSelfPayByPayId(String payId)
    {
        return selfPayMapper.deleteSelfPayByPayId(payId);
    }
}
