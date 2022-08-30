package com.ruoyi.project.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
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
@Data
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

}

