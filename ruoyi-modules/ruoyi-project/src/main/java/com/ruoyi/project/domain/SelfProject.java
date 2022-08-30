package com.ruoyi.project.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

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

    /** 根据type判断获取count */
    @ApiModelProperty(value = "根据type判断获取count")
    private Integer type;

    /** 创建时间 */
    @ApiModelProperty(value = "创建时间")
    private String start;

    /** 结束时间 */
    @ApiModelProperty(value = "结束时间")
    private String end;

    /** ID */
    private String projectId;

    /** 项目编号 */
    @ApiModelProperty(value = "项目编号")
    private String projectCode;

    /** 项目名称 */
    @ApiModelProperty(value = "项目名称")
    private String projectName;

    /** 所属行业 */
    @ApiModelProperty(value = "所属行业")
    private String projectTrade;

    /** 项目主体(乙方) */
    @ApiModelProperty(value = "乙方(个体户编号)")
    private String projectOwner;

    /** 个体户名 */
    @ApiModelProperty(value = "个体户名")
    private String selfName;

    /** 采购方公司（甲方） */
    @ApiModelProperty(value = "采购方公司 甲方")
    private String purchCompany;

    /** 项目总金额 */
    @ApiModelProperty(value = "项目总金额")
    private BigDecimal projectTotalAmount;

    /** 项目负责人(业务经理) */
    @ApiModelProperty(value = "项目负责人(业务经理)")
    private String projectLeader;

    /** 项目毛利率(%) */
    @ApiModelProperty(value = "项目毛利率(%)")
    private BigDecimal projectGrossMargin;

    /** 项目毛利润 */
    @ApiModelProperty(value = "项目毛利润")
    private BigDecimal projectGrossProfit;

    /** 项目净利润 */
    @ApiModelProperty(value = "项目净利润")
    private BigDecimal projectNetProfit;

    /** 分包总金额 */
    @ApiModelProperty(value = "分包总金额")
    private BigDecimal projectPackageAmount;

    /** 项目描述(开票内容） */
    @ApiModelProperty(value = "项目描述(开票内容）")
    private String projectDesc;

    /** 项目状态,0:;1:;2:;3: 0进行 1异常 2完结 */
    @ApiModelProperty(value = "项目状态,0:;1:;2:;3: 0进行 1异常 2完结")
    private Long projectStatus;

    /** 是否删除;1:删除,0:未删除 */
    @ApiModelProperty(value = "是否删除;1:删除,0:未删除")
    private String isDeleted;

    /** 审核意见（驳回原因） */
    @ApiModelProperty(value = "审核意见 驳回原因")
    private String checkContent;

    /** 开票内容 */
    @ApiModelProperty(value = "开票内容")
    private String fileName;

    /** 项目时间开始 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "项目时间开始")
    private LocalDateTime projectTimeStart;

    /** 项目时间结束 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "项目时间结束")
    private LocalDateTime projectTimeEnd;

    /** 审核状态 0否 1是 */
    @ApiModelProperty(value = "审核状态 0否 1是")
    private Long projectCheckStatus;

    /** 开票状态 0否 1是 */
    @ApiModelProperty(value = "开票状态 0否 1是")
    private Long projectTicketStatus;

    /** 合同状态 0否 1是 */
    @ApiModelProperty(value = "合同状态 0否 1是")
    private Long projectContractStatus;

    /** 验收状态 0否 1是 */
    @ApiModelProperty(value = "验收状态 0否 1是")
    private Long projectAcceptanceStatus;

    /** 完税状态 0否 1是 */
    @ApiModelProperty(value = "完税状态 0否 1是")
    private Long projectDutypaidStatus;

    /** 渠道商编码 */
    @ApiModelProperty(value = "渠道商编码")
    private String placeCode;

    /** 渠道商编码 */
    @ApiModelProperty(value = "甲方纳税人识别号")
    private String purchCompanyTaxid;

    /** 发票类型 */
    @ApiModelProperty(value = "发票类型")
    private Long ticketType;

    /** 发票税率 */
    @ApiModelProperty(value = "发票税率")
    private BigDecimal ticketTax;

    /** 乙方纳税人识别号 */
    @ApiModelProperty(value = "乙方纳税人识别号")
    private String projectOwnerTaxid;

    /** 剩余可用金额 */
    @ApiModelProperty(value = "剩余可用金额")
    private BigDecimal projectRemainAmount;

    /** 票据审核不通过备注 */
    @ApiModelProperty(value = "票据审核不通过备注")
    private String ticketRemark;

    /** 合同审核不通过备注 */
    @ApiModelProperty(value = "合同审核不通过备注")
    private String contractRemark;

    /** 验收审核不通过备注 */
    @ApiModelProperty(value = "验收审核不通过备注")
    private String checkRemark;

    /** 完税审核不通过备注 */
    @ApiModelProperty(value = "完税审核不通过备注")
    private String taxRemark;

    /** 合同 */
    @ApiModelProperty(value = "合同")
    private String fileName1;

    /** 验收 */
    @ApiModelProperty(value = "验收")
    private String fileName2;

    /** 完税 */
    @ApiModelProperty(value = "完税")
    private String fileName3;

    /** 业务员（登录用户id） */
    @ApiModelProperty(value = "业务员 登录用户id")
    private Long userId;

    /** 完结状态 0未完结 1已完结 2未通过（异常） */
    @ApiModelProperty(value = "完结状态 0未完结 1已完结 2未通过 异常")
    private Integer endStatus;

    /** 完结时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "完结时间")
    private Date endTime;

}
