package com.ruoyi.invoice.vo;

import com.ruoyi.invoice.pojo.AccountCheckInvoices;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AccountCheckInvoicesVo extends AccountCheckInvoices {
    @ApiModelProperty(value = "用户名")
    private String username;
}
