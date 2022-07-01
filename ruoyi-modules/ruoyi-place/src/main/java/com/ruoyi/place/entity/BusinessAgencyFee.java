package com.ruoyi.place.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("business_agency_fee")
public class BusinessAgencyFee implements Serializable {

    private static final long serialVersionUID = 1L;

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


}
