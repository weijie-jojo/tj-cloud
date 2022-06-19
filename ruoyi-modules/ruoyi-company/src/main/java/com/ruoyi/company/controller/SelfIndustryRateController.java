package com.ruoyi.company.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.company.domain.SelfLegalPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.company.domain.SelfIndustryRate;
import com.ruoyi.company.service.ISelfIndustryRateService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 个体户行业类型税率Controller
 * 
 * @author ruoyi
 * @date 2022-06-17
 */
@RestController
@RequestMapping("/rate")
public class SelfIndustryRateController extends BaseController
{
    @Autowired
    private ISelfIndustryRateService selfIndustryRateService;

    /**
     * 导出个体户行业类型税率列表（不分页）
     */
    @RequiresPermissions("company:person:list")
    @GetMapping("/all")
    public TableDataInfo all()
    {
        List<SelfIndustryRate> list = selfIndustryRateService.selectSelfIndustryRate();
        return getDataTable(list);
    }

    /**
     * 查询个体户行业类型税率列表
     */
    @RequiresPermissions("company:rate:list")
    @GetMapping("/list")
    public TableDataInfo list(SelfIndustryRate selfIndustryRate)
    {
        startPage();
        List<SelfIndustryRate> list = selfIndustryRateService.selectSelfIndustryRateList(selfIndustryRate);
        return getDataTable(list);
    }

    /**
     * 导出个体户行业类型税率列表
     */
    @RequiresPermissions("company:rate:export")
    @Log(title = "个体户行业类型税率", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SelfIndustryRate selfIndustryRate)
    {
        List<SelfIndustryRate> list = selfIndustryRateService.selectSelfIndustryRateList(selfIndustryRate);
        ExcelUtil<SelfIndustryRate> util = new ExcelUtil<SelfIndustryRate>(SelfIndustryRate.class);
        util.exportExcel(response, list, "个体户行业类型税率数据");
    }

    /**
     * 获取个体户行业类型税率详细信息
     */
    @RequiresPermissions("company:rate:query")
    @GetMapping(value = "/{industryId}")
    public AjaxResult getInfo(@PathVariable("industryId") String industryId)
    {
        return AjaxResult.success(selfIndustryRateService.selectSelfIndustryRateByIndustryId(industryId));
    }

    /**
     * 新增个体户行业类型税率
     */
    @RequiresPermissions("company:rate:add")
    @Log(title = "个体户行业类型税率", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SelfIndustryRate selfIndustryRate)
    {
        return toAjax(selfIndustryRateService.insertSelfIndustryRate(selfIndustryRate));
    }

    /**
     * 修改个体户行业类型税率
     */
    @RequiresPermissions("company:rate:edit")
    @Log(title = "个体户行业类型税率", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SelfIndustryRate selfIndustryRate)
    {
        return toAjax(selfIndustryRateService.updateSelfIndustryRate(selfIndustryRate));
    }

    /**
     * 删除个体户行业类型税率
     */
    @RequiresPermissions("company:rate:remove")
    @Log(title = "个体户行业类型税率", businessType = BusinessType.DELETE)
	@DeleteMapping("/{industryIds}")
    public AjaxResult remove(@PathVariable String[] industryIds)
    {
        return toAjax(selfIndustryRateService.deleteSelfIndustryRateByIndustryIds(industryIds));
    }
}
