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
 * 【请填写功能名称】对象 self_pay_receive
 *
 * @author ruoyi
 * @date 2022-09-02
 */
@Data
public class SelfReceive extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String receiveId;

    /** 项目编号 */
    @ApiModelProperty(value = "项目编号")
    private String projectCode;

    /** 收付款流水号（系统自动生成） */
    @ApiModelProperty(value = "收付款流水号 系统自动生成")
    private String receiveSysCode;

    /** 收付款流水号（财务输入） */
    @ApiModelProperty(value = "收付款流水号 财务输入")
    private String receiveCode;

    /** 项目名称 */
    @ApiModelProperty(value = "项目名称")
    private String projectName;

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

    /** 是否审核（0审核中 1完成 2异常） */
    @ApiModelProperty(value = "是否审核（0审核中 1完成 2异常）")
    private Long isCheck;

}
