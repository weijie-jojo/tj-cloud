package com.ruoyi.project.service;

import com.ruoyi.project.domain.SelfReceive;
import com.ruoyi.project.domain.vo.SelfPayReceiveVo;

import java.util.List;

/**
 * 收付款信息Service接口
 *
 * @author ruoyi
 * @date 2022-09-06
 */
public interface ISelfReceiveService
{
    /*
     *
     * 查询最后一条数据
     * */
    public SelfReceive selectLast() ;

    /*
     * 根据receiveSysCode获取收付款流水
     *
     * */
    public List<SelfPayReceiveVo> selectSelfPayReceiveJoinByCode(String receiveSysCode);

    /**
     * 获取出款信息详细信息(根据projectCode)
     *
     * @param projectCode
     * @return List<SelfReceive>
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
     * 批量删除收付款信息
     *
     * @param receiveIds 需要删除的收付款信息主键集合
     * @return 结果
     */
    public int deleteSelfReceiveByReceiveIds(String[] receiveIds);

    /**
     * 删除收付款信息信息
     *
     * @param receiveId 收付款信息主键
     * @return 结果
     */
    public int deleteSelfReceiveByReceiveId(String receiveId);
}
