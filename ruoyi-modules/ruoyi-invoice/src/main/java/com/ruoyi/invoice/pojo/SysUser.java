package com.ruoyi.invoice.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysUser {
    private Integer userId;
    @ApiModelProperty(value = "用户昵称")
    private String nickName;
    @ApiModelProperty(value = "报销卡")
    private String payCheckBank;
    @ApiModelProperty(value = "报销卡开户银行")
    private String accountCardBank;
    @ApiModelProperty(value = "工资卡号")
    private String payCheck;
}
