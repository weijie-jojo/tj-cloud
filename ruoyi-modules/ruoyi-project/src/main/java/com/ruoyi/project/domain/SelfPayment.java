package com.ruoyi.project.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 收付款信息对象 self_payment
 *
 * @author ruoyi
 * @date 2022-09-27
 */
@Data
public class SelfPayment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String paymentId;

    /** 收款流水号 */
    @ApiModelProperty(value = "收款流水号")
    private String receiveSysCode;

    /** 转账账户（付款信息） */
    @ApiModelProperty(value = "转账账户 付款信息")
    private String paymentName;

    /** 转账时间（付款信息） */
    @ApiModelProperty(value = "转账时间 付款信息")
    private Date paymentTime;

    /** 收款金额（付款信息） */
    @ApiModelProperty(value = "收款金额 付款信息")
    private BigDecimal paymentMoney;

    /** 转账帐号（付款信息） */
    @ApiModelProperty(value = "转账帐号 付款信息")
    private String paymentAccount;

    /** 转账凭证（付款信息） */
    @ApiModelProperty(value = "转账凭证 付款信息")
    private String fileNamePayment;

}

