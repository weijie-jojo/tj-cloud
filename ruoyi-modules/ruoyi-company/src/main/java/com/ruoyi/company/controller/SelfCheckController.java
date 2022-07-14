package com.ruoyi.company.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.company.domain.SelfCheck;
import com.ruoyi.company.service.ISelfCheckService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 单据审批结果Controller
 *
 * @author ruoyi
 * @date 2022-07-14
 */
@RestController
@RequestMapping("/check")
public class SelfCheckController extends BaseController
{
    @Autowired
    private ISelfCheckService selfCheckService;

    /**
     * 查询单据审批结果列表
     */
    @RequiresPermissions("company:check:list")
    @GetMapping("/list")
    public TableDataInfo list(SelfCheck selfCheck)
    {
        startPage();
        List<SelfCheck> list = selfCheckService.selectSelfCheckList(selfCheck);
        return getDataTable(list);
    }

    /**
     * 导出单据审批结果列表
     */
    @RequiresPermissions("company:check:export")
    @Log(title = "单据审批结果", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SelfCheck selfCheck)
    {
        List<SelfCheck> list = selfCheckService.selectSelfCheckList(selfCheck);
        ExcelUtil<SelfCheck> util = new ExcelUtil<SelfCheck>(SelfCheck.class);
        util.exportExcel(response, list, "单据审批结果数据");
    }

    /**
     * 获取单据审批结果详细信息
     */
    @RequiresPermissions("company:check:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(selfCheckService.selectSelfCheckById(id));
    }

    /**
     * 新增单据审批结果
     */
    @RequiresPermissions("company:check:add")
    @Log(title = "单据审批结果", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SelfCheck selfCheck)
    {
        return toAjax(selfCheckService.insertSelfCheck(selfCheck));
    }

    /**
     * 修改单据审批结果
     */
    @RequiresPermissions("company:check:edit")
    @Log(title = "单据审批结果", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SelfCheck selfCheck)
    {
        return toAjax(selfCheckService.updateSelfCheck(selfCheck));
    }

    /**
     * 删除单据审批结果
     */
    @RequiresPermissions("company:check:remove")
    @Log(title = "单据审批结果", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(selfCheckService.deleteSelfCheckByIds(ids));
    }
}
