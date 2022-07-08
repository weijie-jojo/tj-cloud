package com.ruoyi.project.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.project.domain.SelfProject;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class ProjectJoinTicketVo extends SelfProject {
    /*selfTicket表*/
    /** ID */
    private String ticketId;

    /** 项目编号 */
    @Excel(name = "项目编号")
    private String projectCode;

    /** 开票金额 */
    @Excel(name = "开票金额")
    private BigDecimal ticketAmount;

    /** 发票类型 */
    @Excel(name = "发票类型")
    private Long ticketType;

    /** 开票内容 */
    @Excel(name = "开票内容")
    private String ticketContent;

    /** 发票备注 */
    @Excel(name = "发票备注")
    private String ticketRemark;

    /** 发票税率 */
    @Excel(name = "发票税率")
    private BigDecimal ticketTax;

    /** 发票编号 */
    @Excel(name = "发票编号")
    private String ticketCode;

    /** 开票时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开票时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ticketTime;

    /** 发票影像 */
    @Excel(name = "发票影像")
    private String fileName;

    /** 发票种类编号 */
    @Excel(name = "发票种类编号")
    private String ticketTypeCode;

    /*SelfEmployed表*/
    /** 个体户名称 */
    @Excel(name = "个体户名称")
    private String selfName;

    /** 税号 */
    @Excel(name = "税号")
    private String taxId;

    /** 经营范围 */
    @Excel(name = "经营范围")
    private String natureBusiness;

    /** 行业类型 */
    @Excel(name = "行业类型")
    private String industryType;

    /** 行业税率 */
    @Excel(name = "行业税率")
    private BigDecimal industryTax;

    /** 状态 0休眠 1激活 2报警 */
    @Excel(name = "状态 0休眠 1激活 2报警")
    private Long isActive;

    /*BusinessPlace表*/
    /** 渠道商名称 */
    @Excel(name = "渠道商名称")
    private String placeName;

    /** 渠道商状态(0:正常 1:锁定 ) */
    @Excel(name = "渠道商状态(0:正常 1:锁定 )")
    private Long placeStatus;
}
