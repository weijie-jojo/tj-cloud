package com.ruoyi.project.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.project.domain.SelfTicket;
import com.ruoyi.project.service.ISelfTicketService;
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

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 发票Controller
 *
 * @author ruoyi
 * @date 2022-07-06
 */
@RestController
@RequestMapping("/ticket")
@Api(tags = "发票")
public class SelfTicketController extends BaseController
{
    @Autowired
    private ISelfTicketService selfTicketService;

    /**
     * 查询发票列表
     */
    @ApiOperation("查询发票列表")
//    @RequiresPermissions("project:ticket:list")
    @GetMapping("/list")
    public TableDataInfo list(SelfTicket selfTicket)
    {
        startPage();
        List<SelfTicket> list = selfTicketService.selectSelfTicketList(selfTicket);
        return getDataTable(list);
    }

    /**
     * 导出发票列表
     */
    @ApiOperation("导出发票列表")
//    @RequiresPermissions("project:ticket:export")
    @Log(title = "发票", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SelfTicket selfTicket)
    {
        List<SelfTicket> list = selfTicketService.selectSelfTicketList(selfTicket);
        ExcelUtil<SelfTicket> util = new ExcelUtil<SelfTicket>(SelfTicket.class);
        util.exportExcel(response, list, "发票数据");
    }

    /**
     * 获取发票详细信息
     */
    @ApiOperation("获取发票详细信息")
//    @RequiresPermissions("project:ticket:query")
    @GetMapping(value = "/{ticketId}")
    public AjaxResult getInfo(@PathVariable("ticketId") String ticketId)
    {
        return AjaxResult.success(selfTicketService.selectSelfTicketByTicketId(ticketId));
    }

    /**
     * 新增发票
     */
    @ApiOperation("新增发票")
//    @RequiresPermissions("project:ticket:add")
    @Log(title = "发票", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SelfTicket selfTicket)
    {
        return toAjax(selfTicketService.insertSelfTicket(selfTicket));
    }

    /**
     * 修改发票
     */
    @ApiOperation("修改发票")
//    @RequiresPermissions("project:ticket:edit")
    @Log(title = "发票", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SelfTicket selfTicket)
    {
        return toAjax(selfTicketService.updateSelfTicket(selfTicket));
    }

    /**
     * 删除发票
     */
    @ApiOperation("删除发票")
//    @RequiresPermissions("project:ticket:remove")
    @Log(title = "发票", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ticketIds}")
    public AjaxResult remove(@PathVariable String[] ticketIds)
    {
        return toAjax(selfTicketService.deleteSelfTicketByTicketIds(ticketIds));
    }

    /**
     * 删除发票（伪删）
     */
    @ApiOperation("删除发票（伪删）")
//    @RequiresPermissions("company:place:remove")
    @Log(title = "发票", businessType = BusinessType.UPDATE)
    @PutMapping("/del/{ticketIds}")
    public AjaxResult remove2(@PathVariable String[] ticketIds)
    {
        return toAjax(selfTicketService.deleteSelfTicketByTicketIds2(ticketIds));
    }
}

