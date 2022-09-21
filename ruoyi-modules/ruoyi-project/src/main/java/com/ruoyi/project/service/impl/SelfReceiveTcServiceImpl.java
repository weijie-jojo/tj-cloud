package com.ruoyi.project.service.impl;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.project.domain.SelfReceive;
import com.ruoyi.project.domain.vo.SelfPayReceiveVo;
import com.ruoyi.project.mapper.SelfReceiveTcMapper;
import com.ruoyi.project.service.ISelfReceiveTcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收付款信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-09-06
 */
@Service
public class SelfReceiveTcServiceImpl implements ISelfReceiveTcService
{
    @Autowired
    private SelfReceiveTcMapper selfReceiveMapper;

    /*
     *
     * 查询最后一条数据
     * */
    @Override
    public SelfReceive selectLast() {
        return selfReceiveMapper.selectLast();
    }
    /*
     * 根据receiveSysCode获取收付款流水
     *
     * */
    @Override
    public List<SelfPayReceiveVo> selectSelfPayReceiveJoinByCode(String receiveSysCode) {
        return selfReceiveMapper.selectSelfPayReceiveJoinByCode(receiveSysCode);
    }
    /**
     * 获取出款信息详细信息(根据projectCode)
     *
     * @param projectCode
     * @return List<SelfReceive>
     */
    @Override
    public List<SelfReceive> selectSelfReceiveByProjectCode(String projectCode) {
        return selfReceiveMapper.selectSelfReceiveByProjectCode(projectCode);
    }

    /**
     * 查询收付款信息
     *
     * @param receiveId 收付款信息主键
     * @return 收付款信息
     */
    @Override
    public SelfReceive selectSelfReceiveByReceiveId(String receiveId)
    {
        return selfReceiveMapper.selectSelfReceiveByReceiveId(receiveId);
    }

    /**
     * 查询收付款信息列表
     *
     * @param selfReceive 收付款信息
     * @return 收付款信息
     */
    @Override
    public List<SelfReceive> selectSelfReceiveList(SelfReceive selfReceive)
    {
        return selfReceiveMapper.selectSelfReceiveList(selfReceive);
    }

    /**
     * 新增收付款信息
     *
     * @param selfReceive 收付款信息
     * @return 结果
     */
    @Override
    public int insertSelfReceive(SelfReceive selfReceive)
    {
        selfReceive.setCreateTime(DateUtils.getNowDate());
        return selfReceiveMapper.insertSelfReceive(selfReceive);
    }

    /**
     * 修改收付款信息
     *
     * @param selfReceive 收付款信息
     * @return 结果
     */
    @Override
    public int updateSelfReceive(SelfReceive selfReceive)
    {
        selfReceive.setUpdateTime(DateUtils.getNowDate());
        return selfReceiveMapper.updateSelfReceive(selfReceive);
    }

    /**
     * 批量删除收付款信息
     *
     * @param receiveIds 需要删除的收付款信息主键
     * @return 结果
     */
    @Override
    public int deleteSelfReceiveByReceiveIds(String[] receiveIds)
    {
        return selfReceiveMapper.deleteSelfReceiveByReceiveIds(receiveIds);
    }

    /**
     * 删除收付款信息信息
     *
     * @param receiveId 收付款信息主键
     * @return 结果
     */
    @Override
    public int deleteSelfReceiveByReceiveId(String receiveId)
    {
        return selfReceiveMapper.deleteSelfReceiveByReceiveId(receiveId);
    }

    /*
     *
     * 回收
     * */
    @Override
    public int deleteReceiveByCode(String receiveSysCode) {
        Integer num1=selfReceiveMapper.recycle(receiveSysCode);
        Integer num2=selfReceiveMapper.deleteReceiveByCode(receiveSysCode);
        return num1+num2;
    }

}
