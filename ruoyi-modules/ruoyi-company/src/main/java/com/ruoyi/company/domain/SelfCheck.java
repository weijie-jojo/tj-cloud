package com.ruoyi.company.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @ApiModelProperty(value ="时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkDate;

    /** 审核人员 */
    @ApiModelProperty(value ="审核人员")
    private String checkUser;

    /** 详情 */
    @ApiModelProperty(value ="详情")
    private String checkReasult;

    /** 个体户编码 */
    @ApiModelProperty(value ="个体户编码")
    private String selfCode;

    /** 步骤
     */
    @ApiModelProperty(value ="步骤 ")
    private String selfType;

    /** 手机号码 */
    @ApiModelProperty(value ="手机号码")
    private String phonenumber;
    
}
