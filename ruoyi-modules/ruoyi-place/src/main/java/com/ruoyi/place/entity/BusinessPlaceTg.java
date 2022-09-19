package com.ruoyi.place.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 平台渠道商
 * </p>
 *
 * @author lcc
 * @since 2022-06-03
 */
@Data
public class BusinessPlaceTg implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer placeId;

    /**
     * 渠道商编码
     */
    @ApiModelProperty(value = "渠道商编码")
    private String placeCode;

    /**
     * 渠道商名称
     */
    @ApiModelProperty(value = "渠道商名称")
    private String placeName;

    /**
     * 渠道商简称
     */
    @ApiModelProperty(value = "渠道商简称")
    private String placeAliasName;

    /**
     * 渠道商类型(0:企业客户 1:个人客户,2:直客)
     */
    @ApiModelProperty(value = "渠道商类型(0:企业客户 1:个人客户,2:直客)")
    private Integer placeType;

    /**
     * 渠道商联系人
     */
    @ApiModelProperty(value = "渠道商联系人")
    private String placeLinkman;

    /**
     * 渠道商联系电话
     */
    @ApiModelProperty(value = "渠道商联系电话")
    private String placeTel;

    /**
     * 渠道商电子邮箱
     */
    @ApiModelProperty(value = "渠道商电子邮箱")
    private String placeEmail;

    /**
     * 开户行
     */
    @ApiModelProperty(value = "开户行")
    private String placeOpenBank;

    /**
     * 开户行账号
     */
    @ApiModelProperty(value = "开户行账号")
    private String placeBankAccount;

    /**
     * 渠道商星级
     */
    @ApiModelProperty(value = "渠道商星级")
    private Integer placeStarLevel;

    /**
     * 渠道商状态(0:正常 1:锁定 )
     */
    @ApiModelProperty(value = "渠道商状态(0:正常 1:锁定 )")
    private Integer placeStatus;

    /**
     * 渠道商注册平台时间
     */
    @ApiModelProperty(value = "渠道商注册平台时间")
    private Date registTime;

    /**
     * 归属业务员
     */
    @ApiModelProperty(value = "归属业务员")
    private Long userId;

    /**
     * 是否删除(0:正常 1:删除)
     */
    @ApiModelProperty(value = "是否删除(0:正常 1:删除)")
    private Boolean isDelete;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updateBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 业务经理名
     */
    @ApiModelProperty(value = "业务经理名")
    private String userName;

    /** 渠道商别名 */
    @ApiModelProperty(value = "渠道商别名")
    private String placeAlias;

    /** 客户类型(0 渠道 1 直客） */
    @ApiModelProperty(value = "客户类型(0 渠道 1 直客）")
    private Long customerType;


}
