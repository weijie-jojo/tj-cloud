package com.ruoyi.project.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 个体商户对象 self_employed
 *
 * @author ruoyi
 * @date 2022-07-06
 */
@Data
public class SelfEmployed extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 个体户id */
    private String selfId;

    /** 个体户编码 */
    @ApiModelProperty(value = "个体户编码")
    private String selfCode;

    /** 渠道商编码 */
    @ApiModelProperty(value = "渠道商编码")
    private String placeCode;

    /** 所属业务员 */
    @ApiModelProperty(value = "所属业务员")
    private Long userId;

    /** 税号 */
    @ApiModelProperty(value = "税号")
    private String taxId;

    /** 个体户注册地址 */
    @ApiModelProperty(value = "个体户注册地址")
    private String selfAddress;

    /** 个体户名称 */
    @ApiModelProperty(value = "个体户名称")
    private String selfName;

    /** 法人姓名 */
    @ApiModelProperty(value = "法人姓名")
    private String legalPersonName;

    /** 法人身份证 */
    @ApiModelProperty(value = "法人身份证")
    private String idCardNum;

    /** 登录密码 */
    @ApiModelProperty(value = "登录密码")
    private String password;

    /** 认证状态
     0->业务员已录入,待注册
     1->数据已提交,仅保存
     2->数据已提交,待审核
     3->审核通过
     4->审核未通过
     5->通过代办
     6->审核强制终止
     7->停用 */
    @ApiModelProperty(value = "认证状态 ")
    private Long status;

    /** 渠道名称 */
    @ApiModelProperty(value = "渠道名称")
    private String placeName;

    /** 业务员名字 */
    @ApiModelProperty(value = "业务员名字")
    private String username;

    /** 快递状态  0->未处理  1->已处理 */
    @ApiModelProperty(value = "快递状态  0->未处理  1->已处理")
    private Long expStatus;

    /** 每月可开票金额 */
    @ApiModelProperty(value = "每月可开票金额")
    private BigDecimal maximum;

    /** 注册时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "注册时间")
    private Date registerTime;

    /** 行业类型 */
    @ApiModelProperty(value = "行业类型")
    private String industryType;

    /** 行业税率 */
    @ApiModelProperty(value = "行业税率")
    private BigDecimal industryTax;

    /** 组织形式 */
    @ApiModelProperty(value = "组织形式")
    private String organizationalForm;

    /** 从业人数 */
    @ApiModelProperty(value = "从业人数")
    private Long numberEmployees;

    /** 出资金额 */
    @ApiModelProperty(value = "出资金额")
    private Long contributionAmount;

    /** 城市 */
    @ApiModelProperty(value = "城市")
    private String city;

    /** 区县 */
    @ApiModelProperty(value = "区县")
    private String county;

    /** 电子商务经营者 */
    @ApiModelProperty(value = "电子商务经营者")
    private String electronicCommerce;

    /** 所属自贸区 */
    @ApiModelProperty(value = "所属自贸区")
    private String freeTradeZone;

    /** 所属自贸片区 */
    @ApiModelProperty(value = "所属自贸片区")
    private String freeTradeArea;

    /** 产权 */
    @ApiModelProperty(value = "产权")
    private String propertyRight;

    /** 经营范围 */
    @ApiModelProperty(value = "经营范围")
    private String natureBusiness;

    /** 账户类型 */
    @ApiModelProperty(value = "账户类型")
    private String accountType;

    /** 账户类型2 */
    @ApiModelProperty(value = "账户类型2")
    private String accountType2;

    /** 私人账户开户银行 */
    @ApiModelProperty(value = "私人账户开户银行")
    private String privateDepositBank;

    /** 私人账户账号 */
    @ApiModelProperty(value = "私人账户账号")
    private String privateAccountNumber;

    /** 对公账户开户银行（基本户） */
    @ApiModelProperty(value = "对公账户开户银行 基本户")
    private String publicDepositBank1;

    /** 对公账户账号（基本户） */
    @ApiModelProperty(value = "对公账户账号 基本户")
    private String publicAccountNumber1;

    /** 对公账户开户银行（一般户） */
    @ApiModelProperty(value = "对公账户开户银行 一般户")
    private String publicDepositBank2;

    /** 对公账户账号（一般户） */
    @ApiModelProperty(value = "对公账户账号 一般户")
    private String publicAccountNumber2;

    /** 纳税账户户名 */
    @ApiModelProperty(value = "纳税账户户名")
    private String accountName;

    /** 纳税账户开户银行 */
    @ApiModelProperty(value = "纳税账户开户银行")
    private String publicDepositBank3;

    /** 纳税账户账号 */
    @ApiModelProperty(value = "纳税账户账号")
    private String publicAccountNumber3;

    /** 工商审核是否通过 0未通过 1已通过 */
    @ApiModelProperty(value = "工商审核是否通过 0未通过 1已通过")
    private Long businessStatus;

    /** 工商文件名 */
    @ApiModelProperty(value = "工商文件名")
    private String fileName1;

    /** 税务文件名 */
    @ApiModelProperty(value = "税务文件名")
    private String fileName2;

    /** 信息名字审核是否通过 0未通过 1已通过 */
    @ApiModelProperty(value = "信息名字审核是否通过 0未通过 1已通过")
    private Long infoStatus;

    /** 税务审核是否通过 0未通过 1已通过 */
    @ApiModelProperty(value = "税务审核是否通过 0未通过 1已通过")
    private Long taxStatus;

    /** 银行审核是否通过 0未通过 1已通过 */
    @ApiModelProperty(value = "银行审核是否通过 0未通过 1已通过")
    private Long bankStatus;

    /** 纳税委托协议 */
    @ApiModelProperty(value = "纳税委托协议")
    private String fileName3;

    /** 三方协议签约凭证 */
    @ApiModelProperty(value = "三方协议签约凭证")
    private String fileName4;

    /** 完结时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "完结时间")
    private Date endTime;

    /** 完结状态 0未完结 1已完结 */
    @ApiModelProperty(value = "完结状态 0未完结 1已完结")
    private String endStatus;

    /** 信息审核不通过原因 */
    @ApiModelProperty(value = "信息审核不通过原因")
    private String remarkInfo;

    /** 身份证 */
    @ApiModelProperty(value = "身份证")
    private String fileName5;

    /** 营业期限 */
    @ApiModelProperty(value = "营业期限")
    private String businessTerm;

    /** 状态 0休眠 1激活 2报警 */
    @ApiModelProperty(value = "状态 0休眠 1激活 2报警")
    private Long isActive;

    /** 名称审核不通过原因 */
    @ApiModelProperty(value = "名称审核不通过原因")
    private String remarkName;

}

