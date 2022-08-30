package com.ruoyi.invoice.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class sysGroup {
    private Integer groupId;
    @ApiModelProperty(value = "公司编码")
    private String groupCode;
    @ApiModelProperty(value = "公司名称")
    private String groupName;
    @ApiModelProperty(value = "办公地址")
    private String groupAddress;
    @ApiModelProperty(value = "注册地址")
    private String groupRegistAddress;
    @ApiModelProperty(value = "开户行")
    private String groupOpenBank;
    @ApiModelProperty(value = "开户行账号")
    private String groupBankAccount;
    @ApiModelProperty(value = "企业信用代码")
    private String groupCreditCode;
    @ApiModelProperty(value = "企业状态(0:正常1:作废)")
    private Integer groupStatus;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "是否删除;1:删除,0:未删除")
    private Integer isDeleted;
}
