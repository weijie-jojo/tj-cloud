package com.ruoyi.invoice.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AccountExpense {
    @ApiModelProperty(value = "报销编号")
    private String expenseCode;
    @ApiModelProperty(value = "报销id")
    private int expenseId;
    @ApiModelProperty(value = "部门")
    private String dept;

    private Long createUser;
    private String createTime;
    private String updateTime;

    @ApiModelProperty(value = "项目附件数")
    private String accessoryNum1;
    private String accessoryNum2;
    private String accessoryNum3;
    private String accessoryNum4;
    private String accessoryNum5;

    @ApiModelProperty(value = "项目备注")
    private String item1Remark;
    @ApiModelProperty(value = "项目金额")
    private double item1Money;
    @ApiModelProperty(value = "项目描述")
    private String item1Desc;
    private String item2Remark;
    private double item2Money;
    private String item2Desc;
    private String item3Remark;
    private double item3Money;
    private String item3Desc;
    private String item4Remark;
    private double item4Money;
    private String item4Desc;
    private String item5Remark;
    private double item5Money;
    private String item5Desc;

    @ApiModelProperty(value = "总费用")
    private double totalMoney;
    @ApiModelProperty(value = "付款方式")
    private Integer paywayId;
    @ApiModelProperty(value = "付款方式备注")
    private String paywayRemark;
    @ApiModelProperty(value = "收款单位（人）")
    private String userGetid;
    @ApiModelProperty(value = "收款单位（人）银行卡")
    private String bankcardGetid;
    @ApiModelProperty(value = "收款单位（人）银行卡所属银行")
    private String bankGetname;
    @ApiModelProperty(value = "付款单位id")
    private String companyPayId;
    @ApiModelProperty(value = "付款单位银行卡")
    private String bankPaycode;
    @ApiModelProperty(value = "付款单位银行卡所属银行")
    private String bankPayname;
    @ApiModelProperty(value = "是否删除;1:删除,0:未删除")
    private Integer isDeleted;
    @ApiModelProperty(value = "单据状态（关联字典表）")
    private Integer invoiceType;
    @ApiModelProperty(value = "部门id")
    private Integer deptId;
    @ApiModelProperty(value = "总经理审核")
    private String gmCheck;
    @ApiModelProperty(value = "部门主管审核")
    private String dmCheck;
    @ApiModelProperty(value = "财务审核")
    private String financeCheck;
    @ApiModelProperty(value = "报销凭证影像")
    private String expenseImage;
    @ApiModelProperty(value = "付款凭证影像")
    private String expenseImage2;
    @ApiModelProperty(value = "进度状态（1 办理中、2 完成、3 异常）")
    private Integer stepType;
}
