package com.ruoyi.project.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

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
    @ApiModelProperty(value = "项目编号")
    private String projectCode;

    /** 开票金额 */
    @ApiModelProperty(value = "开票金额")
    private BigDecimal ticketAmount;

    /** 发票类型 */
    @ApiModelProperty(value = "发票类型")
    private Long ticketType;

    /** 开票内容 */
    @ApiModelProperty(value = "开票内容")
    private String ticketContent;

    /** 发票备注 */
    @ApiModelProperty(value = "发票备注")
    private String ticketRemark;

    /** 发票税率 */
    @ApiModelProperty(value = "发票税率")
    private BigDecimal ticketTax;

    /** 发票编号 */
    @ApiModelProperty(value = "发票编号")
    private String ticketCode;

    /** 开票时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "开票时间")
    private Date ticketTime;

    /** 发票影像 */
    @ApiModelProperty(value = "发票影像")
    private String fileName;

    /** 发票种类编号 */
    @ApiModelProperty(value = "发票种类编号")
    private String ticketTypeCode;

    /** 发票种类编号 */
    @ApiModelProperty(value = "是否删除")
    private String isDeleted;

    /** 发票不通过备注 */
    @ApiModelProperty(value = "发票不通过备注")
    private String ticketRemark2;

    /** 收款人 */
    @ApiModelProperty(value = "收款人")
    private String receiveUser;

    /** 复核 */
    @ApiModelProperty(value = "复核")
    private String checkUser;

    /** 开票人 */
    @ApiModelProperty(value = "开票人")
    private String createUser;

    /** 发票张数 */
    @ApiModelProperty(value = "发票张数")
    private Long ticketNum;

}

