package com.ruoyi.company.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.stringtemplate.v4.ST;

/**
 * 个体户注册申请信息对象 self_application_info
 * 
 * @author ruoyi
 * @date 2022-06-17
 */
public class SelfApplicationInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 申请id */
    private Long id;

    /** 个体户编码 */
    @Excel(name = "个体户编码")
    private String selfCode;

    /** 是否本人申请 */
    @Excel(name = "是否本人申请")
    private String oneselfApply;

    /** 申请人姓名 */
    @Excel(name = "申请人姓名")
    private String applyName;

    /** 申请人电话 */
    @Excel(name = "申请人电话")
    private String applyPhone;

    /** 申请人证件号码 */
    @Excel(name = "申请人证件号码")
    private String applyIdNum;

    /** 申请人证件类型 */
    @Excel(name = "申请人证件类型")
    private String applyDocumentType;

    /** 联络人姓名id */
    @Excel(name = "联络人姓名")
    private String contactName;

    /** 联络人电话 */
    @Excel(name = "联络人电话")
    private String contactPhone;

    /** 联络人证件号码 */
    @Excel(name = "联络人证件号码")
    private String contactIdNum;

    /** 联络人证件类型 */
    @Excel(name = "联络人证件类型")
    private String contactDocumentType;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSelfCode(String selfCode) 
    {
        this.selfCode = selfCode;
    }

    public String getSelfCode() 
    {
        return selfCode;
    }
    public void setOneselfApply(String oneselfApply) 
    {
        this.oneselfApply = oneselfApply;
    }

    public String getOneselfApply() 
    {
        return oneselfApply;
    }
    public void setApplyName(String applyName) 
    {
        this.applyName = applyName;
    }

    public String getApplyName() 
    {
        return applyName;
    }
    public void setApplyPhone(String applyPhone) 
    {
        this.applyPhone = applyPhone;
    }

    public String getApplyPhone() 
    {
        return applyPhone;
    }
    public void setApplyIdNum(String applyIdNum)
    {
        this.applyIdNum = applyIdNum;
    }

    public String getApplyIdNum()
    {
        return applyIdNum;
    }
    public void setApplyDocumentType(String applyDocumentType) 
    {
        this.applyDocumentType = applyDocumentType;
    }

    public String getApplyDocumentType() 
    {
        return applyDocumentType;
    }
    public void setContactName(String contactName)
    {
        this.contactName = contactName;
    }

    public String getContactName()
    {
        return contactName;
    }
    public void setContactPhone(String contactPhone) 
    {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone() 
    {
        return contactPhone;
    }
    public void setContactIdNum(String contactIdNum)
    {
        this.contactIdNum = contactIdNum;
    }

    public String getContactIdNum()
    {
        return contactIdNum;
    }
    public void setContactDocumentType(String contactDocumentType) 
    {
        this.contactDocumentType = contactDocumentType;
    }

    public String getContactDocumentType() 
    {
        return contactDocumentType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("selfCode", getSelfCode())
            .append("oneselfApply", getOneselfApply())
            .append("applyName", getApplyName())
            .append("applyPhone", getApplyPhone())
            .append("applyIdNum", getApplyIdNum())
            .append("applyDocumentType", getApplyDocumentType())
            .append("contactName", getContactName())
            .append("contactPhone", getContactPhone())
            .append("contactIdNum", getContactIdNum())
            .append("contactDocumentType", getContactDocumentType())
            .toString();
    }
}
