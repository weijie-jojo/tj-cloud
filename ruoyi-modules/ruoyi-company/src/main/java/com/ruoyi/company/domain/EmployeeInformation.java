package com.ruoyi.company.domain;

import com.ruoyi.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 员工信息对象 employee_information
 * 
 * @author employeeInformation
 * @date 2022-06-17
 */
@Data
public class EmployeeInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long userId;

    /** 部门名称 */
    @ApiModelProperty(value = "部门名称")
    private Long deptId;

    /** 部门名称 */
    @ApiModelProperty(value = "部门名称")
    private String department;

    /** 员工编号 */
    @ApiModelProperty(value = "员工编号")
    private String employeeNumber;

    /** 用户名 */
    @ApiModelProperty(value = "用户名")
    private String username;

    /** 昵称 */
    @ApiModelProperty(value = "昵称")
    private String nickName;

    /** 身份证号码 */
    @ApiModelProperty(value = "身份证号码")
    private String idNo;

    /** 性别 */
    @ApiModelProperty(value = "性别")
    private String gender;

    /** 职务 */
    @ApiModelProperty(value = "职务")
    private String postId;

    /** 手机号码 */
    @ApiModelProperty(value = "手机号码")
    private String phone;

    /** 邮箱 */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /** 状态：1在职、0离职 */
    @ApiModelProperty(value = "状态：1在职、0离职")
    private Long enabled;

    /** 工资卡号 */
    @ApiModelProperty(value = "工资卡号")
    private String payCheck;

    /** 工资卡开户银行 */
    @ApiModelProperty(value = "工资卡开户银行")
    private String payCheckBank;

    /** 报销卡 */
    @ApiModelProperty(value = "报销卡")
    private String accountCard;

    /** 报销卡开户银行 */
    @ApiModelProperty(value = "报销卡开户银行")
    private String accountCardBank;

}
