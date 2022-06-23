package com.ruoyi.company.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 纳税账户
 对象 pay_tax_info
 *
 * @author ruoyi
 * @date 2022-06-23
 */
public class PayTaxInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long taxId;

    /** 员工编号 */
    @Excel(name = "员工编号")
    private String employeeNumber;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickName;

    /** 身份证号码 */
    @Excel(name = "身份证号码")
    private String idNo;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    /** 职务 */
    @Excel(name = "职务")
    private String postId;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 状态：1在职、0离职 */
    @Excel(name = "状态：1在职、0离职")
    private Long enabled;

    /** 纳税账户账号 */
    @Excel(name = "纳税账户账号")
    private String publicAccountNumber3;

    /** 纳税账户开户行 */
    @Excel(name = "纳税账户开户行")
    private String publicDepositBank3;

    /** 纳税账户户名 */
    @Excel(name = "纳税账户户名")
    private String accountName;

    public void setTaxId(Long taxId)
    {
        this.taxId = taxId;
    }

    public Long getTaxId()
    {
        return taxId;
    }
    public void setEmployeeNumber(String employeeNumber)
    {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeNumber()
    {
        return employeeNumber;
    }
    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public String getNickName()
    {
        return nickName;
    }
    public void setIdNo(String idNo)
    {
        this.idNo = idNo;
    }

    public String getIdNo()
    {
        return idNo;
    }
    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getGender()
    {
        return gender;
    }
    public void setPostId(String postId)
    {
        this.postId = postId;
    }

    public String getPostId()
    {
        return postId;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }
    public void setEnabled(Long enabled)
    {
        this.enabled = enabled;
    }

    public Long getEnabled()
    {
        return enabled;
    }
    public void setPublicAccountNumber3(String publicAccountNumber3)
    {
        this.publicAccountNumber3 = publicAccountNumber3;
    }

    public String getPublicAccountNumber3()
    {
        return publicAccountNumber3;
    }
    public void setPublicDepositBank3(String publicDepositBank3)
    {
        this.publicDepositBank3 = publicDepositBank3;
    }

    public String getPublicDepositBank3()
    {
        return publicDepositBank3;
    }
    public void setAccountName(String accountName)
    {
        this.accountName = accountName;
    }

    public String getAccountName()
    {
        return accountName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("taxId", getTaxId())
                .append("employeeNumber", getEmployeeNumber())
                .append("nickName", getNickName())
                .append("idNo", getIdNo())
                .append("gender", getGender())
                .append("postId", getPostId())
                .append("phone", getPhone())
                .append("email", getEmail())
                .append("enabled", getEnabled())
                .append("publicAccountNumber3", getPublicAccountNumber3())
                .append("publicDepositBank3", getPublicDepositBank3())
                .append("accountName", getAccountName())
                .append("createBy", getCreateBy())
                .append("updateBy", getUpdateBy())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
