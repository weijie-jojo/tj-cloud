package com.ruoyi.project.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 发票对象 self_ticket
 *
 * @author ruoyi
 * @date 2022-07-06
 */
@Data
public class SelfTicket extends BaseEntity
{
    private static final long serialVersionUID = 1L;

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

    /** 发票种类编号 */
    @Excel(name = "是否删除")
    private String isDeleted;


    public void setTicketId(String ticketId)
    {
        this.ticketId = ticketId;
    }

    public String getTicketId()
    {
        return ticketId;
    }
    public void setProjectCode(String projectCode)
    {
        this.projectCode = projectCode;
    }

    public String getProjectCode()
    {
        return projectCode;
    }
    public void setTicketAmount(BigDecimal ticketAmount)
    {
        this.ticketAmount = ticketAmount;
    }

    public BigDecimal getTicketAmount()
    {
        return ticketAmount;
    }
    public void setTicketType(Long ticketType)
    {
        this.ticketType = ticketType;
    }

    public Long getTicketType()
    {
        return ticketType;
    }
    public void setTicketContent(String ticketContent)
    {
        this.ticketContent = ticketContent;
    }

    public String getTicketContent()
    {
        return ticketContent;
    }
    public void setTicketRemark(String ticketRemark)
    {
        this.ticketRemark = ticketRemark;
    }

    public String getTicketRemark()
    {
        return ticketRemark;
    }
    public void setTicketTax(BigDecimal ticketTax)
    {
        this.ticketTax = ticketTax;
    }

    public BigDecimal getTicketTax()
    {
        return ticketTax;
    }
    public void setTicketCode(String ticketCode)
    {
        this.ticketCode = ticketCode;
    }

    public String getTicketCode()
    {
        return ticketCode;
    }
    public void setTicketTime(Date ticketTime)
    {
        this.ticketTime = ticketTime;
    }

    public Date getTicketTime()
    {
        return ticketTime;
    }
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileName()
    {
        return fileName;
    }
    public void setTicketTypeCode(String ticketTypeCode)
    {
        this.ticketTypeCode = ticketTypeCode;
    }

    public String getTicketTypeCode()
    {
        return ticketTypeCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("ticketId", getTicketId())
                .append("projectCode", getProjectCode())
                .append("ticketAmount", getTicketAmount())
                .append("ticketType", getTicketType())
                .append("ticketContent", getTicketContent())
                .append("ticketRemark", getTicketRemark())
                .append("ticketTax", getTicketTax())
                .append("ticketCode", getTicketCode())
                .append("ticketTime", getTicketTime())
                .append("fileName", getFileName())
                .append("ticketTypeCode", getTicketTypeCode())
                .append("createBy", getCreateBy())
                .append("updateBy", getUpdateBy())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}

