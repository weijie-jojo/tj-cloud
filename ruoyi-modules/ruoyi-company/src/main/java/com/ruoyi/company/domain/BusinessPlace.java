package com.ruoyi.company.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 平台渠道商对象 business_place
 * 
 * @author ruoyi
 * @date 2022-06-18
 */
public class BusinessPlace extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String placeId;

    /** 渠道商编码 */
    @Excel(name = "渠道商编码")
    private String placeCode;

    /** 渠道商名称 */
    @Excel(name = "渠道商名称")
    private String placeName;

    /** 渠道商简称 */
    @Excel(name = "渠道商简称")
    private String placeAliasName;

    /** 渠道商类型(0:企业客户 1:个人客户,2:直客) */
    @Excel(name = "渠道商类型(0:企业客户 1:个人客户,2:直客)")
    private Long placeType;

    /** 渠道商联系人 */
    @Excel(name = "渠道商联系人")
    private String placeLinkman;

    /** 渠道商联系电话 */
    @Excel(name = "渠道商联系电话")
    private String placeTel;

    /** 渠道商电子邮箱 */
    @Excel(name = "渠道商电子邮箱")
    private String placeEmail;

    /** 开户行 */
    @Excel(name = "开户行")
    private String placeOpenBank;

    /** 开户行账号 */
    @Excel(name = "开户行账号")
    private String placeBankAccount;

    /** 渠道商星级 */
    @Excel(name = "渠道商星级")
    private Long placeStarLevel;

    /** 渠道商状态(0:正常 1:锁定 ) */
    @Excel(name = "渠道商状态(0:正常 1:锁定 )")
    private Long placeStatus;

    /** 渠道商注册平台时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "渠道商注册平台时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registTime;

    /** 归属业务员 */
    @Excel(name = "归属业务员")
    private Long userId;

    /** 是否删除(1:正常 0:删除) */
    @Excel(name = "是否删除(1:正常 0:删除)")
    private Integer isDelete;

    /** 业务经理名 */
    @Excel(name = "业务经理名")
    private String userName;

    public void setPlaceId(String placeId) 
    {
        this.placeId = placeId;
    }

    public String getPlaceId() 
    {
        return placeId;
    }
    public void setPlaceCode(String placeCode) 
    {
        this.placeCode = placeCode;
    }

    public String getPlaceCode() 
    {
        return placeCode;
    }
    public void setPlaceName(String placeName) 
    {
        this.placeName = placeName;
    }

    public String getPlaceName() 
    {
        return placeName;
    }
    public void setPlaceAliasName(String placeAliasName) 
    {
        this.placeAliasName = placeAliasName;
    }

    public String getPlaceAliasName() 
    {
        return placeAliasName;
    }
    public void setPlaceType(Long placeType) 
    {
        this.placeType = placeType;
    }

    public Long getPlaceType() 
    {
        return placeType;
    }
    public void setPlaceLinkman(String placeLinkman) 
    {
        this.placeLinkman = placeLinkman;
    }

    public String getPlaceLinkman() 
    {
        return placeLinkman;
    }
    public void setPlaceTel(String placeTel) 
    {
        this.placeTel = placeTel;
    }

    public String getPlaceTel() 
    {
        return placeTel;
    }
    public void setPlaceEmail(String placeEmail) 
    {
        this.placeEmail = placeEmail;
    }

    public String getPlaceEmail() 
    {
        return placeEmail;
    }
    public void setPlaceOpenBank(String placeOpenBank) 
    {
        this.placeOpenBank = placeOpenBank;
    }

    public String getPlaceOpenBank() 
    {
        return placeOpenBank;
    }
    public void setPlaceBankAccount(String placeBankAccount) 
    {
        this.placeBankAccount = placeBankAccount;
    }

    public String getPlaceBankAccount() 
    {
        return placeBankAccount;
    }
    public void setPlaceStarLevel(Long placeStarLevel) 
    {
        this.placeStarLevel = placeStarLevel;
    }

    public Long getPlaceStarLevel() 
    {
        return placeStarLevel;
    }
    public void setPlaceStatus(Long placeStatus) 
    {
        this.placeStatus = placeStatus;
    }

    public Long getPlaceStatus() 
    {
        return placeStatus;
    }
    public void setRegistTime(Date registTime) 
    {
        this.registTime = registTime;
    }

    public Date getRegistTime() 
    {
        return registTime;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setIsDelete(Integer isDelete) 
    {
        this.isDelete = isDelete;
    }

    public Integer getIsDelete() 
    {
        return isDelete;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("placeId", getPlaceId())
            .append("placeCode", getPlaceCode())
            .append("placeName", getPlaceName())
            .append("placeAliasName", getPlaceAliasName())
            .append("placeType", getPlaceType())
            .append("placeLinkman", getPlaceLinkman())
            .append("placeTel", getPlaceTel())
            .append("placeEmail", getPlaceEmail())
            .append("placeOpenBank", getPlaceOpenBank())
            .append("placeBankAccount", getPlaceBankAccount())
            .append("placeStarLevel", getPlaceStarLevel())
            .append("placeStatus", getPlaceStatus())
            .append("registTime", getRegistTime())
            .append("userId", getUserId())
            .append("isDelete", getIsDelete())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("userName", getUserName())
            .toString();
    }
}
