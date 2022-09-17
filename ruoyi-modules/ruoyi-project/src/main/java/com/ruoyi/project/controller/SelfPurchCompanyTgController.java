package com.ruoyi.project.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.project.domain.SelfPurchCompany;
import com.ruoyi.project.service.ISelfPurchCompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 甲方信息Controller
 *
 * @author ruoyi
 * @date 2022-09-01
 */
@RestController
@RequestMapping("/company")
@Api(tags = "甲方信息表")
public class SelfPurchCompanyTgController extends BaseController
{
    @Autowired
    private ISelfPurchCompanyService selfPurchCompanyService;

    /**
     * 查询甲方信息列表
     */
    @RequiresPermissions("company:company:list")
    @GetMapping("/list")
    @ApiOperation("查询甲方信息列表")
    public TableDataInfo list(SelfPurchCompany selfPurchCompany)
    {
//        startPage();
        List<SelfPurchCompany> list = selfPurchCompanyService.selectSelfPurchCompanyList(selfPurchCompany);
        return getDataTable(list);
    }

    /**
     * 查询甲方信息列表（分页）
     */
    @RequiresPermissions("company:company:list")
    @GetMapping("/list1")
    @ApiOperation("查询甲方信息列表（分页）")
    public TableDataInfo list1(SelfPurchCompany selfPurchCompany)
    {
        startPage();
        List<SelfPurchCompany> list = selfPurchCompanyService.selectSelfPurchCompanyList(selfPurchCompany);
        return getDataTable(list);
    }

    /**
     * 导出甲方信息列表
     */
    @RequiresPermissions("company:company:export")
    @Log(title = "甲方信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("导出甲方信息列表")
    public void export(HttpServletResponse response, SelfPurchCompany selfPurchCompany)
    {
        List<SelfPurchCompany> list = selfPurchCompanyService.selectSelfPurchCompanyList(selfPurchCompany);
        ExcelUtil<SelfPurchCompany> util = new ExcelUtil<SelfPurchCompany>(SelfPurchCompany.class);
        util.exportExcel(response, list, "甲方信息数据");
    }

    /**
     * 获取甲方信息详细信息
     */
    @RequiresPermissions("company:company:query")
    @GetMapping(value = "/{purchId}")
    @ApiOperation("获取甲方信息详细信息")
    public AjaxResult getInfo(@PathVariable("purchId") String purchId)
    {
        return AjaxResult.success(selfPurchCompanyService.selectSelfPurchCompanyByPurchId(purchId));
    }

    /**
     * 新增甲方信息
     */
    @RequiresPermissions("company:company:add")
    @Log(title = "甲方信息", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增甲方信息")
    public AjaxResult add(@RequestBody SelfPurchCompany selfPurchCompany)
    {
        try {
            return toAjax(selfPurchCompanyService.insertSelfPurchCompany(selfPurchCompany));
        }catch (DuplicateKeyException ex){
            return error("甲方或税号重复，请重新填写");
        }
    }

    /**
     * 修改甲方信息
     */
    @RequiresPermissions("company:company:edit")
    @Log(title = "甲方信息", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改甲方信息")
    public AjaxResult edit(@RequestBody SelfPurchCompany selfPurchCompany)
    {
        return toAjax(selfPurchCompanyService.updateSelfPurchCompany(selfPurchCompany));
    }

    /**
     * 删除甲方信息
     */
    @RequiresPermissions("company:company:remove")
    @Log(title = "甲方信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{purchIds}")
    @ApiOperation("删除甲方信息")
    public AjaxResult remove(@PathVariable String[] purchIds)
    {
        return toAjax(selfPurchCompanyService.deleteSelfPurchCompanyByPurchIds(purchIds));
    }
}
