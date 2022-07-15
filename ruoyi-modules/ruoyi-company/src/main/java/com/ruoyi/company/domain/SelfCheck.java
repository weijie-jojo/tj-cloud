package com.ruoyi.company.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.stringtemplate.v4.ST;

/**
 * 单据审批结果对象 self_check
 *
 * @author ruoyi
 * @date 2022-07-14
 */
@Data
public class SelfCheck extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 时间 */
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkDate;

    /** 审核人员 */
    @Excel(name = "审核人员")
    private String checkUser;

    /** 详情 */
    @Excel(name = "详情")
    private String checkReasult;

    /** 个体户编码 */
    @Excel(name = "个体户编码")
    private String selfCode;

    /** 步骤
     */
    @Excel(name = "步骤 ")
    private String selfType;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phonenumber;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
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
    public void setSelfCode(String selfCode)
    {
        this.selfCode = selfCode;
    }

    public String getSelfCode()
    {
        return selfCode;
    }
    public void setSelfType(String selfType)
    {
        this.selfType = selfType;
    }

    public String getSelfType()
    {
        return selfType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("checkDate", getCheckDate())
                .append("checkUser", getCheckUser())
                .append("checkReasult", getCheckReasult())
                .append("selfCode", getSelfCode())
                .append("selfType", getSelfType())
                .toString();
    }
}
