package com.ruoyi.place.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 渠道商代理费
 * </p>
 *
 * @author lcc
 * @since 2022-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("business_agency_fee_recycle")
public class BusinessAgencyFeeRecycle implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 渠道商名称(不做orm映射)
     */
//    @Transient
//    private String placeName;
    /**
     * id
     */
    @TableId(value = "agency_fee_id", type = IdType.AUTO)
    private Integer agencyFeeId;

    /**
     * 渠道商编码
     */
    @TableField("place_code")
    @ApiModelProperty(value = "渠道商编码")
    private String placeCode;

    /**
     * 6%专票代开费率
     */
    @TableField("special_invoice_6")
    @ApiModelProperty(value = "6%专票代开费率")
    private BigDecimal specialInvoice6;

    /**
     * 13%专票代开费率
     */
    @TableField("special_invoice_13")
    @ApiModelProperty(value = "13%专票代开费率")
    private BigDecimal specialInvoice13;

    /**
     * 专票个体户代办费
     */
    @TableField("special_self_fee")
    @ApiModelProperty(value = "专票个体户代办费")
    private BigDecimal specialSelfFee;

    /**
     * 是否含税-专票
     */
    @TableField("is_special_tax")
    @ApiModelProperty(value = "是否含税-专票")
    private Boolean isSpecialTax;

    /**
     * 普票个体户代办费
     */
    @TableField("ordinary_self_fee")
    @ApiModelProperty(value = "普票个体户代办费")
    private BigDecimal ordinarySelfFee;

    /**
     * 普票代开收费
     */
    @TableField("ordinary_proxy_fee")
    @ApiModelProperty(value = "普票代开收费")
    private BigDecimal ordinaryProxyFee;

    /**
     * 是否含税-普票
     */
    @TableField("is_ordinary_tax")
    @ApiModelProperty(value = "是否含税-普票")
    private Boolean isOrdinaryTax;

    /**
     * 是否删除(0:正常 1:删除)
     */
    @TableField("is_delete")
    @ApiModelProperty(value = "是否删除(0:正常 1:删除)")
    private Boolean isDelete;

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


    /** 6%专票代开费率(元） */
    @TableField("special_invoice6_money")
    @ApiModelProperty(value = "6%专票代开费率(元）")
    private BigDecimal specialInvoice6Money;

    /** 13%专票代开费率(元） */
    @TableField("special_invoice13_money")
    @ApiModelProperty(value = "13%专票代开费率(元）")
    private BigDecimal specialInvoice13Money;

    /** 普票平台服务费(元） */
    @TableField("ordinary_proxy_money")
    @ApiModelProperty(value = "普票平台服务费(元）")
    private BigDecimal ordinaryProxyMoney;

    /** 专票平台服务费 */
    @TableField("special_proxy_fee")
    @ApiModelProperty(value = "专票平台服务费")
    private BigDecimal specialProxyFee;

    /** 专票平台服务费(元） */
    @TableField("special_proxy_money")
    @ApiModelProperty(value = "专票平台服务费(元）")
    private BigDecimal specialProxyMoney;

    /** 普票税率(增值税普通发票） */
    @TableField("ordinary_tax")
    @ApiModelProperty(value = "普票税率(增值税普通发票）")
    private BigDecimal ordinaryTax;

    /** 普票税率(增值税专用发票） */
    @TableField("ordinary_special_tax")
    @ApiModelProperty(value = "普票税率(增值税专用发票）")
    private BigDecimal ordinarySpecialTax;

    /** 分润方式(增值税普通发票）百分比 */
    @TableField("ordinary_share")
    @ApiModelProperty(value = "分润方式(增值税普通发票）百分比")
    private BigDecimal ordinaryShare;

    /** 分润方式(增值税普通发票）定额 */
    @TableField("ordinary_share_money")
    @ApiModelProperty(value = "分润方式(增值税普通发票）定额")
    private Long ordinaryShareMoney;

    /** 分润方式(增值税专用发票）百分比 */
    @TableField("special_share")
    @ApiModelProperty(value = "分润方式(增值税专用发票）百分比")
    private BigDecimal specialShare;

    /** 分润方式(增值税专用发票）定额 */
    @TableField("special_share_money")
    @ApiModelProperty(value = "分润方式(增值税专用发票）定额")
    private Long specialShareMoney;

    /** 价税分离(普票) 0 是 1否 */
    @TableField("is_self_tax")
    @ApiModelProperty(value = "价税分离(普票) 0 是 1否")
    private Long isSelfTax;

    /** 价税分离(专票) 0 是 1否 */
    @TableField("is_special_self_tax")
    @ApiModelProperty(value = "价税分离(专票) 0 是 1否")
    private Long isSpecialSelfTax;

    /** 普票平台服务费是否定额 */
    @TableField("ordinary_proxy_ismoney")
    @ApiModelProperty(value = "普票平台服务费是否定额")
    private Long ordinaryProxyIsmoney;

    /** 专票平台服务费是否定额 */
    @TableField("special_proxy_ismoney")
    @ApiModelProperty(value = "专票平台服务费是否定额")
    private Long specialProxyIsmoney;

    /** 普票分润方式是否定额 */
    @TableField("ordinary_share_ismoney")
    @ApiModelProperty(value = "普票分润方式是否定额")
    private Long ordinaryShareIsmoney;

    /** 专票分润方式是否定额 */
    @TableField("special_share_ismoney")
    @ApiModelProperty(value = "专票分润方式是否定额")
    private Long specialShareIsmoney;

    /** 是否开启分润 0是 1否(普票) */
    @TableField("is_ordinary_share")
    @ApiModelProperty(value = "是否开启分润 0是 1否(普票)")
    private Long isOrdinaryShare;

    /** 是否开启分润 0是 1否(专票) */
    @TableField("is_special_share")
    @ApiModelProperty(value = "是否开启分润 0是 1否(专票)")
    private Long isSpecialShare;

    /** 滑块0是 1否 */
    @TableField("is_slider")
    @ApiModelProperty(value = "滑块0是 1否 ")
    private Long isSlider;

    /** 滑块0是 1否(普票) */
    @TableField("is_slider_ordinary")
    @ApiModelProperty(value = "滑块0是 1否(普票)")
    private Long isSliderOrdinary;

    /** 分润方式(增值税普通发票) */
    @TableField("self_share")
    @ApiModelProperty(value = "分润方式(增值税普通发票)")
    private BigDecimal selfShare;

    /** 是否开启分润 0是 1否 */
    @TableField("is_self_share")
    @ApiModelProperty(value = "是否开启分润 0是 1否 ")
    private Long isSelfShare;

    /** 个体户注册费是否定额 1 百分比 0定额 */
    @TableField("self_share_ismoney")
    @ApiModelProperty(value = "个体户注册费是否定额 1 百分比 0定额")
    private Long selfShareIsmoney;

    /** 是否收取注册服务费 */
    @ApiModelProperty(value =  "是否收取注册服务费")
    private Long isRegisterMoney;

    /** 是否开启一次性收取费用 0是 1否 */
    @ApiModelProperty(value =  "是否开启一次性收取费用 0是 1否")
    private Long isDisposable;

    /** 分润值(一次性收取费用) */
    @ApiModelProperty(value =  "分润值(一次性收取费用)")
    private BigDecimal disposableShare;

    /** 分润方式是否定额 1 百分比 0定额(一次性收取费用) */
    @ApiModelProperty(value =  "分润方式是否定额 1 百分比 0定额(一次性收取费用)")
    private Long disposableShareIsmoney;

    /** 是否开启分润 0是 1否(一次性收取费用) */
    @ApiModelProperty(value =  "是否开启分润 0是 1否(一次性收取费用)")
    private Long isDisposableShare;

    /** 一次性收取费用值 */
    @ApiModelProperty(value =  "一次性收取费用值")
    private BigDecimal disposableFee;

    /** 一次性收取费用是否定额 1 百分比 0定额 */
    @ApiModelProperty(value =  "一次性收取费用是否定额 1 百分比 0定额")
    private Long disposableFeeIsmoney;
}
