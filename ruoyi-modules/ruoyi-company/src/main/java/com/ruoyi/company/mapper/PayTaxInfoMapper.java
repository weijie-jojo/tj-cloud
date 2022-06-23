package com.ruoyi.company.mapper;

import java.util.List;
import com.ruoyi.company.domain.PayTaxInfo;

/**
 * 纳税账户
 Mapper接口
 *
 * @author ruoyi
 * @date 2022-06-23
 */
public interface PayTaxInfoMapper
{
    /*
    * 查询所有（不分页）
    *
    * */
    public List<PayTaxInfo> selectPayTaxInfoAll();
    /**
     * 查询纳税账户

     *
     * @param taxId 纳税账户
    主键
     * @return 纳税账户

     */
    public PayTaxInfo selectPayTaxInfoByTaxId(Long taxId);

    /**
     * 查询纳税账户
     列表
     *
     * @param payTaxInfo 纳税账户

     * @return 纳税账户
    集合
     */
    public List<PayTaxInfo> selectPayTaxInfoList(PayTaxInfo payTaxInfo);

    /**
     * 新增纳税账户

     *
     * @param payTaxInfo 纳税账户

     * @return 结果
     */
    public int insertPayTaxInfo(PayTaxInfo payTaxInfo);

    /**
     * 修改纳税账户

     *
     * @param payTaxInfo 纳税账户

     * @return 结果
     */
    public int updatePayTaxInfo(PayTaxInfo payTaxInfo);

    /**
     * 删除纳税账户

     *
     * @param taxId 纳税账户
    主键
     * @return 结果
     */
    public int deletePayTaxInfoByTaxId(Long taxId);

    /**
     * 批量删除纳税账户

     *
     * @param taxIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePayTaxInfoByTaxIds(Long[] taxIds);
}
