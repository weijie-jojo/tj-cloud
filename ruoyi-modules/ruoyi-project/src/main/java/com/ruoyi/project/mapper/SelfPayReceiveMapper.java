package com.ruoyi.project.mapper;

import com.ruoyi.project.domain.SelfPayReceive;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2022-09-02
 */
public interface SelfPayReceiveMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param payReceiveId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public SelfPayReceive selectSelfPayReceiveByPayReceiveId(String payReceiveId);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param selfPayReceive 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<SelfPayReceive> selectSelfPayReceiveList(SelfPayReceive selfPayReceive);

    /**
     * 新增【请填写功能名称】
     *
     * @param selfPayReceive 【请填写功能名称】
     * @return 结果
     */
    public int insertSelfPayReceive(SelfPayReceive selfPayReceive);

    /**
     * 修改【请填写功能名称】
     *
     * @param selfPayReceive 【请填写功能名称】
     * @return 结果
     */
    public int updateSelfPayReceive(SelfPayReceive selfPayReceive);

    /**
     * 删除【请填写功能名称】
     *
     * @param payReceiveId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteSelfPayReceiveByPayReceiveId(String payReceiveId);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param payReceiveIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSelfPayReceiveByPayReceiveIds(String[] payReceiveIds);
}
