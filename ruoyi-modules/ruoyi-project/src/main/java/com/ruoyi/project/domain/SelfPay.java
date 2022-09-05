package com.ruoyi.project.domain;

import com.ruoyi.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 出款信息对象 self_pay
 *
 * @author ruoyi
 * @date 2022-09-05
 */
@Data
public class SelfPay extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String payId;

    /** 收付款流水号（关联收付款表） */
    @ApiModelProperty(value = "收付款流水号 关联收付款表")
    private String payReceiveSysCode;

    /** 出账账户（出款信息） */
    @ApiModelProperty(value = "出账账户")
    private String payName;

    /** 转账时间（出款信息） */
    @ApiModelProperty(value = "转账时间")
    private Date payTime;

    /** 出款金额（出款信息） */
    @ApiModelProperty(value = "出款金额")
    private BigDecimal payMoney;

    /** 出账帐号（出款信息） */
    @ApiModelProperty(value = "出账帐号")
    private String payAccount;

    /** 出账凭证（出款信息） */
    @ApiModelProperty(value = "出账凭证")
    private String fileNamePay;

    /** 项目编号 */
    @ApiModelProperty(value = "项目编号")
    private String projectCode;

    /** 付款流水号（财务输入） */
    @ApiModelProperty(value = "付款流水号 财务输入")
    private String payCode;

    /** 付款流水号（关联收付款表） */
    @ApiModelProperty(value = "付款流水号 关联收付款表")
    private String paySysCode;
    
}
