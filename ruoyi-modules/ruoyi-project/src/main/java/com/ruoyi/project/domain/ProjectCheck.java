package com.ruoyi.project.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 个体户审批进度对象 project_check
 *
 * @author ruoyi
 * @date 2022-08-01
 */
public class ProjectCheck extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @Excel(name = "时间", width = 30, dateFormat = "yyyy-MM-dd HH:ss:mm")
    @ApiModelProperty(value = "时间")
    private Date checkDate;

    /** 审核人员 */
    @Excel(name = "审核人员")
    @ApiModelProperty(value = "审核人员")
    private String checkUser;

    /** 详情 */
    @Excel(name = "详情")
    @ApiModelProperty(value = "详情")
    private String checkReasult;

    /** 个体户编码 */
    @Excel(name = "个体户编码")
    @ApiModelProperty(value = "个体户编码")
    private String projectCode;

    /** 步骤
     关联字典表 */
    @Excel(name = "步骤 关联字典表")
    @ApiModelProperty(value = "步骤 关联字典表")
    private String projectType;

    /** 手机号码 */
    @Excel(name = "手机号码")
    @ApiModelProperty(value = "手机号码")
    private String phonenumber;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setCheckDate(Date checkDate)
    {
        this.checkDate = checkDate;
    }

    public Date getCheckDate()
    {
        return checkDate;
    }
    public void setCheckUser(String checkUser)
    {
        this.checkUser = checkUser;
    }

    public String getCheckUser()
    {
        return checkUser;
    }
    public void setCheckReasult(String checkReasult)
    {
        this.checkReasult = checkReasult;
    }

    public String getCheckReasult()
    {
        return checkReasult;
    }
    public void setProjectCode(String projectCode)
    {
        this.projectCode = projectCode;
    }

    public String getProjectCode()
    {
        return projectCode;
    }
    public void setProjectType(String projectType)
    {
        this.projectType = projectType;
    }

    public String getProjectType()
    {
        return projectType;
    }
    public void setPhonenumber(String phonenumber)
    {
        this.phonenumber = phonenumber;
    }

    public String getPhonenumber()
    {
        return phonenumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("checkDate", getCheckDate())
                .append("checkUser", getCheckUser())
                .append("checkReasult", getCheckReasult())
                .append("projectCode", getProjectCode())
                .append("projectType", getProjectType())
                .append("phonenumber", getPhonenumber())
                .toString();
    }
}

