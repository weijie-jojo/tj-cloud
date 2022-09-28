package com.ruoyi.project.domain.vo;

import com.ruoyi.common.core.web.domain.BaseEntity;
import com.ruoyi.project.domain.SelfPay;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 出款信息对象 self_pay
 *
 * @author ruoyi
 * @date 2022-09-05
 */
@Data
public class SelfPayVo extends SelfPay
{
    private static final long serialVersionUID = 1L;

    /** 转账账户（收款信息） */
    @ApiModelProperty(value = "转账账户 收款信息")
    private String receiveName;

    /** 转账时间（收款信息） */
    @ApiModelProperty(value = "转账时间 收款信息")
    private String receiveTime;

    /** 收款金额（收款信息） */
    @ApiModelProperty(value = "收款金额 收款信息")
    private BigDecimal receiveMoney;

    /** 转账帐号（收款信息） */
    @ApiModelProperty(value = "转账帐号 收款信息")
    private String receiveAccount;

    /** 转账凭证（收款信息） */
    @ApiModelProperty(value = "转账凭证 收款信息")
    private String fileNameReceive;
}
