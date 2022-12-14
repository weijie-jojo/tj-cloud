package com.ruoyi.invoice.handler;


import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.invoice.dto.DataDto;
import com.ruoyi.invoice.pojo.AccountCheckInvoices;
import com.ruoyi.invoice.service.AccountCheckInvoicesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor //代替了resouse或者Autowrited
@Api(tags = "单据审批结果信息")
@RequestMapping("/checkInvoices")
public class AccountCheckInvoicesHandler {

    private final AccountCheckInvoicesService accountCheckInvoicesService;

    @GetMapping(value ="/getAllCheck")
    @ApiOperation("单据审批结果信息")
    public List<AccountCheckInvoices> getAllBorrow(AccountCheckInvoices sysCheckInvoice){
        List<AccountCheckInvoices> accountCheckInvoices = accountCheckInvoicesService.selectAllCheck(sysCheckInvoice);
        return accountCheckInvoices;
    }

    @PostMapping(value ="/addCheckInvoices")
    @Log(title = "插入审批结果信息",businessType = BusinessType.INSERT)
    @ApiOperation("插入审批结果信息")
    public DataDto addCheckInvoices(AccountCheckInvoices sysCheckInvoice){
        int num= accountCheckInvoicesService.addCheckInvoices(sysCheckInvoice);
        DataDto dataDto = new DataDto();
        if (num>0){
            dataDto.success("借支单填写成功");
        }
        return dataDto;
    }
}
