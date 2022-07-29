package com.ruoyi.project.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.checkerframework.checker.formatter.qual.Format;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 项目信息对象 self_project
 *
 * @author ruoyi
 * @date 2022-07-06
 */
@Data
public class SelfProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 创建时间 */
    private String start;

    /** 创建时间 */
    private String end;

    /** ID */
    private String projectId;

    /** 项目编号 */
    @Excel(name = "项目编号")
    private String projectCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 所属行业 */
    @Excel(name = "所属行业")
    private String projectTrade;

    /** 项目主体(乙方) */
    @Excel(name = "乙方(个体户编号)")
    private String projectOwner;

    /** 个体户名 */
    @Excel(name = "个体户名")
    private String selfName;

    /** 采购方公司（甲方） */
    @Excel(name = "采购方公司", readConverterExp = "甲=方")
    private String purchCompany;

    /** 项目总金额 */
    @Excel(name = "项目总金额")
    private BigDecimal projectTotalAmount;

    /** 项目负责人(业务经理) */
    @Excel(name = "项目负责人(业务经理)")
    private String projectLeader;

    /** 项目毛利率(%) */
    @Excel(name = "项目毛利率(%)")
    private BigDecimal projectGrossMargin;

    /** 项目毛利润 */
    @Excel(name = "项目毛利润")
    private BigDecimal projectGrossProfit;

    /** 项目净利润 */
    @Excel(name = "项目净利润")
    private BigDecimal projectNetProfit;

    /** 分包总金额 */
    @Excel(name = "分包总金额")
    private BigDecimal projectPackageAmount;

    /** 项目描述(开票内容） */
    @Excel(name = "项目描述(开票内容）")
    private String projectDesc;

    /** 项目状态,0:;1:;2:;3: 0进行 1异常 2完结 */
    @Excel(name = "项目状态,0:;1:;2:;3: 0进行 1异常 2完结")
    private Long projectStatus;

    /** 是否删除;1:删除,0:未删除 */
    @Excel(name = "是否删除;1:删除,0:未删除")
    private String isDeleted;

    /** 审核意见（驳回原因） */
    @Excel(name = "审核意见", readConverterExp = "驳=回原因")
    private String checkContent;

    /** 开票内容 */
    @Excel(name = "开票内容")
    private String fileName;

    /** 项目时间开始 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @Excel(name = "项目时间开始", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime projectTimeStart;

    /** 项目时间结束 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @Excel(name = "项目时间结束", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime projectTimeEnd;

    /** 审核状态 0否 1是 */
    @Excel(name = "审核状态 0否 1是")
    private String projectCheckStatus;

    /** 开票状态 0否 1是 */
    @Excel(name = "开票状态 0否 1是")
    private String projectTicketStatus;

    /** 合同状态 0否 1是 */
    @Excel(name = "合同状态 0否 1是")
    private String projectContractStatus;

    /** 验收状态 0否 1是 */
    @Excel(name = "验收状态 0否 1是")
    private String projectAcceptanceStatus;

    /** 完税状态 0否 1是 */
    @Excel(name = "完税状态 0否 1是")
    private String projectDutypaidStatus;

    /** 渠道商编码 */
    @Excel(name = "渠道商编码")
    private String placeCode;

    /** 渠道商编码 */
    @Excel(name = "甲方纳税人识别号")
    private String purchCompanyTaxid;

    /** 发票类型 */
    @Excel(name = "发票类型")
    private Long ticketType;

    /** 发票税率 */
    @Excel(name = "发票税率")
    private BigDecimal ticketTax;

    /** 乙方纳税人识别号 */
    @Excel(name = "乙方纳税人识别号")
    private String projectOwnerTaxid;

    /** 剩余可用金额 */
    @Excel(name = "剩余可用金额")
    private BigDecimal projectRemainAmount;

    /** 票据审核不通过备注 */
    @Excel(name = "票据审核不通过备注")
    private String ticketRemark;

    /** 合同审核不通过备注 */
    @Excel(name = "合同审核不通过备注")
    private String contractRemark;

    /** 验收审核不通过备注 */
    @Excel(name = "验收审核不通过备注")
    private String checkRemark;

    /** 完税审核不通过备注 */
    @Excel(name = "完税审核不通过备注")
    private String taxRemark;

    /** 合同 */
    @Excel(name = "合同")
    private String fileName1;

    /** 验收 */
    @Excel(name = "验收")
    private String fileName2;

    /** 完税 */
    @Excel(name = "完税")
    private String fileName3;

    public void setProjectId(String projectId)
    {
        this.projectId = projectId;
    }

    public String getProjectId()
    {
        return projectId;
    }
    public void setProjectCode(String projectCode)
    {
        this.projectCode = projectCode;
    }

    public String getProjectCode()
    {
        return projectCode;
    }
    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }

    public String getProjectName()
    {
        return projectName;
    }
    public void setProjectTrade(String projectTrade)
    {
        this.projectTrade = projectTrade;
    }

    public String getProjectTrade()
    {
        return projectTrade;
    }
    public void setProjectOwner(String projectOwner)
    {
        this.projectOwner = projectOwner;
    }

    public String getProjectOwner()
    {
        return projectOwner;
    }
    public void setPurchCompany(String purchCompany)
    {
        this.purchCompany = purchCompany;
    }

    public String getPurchCompany()
    {
        return purchCompany;
    }
    public void setProjectTotalAmount(BigDecimal projectTotalAmount)
    {
        this.projectTotalAmount = projectTotalAmount;
    }

    public BigDecimal getProjectTotalAmount()
    {
        return projectTotalAmount;
    }
    public void setProjectLeader(String projectLeader)
    {
        this.projectLeader = projectLeader;
    }

    public String getProjectLeader()
    {
        return projectLeader;
    }
    public void setProjectGrossMargin(BigDecimal projectGrossMargin)
    {
        this.projectGrossMargin = projectGrossMargin;
    }

    public BigDecimal getProjectGrossMargin()
    {
        return projectGrossMargin;
    }
    public void setProjectGrossProfit(BigDecimal projectGrossProfit)
    {
        this.projectGrossProfit = projectGrossProfit;
    }

    public BigDecimal getProjectGrossProfit()
    {
        return projectGrossProfit;
    }
    public void setProjectNetProfit(BigDecimal projectNetProfit)
    {
        this.projectNetProfit = projectNetProfit;
    }

    public BigDecimal getProjectNetProfit()
    {
        return projectNetProfit;
    }
    public void setProjectPackageAmount(BigDecimal projectPackageAmount)
    {
        this.projectPackageAmount = projectPackageAmount;
    }

    public BigDecimal getProjectPackageAmount()
    {
        return projectPackageAmount;
    }
    public void setProjectDesc(String projectDesc)
    {
        this.projectDesc = projectDesc;
    }

    public String getProjectDesc()
    {
        return projectDesc;
    }
    public void setProjectStatus(Long projectStatus)
    {
        this.projectStatus = projectStatus;
    }

    public Long getProjectStatus()
    {
        return projectStatus;
    }
    public void setIsDeleted(String isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    public String getIsDeleted()
    {
        return isDeleted;
    }
    public void setCheckContent(String checkContent)
    {
        this.checkContent = checkContent;
    }

    public String getCheckContent()
    {
        return checkContent;
    }
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileName()
    {
        return fileName;
    }
    public void setProjectTimeStart(LocalDateTime projectTimeStart)
    {
        this.projectTimeStart = projectTimeStart;
    }

    public LocalDateTime getProjectTimeStart()
    {
        return projectTimeStart;
    }
    public void setProjectTimeEnd(LocalDateTime projectTimeEnd)
    {
        this.projectTimeEnd = projectTimeEnd;
    }

    public LocalDateTime getProjectTimeEnd()
    {
        return projectTimeEnd;
    }
    public void setProjectCheckStatus(String projectCheckStatus)
    {
        this.projectCheckStatus = projectCheckStatus;
    }

    public String getProjectCheckStatus()
    {
        return projectCheckStatus;
    }
    public void setProjectTicketStatus(String projectTicketStatus)
    {
        this.projectTicketStatus = projectTicketStatus;
    }

    public String getProjectTicketStatus()
    {
        return projectTicketStatus;
    }
    public void setProjectContractStatus(String projectContractStatus)
    {
        this.projectContractStatus = projectContractStatus;
    }

    public String getProjectContractStatus()
    {
        return projectContractStatus;
    }
    public void setProjectAcceptanceStatus(String projectAcceptanceStatus)
    {
        this.projectAcceptanceStatus = projectAcceptanceStatus;
    }

    public String getProjectAcceptanceStatus()
    {
        return projectAcceptanceStatus;
    }
    public void setProjectDutypaidStatus(String projectDutypaidStatus)
    {
        this.projectDutypaidStatus = projectDutypaidStatus;
    }

    public String getProjectDutypaidStatus()
    {
        return projectDutypaidStatus;
    }
    public void setPlaceCode(String placeCode)
    {
        this.placeCode = placeCode;
    }

    public String getPlaceCode()
    {
        return placeCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("projectId", getProjectId())
                .append("projectCode", getProjectCode())
                .append("projectName", getProjectName())
                .append("projectTrade", getProjectTrade())
                .append("projectOwner", getProjectOwner())
                .append("purchCompany", getPurchCompany())
                .append("projectTotalAmount", getProjectTotalAmount())
                .append("projectLeader", getProjectLeader())
                .append("projectGrossMargin", getProjectGrossMargin())
                .append("projectGrossProfit", getProjectGrossProfit())
                .append("projectNetProfit", getProjectNetProfit())
                .append("projectPackageAmount", getProjectPackageAmount())
                .append("projectDesc", getProjectDesc())
                .append("projectStatus", getProjectStatus())
                .append("isDeleted", getIsDeleted())
                .append("createBy", getCreateBy())
                .append("updateBy", getUpdateBy())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("checkContent", getCheckContent())
                .append("fileName", getFileName())
                .append("projectTimeStart", getProjectTimeStart())
                .append("projectTimeEnd", getProjectTimeEnd())
                .append("projectCheckStatus", getProjectCheckStatus())
                .append("projectTicketStatus", getProjectTicketStatus())
                .append("projectContractStatus", getProjectContractStatus())
                .append("projectAcceptanceStatus", getProjectAcceptanceStatus())
                .append("projectDutypaidStatus", getProjectDutypaidStatus())
                .append("placeCode", getPlaceCode())
                .toString();
    }
}
