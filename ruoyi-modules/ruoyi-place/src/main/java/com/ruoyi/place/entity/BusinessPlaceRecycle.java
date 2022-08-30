package com.ruoyi.place.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(callSuper = false)
@TableName("business_place_recycle")
public class BusinessPlaceRecycle implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "place_id", type = IdType.AUTO)
    private Integer placeId;

    /**
     * 渠道商编码
     */
    @TableField("place_code")
    @ApiModelProperty(value = "渠道商编码")
    private String placeCode;

    /**
     * 渠道商名称
     */
    @TableField("place_name")
    @ApiModelProperty(value = "渠道商名称")
    private String placeName;

    /**
     * 渠道商简称
     */
    @TableField("place_alias_name")
    @ApiModelProperty(value = "渠道商简称")
    private String placeAliasName;

    /**
     * 渠道商类型(0:企业客户 1:个人客户,2:直客)
     */
    @TableField("place_type")
    @ApiModelProperty(value = "渠道商类型(0:企业客户 1:个人客户,2:直客)")
    private Integer placeType;

    /**
     * 渠道商联系人
     */
    @TableField("place_linkman")
    @ApiModelProperty(value = "渠道商联系人")
    private String placeLinkman;

    /**
     * 渠道商联系电话
     */
    @TableField("place_tel")
    @ApiModelProperty(value = "渠道商联系电话")
    private String placeTel;

    /**
     * 渠道商电子邮箱
     */
    @TableField("place_email")
    @ApiModelProperty(value = "渠道商电子邮箱")
    private String placeEmail;

    /**
     * 开户行
     */
    @TableField("place_open_bank")
    @ApiModelProperty(value = "开户行")
    private String placeOpenBank;

    /**
     * 开户行账号
     */
    @TableField("place_bank_account")
    @ApiModelProperty(value = "开户行账号")
    private String placeBankAccount;

    /**
     * 渠道商星级
     */
    @TableField("place_star_level")
    @ApiModelProperty(value = "渠道商星级")
    private Integer placeStarLevel;

    /**
     * 渠道商状态(0:正常 1:锁定 )
     */
    @TableField("place_status")
    @ApiModelProperty(value = "渠道商状态(0:正常 1:锁定 )")
    private Integer placeStatus;

    /**
     * 渠道商注册平台时间
     */
    @TableField("regist_time")
    @ApiModelProperty(value = "渠道商注册平台时间")
    private Date registTime;

    /**
     * 归属业务员
     */
    @TableField("user_id")
    @ApiModelProperty(value = "归属业务员")
    private Long userId;

    /**
     * 是否删除(0:正常 1:删除)
     */
    @TableField("is_delete")
    @ApiModelProperty(value = "是否删除(0:正常 1:删除)")
    private Boolean isDelete;

    /**
     * 备注
     */
    @TableField("remark")
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    @TableField("create_by")
    @ApiModelProperty(value = "创建人")
    private String createBy;

    /**
     * 更新人
     */
    @TableField("update_by")
    @ApiModelProperty(value = "更新人")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField("update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 业务经理名
     */
    @TableField("user_name")
    @ApiModelProperty(value = "业务经理名")
    private String userName;

    /** 渠道商别名 */
    @TableField("place_alias")
    @ApiModelProperty(value = "渠道商别名")
    private String placeAlias;

    /** 客户类型(0 渠道 1 直客） */
    @TableField("customer_type")
    @ApiModelProperty(value = "客户类型(0 渠道 1 直客）")
    private Long customerType;

}
