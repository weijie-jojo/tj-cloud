package com.ruoyi.project.service;

import com.ruoyi.project.domain.SelfPay;

import java.util.List;

/**
 * 出款信息Service接口
 *
 * @author ruoyi
 * @date 2022-09-05
 */
public interface ISelfPayTcService
{
    /*
     *
     * 查询最后一条数据
     * */
    public SelfPay selectLast() ;

    /**
     * 获取付款信息详细信息(根据projectCode)
     *
     * @param projectCode 出款信息主键
     * @return 出款信息
     */
    public List<SelfPay> selectSelfPayByProjectCode(String projectCode);

    /**
     * 获取付款信息详细信息(根据receiveSysCode)
     *
     * @param receiveSysCode 出款信息主键
     * @return 出款信息
     */
    public List<SelfPay> selectPayByReceiveCode(String receiveSysCode);

    /**
     * 查询出款信息
     *
     * @param payId 出款信息主键
     * @return 出款信息
     */
    public SelfPay selectSelfPayByPayId(String payId);


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
     * 批量删除出款信息
     *
     * @param payIds 需要删除的出款信息主键集合
     * @return 结果
     */
    public int deleteSelfPayByPayIds(String[] payIds);

    /**
     * 删除出款信息信息
     *
     * @param payId 出款信息主键
     * @return 结果
     */
    public int deleteSelfPayByPayId(String payId);

    /**
     * 回收
     *
     */
    public int deletePayByCode(String paySysCode);
}
