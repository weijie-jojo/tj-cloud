package com.ruoyi.place.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Transient;
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
@TableName("business_agency_fee")
public class BusinessAgencyFee implements Serializable {

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
    private String placeCode;

    /**
     * 6%专票代开费率
     */
    @TableField("special_invoice_6")
    private BigDecimal specialInvoice6;

    /**
     * 13%专票代开费率
     */
    @TableField("special_invoice_13")
    private BigDecimal specialInvoice13;

    /**
     * 专票个体户代办费
     */
    @TableField("special_self_fee")
    private BigDecimal specialSelfFee;

    /**
     * 是否含税-专票
     */
    @TableField("is_special_tax")
    private Boolean isSpecialTax;

    /**
     * 普票个体户代办费
     */
    @TableField("ordinary_self_fee")
    private BigDecimal ordinarySelfFee;

    /**
     * 普票代开收费
     */
    @TableField("ordinary_proxy_fee")
    private BigDecimal ordinaryProxyFee;

    /**
     * 是否含税-普票
     */
    @TableField("is_ordinary_tax")
    private Boolean isOrdinaryTax;

    /**
     * 是否删除(0:正常 1:删除)
     */
    @TableField("is_delete")
    private Boolean isDelete;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 创建人
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 更新人
     */
    @TableField("update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


    /** 6%专票代开费率(元） */
    @TableField("special_invoice6_money")
    private BigDecimal specialInvoice6Money;

    /** 13%专票代开费率(元） */
    @TableField("special_invoice13_money")
    private BigDecimal specialInvoice13Money;

    /** 普票平台服务费(元） */
    @TableField("ordinary_proxy_money")
    private BigDecimal ordinaryProxyMoney;

    /** 专票平台服务费 */
    @TableField("special_proxy_fee")
    private BigDecimal specialProxyFee;

    /** 专票平台服务费(元） */
    @TableField("special_proxy_money")
    private BigDecimal specialProxyMoney;

    /** 普票税率(增值税普通发票） */
    @TableField("ordinary_tax")
    private BigDecimal ordinaryTax;

    /** 普票税率(增值税专用发票） */
    @TableField("ordinary_special_tax")
    private BigDecimal ordinarySpecialTax;

    /** 分润方式(增值税普通发票）百分比 */
    @TableField("ordinary_share")
    private Long ordinaryShare;

    /** 分润方式(增值税普通发票）定额 */
    @TableField("ordinary_share_money")
    private Long ordinaryShareMoney;

    /** 分润方式(增值税专用发票）百分比 */
    @TableField("special_share")
    private Long specialShare;

    /** 分润方式(增值税专用发票）定额 */
    @TableField("special_share_money")
    private Long specialShareMoney;

    /** 价税分离(普票) 0 是 1否 */
    @TableField("is_self_tax")
    private Long isSelfTax;

    /** 价税分离(专票) 0 是 1否 */
    @TableField("is_special_self_tax")
    private Long isSpecialSelfTax;

    /** 普票平台服务费是否定额 */
    @TableField("ordinary_proxy_ismoney")
    private Long ordinaryProxyIsmoney;

    /** 专票平台服务费是否定额 */
    @TableField("special_proxy_ismoney")
    private Long specialProxyIsmoney;

    /** 普票分润方式是否定额 */
    @TableField("ordinary_share_ismoney")
    private Long ordinaryShareIsmoney;

    /** 专票分润方式是否定额 */
    @TableField("special_share_ismoney")
    private Long specialShareIsmoney;

}
