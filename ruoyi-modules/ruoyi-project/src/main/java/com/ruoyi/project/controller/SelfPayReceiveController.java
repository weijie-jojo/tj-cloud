package com.ruoyi.project.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.project.domain.SelfPayReceive;
import com.ruoyi.project.service.ISelfPayReceiveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 【收付款信息表】Controller
 *
 * @author ruoyi
 * @date 2022-09-02
 */
@RestController
@RequestMapping("/receive")
@Api(tags = "收付款信息表")
public class SelfPayReceiveController extends BaseController
{
    @Autowired
    private ISelfPayReceiveService selfPayReceiveService;

    /**
     * 查询【收付款信息表】列表
     */
    @RequiresPermissions("company:receive:list")
    @GetMapping("/list")
    @ApiOperation("查询【收付款信息表】列表")
    public TableDataInfo list(SelfPayReceive selfPayReceive)
    {
        startPage();
        List<SelfPayReceive> list = selfPayReceiveService.selectSelfPayReceiveList(selfPayReceive);
        return getDataTable(list);
    }

    /**
     * 导出【收付款信息表】列表
     */
    @RequiresPermissions("company:receive:export")
    @Log(title = "【收付款信息表】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("导出【收付款信息表】列表")
    public void export(HttpServletResponse response, SelfPayReceive selfPayReceive)
    {
        List<SelfPayReceive> list = selfPayReceiveService.selectSelfPayReceiveList(selfPayReceive);
        ExcelUtil<SelfPayReceive> util = new ExcelUtil<SelfPayReceive>(SelfPayReceive.class);
        util.exportExcel(response, list, "【收付款信息表】数据");
    }

    /**
     * 获取【收付款信息表】详细信息
     */
    @RequiresPermissions("company:receive:query")
    @GetMapping(value = "/{payReceiveId}")
    @ApiOperation("获取【收付款信息表】详细信息")
    public AjaxResult getInfo(@PathVariable("payReceiveId") String payReceiveId)
    {
        return AjaxResult.success(selfPayReceiveService.selectSelfPayReceiveByPayReceiveId(payReceiveId));
    }

    /**
     * 新增【收付款信息表】
     */
    @RequiresPermissions("company:receive:add")
    @Log(title = "【收付款信息表】", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增【收付款信息表】")
    public AjaxResult add(@RequestBody SelfPayReceive selfPayReceive)
    {
        return toAjax(selfPayReceiveService.insertSelfPayReceive(selfPayReceive));
    }

    /**
     * 修改【收付款信息表】
     */
    @RequiresPermissions("company:receive:edit")
    @Log(title = "【收付款信息表】", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改【收付款信息表】")
    public AjaxResult edit(@RequestBody SelfPayReceive selfPayReceive)
    {
        return toAjax(selfPayReceiveService.updateSelfPayReceive(selfPayReceive));
    }

    /**
     * 删除【收付款信息表】
     */
    @RequiresPermissions("company:receive:remove")
    @Log(title = "【收付款信息表】", businessType = BusinessType.DELETE)
    @DeleteMapping("/{payReceiveIds}")
    @ApiOperation("删除【收付款信息表】")
    public AjaxResult remove(@PathVariable String[] payReceiveIds)
    {
        return toAjax(selfPayReceiveService.deleteSelfPayReceiveByPayReceiveIds(payReceiveIds));
    }
}
