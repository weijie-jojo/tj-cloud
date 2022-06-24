package com.ruoyi.company.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

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

    /** 完结时间 */
    @Excel(name = "完结时间")
    private String endTime;

    /** 审核不通过原因 */
    @Excel(name = "审核不通过原因")
    private String employedRemark;

    /** 完结状态 */
    @Excel(name = "完结状态")
    private Integer endStatus;

    /** 个体户编码 */
    @Excel(name = "个体户编码")
    private String selfCode;

    /** 渠道商编码 */
    @Excel(name = "渠道商编码")
    private String placeCode;

    /** 所属业务员 */
    @Excel(name = "所属业务员")
    private Long userId;

    /** 税号 */
    @Excel(name = "税号")
    private String taxId;

    /** 个体户注册地址 */
    @Excel(name = "个体户注册地址")
    private String selfAddress;

    /** 个体户名称 */
    @Excel(name = "个体户名称")
    private String selfName;

    /** 法人姓名 */
    @Excel(name = "法人姓名")
    private String legalPersonName;

    /** 法人身份证 */
    @Excel(name = "法人身份证")
    private String idCardNum;

    /** 登录密码 */
    @Excel(name = "登录密码")
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
    @Excel(name = "认证状态 ")
            private Long status;

            /** 渠道名称 */
            @Excel(name = "渠道名称")
            private String placeName;

            /** 业务员名字 */
            @Excel(name = "业务员名字")
            private String username;

            /** 快递状态  0->未处理  1->已处理 */
            @Excel(name = "快递状态  0->未处理  1->已处理")
            private Long expStatus;

            /** 每月可开票金额 */
            @Excel(name = "每月可开票金额")
            private BigDecimal maximum;

            /** 注册时间 */
            @JsonFormat(pattern = "yyyy-MM-dd")
            @Excel(name = "注册时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registerTime;

    /** 行业类型 */
    @Excel(name = "行业类型")
    private String industryType;

    /** 行业税率 */
    @Excel(name = "行业税率")
    private BigDecimal industryTax;

    /** 组织形式 */
    @Excel(name = "组织形式")
    private String organizationalForm;

    /** 从业人数 */
    @Excel(name = "从业人数")
    private Long numberEmployees;

    /** 出资金额 */
    @Excel(name = "出资金额")
    private Long contributionAmount;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 区县 */
    @Excel(name = "区县")
    private String county;

    /** 电子商务经营者 */
    @Excel(name = "电子商务经营者")
    private String electronicCommerce;

    /** 所属自贸区 */
    @Excel(name = "所属自贸区")
    private String freeTradeZone;

    /** 所属自贸片区 */
    @Excel(name = "所属自贸片区")
    private String freeTradeArea;

    /** 产权 */
    @Excel(name = "产权")
    private String propertyRight;

    /** 行业 */
    @Excel(name = "行业")
    private String industry;

    /** 经营范围 */
    @Excel(name = "经营范围")
    private String natureBusiness;

    /** 账户类型 */
    @Excel(name = "账户类型")
    private String accountType;

    /** 账户类型2 */
    @Excel(name = "账户类型2")
    private String accountType2;

    /** 私人账户开户银行 */
    @Excel(name = "私人账户开户银行")
    private String privateDepositBank;

    /** 私人账户账号 */
    @Excel(name = "私人账户账号")
    private String privateAccountNumber;

    /** 对公账户开户银行（基本户） */
    @Excel(name = "对公账户开户银行", readConverterExp = "基=本户")
    private String publicDepositBank1;

    /** 对公账户账号（基本户） */
    @Excel(name = "对公账户账号", readConverterExp = "基=本户")
    private String publicAccountNumber1;

    /** 对公账户开户银行（一般户） */
    @Excel(name = "对公账户开户银行", readConverterExp = "一=般户")
    private String publicDepositBank2;

    /** 对公账户账号（一般户） */
    @Excel(name = "对公账户账号", readConverterExp = "一=般户")
    private String publicAccountNumber2;

    /** 纳税账户户名 */
    @Excel(name = "纳税账户户名")
    private String accountName;

    /** 纳税账户开户银行 */
    @Excel(name = "纳税账户开户银行")
    private String publicDepositBank3;

    /** 纳税账户账号 */
    @Excel(name = "纳税账户账号")
    private String publicAccountNumber3;

    /** 工商审核是否通过 0未通过 1已通过 */
    @Excel(name = "工商审核是否通过 0未通过 1已通过")
    private Long businessStatus;

    /** 工商文件名 */
    @Excel(name = "工商文件名")
    private String fileName1;

    /** 税务文件名 */
    @Excel(name = "税务文件名")
    private String fileName2;

    /** 工商文件名 */
    @Excel(name = "纳税委托协议")
    private String fileName3;

    /** 税务文件名 */
    @Excel(name = "三方协议签约凭证")
    private String fileName4;

    /** 信息名字审核是否通过 0未通过 1已通过 */
    @Excel(name = "信息名字审核是否通过 0未通过 1已通过")
    private Long infoStatus;

    /** 税务审核是否通过 0未通过 1已通过 */
    @Excel(name = "税务审核是否通过 0未通过 1已通过")
    private Long taxStatus;

    /** 银行审核是否通过 0未通过 1已通过 */
    @Excel(name = "银行审核是否通过 0未通过 1已通过")
    private Long bankStatus;

    public void setSelfId(String selfId)
    {
        this.selfId = selfId;
    }

    public String getSelfId()
    {
        return selfId;
    }
    public void setSelfCode(String selfCode)
    {
        this.selfCode = selfCode;
    }

    public String getSelfCode()
    {
        return selfCode;
    }
    public void setPlaceCode(String placeCode)
    {
        this.placeCode = placeCode;
    }

    public String getPlaceCode()
    {
        return placeCode;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setTaxId(String taxId)
    {
        this.taxId = taxId;
    }

    public String getTaxId()
    {
        return taxId;
    }
    public void setSelfAddress(String selfAddress)
    {
        this.selfAddress = selfAddress;
    }

    public String getSelfAddress()
    {
        return selfAddress;
    }
    public void setSelfName(String selfName)
    {
        this.selfName = selfName;
    }

    public String getSelfName()
    {
        return selfName;
    }
    public void setLegalPersonName(String legalPersonName)
    {
        this.legalPersonName = legalPersonName;
    }

    public String getLegalPersonName()
    {
        return legalPersonName;
    }
    public void setIdCardNum(String idCardNum)
    {
        this.idCardNum = idCardNum;
    }

    public String getIdCardNum()
    {
        return idCardNum;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }
    public void setPlaceName(String placeName)
    {
        this.placeName = placeName;
    }

    public String getPlaceName()
    {
        return placeName;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }
    public void setExpStatus(Long expStatus)
    {
        this.expStatus = expStatus;
    }

    public Long getExpStatus()
    {
        return expStatus;
    }
    public void setMaximum(BigDecimal maximum)
    {
        this.maximum = maximum;
    }

    public BigDecimal getMaximum()
    {
        return maximum;
    }
    public void setRegisterTime(Date registerTime)
    {
        this.registerTime = registerTime;
    }

    public Date getRegisterTime()
    {
        return registerTime;
    }
    public void setIndustryType(String industryType)
    {
        this.industryType = industryType;
    }

    public String getIndustryType()
    {
        return industryType;
    }
    public void setIndustryTax(BigDecimal industryTax)
    {
        this.industryTax = industryTax;
    }

    public BigDecimal getIndustryTax()
    {
        return industryTax;
    }
    public void setOrganizationalForm(String organizationalForm)
    {
        this.organizationalForm = organizationalForm;
    }

    public String getOrganizationalForm()
    {
        return organizationalForm;
    }
    public void setNumberEmployees(Long numberEmployees)
    {
        this.numberEmployees = numberEmployees;
    }

    public Long getNumberEmployees()
    {
        return numberEmployees;
    }
    public void setContributionAmount(Long contributionAmount)
    {
        this.contributionAmount = contributionAmount;
    }

    public Long getContributionAmount()
    {
        return contributionAmount;
    }
    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCity()
    {
        return city;
    }
    public void setCounty(String county)
    {
        this.county = county;
    }

    public String getCounty()
    {
        return county;
    }
    public void setElectronicCommerce(String electronicCommerce)
    {
        this.electronicCommerce = electronicCommerce;
    }

    public String getElectronicCommerce()
    {
        return electronicCommerce;
    }
    public void setFreeTradeZone(String freeTradeZone)
    {
        this.freeTradeZone = freeTradeZone;
    }

    public String getFreeTradeZone()
    {
        return freeTradeZone;
    }
    public void setFreeTradeArea(String freeTradeArea)
    {
        this.freeTradeArea = freeTradeArea;
    }

    public String getFreeTradeArea()
    {
        return freeTradeArea;
    }
    public void setPropertyRight(String propertyRight)
    {
        this.propertyRight = propertyRight;
    }

    public String getPropertyRight()
    {
        return propertyRight;
    }
    public void setIndustry(String industry)
    {
        this.industry = industry;
    }

    public String getIndustry()
    {
        return industry;
    }
    public void setNatureBusiness(String natureBusiness)
    {
        this.natureBusiness = natureBusiness;
    }

    public String getNatureBusiness()
    {
        return natureBusiness;
    }
    public void setAccountType(String accountType)
    {
        this.accountType = accountType;
    }

    public String getAccountType()
    {
        return accountType;
    }
    public void setAccountType2(String accountType2)
    {
        this.accountType2 = accountType2;
    }

    public String getAccountType2()
    {
        return accountType2;
    }
    public void setPrivateDepositBank(String privateDepositBank)
    {
        this.privateDepositBank = privateDepositBank;
    }

    public String getPrivateDepositBank()
    {
        return privateDepositBank;
    }
    public void setPrivateAccountNumber(String privateAccountNumber)
    {
        this.privateAccountNumber = privateAccountNumber;
    }

    public String getPrivateAccountNumber()
    {
        return privateAccountNumber;
    }
    public void setPublicDepositBank1(String publicDepositBank1)
    {
        this.publicDepositBank1 = publicDepositBank1;
    }

    public String getPublicDepositBank1()
    {
        return publicDepositBank1;
    }
    public void setPublicAccountNumber1(String publicAccountNumber1)
    {
        this.publicAccountNumber1 = publicAccountNumber1;
    }

    public String getPublicAccountNumber1()
    {
        return publicAccountNumber1;
    }
    public void setPublicDepositBank2(String publicDepositBank2)
    {
        this.publicDepositBank2 = publicDepositBank2;
    }

    public String getPublicDepositBank2()
    {
        return publicDepositBank2;
    }
    public void setPublicAccountNumber2(String publicAccountNumber2)
    {
        this.publicAccountNumber2 = publicAccountNumber2;
    }

    public String getPublicAccountNumber2()
    {
        return publicAccountNumber2;
    }
    public void setAccountName(String accountName)
    {
        this.accountName = accountName;
    }

    public String getAccountName()
    {
        return accountName;
    }
    public void setPublicDepositBank3(String publicDepositBank3)
    {
        this.publicDepositBank3 = publicDepositBank3;
    }

    public String getPublicDepositBank3()
    {
        return publicDepositBank3;
    }
    public void setPublicAccountNumber3(String publicAccountNumber3)
    {
        this.publicAccountNumber3 = publicAccountNumber3;
    }

    public String getPublicAccountNumber3()
    {
        return publicAccountNumber3;
    }
    public void setBusinessStatus(Long businessStatus)
    {
        this.businessStatus = businessStatus;
    }

    public Long getBusinessStatus()
    {
        return businessStatus;
    }
    public void setFileName1(String fileName1)
    {
        this.fileName1 = fileName1;
    }

    public String getFileName1()
    {
        return fileName1;
    }
    public void setFileName2(String fileName2)
    {
        this.fileName2 = fileName2;
    }

    public String getFileName2()
    {
        return fileName2;
    }
    public void setInfoStatus(Long infoStatus)
    {
        this.infoStatus = infoStatus;
    }

    public Long getInfoStatus()
    {
        return infoStatus;
    }
    public void setTaxStatus(Long taxStatus)
    {
        this.taxStatus = taxStatus;
    }

    public Long getTaxStatus()
    {
        return taxStatus;
    }
    public void setBankStatus(Long bankStatus)
    {
        this.bankStatus = bankStatus;
    }

    public Long getBankStatus()
    {
        return bankStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("selfId", getSelfId())
                .append("selfCode", getSelfCode())
                .append("placeCode", getPlaceCode())
                .append("userId", getUserId())
                .append("taxId", getTaxId())
                .append("selfAddress", getSelfAddress())
                .append("selfName", getSelfName())
                .append("legalPersonName", getLegalPersonName())
                .append("idCardNum", getIdCardNum())
                .append("password", getPassword())
                .append("status", getStatus())
                .append("remark", getRemark())
                .append("placeName", getPlaceName())
                .append("username", getUsername())
                .append("expStatus", getExpStatus())
                .append("maximum", getMaximum())
                .append("registerTime", getRegisterTime())
                .append("industryType", getIndustryType())
                .append("industryTax", getIndustryTax())
                .append("organizationalForm", getOrganizationalForm())
                .append("numberEmployees", getNumberEmployees())
                .append("contributionAmount", getContributionAmount())
                .append("city", getCity())
                .append("county", getCounty())
                .append("electronicCommerce", getElectronicCommerce())
                .append("freeTradeZone", getFreeTradeZone())
                .append("freeTradeArea", getFreeTradeArea())
                .append("propertyRight", getPropertyRight())
                .append("industry", getIndustry())
                .append("natureBusiness", getNatureBusiness())
                .append("accountType", getAccountType())
                .append("accountType2", getAccountType2())
                .append("privateDepositBank", getPrivateDepositBank())
                .append("privateAccountNumber", getPrivateAccountNumber())
                .append("publicDepositBank1", getPublicDepositBank1())
                .append("publicAccountNumber1", getPublicAccountNumber1())
                .append("publicDepositBank2", getPublicDepositBank2())
                .append("publicAccountNumber2", getPublicAccountNumber2())
                .append("accountName", getAccountName())
                .append("publicDepositBank3", getPublicDepositBank3())
                .append("publicAccountNumber3", getPublicAccountNumber3())
                .append("createBy", getCreateBy())
                .append("updateBy", getUpdateBy())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("businessStatus", getBusinessStatus())
                .append("fileName1", getFileName1())
                .append("fileName2", getFileName2())
                .append("infoStatus", getInfoStatus())
                .append("taxStatus", getTaxStatus())
                .append("bankStatus", getBankStatus())
                .toString();
    }
}

