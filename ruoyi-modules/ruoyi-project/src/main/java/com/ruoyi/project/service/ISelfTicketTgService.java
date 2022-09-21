package com.ruoyi.project.service;

import com.ruoyi.project.domain.SelfTicket;

import java.util.List;

/**
 * 发票Service接口
 *
 * @author ruoyi
 * @date 2022-07-06
 */
public interface ISelfTicketTgService
{
    /**
     * 查询发票
     *
     * @param ticketId 发票主键
     * @return 发票
     */
    public SelfTicket selectSelfTicketByTicketId(String ticketId);
    /**
     * 查询发票
     *
     * @param projectCode 项目编号
     * @return 发票
     */
    public List<SelfTicket> selectSelfTicketByProjectCode(String projectCode);
    /**
     * 查询发票列表
     *
     * @param selfTicket 发票
     * @return 发票集合
     */
    public List<SelfTicket> selectSelfTicketList(SelfTicket selfTicket);

    /**
     * 新增发票
     *
     * @param selfTicket 发票
     * @return 结果
     */
    public int insertSelfTicket(SelfTicket selfTicket);

    /**
     * 修改发票
     *
     * @param selfTicket 发票
     * @return 结果
     */
    public int updateSelfTicket(SelfTicket selfTicket);

    /**
     * 批量删除发票
     *
     * @param ticketIds 需要删除的发票主键集合
     * @return 结果
     */
    public int deleteSelfTicketByTicketIds(String[] ticketIds);

    /**
     * 删除发票信息
     *
     * @param ticketId 发票主键
     * @return 结果
     */
    public int deleteSelfTicketByTicketId(String ticketId);

    /**
     * 批量删除发票(伪删)
     *
     * @param ticketIds 需要删除的发票主键集合
     * @return 结果
     */
    public int deleteSelfTicketByTicketIds2(String[] ticketIds);

    /**
     * 删除发票信息(伪删)
     *
     * @param ticketId 发票主键
     * @return 结果
     */
    public int deleteSelfTicketByTicketId2(String ticketId);
}
