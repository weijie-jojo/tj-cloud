package com.ruoyi.invoice.vo;

import com.ruoyi.invoice.pojo.AccountTravelExpense;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AccountTravelExpenseVo extends AccountTravelExpense {
    @ApiModelProperty(value = "字典表中状态名")
    private String dictLabel;
    @ApiModelProperty(value = "用户表中的用户名")
    private String userName;
    @ApiModelProperty(value = "用户表中的用户昵称")
    private String nickName;
    @ApiModelProperty(value = "部门名")
    private String deptName;
}
