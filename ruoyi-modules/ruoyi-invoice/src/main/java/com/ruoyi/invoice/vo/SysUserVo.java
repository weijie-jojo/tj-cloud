package com.ruoyi.invoice.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysUserVo {
    private Long userId;
    @ApiModelProperty(value = "用户名")
    private String nickName;
    @ApiModelProperty(value = "报销卡开户银行名")
    private String accountCard;
    @ApiModelProperty(value = "报销卡开户银行卡号")
    private String accountCardBank;
    @ApiModelProperty(value = "角色id")
    private int roleId;
    @ApiModelProperty(value = "部门id")
    private int deptId;
}
