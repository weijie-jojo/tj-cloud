package com.ruoyi.invoice.vo;

import com.ruoyi.invoice.pojo.AccountTravelExpense;
import lombok.Data;

@Data
public class AccountTravelExpenseVo extends AccountTravelExpense {
    private String dictLabel;//字典表中状态名
    private String userName;//用户表中的用户名
    private String nickName;//用户表中的用户名
}
