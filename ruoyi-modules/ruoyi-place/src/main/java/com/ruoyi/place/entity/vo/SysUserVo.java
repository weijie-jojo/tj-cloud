package com.ruoyi.place.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysUserVo {
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "用户昵称")
    private String nickName;
    @ApiModelProperty(value = "结算卡号")
    private String accountCard;
    @ApiModelProperty(value = "结算卡银行名")
    private String accountCardBank;
    @ApiModelProperty(value = "角色id")
    private int roleId;
    @ApiModelProperty(value = "部门id")
    private int deptId;
    @ApiModelProperty(value = "员工编号")
    private String employeeNumber;
}
