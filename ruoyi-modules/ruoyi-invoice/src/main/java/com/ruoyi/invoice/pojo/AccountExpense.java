package com.ruoyi.invoice.pojo;

import lombok.Data;

@Data
public class AccountExpense {
    private String expenseCode;
    private int expenseId;
    private String dept;
    private String createTime;
    private String updateTime;
    private int accessoryNum;
    private String item1Remark;
    private double item1Money;
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
    private double totalMoney;
    private Integer paywayId;
    private String paywayRemark;
    private String userGetid;
    private String bankcardGetid;
    private String bankGetname;
    private String companyPayId;
    private String bankPaycode;
    private String bankPayname;
    private Integer isDeleted;
    private Integer invoiceType;
    private Long createUser;
    private Integer deptId;
    private String gmCheck;
    private String dmCheck;
    private String financeCheck;
    private String expenseImage;
    private String expenseImage2;
    private Integer stepType;
}
