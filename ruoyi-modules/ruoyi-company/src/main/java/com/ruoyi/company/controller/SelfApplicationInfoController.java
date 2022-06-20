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
import com.ruoyi.company.domain.SelfApplicationInfo;
import com.ruoyi.company.service.ISelfApplicationInfoService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 个体户注册申请信息Controller
 * 
 * @author ruoyi
 * @date 2022-06-17
 */
@RestController
@RequestMapping("/info")
public class SelfApplicationInfoController extends BaseController
{
    @Autowired
    private ISelfApplicationInfoService selfApplicationInfoService;

    /**
     * 查询个体户注册申请信息列表
     */
    @RequiresPermissions("company:info:list")
    @GetMapping("/list")
    public TableDataInfo list(SelfApplicationInfo selfApplicationInfo)
    {
        startPage();
        List<SelfApplicationInfo> list = selfApplicationInfoService.selectSelfApplicationInfoList(selfApplicationInfo);
        return getDataTable(list);
    }

    /**
     * 导出个体户注册申请信息列表
     */
    @RequiresPermissions("company:info:export")
    @Log(title = "个体户注册申请信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SelfApplicationInfo selfApplicationInfo)
    {
        List<SelfApplicationInfo> list = selfApplicationInfoService.selectSelfApplicationInfoList(selfApplicationInfo);
        ExcelUtil<SelfApplicationInfo> util = new ExcelUtil<SelfApplicationInfo>(SelfApplicationInfo.class);
        util.exportExcel(response, list, "个体户注册申请信息数据");
    }

    /**
     * 获取个体户注册申请信息详细信息
     */
    @RequiresPermissions("company:info:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(selfApplicationInfoService.selectSelfApplicationInfoById(id));
    }

    /**
     * 新增个体户注册申请信息
     */
    @RequiresPermissions("company:info:add")
    @Log(title = "个体户注册申请信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SelfApplicationInfo selfApplicationInfo)
    {
        return toAjax(selfApplicationInfoService.insertSelfApplicationInfo(selfApplicationInfo));
    }

    /**
     * 修改个体户注册申请信息
     */
    @RequiresPermissions("company:info:edit")
    @Log(title = "个体户注册申请信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SelfApplicationInfo selfApplicationInfo)
    {
        return toAjax(selfApplicationInfoService.updateSelfApplicationInfo(selfApplicationInfo));
    }

    /**
     * 删除个体户注册申请信息
     */
    @RequiresPermissions("company:info:remove")
    @Log(title = "个体户注册申请信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(selfApplicationInfoService.deleteSelfApplicationInfoByIds(ids));
    }
}
