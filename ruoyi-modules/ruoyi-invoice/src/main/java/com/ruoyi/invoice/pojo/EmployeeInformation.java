package com.ruoyi.invoice.pojo;

import lombok.Data;

@Data
public class EmployeeInformation {
    /** ID */
    private Long userId;

    /** 部门id */
    private Long deptId;

    /** 部门名称 */
    private String department;

    /** 员工编号 */
    private String employeeNumber;

    /** 用户名 */
    private String username;

    /** 昵称 */
    private String nickName;

    /** 身份证号码 */
    private String idNo;

    /** 性别 */
    private String gender;

    /** 职务 */
    private String postId;

    /** 手机号码 */
    private String phone;

    /** 邮箱 */
    private String email;

    /** 状态：1在职、0离职 */
    private Long enabled;

    /** 工资卡号 */
    private String payCheck;

    /** 工资卡开户银行 */
    private String payCheckBank;

    /** 报销卡 */
    private String accountCard;

    /** 报销卡开户银行 */
    private String accountCardBank;

    /** 岗位名 */
    private String postName;
}
