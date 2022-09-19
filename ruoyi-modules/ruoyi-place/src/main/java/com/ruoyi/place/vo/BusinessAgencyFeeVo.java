package com.ruoyi.place.vo;

import com.ruoyi.place.entity.BusinessPlace;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class BusinessAgencyFeeVo extends BusinessPlace {


    /**
     * 6%专票代开费率
     */
    @ApiModelProperty(value ="6%专票代开费率")
    private BigDecimal specialInvoice6;

    /**
     * 13%专票代开费率
     */
    @ApiModelProperty(value ="13%专票代开费率")
    private BigDecimal specialInvoice13;

    /**
     * 专票个体户代办费
     */
    @ApiModelProperty(value ="专票个体户代办费")
    private BigDecimal specialSelfFee;

    /**
     * 是否含税-专票
     */
    @ApiModelProperty(value ="是否含税-专票")
    private Boolean isSpecialTax;

    /**
     * 普票个体户代办费
     */
    @ApiModelProperty(value ="普票个体户代办费")
    private BigDecimal ordinarySelfFee;

    /**
     * 普票代开收费
     */
    @ApiModelProperty(value ="普票代开收费")
    private BigDecimal ordinaryProxyFee;

    /**
     * 是否含税-普票
     */
    @ApiModelProperty(value = "是否含税-普票")
    private Boolean isOrdinaryTax;


}
