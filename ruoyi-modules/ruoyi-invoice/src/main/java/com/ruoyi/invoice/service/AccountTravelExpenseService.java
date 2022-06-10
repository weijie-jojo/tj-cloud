package com.ruoyi.invoice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.invoice.pojo.AccountExpenseItem;
import com.ruoyi.invoice.pojo.AccountTravelExpense;
import com.ruoyi.invoice.qo.TimeQo;
import com.ruoyi.invoice.vo.AccountTravelExpenseVo;

import java.util.List;

public interface AccountTravelExpenseService {
    int editTravelExpense(AccountTravelExpense accountTravelExpense);
    int editTravelExpenseById(AccountTravelExpense accountTravelExpense);
    int editTravelExpenseType(AccountTravelExpense accountTravelExpense);
    int addTravelExpense(AccountTravelExpense accountTravelExpense);
    IPage<AccountTravelExpenseVo> getAllTravelExpense(AccountTravelExpense accountTravelExpense, TimeQo timeQo, Integer currentPage, Integer limit);
    int editExpenseById(int id);
    List<AccountExpenseItem> getAllExpenseItem();
    List<AccountTravelExpense> selectMaxCode();
}
