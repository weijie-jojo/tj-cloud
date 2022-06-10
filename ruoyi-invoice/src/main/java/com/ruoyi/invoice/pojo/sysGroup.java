package com.ruoyi.invoice.pojo;

import lombok.Data;

@Data
public class sysGroup {
    private Integer groupId;
    private String groupCode;
    private String groupName;
    private String groupAddress;
    private String groupRegistAddress;
    private String groupOpenBank;
    private String groupBankAccount;
    private String groupCreditCode;
    private Integer groupStatus;
    private String createTime;
    private Integer isDeleted;
}
