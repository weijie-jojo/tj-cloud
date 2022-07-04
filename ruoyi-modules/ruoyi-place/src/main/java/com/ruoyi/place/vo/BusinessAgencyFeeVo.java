package com.ruoyi.place.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.place.entity.BusinessAgencyFee;
import com.ruoyi.place.entity.BusinessPlace;
import lombok.Data;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class BusinessAgencyFeeVo extends BusinessPlace {


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


}
