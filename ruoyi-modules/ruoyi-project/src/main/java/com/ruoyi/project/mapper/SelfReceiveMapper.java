package com.ruoyi.project.mapper;

import com.ruoyi.project.domain.SelfReceive;
import com.ruoyi.project.domain.vo.SelfPayReceiveVo;

import java.util.List;

/**
 * 收付款信息Mapper接口
 *
 * @author ruoyi
 * @date 2022-09-06
 */
public interface SelfReceiveMapper
{
    /**
     * 获取最后一条数据
     *
     * @return 项目信息
     */
    public SelfReceive selectLast();

    /*
     * 根据receiveSysCode获取收付款流水
     *
     * */
    public List<SelfPayReceiveVo> selectSelfPayReceiveJoinByCode(String receiveSysCode);

    /**
     * 查询
     *
     * @param projectCode
     * @return
     */
    public List<SelfReceive> selectSelfReceiveByProjectCode(String projectCode);


    /**
     * 查询收付款信息
     *
     * @param receiveId 收付款信息主键
     * @return 收付款信息
     */
    public SelfReceive selectSelfReceiveByReceiveId(String receiveId);

    /**
     * 查询收付款信息列表
     *
     * @param selfReceive 收付款信息
     * @return 收付款信息集合
     */
    public List<SelfReceive> selectSelfReceiveList(SelfReceive selfReceive);

    /**
     * 新增收付款信息
     *
     * @param selfReceive 收付款信息
     * @return 结果
     */
    public int insertSelfReceive(SelfReceive selfReceive);

    /**
     * 修改收付款信息
     *
     * @param selfReceive 收付款信息
     * @return 结果
     */
    public int updateSelfReceive(SelfReceive selfReceive);

    /**
     * 删除收付款信息
     *
     * @param receiveId 收付款信息主键
     * @return 结果
     */
    public int deleteSelfReceiveByReceiveId(String receiveId);

    /**
     * 批量删除收付款信息
     *
     * @param receiveIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSelfReceiveByReceiveIds(String[] receiveIds);
}
