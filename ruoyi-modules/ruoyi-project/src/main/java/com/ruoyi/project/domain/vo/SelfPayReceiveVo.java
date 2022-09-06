package com.ruoyi.project.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.project.domain.SelfReceive;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SelfPayReceiveVo extends SelfReceive {
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

    /** 创建者 */
    @ApiModelProperty(value = "创建者 出款信息")
    private String createByPay;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间 出款信息")
    private Date createTimePay;

    /** 更新者 */
    @ApiModelProperty(value = "更新者 出款信息")
    private String updateByPay;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间 出款信息")
    private Date updateTimePay;

    /** 付款流水号（财务输入） */
    @ApiModelProperty(value = "付款流水号 财务输入")
    private String payCode;

    /** 付款流水号（关联收付款表） */
    @ApiModelProperty(value = "付款流水号 关联收付款表")
    private String paySysCode;
}
