package com.ruoyi.invoice.service;

import com.ruoyi.invoice.pojo.AccountCheckInvoices;

import java.util.List;

public interface AccountCheckInvoicesService {
    List<AccountCheckInvoices> selectAllCheck(AccountCheckInvoices accountCheckInvoices);
    int addCheckInvoices(AccountCheckInvoices accountCheckInvoices);
}
