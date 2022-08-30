package com.ruoyi.invoice.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysBankcard {
    private int bankcardId;
    @ApiModelProperty(value = "编号")
    private String bankcardCode;
    private int bankId;
    private int userId;
}
