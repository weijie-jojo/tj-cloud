package com.ruoyi.company.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 个体商户法人对象 self_legal_person
 * 
 * @author ruoyi
 * @date 2022-06-17
 */
public class SelfLegalPerson extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 个体商户法人id */
    private Long legalPersonId;

    /** 个体户标识 */
    @Excel(name = "个体户标识")
    private String selfCode;

    /** 法人姓名 */
    @Excel(name = "法人姓名")
    private String legalPersonName;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String documentType;

    /** 法人身份证号 */
    @Excel(name = "法人身份证号")
    private String idCardNum;

    /** 政治面貌 */
    @Excel(name = "政治面貌")
    private String politicalStatus;

    /** 文化程度 */
    @Excel(name = "文化程度")
    private String eduation;

    /** 法人手机号 */
    @Excel(name = "法人手机号")
    private String telNum;

    /** 法人邮箱 */
    @Excel(name = "法人邮箱")
    private String mail;

    /** 法人银行 */
    @Excel(name = "法人银行")
    private String bank;

    /** 法人银行卡号 */
    @Excel(name = "法人银行卡号")
    private String bankCard;

    /** 开户行 */
    @Excel(name = "开户行")
    private String openingBank;

    /** 快递收件地址 */
    @Excel(name = "快递收件地址")
    private String receivingAddress;

    /** 登录密码 */
    @Excel(name = "登录密码")
    private String password;

    /** 身份证有效期(起始日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "身份证有效期(起始日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date initial;

    /** 身份证有效期(过期日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "身份证有效期(过期日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ending;

    /** 法人与授权人是否是同一个人 */
    @Excel(name = "法人与授权人是否是同一个人")
    private Integer same;

    /** 授权人姓名 */
    @Excel(name = "授权人姓名")
    private String contactName;

    /** 授权人身份证号 */
    @Excel(name = "授权人身份证号")
    private String contactIdNum;

    /** 授权人联系方式 */
    @Excel(name = "授权人联系方式")
    private String contactTel;

    /** 性别1 男 2 女 */
    @Excel(name = "性别1 男 2 女")
    private String gender;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dateBirth;

    /** 民族 */
    @Excel(name = "民族")
    private String nation;

    /** 经营者居所 */
    @Excel(name = "经营者居所")
    private String residence;

    /** 人员类型 */
    @Excel(name = "人员类型")
    private String personnelType;

    /** 申请前职业状况 */
    @Excel(name = "申请前职业状况")
    private String occupationalStatus;

    public void setLegalPersonId(Long legalPersonId) 
    {
        this.legalPersonId = legalPersonId;
    }

    public Long getLegalPersonId() 
    {
        return legalPersonId;
    }
    public void setSelfCode(String selfCode) 
    {
        this.selfCode = selfCode;
    }

    public String getSelfCode() 
    {
        return selfCode;
    }
    public void setLegalPersonName(String legalPersonName) 
    {
        this.legalPersonName = legalPersonName;
    }

    public String getLegalPersonName() 
    {
        return legalPersonName;
    }
    public void setDocumentType(String documentType) 
    {
        this.documentType = documentType;
    }

    public String getDocumentType() 
    {
        return documentType;
    }
    public void setIdCardNum(String idCardNum) 
    {
        this.idCardNum = idCardNum;
    }

    public String getIdCardNum() 
    {
        return idCardNum;
    }
    public void setPoliticalStatus(String politicalStatus) 
    {
        this.politicalStatus = politicalStatus;
    }

    public String getPoliticalStatus() 
    {
        return politicalStatus;
    }
    public void setEduation(String eduation) 
    {
        this.eduation = eduation;
    }

    public String getEduation() 
    {
        return eduation;
    }
    public void setTelNum(String telNum) 
    {
        this.telNum = telNum;
    }

    public String getTelNum() 
    {
        return telNum;
    }
    public void setMail(String mail) 
    {
        this.mail = mail;
    }

    public String getMail() 
    {
        return mail;
    }
    public void setBank(String bank) 
    {
        this.bank = bank;
    }

    public String getBank() 
    {
        return bank;
    }
    public void setBankCard(String bankCard) 
    {
        this.bankCard = bankCard;
    }

    public String getBankCard() 
    {
        return bankCard;
    }
    public void setOpeningBank(String openingBank) 
    {
        this.openingBank = openingBank;
    }

    public String getOpeningBank() 
    {
        return openingBank;
    }
    public void setReceivingAddress(String receivingAddress) 
    {
        this.receivingAddress = receivingAddress;
    }

    public String getReceivingAddress() 
    {
        return receivingAddress;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setInitial(Date initial) 
    {
        this.initial = initial;
    }

    public Date getInitial() 
    {
        return initial;
    }
    public void setEnding(Date ending) 
    {
        this.ending = ending;
    }

    public Date getEnding() 
    {
        return ending;
    }
    public void setSame(Integer same) 
    {
        this.same = same;
    }

    public Integer getSame() 
    {
        return same;
    }
    public void setContactName(String contactName) 
    {
        this.contactName = contactName;
    }

    public String getContactName() 
    {
        return contactName;
    }
    public void setContactIdNum(String contactIdNum) 
    {
        this.contactIdNum = contactIdNum;
    }

    public String getContactIdNum() 
    {
        return contactIdNum;
    }
    public void setContactTel(String contactTel) 
    {
        this.contactTel = contactTel;
    }

    public String getContactTel() 
    {
        return contactTel;
    }
    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }
    public void setDateBirth(Date dateBirth) 
    {
        this.dateBirth = dateBirth;
    }

    public Date getDateBirth() 
    {
        return dateBirth;
    }
    public void setNation(String nation) 
    {
        this.nation = nation;
    }

    public String getNation() 
    {
        return nation;
    }
    public void setResidence(String residence) 
    {
        this.residence = residence;
    }

    public String getResidence() 
    {
        return residence;
    }
    public void setPersonnelType(String personnelType) 
    {
        this.personnelType = personnelType;
    }

    public String getPersonnelType() 
    {
        return personnelType;
    }
    public void setOccupationalStatus(String occupationalStatus) 
    {
        this.occupationalStatus = occupationalStatus;
    }

    public String getOccupationalStatus() 
    {
        return occupationalStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("legalPersonId", getLegalPersonId())
            .append("selfCode", getSelfCode())
            .append("legalPersonName", getLegalPersonName())
            .append("documentType", getDocumentType())
            .append("idCardNum", getIdCardNum())
            .append("politicalStatus", getPoliticalStatus())
            .append("eduation", getEduation())
            .append("telNum", getTelNum())
            .append("mail", getMail())
            .append("bank", getBank())
            .append("bankCard", getBankCard())
            .append("openingBank", getOpeningBank())
            .append("receivingAddress", getReceivingAddress())
            .append("password", getPassword())
            .append("initial", getInitial())
            .append("ending", getEnding())
            .append("same", getSame())
            .append("contactName", getContactName())
            .append("contactIdNum", getContactIdNum())
            .append("contactTel", getContactTel())
            .append("gender", getGender())
            .append("dateBirth", getDateBirth())
            .append("nation", getNation())
            .append("residence", getResidence())
            .append("personnelType", getPersonnelType())
            .append("occupationalStatus", getOccupationalStatus())
            .toString();
    }
}
