package com.ruoyi.project.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.project.domain.SelfEmployed;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class SelfEmployedVo extends SelfEmployed {

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
