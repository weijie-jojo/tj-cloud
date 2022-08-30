package com.ruoyi.invoice.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysDept {
    private int deptId;
    @ApiModelProperty(value = "名字")
    private String name;
    @ApiModelProperty(value = "是否启用")
    private int enabled;
    @ApiModelProperty(value = "部门名")
    private String deptName;
}
