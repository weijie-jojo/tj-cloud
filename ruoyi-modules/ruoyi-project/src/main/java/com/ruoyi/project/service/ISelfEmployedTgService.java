package com.ruoyi.project.service;

import com.ruoyi.project.domain.SelfEmployed;
import com.ruoyi.project.domain.vo.SelfEmployedVo;

import java.util.List;

/**
 * 个体商户Service接口
 *
 * @author ruoyi
 * @date 2022-07-06
 */
public interface ISelfEmployedTgService
{
    /**
     * 查询个体商户列表(根据业务员跟行业类型)
     *
     * @param userIdArr,industryType
     * @return 个体商户集合
     */
    public List<SelfEmployedVo> selectSelfEmployedByUser(List<Long> userIdArr);

    /**
     * 查询个体商户
     *
     * @param selfId 个体商户主键
     * @return 个体商户
     */
    public SelfEmployed selectSelfEmployedBySelfId(String selfId);
    /**
     * 查询个体商户
     *
     * @param selfCode 个体商户主键
     * @return 个体商户
     */
    public SelfEmployed selectSelfEmployedBySelfCode(String selfCode);
    /**
     * 查询个体商户列表
     *
     * @param selfEmployed 个体商户
     * @return 个体商户集合
     */
    public List<SelfEmployed> selectSelfEmployedList(SelfEmployed selfEmployed);

    /**
     * 新增个体商户
     *
     * @param selfEmployed 个体商户
     * @return 结果
     */
    public int insertSelfEmployed(SelfEmployed selfEmployed);

    /**
     * 修改个体商户
     *
     * @param selfEmployed 个体商户
     * @return 结果
     */
    public int updateSelfEmployed(SelfEmployed selfEmployed);

    /**
     * 批量删除个体商户
     *
     * @param selfIds 需要删除的个体商户主键集合
     * @return 结果
     */
    public int deleteSelfEmployedBySelfIds(String[] selfIds);

    /**
     * 删除个体商户信息
     *
     * @param selfId 个体商户主键
     * @return 结果
     */
    public int deleteSelfEmployedBySelfId(String selfId);
}

