package com.ruoyi.company.controller;

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.company.domain.PayTaxInfo;
import com.ruoyi.company.service.IPayTaxInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 纳税账户
 Controller
 *
 * @author ruoyi
 * @date 2022-06-23
 */
@RestController
@RequestMapping("/payTaxInfo")
@Api(tags = "纳税账户")
public class PayTaxInfoController extends BaseController {
    @Autowired
    private IPayTaxInfoService payTaxInfoService;
    /**
     * 查询纳税账户
     列表
     */
    @RequiresPermissions("company:payTaxInfo:list")
    @GetMapping("/list")
    @ApiOperation("查询纳税账户（分页）")
    public TableDataInfo list(PayTaxInfo payTaxInfo)
    {
        startPage();
        List<PayTaxInfo> list = payTaxInfoService.selectPayTaxInfoList(payTaxInfo);
        return getDataTable(list);
    }
    /**
     * 查询纳税账户
     列表
     */
    @RequiresPermissions("company:payTaxInfo:list")
    @GetMapping("/all")
    @ApiOperation("查询纳税账户（不分页）")
    public List<PayTaxInfo> all()
    {
        List<PayTaxInfo> list = payTaxInfoService.selectPayTaxInfoAll();
        return list;
    }
}
