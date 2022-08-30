package com.ruoyi.company.domain;

import com.ruoyi.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 个体户名字审核对象 self_name_review
 * 
 * @author ruoyi
 * @date 2022-06-16
 */
@Data
public class SelfNameReview extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 个体户名字审核是否通过 0未通过 1已通过  */
    private Integer nameStatus;

    /** 个体户id */
    private Long selfId;

    /** 审核不通过原因 */
    @ApiModelProperty(value ="审核不通过原因")
    private String nameRemark;

    /** 用户名 */
    @ApiModelProperty(value ="用户名")
    private String username;

    /** 个体户编码 */
    @ApiModelProperty(value ="个体户编码")
    private String selfCode;

    /** 冠名类型 */
    @ApiModelProperty(value ="冠名类型")
    private String titleType;

    /** 行政区划 */
    @ApiModelProperty(value ="行政区划")
    private String administrativeDivision;

    /** 行业 */
    @ApiModelProperty(value ="行业")
    private String industry;

    /** 组织形式 */
    @ApiModelProperty(value ="组织形式")
    private String organizationalForm;

    /** 所在行政区划 */
    @ApiModelProperty(value ="所在行政区划")
    private String administrativeRegion;

    /** 是否随机字号 */
    @ApiModelProperty(value ="是否随机字号")
    private String random;

    /** 登记机关 */
    @ApiModelProperty(value ="登记机关")
    private String registrationAuthority;

    /** 字号1 */
    @ApiModelProperty(value ="字号1")
    private String fontSize1;

    /** 拟设名称1 */
    @ApiModelProperty(value ="拟设名称1")
    private String poposedName1;

    /** 字号2 */
    @ApiModelProperty(value ="字号2")
    private String fontSize2;

    /** 拟设名称2 */
    @ApiModelProperty(value ="拟设名称2")
    private String poposedName2;

    /** 字号3 */
    @ApiModelProperty(value ="字号3")
    private String fontSize3;

    /** 拟设名称3 */
    @ApiModelProperty(value ="拟设名称3")
    private String poposedName3;

    /** 字号4 */
    @ApiModelProperty(value ="字号4")
    private String fontSize4;

    /** 拟设名称4 */
    @ApiModelProperty(value ="拟设名称4")
    private String poposedName4;

    /** 字号5 */
    @ApiModelProperty(value ="字号5")
    private String fontSize5;

    /** 拟设名称5 */
    @ApiModelProperty(value ="拟设名称5")
    private String poposedName5;

    /** 字号6 */
    @ApiModelProperty(value ="字号6")
    private String fontSize6;

    /** 拟设名称6 */
    @ApiModelProperty(value ="拟设名称6")
    private String poposedName6;

    /** 字号7 */
    @ApiModelProperty(value ="字号7")
    private String fontSize7;

    /** 拟设名称7 */
    @ApiModelProperty(value ="拟设名称7")
    private String poposedName7;

    /** 字号8 */
    @ApiModelProperty(value ="字号8")
    private String fontSize8;

    /** 拟设名称8 */
    @ApiModelProperty(value ="拟设名称8")
    private String poposedName8;

    /** 字号9 */
    @ApiModelProperty(value ="字号9")
    private String fontSize9;

    /** 拟设名称9 */
    @ApiModelProperty(value ="拟设名称9")
    private String poposedName9;

    /** 字号10 */
    @ApiModelProperty(value ="字号10")
    private String fontSize10;

    /** 拟设名称10 */
    @ApiModelProperty(value ="拟设名称10")
    private String poposedName10;

}
