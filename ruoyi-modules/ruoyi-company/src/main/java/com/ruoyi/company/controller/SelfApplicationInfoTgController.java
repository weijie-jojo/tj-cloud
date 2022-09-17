package com.ruoyi.company.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.company.domain.SelfApplicationInfo;
import com.ruoyi.company.service.ISelfApplicationInfoTgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 个体户注册申请信息Controller
 * 
 * @author ruoyi
 * @date 2022-06-17
 */
@RestController
@RequestMapping("/info/tg")
@Api(tags = "个体户注册申请信息")
public class SelfApplicationInfoTgController extends BaseController
{
    @Autowired
    private ISelfApplicationInfoTgService selfApplicationInfoService;

    /**
     * 查询个体户注册申请信息列表
     */
    @ApiOperation("查询个体户注册申请信息列表")
//    @RequiresPermissions("company:info:list")
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
    @ApiOperation("导出个体户注册申请信息列表")
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
    @ApiOperation("获取个体户注册申请信息详细信息")
//    @RequiresPermissions("company:info:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(selfApplicationInfoService.selectSelfApplicationInfoById(id));
    }

    /**
     * 新增个体户注册申请信息
     */
    @ApiOperation("新增个体户注册申请信息")
//    @RequiresPermissions("company:employed:add")
    @Log(title = "个体户注册申请信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SelfApplicationInfo selfApplicationInfo)
    {
        try {
            return toAjax(selfApplicationInfoService.insertSelfApplicationInfo(selfApplicationInfo));
        }catch (DuplicateKeyException ex){
            return error("不允许插入重复单据，自动返回，请重新创建");
        }

    }

    /**
     * 修改个体户注册申请信息
     */
    @ApiOperation("修改个体户注册申请信息")
//    @RequiresPermissions("company:employed:edit")
    @Log(title = "个体户注册申请信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SelfApplicationInfo selfApplicationInfo)
    {
        return toAjax(selfApplicationInfoService.updateSelfApplicationInfo(selfApplicationInfo));
    }

    /**
     * 删除个体户注册申请信息
     */
    @ApiOperation("删除个体户注册申请信息")
//    @RequiresPermissions("company:info:remove")
    @Log(title = "个体户注册申请信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(selfApplicationInfoService.deleteSelfApplicationInfoByIds(ids));
    }
}
