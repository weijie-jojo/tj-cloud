package com.ruoyi.invoice.vo;

import com.ruoyi.invoice.pojo.SysBankcard;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysBankcardVo extends SysBankcard {
    @ApiModelProperty(value = "银行名")
    private String bankName;
}
