package com.ruoyi.project.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.project.domain.SelfTicket;
import com.ruoyi.project.service.ISelfTicketTgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 发票Controller
 *
 * @author ruoyi
 * @date 2022-07-06
 */
@RestController
@RequestMapping("/ticket/tg")
@Api(tags = "发票（腾工）")
public class SelfTicketTgController extends BaseController
{
    @Autowired
    private ISelfTicketTgService selfTicketService;

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
        for (SelfTicket selfTicket1:list){
            selfTicket1.setTicketTax(selfTicket1.getTicketTax().movePointRight(2));
        }
        return getDataTable(list);
    }

    /**
     * 查询发票列表(不分页)
     */
    @ApiOperation("查询发票列表(不分页)")
//    @RequiresPermissions("project:ticket:list")
    @GetMapping("/list2")
    public List<SelfTicket>  list2(SelfTicket selfTicket)
    {
        List<SelfTicket> list = selfTicketService.selectSelfTicketList(selfTicket);
        for (SelfTicket selfTicket1:list){
            selfTicket1.setTicketTax(selfTicket1.getTicketTax().movePointRight(2));
        }
        return list;
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
        SelfTicket selfTicket= selfTicketService.selectSelfTicketByTicketId(ticketId);
        selfTicket.setTicketTax(selfTicket.getTicketTax().movePointRight(2));
        return AjaxResult.success(selfTicket);
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
        selfTicket.setTicketTax(selfTicket.getTicketTax().movePointLeft(2));
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
        if (selfTicket.getTicketTax()!=null){
            selfTicket.setTicketTax(selfTicket.getTicketTax().movePointLeft(2));
        }
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

