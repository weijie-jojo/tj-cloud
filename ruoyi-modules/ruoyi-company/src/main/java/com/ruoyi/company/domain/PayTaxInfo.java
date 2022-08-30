package com.ruoyi.company.domain;

import com.ruoyi.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 纳税账户
 对象 pay_tax_info
 *
 * @author ruoyi
 * @date 2022-06-23
 */
@Data
public class PayTaxInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long taxId;

    /** 员工编号 */
    @ApiModelProperty(value = "员工编号")
    private String employeeNumber;

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

    /** 纳税账户账号 */
    @ApiModelProperty(value = "纳税账户账号")
    private String publicAccountNumber3;

    /** 纳税账户开户行 */
    @ApiModelProperty(value = "纳税账户开户行")
    private String publicDepositBank3;

    /** 纳税账户户名 */
    @ApiModelProperty(value = "纳税账户户名")
    private String accountName;
}
