package com.ruoyi.project.domain;

import com.ruoyi.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 【请填写功能名称】对象 self_pay_receive
 *
 * @author ruoyi
 * @date 2022-09-02
 */
@Data
public class SelfPayReceive extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String payReceiveId;

    /** 项目编号 */
    @ApiModelProperty(value = "项目编号")
    private String projectCode;

    /** 收付款流水号（系统自动生成） */
    @ApiModelProperty(value = "收付款流水号 系统自动生成")
    private String payReceiveSysCode;

    /** 收付款流水号（财务输入） */
    @ApiModelProperty(value = "收付款流水号 财务输入")
    private String payReceiveCode;

    /** 项目名称 */
    @ApiModelProperty(value = "项目名称")
    private String projectName;

    /** 应收账款（项目总金额） */
    @ApiModelProperty(value = "应收账款 项目总金额")
    private BigDecimal projectTotalAmount;

    /** 已收账款（分包总金额） */
    @ApiModelProperty(value = "已收账款 分包总金额")
    private BigDecimal projectPackageAmount;

    /** 未收账款（剩余可用金额） */
    @ApiModelProperty(value = "未收账款 剩余可用金额")
    private BigDecimal projectRemainAmount;

    /** 应出账款 */
    @ApiModelProperty(value = "应出账款")
    private BigDecimal payTotalAmount;

    /** 已出账款 */
    @ApiModelProperty(value = "已出账款")
    private BigDecimal payPackageAmount;

    /** 未出账款 */
    @ApiModelProperty(value = "未出账款")
    private BigDecimal payRemainAmount;

    /** 转账账户（收款信息） */
    @ApiModelProperty(value = "转账账户 收款信息")
    private String receiveName;

    /** 转账时间（收款信息） */
    @ApiModelProperty(value = "转账时间 收款信息")
    private Date receiveTime;

    /** 收款金额（收款信息） */
    @ApiModelProperty(value = "收款金额 收款信息")
    private BigDecimal receiveMoney;

    /** 转账帐号（收款信息） */
    @ApiModelProperty(value = "转账帐号 收款信息")
    private String receiveAccount;

    /** 转账凭证（收款信息） */
    @ApiModelProperty(value = "转账凭证 收款信息")
    private String fileNameReceive;

    /** 出账账户（出款信息） */
    @ApiModelProperty(value = "出账账户 出款信息")
    private String payName;

    /** 转账时间（出款信息） */
    @ApiModelProperty(value = "转账时间 出款信息")
    private Date payTime;

    /** 出款金额（出款信息） */
    @ApiModelProperty(value = "出款金额 出款信息")
    private BigDecimal payMoney;

    /** 出账帐号（出款信息） */
    @ApiModelProperty(value = "出账帐号 出款信息")
    private String payAccount;

    /** 出账凭证（出款信息） */
    @ApiModelProperty(value = "出账凭证 出款信息")
    private String fileNamePay;

}
