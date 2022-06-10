package com.ruoyi.invoice.vo;

import com.ruoyi.invoice.pojo.AccountBorrow;
import lombok.Data;

@Data
public class AccountBorrowVo extends AccountBorrow {
    private String label;//字典表中状态名
    private String username;//用户表中的用户名
    private String nickName;//用户表中的用户名
}
