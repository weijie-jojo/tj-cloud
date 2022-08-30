package com.ruoyi.company.domain;

import com.ruoyi.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 个体户注册申请信息对象 self_application_info
 * 
 * @author ruoyi
 * @date 2022-06-17
 */
@Data
public class SelfApplicationInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 申请id */
    private Long id;

    /** 用户名 */
    @ApiModelProperty(value = "用户名")
    private String username;

    /** 个体户编码 */
    @ApiModelProperty(value = "个体户编码")
    private String selfCode;

    /** 是否本人申请 */
    @ApiModelProperty(value = "是否本人申请")
    private String oneselfApply;

    /** 申请人姓名 */
    @ApiModelProperty(value = "申请人姓名")
    private String applyName;

    /** 申请人电话 */
    @ApiModelProperty(value = "申请人电话")
    private String applyPhone;

    /** 申请人证件号码 */
    @ApiModelProperty(value = "申请人证件号码")
    private String applyIdNum;

    /** 申请人证件类型 */
    @ApiModelProperty(value = "申请人证件类型")
    private String applyDocumentType;

    /** 联络人姓名id */
    @ApiModelProperty(value = "联络人姓名")
    private String contactName;

    /** 联络人电话 */
    @ApiModelProperty(value = "联络人电话")
    private String contactPhone;

    /** 联络人证件号码 */
    @ApiModelProperty(value = "联络人证件号码")
    private String contactIdNum;

    /** 联络人证件类型 */
    @ApiModelProperty(value = "联络人证件类型")
    private String contactDocumentType;
    
}
