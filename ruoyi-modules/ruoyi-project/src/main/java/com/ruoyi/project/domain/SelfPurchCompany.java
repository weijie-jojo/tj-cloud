package com.ruoyi.project.domain;

import com.ruoyi.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 甲方信息对象 self_purch_company
 *
 * @author ruoyi
 * @date 2022-09-01
 */
@Data
public class SelfPurchCompany extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String purchId;

    /** 采购方公司名（甲方） */
    @ApiModelProperty(value = "采购方公司名 甲方")
    private String purchCompany;

    /** 业务员（登录用户id） */
    @ApiModelProperty(value = "业务员 登录用户id")
    private Long userId;

    /** 甲方纳税人识别号 */
    @ApiModelProperty(value = "甲方纳税人识别号")
    private String purchCompanyTaxid;

    /** 电话 */
    @ApiModelProperty(value = "电话")
    private String purchCompanyPhone;

    /** 地址 */
    @ApiModelProperty(value = "地址")
    private String purchCompanyAddress;

    /** 开户行 */
    @ApiModelProperty(value = "开户行")
    private String bankName;

    /** 开户行帐号 */
    @ApiModelProperty(value = "开户行帐号")
    private String bankCode;

    /** 客户全名（名称+别名） */
    @ApiModelProperty(value = "客户全名 名称+别名")
    private String placeAliasName;

}
