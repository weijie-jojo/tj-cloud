package com.ruoyi.project.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 个体户行业类型税率对象 self_industry_rate
 * 
 * @author ruoyi
 * @date 2022-06-17
 */
public class SelfIndustryRate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String industryId;

    /** 行业编码 */
    @Excel(name = "行业编码")
    @ApiModelProperty(value = "行业编码")
    private String industryCode;

    /** 行业名称 */
    @Excel(name = "行业名称")
    @ApiModelProperty(value = "行业名称")
    private String industryName;

    /** 父节点id */
    @Excel(name = "父节点id")
    @ApiModelProperty(value = "父节点id")
    private Long parentId;

    /** 是否删除，0-未删除，1-删除，默认为0 */
    @Excel(name = "是否删除，0-未删除，1-删除，默认为0")
    @ApiModelProperty(value = "是否删除，0-未删除，1-删除，默认为0")
    private String isDelete;

    /** 应税所得率 */
    @Excel(name = "应税所得率")
    @ApiModelProperty(value = "应税所得率")
    private BigDecimal taxRate;

    public void setIndustryId(String industryId) 
    {
        this.industryId = industryId;
    }

    public String getIndustryId() 
    {
        return industryId;
    }
    public void setIndustryCode(String industryCode) 
    {
        this.industryCode = industryCode;
    }

    public String getIndustryCode() 
    {
        return industryCode;
    }
    public void setIndustryName(String industryName) 
    {
        this.industryName = industryName;
    }

    public String getIndustryName() 
    {
        return industryName;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setIsDelete(String isDelete) 
    {
        this.isDelete = isDelete;
    }

    public String getIsDelete() 
    {
        return isDelete;
    }
    public void setTaxRate(BigDecimal taxRate) 
    {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxRate() 
    {
        return taxRate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("industryId", getIndustryId())
            .append("industryCode", getIndustryCode())
            .append("industryName", getIndustryName())
            .append("parentId", getParentId())
            .append("isDelete", getIsDelete())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("taxRate", getTaxRate())
            .toString();
    }
}
