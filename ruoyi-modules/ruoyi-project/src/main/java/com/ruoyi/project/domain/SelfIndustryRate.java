package com.ruoyi.project.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 个体户行业类型税率对象 self_industry_rate
 * 
 * @author ruoyi
 * @date 2022-06-17
 */
@Data
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

}
