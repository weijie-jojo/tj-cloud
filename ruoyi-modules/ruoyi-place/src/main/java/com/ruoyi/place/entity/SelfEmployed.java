package com.ruoyi.place.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 个体商户对象 self_employed
 *
 * @author ruoyi
 * @date 2022-06-22
 */
@Data
public class SelfEmployed extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 个体户id */
    private String selfId;

    /** 是否激活 */
    private Integer isActive;

    /** 完结时间 */
    @ApiModelProperty(value = "完结时间")
    private String endTime;

    /** 完结时间 */
    @ApiModelProperty(value = "营业期限")
    private String businessTerm;

    /** 信息审核不通过原因 */
    @ApiModelProperty(value = "信息审核不通过原因")
    private String remarkInfo;

    /** 信息审核不通过原因 */
    @ApiModelProperty(value = "名称审核不通过原因")
    private String remarkName;

//    /** 工商审核不通过原因 */
//    @ApiModelProperty(value = "工商审核不通过原因")
//    private String remark_business;

//    /** 银行审核不通过原因 */
//    @ApiModelProperty(value = "银行审核不通过原因")
//    private String remark_bank;
//
//    /** 税务审核不通过原因 */
//    @ApiModelProperty(value = "税务审核不通过原因")
//    private String remark_tax;

    /** 完结状态 */
    @ApiModelProperty(value = "完结状态")
    private Integer endStatus;

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

    /** 行业 */
    @ApiModelProperty(value = "行业")
    private String industry;

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

    /** 工商文件名 */
    @ApiModelProperty(value = "纳税委托协议")
    private String fileName3;

    /** 税务文件名 */
    @ApiModelProperty(value = "三方协议签约凭证")
    private String fileName4;

    /** 身份证 */
    @ApiModelProperty(value = "身份证")
    private String fileName5;

    /** 信息名字审核是否通过 0未通过 1已通过 */
    @ApiModelProperty(value = "信息名字审核是否通过 0未通过 1已通过")
    private Long infoStatus;

    /** 税务审核是否通过 0未通过 1已通过 */
    @ApiModelProperty(value = "税务审核是否通过 0未通过 1已通过")
    private Long taxStatus;

    /** 银行审核是否通过 0未通过 1已通过 */
    @ApiModelProperty(value = "银行审核是否通过 0未通过 1已通过")
    private Long bankStatus;

    /** 工商实名 */
    @ApiModelProperty(value = "工商实名")
    private String fileName6;

    /** 税务实名 */
    @ApiModelProperty(value = "税务实名")
    private String fileName7;

    /** 实名认证是否通过 0未通过 1已通过 */
    @ApiModelProperty(value = "实名认证是否通过 0未通过 1已通过")
    private Long realnameStatus;

    /** 专票个体户代办费 */
    @ApiModelProperty(value = "专票个体户代办费")
    private BigDecimal specialSelfFee;

    /** 普票个体户代办费 */
    @ApiModelProperty(value = "普票个体户代办费")
    private BigDecimal ordinarySelfFee;

    /** 专票个体户代办费(元） */
    @ApiModelProperty(value = "专票个体户代办费(元）")
    private BigDecimal specialSelfMoney;

    /** 普票个体户代办费(元） */
    @ApiModelProperty(value = "普票个体户代办费(元）")
    private BigDecimal ordinarySelfMoney;

    /** 个体户服务费是否含税 */
    @ApiModelProperty(value = "个体户服务费是否含税")
    private Long isSelfTax;

    /** 注册服务费 1500 */
    @ApiModelProperty(value = "注册服务费 1500")
    private BigDecimal registerMoney;

    /** 工商登记申请书pdf */
    @ApiModelProperty(value = "工商登记申请书pdf")
    private String fileName8;

    /** 普票税率(增值税普通发票） */
    @ApiModelProperty(value = "普票税率(增值税普通发票）")
    private BigDecimal ordinaryTax;

    /** 普票税率(增值税专用发票） */
    @ApiModelProperty(value = "普票税率(增值税专用发票）")
    private BigDecimal ordinarySpecialTax;

    /** 分润方式(增值税普通发票）百分比 */
    @ApiModelProperty(value = "分润方式(增值税普通发票）百分比")
    private BigDecimal ordinaryShare;

    /** 分润方式(增值税普通发票）定额 */
    @ApiModelProperty(value = "分润方式(增值税普通发票）定额")
    private Long ordinaryShareMoney;

    /** 分润方式(增值税专用发票）百分比 */
    @ApiModelProperty(value = "分润方式(增值税专用发票）百分比")
    private BigDecimal specialShare;

    /** 分润方式(增值税专用发票）定额 */
    @ApiModelProperty(value = "分润方式(增值税专用发票）定额")
    private Long specialShareMoney;

    /** 普票价税分离（与服务费含税一样，是，否；默认选择否）*/
    @ApiModelProperty(value = "普票价税分离 与服务费含税一样，是，否；默认选择否")
    private Long isOrdinaryTax;

    /** 专票价税分离（与服务费含税一样，是，否；默认选择否） */
    @ApiModelProperty(value = "专票价税分离 与服务费含税一样，是，否；默认选择否")
    private Long isSpecialTax;

    /** 注册确认不通过原因 */
    @ApiModelProperty(value = "注册确认不通过原因")
    private String remarkRegister;

    /** 个体户服务费是否含税(专票) */
    @ApiModelProperty(value = "个体户服务费是否含税(专票)")
    private Long isSpecialSelfTax;

    /** 是否单独结算 */
    @ApiModelProperty(value = "是否单独结算")
    private Long isSelfCount;

    /** 普票平台服务费是否定额 */
    @ApiModelProperty(value = "普票平台服务费是否定额")
    private Long ordinaryProxyIsmoney;

    /** 专票平台服务费是否定额 */
    @ApiModelProperty(value = "专票平台服务费是否定额")
    private Long specialProxyIsmoney;

    /** 普票分润方式是否定额 */
    @ApiModelProperty(value = "普票分润方式是否定额")
    private Long ordinaryShareIsmoney;

    /** 专票分润方式是否定额 */
    @ApiModelProperty(value = "专票分润方式是否定额")
    private Long specialShareIsmoney;

    /** 是否开启分润 0是 1否(普票) */
    @ApiModelProperty(value = "是否开启分润 0是 1否(普票)")
    private Long isOrdinaryShare;

    /** 是否开启分润 0是 1否(专票) */
    @ApiModelProperty(value = "是否开启分润 0是 1否(专票)")
    private Long isSpecialShare;

    /** 滑块0是 1否 */
    @ApiModelProperty(value = "滑块0是 1否")
    private Long isSlider;

    /** 滑块0是 1否(普票) */
    @ApiModelProperty(value = "滑块0是 1否(普票)")
    private Long isSliderOrdinary;

    /** 渠道商全名（名称+别名） */
    @ApiModelProperty(value = "渠道商全名 名称+别名")
    private String placeAliasName;

    /** 对公一般户（0是 1否） */
    @ApiModelProperty(value = "对公一般户 0 是,1 否")
    private Long isPublicUser;

    /** 分润方式(增值税普通发票) */
    @ApiModelProperty(value = "分润方式(增值税普通发票)")
    private BigDecimal selfShare;

    /** 是否开启分润 0是 1否 */
    @ApiModelProperty(value = "是否开启分润 0是 1否")
    private Long isSelfShare;

    /** 个体户注册费是否定额 1 百分比 0定额 */
    @ApiModelProperty(value = "个体户注册费是否定额 1 百分比 0定额")
    private Long selfShareIsmoney;

}

