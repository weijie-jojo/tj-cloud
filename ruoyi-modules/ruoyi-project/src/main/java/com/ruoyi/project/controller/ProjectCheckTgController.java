package com.ruoyi.project.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.project.domain.ProjectCheck;
import com.ruoyi.project.domain.vo.ProjectCheckVo;
import com.ruoyi.project.service.IProjectCheckTgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 个体户审批进度Controller
 *
 * @author ruoyi
 * @date 2022-08-01
 */
@RestController
@RequestMapping("/check/tg")
@Api(tags = "项目审批进度（腾工）")
public class ProjectCheckTgController extends BaseController
{
    @Autowired
    private IProjectCheckTgService projectCheckService;

    /**
     * 查询审批进度列表
     */
    @RequiresPermissions("company:check:list")
    @GetMapping("/list")
    @ApiOperation("查询审批进度列表")
    public TableDataInfo list(ProjectCheck projectCheck,  Long[] projectTypeArr)
    {

        System.out.println("projectTypeArr=="+projectTypeArr);
        List<Long> projectTypes= new ArrayList<>();
        if (projectTypeArr==null){
            projectTypes=null;
        }else {
            projectTypes= Arrays.asList(projectTypeArr);
        }
        System.out.println("projectTypes=="+projectTypes);
        startPage();
        List<ProjectCheckVo> list = projectCheckService.selectProjectCheckList(projectCheck,projectTypes);
        return getDataTable(list);
    }

    /**
     * 导出审批列表
     */
    @RequiresPermissions("company:check:export")
    @Log(title = "导出审批列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("导出审批列表")
    public void export(HttpServletResponse response, ProjectCheck projectCheck,List<Long> projectChecks)
    {
        List<ProjectCheckVo> list = projectCheckService.selectProjectCheckList(projectCheck,projectChecks);
        ExcelUtil<ProjectCheckVo> util = new ExcelUtil<ProjectCheckVo>(ProjectCheckVo.class);
        util.exportExcel(response, list, "个体户审批进度数据");
    }

    /**
     * 查询审批列表明细
     */
    @RequiresPermissions("company:check:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("查询审批列表明细")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(projectCheckService.selectProjectCheckById(id));
    }

    /**
     * 新增审批进度
     */
    @RequiresPermissions("company:check:add")
    @Log(title = "个体户审批进度", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增审批进度")
    public AjaxResult add(@RequestBody ProjectCheck projectCheck)
    {
        Date date = new Date();
        projectCheck.setCheckDate(date);
        return toAjax(projectCheckService.insertProjectCheck(projectCheck));
    }

    /**
     * 修改审批进度
     */
    @RequiresPermissions("company:check:edit")
    @Log(title = "个体户审批进度", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改审批进度")
    public AjaxResult edit(@RequestBody ProjectCheck projectCheck)
    {
        return toAjax(projectCheckService.updateProjectCheck(projectCheck));
    }

    /**
     * 删除审批进度结果
     */
    @RequiresPermissions("company:check:remove")
    @Log(title = "个体户审批进度", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("删除审批进度结果")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(projectCheckService.deleteProjectCheckByIds(ids));
    }
}

