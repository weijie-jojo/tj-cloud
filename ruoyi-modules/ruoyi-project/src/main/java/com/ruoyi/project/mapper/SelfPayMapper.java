package com.ruoyi.project.mapper;

import com.ruoyi.project.domain.SelfPay;
import com.ruoyi.project.domain.SelfPayReceive;

import java.util.List;

/**
 * 出款信息Mapper接口
 *
 * @author ruoyi
 * @date 2022-09-05
 */
public interface SelfPayMapper
{
    /**
     * 查询出款信息
     *
     * @param payId 出款信息主键
     * @return 出款信息
     */
    public SelfPay selectSelfPayByPayId(String payId);

    /**
     * 查询
     *
     * @param projectCode
     * @return
     */
    public List<SelfPay> selectSelfPayByProjectCode(String projectCode);

    /**
     * 查询出款信息列表
     *
     * @param selfPay 出款信息
     * @return 出款信息集合
     */
    public List<SelfPay> selectSelfPayList(SelfPay selfPay);

    /**
     * 新增出款信息
     *
     * @param selfPay 出款信息
     * @return 结果
     */
    public int insertSelfPay(SelfPay selfPay);

    /**
     * 修改出款信息
     *
     * @param selfPay 出款信息
     * @return 结果
     */
    public int updateSelfPay(SelfPay selfPay);

    /**
     * 删除出款信息
     *
     * @param payId 出款信息主键
     * @return 结果
     */
    public int deleteSelfPayByPayId(String payId);

    /**
     * 批量删除出款信息
     *
     * @param payIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSelfPayByPayIds(String[] payIds);
}
