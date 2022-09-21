package com.ruoyi.project.service.impl;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.project.domain.SelfTicket;
import com.ruoyi.project.mapper.SelfTicketTcMapper;
import com.ruoyi.project.service.ISelfTicketTcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 发票Service业务层处理
 *
 * @author ruoyi
 * @date 2022-07-06
 */
@Service
public class SelfTicketTcServiceImpl implements ISelfTicketTcService
{
    @Autowired
    private SelfTicketTcMapper selfTicketMapper;

    /**
     * 查询发票
     *
     * @param ticketId 发票主键
     * @return 发票
     */
    @Override
    public SelfTicket selectSelfTicketByTicketId(String ticketId)
    {
        return selfTicketMapper.selectSelfTicketByTicketId(ticketId);
    }

    /**
     * 查询发票
     *
     * @param projectCode 项目编号
     * @return 发票
     */
    @Override
    public List<SelfTicket> selectSelfTicketByProjectCode(String projectCode)
    {
        return selfTicketMapper.selectSelfTicketByProjectCode(projectCode);
    }

    /**
     * 查询发票列表
     *
     * @param selfTicket 发票
     * @return 发票
     */
    @Override
    public List<SelfTicket> selectSelfTicketList(SelfTicket selfTicket)
    {
        return selfTicketMapper.selectSelfTicketList(selfTicket);
    }

    /**
     * 新增发票
     *
     * @param selfTicket 发票
     * @return 结果
     */
    @Override
    public int insertSelfTicket(SelfTicket selfTicket)
    {
        selfTicket.setCreateTime(DateUtils.getNowDate());
        return selfTicketMapper.insertSelfTicket(selfTicket);
    }

    /**
     * 修改发票
     *
     * @param selfTicket 发票
     * @return 结果
     */
    @Override
    public int updateSelfTicket(SelfTicket selfTicket)
    {
        selfTicket.setUpdateTime(DateUtils.getNowDate());
        return selfTicketMapper.updateSelfTicket(selfTicket);
    }

    /**
     * 批量删除发票
     *
     * @param ticketIds 需要删除的发票主键
     * @return 结果
     */
    @Override
    public int deleteSelfTicketByTicketIds(String[] ticketIds)
    {
        return selfTicketMapper.deleteSelfTicketByTicketIds(ticketIds);
    }

    /**
     * 删除发票信息
     *
     * @param ticketId 发票主键
     * @return 结果
     */
    @Override
    public int deleteSelfTicketByTicketId(String ticketId)
    {
        return selfTicketMapper.deleteSelfTicketByTicketId(ticketId);
    }

    /**
     * 批量删除发票（伪删）
     *
     * @param ticketIds 需要删除的发票主键
     * @return 结果
     */
    @Override
    public int deleteSelfTicketByTicketIds2(String[] ticketIds)
    {
        return selfTicketMapper.deleteSelfTicketByTicketIds2(ticketIds);
    }

    /**
     * 删除发票信息（伪删）
     *
     * @param ticketId 发票主键
     * @return 结果
     */
    @Override
    public int deleteSelfTicketByTicketId2(String ticketId)
    {
        return selfTicketMapper.deleteSelfTicketByTicketId2(ticketId);
    }
}
