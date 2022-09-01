package com.ruoyi.project.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.project.domain.SelfProject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class ProjectJoinTicketVo extends SelfProject {
    /*selfTicket表*/
    /** ID */
    private String ticketId;

    /** 项目编号 */
    @ApiModelProperty(value = "项目编号")
    private String projectCode;

    /** 开票金额 */
    @ApiModelProperty(value = "开票金额")
    private BigDecimal ticketAmount;

    /** 发票类型 */
    @ApiModelProperty(value = "发票类型")
    private Long ticketType;

    /** 开票内容 */
    @ApiModelProperty(value = "开票内容")
    private String ticketContent;

    /** 发票备注 */
    @ApiModelProperty(value = "发票备注")
    private String ticketRemark;

    /** 发票税率 */
    @ApiModelProperty(value = "发票税率")
    private BigDecimal ticketTax;

    /** 发票编号 */
    @ApiModelProperty(value = "发票编号")
    private String ticketCode;

    /** 开票时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "开票时间")
    private Date ticketTime;

    /** 发票影像 */
    @ApiModelProperty(value = "发票影像")
    private String fileName;

    /** 发票种类编号 */
    @ApiModelProperty(value = "发票种类编号")
    private String ticketTypeCode;

    /*SelfEmployed表*/
    /** 个体户名称 */
    @ApiModelProperty(value = "个体户名称")
    private String selfName;

    /** 税号 */
    @ApiModelProperty(value = "税号")
    private String taxId;

    /** 经营范围 */
    @ApiModelProperty(value = "经营范围")
    private String natureBusiness;

    /** 行业类型 */
    @ApiModelProperty(value = "行业类型")
    private String industryType;

    /** 行业税率 */
    @ApiModelProperty(value = "行业税率")
    private BigDecimal industryTax;

    /** 状态 0休眠 1激活 2报警 */
    @ApiModelProperty(value = "状态 0休眠 1激活 2报警")
    private Long isActive;

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

    /*BusinessPlace表*/
    /** 渠道商名称 */
    @ApiModelProperty(value = "渠道商名称")
    private String placeName;

    /** 渠道商状态(0:正常 1:锁定 ) */
    @ApiModelProperty(value = "渠道商状态(0:正常 1:锁定 )")
    private Long placeStatus;

    /** 渠道商全名 */
    @ApiModelProperty(value = "渠道商全名")
    private String placeAliasName;
    
    //self_purch_company
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

    //self_application_info
    /** 法人电话 */
    @ApiModelProperty(value = "法人电话")
    private String contactPhone;

    //self_legal_person
    /** 个体商户法人id */
    private Long legalPersonId;

    /** 用户名 */
    @ApiModelProperty(value ="用户名")
    private String username;

    /** 个体户标识 */
    @ApiModelProperty(value ="个体户标识")
    private String selfCode;

    /** 法人姓名 */
    @ApiModelProperty(value ="法人姓名")
    private String legalPersonName;

    /** 证件类型 */
    @ApiModelProperty(value ="证件类型")
    private String documentType;

    /** 法人身份证号 */
    @ApiModelProperty(value ="法人身份证号")
    private String idCardNum;

    /** 政治面貌 */
    @ApiModelProperty(value ="政治面貌")
    private String politicalStatus;

    /** 文化程度 */
    @ApiModelProperty(value ="文化程度")
    private String eduation;

    /** 法人手机号 */
    @ApiModelProperty(value ="法人手机号")
    private String telNum;

    /** 法人邮箱 */
    @ApiModelProperty(value ="法人邮箱")
    private String mail;

    /** 法人银行 */
    @ApiModelProperty(value ="法人银行")
    private String bank;

    /** 法人银行卡号 */
    @ApiModelProperty(value ="法人银行卡号")
    private String bankCard;

    /** 开户行 */
    @ApiModelProperty(value ="开户行")
    private String openingBank;

    /** 快递收件地址 */
    @ApiModelProperty(value ="快递收件地址")
    private String receivingAddress;

    /** 登录密码 */
    @ApiModelProperty(value ="登录密码")
    private String password;

    /** 身份证有效期(起始日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value ="身份证有效期(起始日")
    private Date initial;

    /** 身份证有效期(过期日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value ="身份证有效期(过期日")
    private Date ending;

    /** 法人与授权人是否是同一个人 */
    @ApiModelProperty(value ="法人与授权人是否是同一个人")
    private Integer same;

    /** 授权人姓名 */
    @ApiModelProperty(value ="授权人姓名")
    private String contactName;

    /** 授权人身份证号 */
    @ApiModelProperty(value ="授权人身份证号")
    private String contactIdNum;

    /** 授权人联系方式 */
    @ApiModelProperty(value ="授权人联系方式")
    private String contactTel;

    /** 性别1 男 2 女 */
    @ApiModelProperty(value ="性别1 男 2 女")
    private String gender;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value ="出生日期")
    private Date dateBirth;

    /** 民族 */
    @ApiModelProperty(value ="民族")
    private String nation;

    /** 经营者居所 */
    @ApiModelProperty(value ="经营者居所")
    private String residence;

    /** 人员类型 */
    @ApiModelProperty(value ="人员类型")
    private String personnelType;

    /** 申请前职业状况 */
    @ApiModelProperty(value ="申请前职业状况")
    private String occupationalStatus;

}
