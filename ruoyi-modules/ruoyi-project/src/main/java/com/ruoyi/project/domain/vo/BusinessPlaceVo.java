package com.ruoyi.project.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.project.domain.BusinessAgencyFee;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class BusinessPlaceVo extends BusinessAgencyFee {
    //businessPlace
    /** 渠道商名称 */
    @Excel(name = "渠道商名称")
    @ApiModelProperty(value = "渠道商名称")
    private String placeName;

    /** 渠道商简称 */
    @Excel(name = "渠道商简称")
    @ApiModelProperty(value = "渠道商简称")
    private String placeAliasName;

    /** 渠道商类型(0:企业客户 1:个人客户,2:直客) */
    @Excel(name = "渠道商类型(0:企业客户 1:个人客户,2:直客)")
    @ApiModelProperty(value = "渠道商类型(0:企业客户 1:个人客户,2:直客)")
    private Long placeType;

    /** 渠道商联系人 */
    @Excel(name = "渠道商联系人")
    @ApiModelProperty(value = "渠道商联系人")
    private String placeLinkman;

    /** 渠道商联系电话 */
    @Excel(name = "渠道商联系电话")
    @ApiModelProperty(value = "渠道商联系电话")
    private String placeTel;

    /** 渠道商电子邮箱 */
    @Excel(name = "渠道商电子邮箱")
    @ApiModelProperty(value = "渠道商电子邮箱")
    private String placeEmail;

    /** 开户行 */
    @Excel(name = "开户行")
    @ApiModelProperty(value = "开户行")
    private String placeOpenBank;

    /** 开户行账号 */
    @Excel(name = "开户行账号")
    @ApiModelProperty(value = "开户行账号")
    private String placeBankAccount;

    /** 渠道商星级 */
    @Excel(name = "渠道商星级")
    @ApiModelProperty(value = "渠道商星级")
    private Long placeStarLevel;

    /** 渠道商状态(0:正常 1:锁定 ) */
    @Excel(name = "渠道商状态(0:正常 1:锁定 )")
    @ApiModelProperty(value = "渠道商状态(0:正常 1:锁定 )")
    private Long placeStatus;

    /** 渠道商注册平台时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "渠道商注册平台时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty(value = "渠道商注册平台时间")
    private Date registTime;

    /** 归属业务员 */
    @Excel(name = "归属业务员")
    @ApiModelProperty(value = "归属业务员")
    private Long userId;

    /** 是否删除(1:正常 0:删除) */
    @Excel(name = "是否删除(1:正常 0:删除)")
    @ApiModelProperty(value = "是否删除(1:正常 0:删除)")
    private Integer isDelete;

    /** 业务经理名 */
    @Excel(name = "业务经理名")
    @ApiModelProperty(value = "业务经理名")
    private String userName;
}
