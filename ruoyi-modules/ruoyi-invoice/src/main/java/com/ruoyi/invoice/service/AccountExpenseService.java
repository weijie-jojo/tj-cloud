package com.ruoyi.invoice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.invoice.pojo.AccountExpense;
import com.ruoyi.invoice.qo.TimeQo;
import com.ruoyi.invoice.vo.AccountExpenseVo;

import java.util.List;


public interface AccountExpenseService {
    int editExpense(AccountExpense accountExpense);
    int editExpenseByExpenseId(AccountExpense accountExpense);
    int editExpenseType(AccountExpense accountExpense);
    List<AccountExpense> selectMaxCode();
    int addExpense(AccountExpense accountExpense);
    int editExpenseById(int expenseId);//逻辑删除（修改isdelete状态为1）
    int checkExpenseById(int expenseId);//逻辑删除（修改isdelete状态为1）
    IPage<AccountExpenseVo> selectAllExpense(AccountExpense accountExpense, TimeQo timeQo, Integer currentPage, Integer limit);
}
