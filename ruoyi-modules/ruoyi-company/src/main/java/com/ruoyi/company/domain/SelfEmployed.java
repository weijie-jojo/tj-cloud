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
 * @date 2022-06-14
 */
@Data
public class SelfEmployed extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 渠道名称 */
    private String username;

    /** 渠道名称 */
    private String placeName;

    /** 个体户id */
    private String selfId;

    /** 个体户编码 */
    private String selfCode;

    /** 渠道商编码 */
    @Excel(name = "渠道商编码")
    private String placeCode;

    /** 税号 */
    private String taxId;

    /** 个体户注册地址 */
    private String selfAddress;

    /** 个体户名称 */
    private String selfName;

    /** 法人姓名 */
    @Excel(name = "法人姓名")
    private String legalPersonName;

    /** 法人身份证 */
    private String idCardNum;

    /** 登录密码 */
    private String password;

    /** 认证状态
0-&gt;业务员已录入,待注册
1-&gt;数据已提交,仅保存
2-&gt;数据已提交,待审核
3-&gt;审核通过
4-&gt;审核未通过
5-&gt;通过代办
6-&gt;审核强制终止
7-&gt;停用 */
    private Long status;

    /** 所属业务员 */
    @Excel(name = "所属业务员")
    private Long userId;

    /** 快递状态  0-&gt;未处理  1-&gt;已处理 */
    private Long expStatus;

    /** 每月可开票金额 */
    private BigDecimal maximum;

    /** 注册时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "注册时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registerTime;

    /** 行业类型 */
    private String industryType;

    /** 组织形式 */
    private String organizationalForm;

    /** 从业人数 */
    private Long numberEmployees;

    /** 出资金额 */
    private Long contributionAmount;

    /** 城市 */
    private String city;

    /** 区县 */
    private String county;

    /** 电子商务经营者 */
    private String electronicCommerce;

    /** 所属自贸区 */
    private String freeTradeZone;

    /** 所属自贸片区 */
    private String freeTradeArea;

    /** 产权 */
    private String propertyRight;

    /** 行业 */
    private String industry;

    /** 经营范围 */
    private String natureBusiness;

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

    public void setPlaceName(String placeName)
    {
        this.placeName = placeName;
    }

    public String getPlaceName()
    {
        return placeName;
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
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("selfId", getSelfId())
            .append("selfCode", getSelfCode())
            .append("placeCode", getPlaceCode())
            .append("taxId", getTaxId())
            .append("selfAddress", getSelfAddress())
            .append("selfName", getSelfName())
            .append("legalPersonName", getLegalPersonName())
            .append("idCardNum", getIdCardNum())
            .append("password", getPassword())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("userId", getUserId())
            .append("expStatus", getExpStatus())
            .append("maximum", getMaximum())
            .append("registerTime", getRegisterTime())
            .append("industryType", getIndustryType())
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
            .toString();
    }
}
