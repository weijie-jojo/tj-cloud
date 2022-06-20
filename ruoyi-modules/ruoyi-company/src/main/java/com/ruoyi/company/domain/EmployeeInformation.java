package com.ruoyi.company.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 员工信息对象 employee_information
 * 
 * @author employeeInformation
 * @date 2022-06-17
 */
public class EmployeeInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long userId;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private Long deptId;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String department;

    /** 员工编号 */
    @Excel(name = "员工编号")
    private String employeeNumber;

    /** 用户名 */
    @Excel(name = "用户名")
    private String username;

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

    /** 工资卡号 */
    @Excel(name = "工资卡号")
    private String payCheck;

    /** 工资卡开户银行 */
    @Excel(name = "工资卡开户银行")
    private String payCheckBank;

    /** 报销卡 */
    @Excel(name = "报销卡")
    private String accountCard;

    /** 报销卡开户银行 */
    @Excel(name = "报销卡开户银行")
    private String accountCardBank;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setDepartment(String department) 
    {
        this.department = department;
    }

    public String getDepartment() 
    {
        return department;
    }
    public void setEmployeeNumber(String employeeNumber) 
    {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeNumber() 
    {
        return employeeNumber;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
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
    public void setPayCheck(String payCheck) 
    {
        this.payCheck = payCheck;
    }

    public String getPayCheck() 
    {
        return payCheck;
    }
    public void setPayCheckBank(String payCheckBank) 
    {
        this.payCheckBank = payCheckBank;
    }

    public String getPayCheckBank() 
    {
        return payCheckBank;
    }
    public void setAccountCard(String accountCard) 
    {
        this.accountCard = accountCard;
    }

    public String getAccountCard() 
    {
        return accountCard;
    }
    public void setAccountCardBank(String accountCardBank) 
    {
        this.accountCardBank = accountCardBank;
    }

    public String getAccountCardBank() 
    {
        return accountCardBank;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("department", getDepartment())
            .append("employeeNumber", getEmployeeNumber())
            .append("username", getUsername())
            .append("nickName", getNickName())
            .append("idNo", getIdNo())
            .append("gender", getGender())
            .append("postId", getPostId())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("enabled", getEnabled())
            .append("payCheck", getPayCheck())
            .append("payCheckBank", getPayCheckBank())
            .append("accountCard", getAccountCard())
            .append("accountCardBank", getAccountCardBank())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
