package com.ruoyi.project.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.project.domain.ProjectCheck;
import com.ruoyi.project.domain.vo.ProjectCheckVo;
import com.ruoyi.project.service.IProjectCheckService;
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
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 个体户审批进度Controller
 *
 * @author ruoyi
 * @date 2022-08-01
 */
@RestController
@RequestMapping("/check")
public class ProjectCheckController extends BaseController
{
    @Autowired
    private IProjectCheckService projectCheckService;

    /**
     * 查询个体户审批进度列表
     */
    @RequiresPermissions("company:check:list")
    @GetMapping("/list")
    public TableDataInfo list(ProjectCheckVo projectCheck)
    {
        startPage();
        List<ProjectCheck> list = projectCheckService.selectProjectCheckList(projectCheck);
        return getDataTable(list);
    }

    /**
     * 导出个体户审批进度列表
     */
    @RequiresPermissions("company:check:export")
    @Log(title = "个体户审批进度", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProjectCheck projectCheck)
    {
        List<ProjectCheck> list = projectCheckService.selectProjectCheckList(projectCheck);
        ExcelUtil<ProjectCheck> util = new ExcelUtil<ProjectCheck>(ProjectCheck.class);
        util.exportExcel(response, list, "个体户审批进度数据");
    }

    /**
     * 获取个体户审批进度详细信息
     */
    @RequiresPermissions("company:check:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(projectCheckService.selectProjectCheckById(id));
    }

    /**
     * 新增个体户审批进度
     */
    @RequiresPermissions("company:check:add")
    @Log(title = "个体户审批进度", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectCheck projectCheck)
    {
        return toAjax(projectCheckService.insertProjectCheck(projectCheck));
    }

    /**
     * 修改个体户审批进度
     */
    @RequiresPermissions("company:check:edit")
    @Log(title = "个体户审批进度", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectCheck projectCheck)
    {
        return toAjax(projectCheckService.updateProjectCheck(projectCheck));
    }

    /**
     * 删除个体户审批进度
     */
    @RequiresPermissions("company:check:remove")
    @Log(title = "个体户审批进度", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(projectCheckService.deleteProjectCheckByIds(ids));
    }
}

