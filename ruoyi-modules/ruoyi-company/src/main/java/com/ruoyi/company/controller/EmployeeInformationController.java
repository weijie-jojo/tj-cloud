package com.ruoyi.company.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import com.ruoyi.company.domain.EmployeeInformation;
import com.ruoyi.company.service.IEmployeeInformationService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 员工信息Controller
 * 
 * @author employeeInformation
 * @date 2022-06-17
 */
@RestController
@RequestMapping("/information")
@Api(tags = "员工信息")
public class EmployeeInformationController extends BaseController {

    @Autowired
    private IEmployeeInformationService employeeInformationService;

    /**
     * 查询员工信息列表（不分页）
     */
    @ApiOperation("查询员工信息列表")
//    @RequiresPermissions("company:information:list")
    @GetMapping("/all")
    public TableDataInfo all() {
        List<EmployeeInformation> list = employeeInformationService.selectEmployeeInformation();
        return getDataTable(list);
    }

    /**
     * 查询员工信息列表
     */
    @ApiOperation("查询员工信息列表")
//    @RequiresPermissions("company:information:list")
    @GetMapping("/list")
    public TableDataInfo list(EmployeeInformation employeeInformation) {
        startPage();
        List<EmployeeInformation> list = employeeInformationService.selectEmployeeInformationList(employeeInformation);
        return getDataTable(list);
    }

    /**
     * 导出员工信息列表
     */
    @ApiOperation("导出员工信息列表")
    @RequiresPermissions("company:information:export")
    @Log(title = "员工信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EmployeeInformation employeeInformation)
    {
        List<EmployeeInformation> list = employeeInformationService.selectEmployeeInformationList(employeeInformation);
        ExcelUtil<EmployeeInformation> util = new ExcelUtil<EmployeeInformation>(EmployeeInformation.class);
        util.exportExcel(response, list, "员工信息数据");
    }

    /**
     * 获取员工信息详细信息
     */
    @ApiOperation("获取员工信息详细信息")
//    @RequiresPermissions("company:information:query")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return AjaxResult.success(employeeInformationService.selectEmployeeInformationByUserId(userId));
    }

    /**
     * 新增员工信息
     */
    @ApiOperation("新增员工信息")
//    @RequiresPermissions("company:information:add")
    @Log(title = "新增员工信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EmployeeInformation employeeInformation)
    {
        return toAjax(employeeInformationService.insertEmployeeInformation(employeeInformation));
    }

    /**
     * 修改员工信息
     */
    @ApiOperation("修改员工信息")
//    @RequiresPermissions("company:information:edit")
    @Log(title = "修改员工信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EmployeeInformation employeeInformation)
    {
        return toAjax(employeeInformationService.updateEmployeeInformation(employeeInformation));
    }

    /**
     * 删除员工信息
     */
    @ApiOperation("删除员工信息")
//    @RequiresPermissions("company:information:remove")
    @Log(title = "删除员工信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(employeeInformationService.deleteEmployeeInformationByUserIds(userIds));
    }
}
