package com.ruoyi.invoice.vo;

import com.ruoyi.invoice.pojo.AccountExpense;
import lombok.Data;

@Data
public class AccountExpenseVo extends AccountExpense {
    private String dictLabel;//字典表中状态名
    private String userName;//用户表中的用户名
    private String nickName;//用户表中的用户名
    private String deptName;//部门名
}
