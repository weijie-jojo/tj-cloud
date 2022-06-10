package com.ruoyi.invoice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.invoice.mapper.AccountCheckInvoicesMapper;
import com.ruoyi.invoice.pojo.AccountCheckInvoices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class AccountCheckInvoicesServiceImpl implements AccountCheckInvoicesService {
    @Resource
    private AccountCheckInvoicesMapper accountCheckInvoicesMapper;


    @Override
    public List<AccountCheckInvoices> selectAllCheck(AccountCheckInvoices accountCheckInvoices) {
        System.out.println("sysCheckInvoices=="+ accountCheckInvoices);
        List<AccountCheckInvoices> selectList = accountCheckInvoicesMapper.selectList(
                new QueryWrapper<AccountCheckInvoices>()
                        .eq("invoice_code", accountCheckInvoices.getInvoiceCode()));
        return selectList;
    }

    @Override
    public int addCheckInvoices(AccountCheckInvoices accountCheckInvoices) {
        return accountCheckInvoicesMapper.insert(accountCheckInvoices);
    }
}
