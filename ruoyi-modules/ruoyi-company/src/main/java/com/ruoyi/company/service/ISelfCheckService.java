package com.ruoyi.company.service;

import java.util.List;
import com.ruoyi.company.domain.SelfCheck;

/**
 * 单据审批结果Service接口
 *
 * @author ruoyi
 * @date 2022-07-14
 */
public interface ISelfCheckService
{
    /**
     * 查询单据审批结果
     *
     * @param id 单据审批结果主键
     * @return 单据审批结果
     */
    public SelfCheck selectSelfCheckById(Long id);

    /**
     * 查询单据审批结果列表
     *
     * @param selfCheck 单据审批结果
     * @return 单据审批结果集合
     */
    public List<SelfCheck> selectSelfCheckList(SelfCheck selfCheck);

    /**
     * 新增单据审批结果
     *
     * @param selfCheck 单据审批结果
     * @return 结果
     */
    public int insertSelfCheck(SelfCheck selfCheck);

    /**
     * 修改单据审批结果
     *
     * @param selfCheck 单据审批结果
     * @return 结果
     */
    public int updateSelfCheck(SelfCheck selfCheck);

    /**
     * 批量删除单据审批结果
     *
     * @param ids 需要删除的单据审批结果主键集合
     * @return 结果
     */
    public int deleteSelfCheckByIds(Long[] ids);

    /**
     * 删除单据审批结果信息
     *
     * @param id 单据审批结果主键
     * @return 结果
     */
    public int deleteSelfCheckById(Long id);
}
