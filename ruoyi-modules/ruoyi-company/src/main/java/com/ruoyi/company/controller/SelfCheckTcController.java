package com.ruoyi.company.controller;

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.company.domain.SelfCheck;
import com.ruoyi.company.domain.vo.SelfCheckVo;
import com.ruoyi.company.service.ISelfCheckTcService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 单据审批结果Controller
 *
 * @author ruoyi
 * @date 2022-07-14
 */
@RestController
@RequestMapping("/check")
@Api(tags = "个体户审批进度")
public class SelfCheckTcController extends BaseController
{
    @Autowired
    private ISelfCheckTcService selfCheckService;

    /**
     * 查询审批进度列表
     */
    @ApiOperation("查询审批进度列表")
//    @RequiresPermissions("company:check:list")
    @GetMapping("/list")
    public List<SelfCheckVo> list(SelfCheck selfCheck)
    {
//        startPage();
        List<SelfCheckVo> list = selfCheckService.selectSelfCheckList(selfCheck);
//        return getDataTable(list);
        return list;
    }

//    /**
//     * 导出审批列表
//     */
//    @ApiOperation("导出审批列表")
////    @RequiresPermissions("company:check:export")
//    @Log(title = "单据审批结果", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, SelfCheck selfCheck)
//    {
//        List<SelfCheck> list = selfCheckService.selectSelfCheckList(selfCheck);
//        ExcelUtil<SelfCheck> util = new ExcelUtil<SelfCheck>(SelfCheck.class);
//        util.exportExcel(response, list, "单据审批结果数据");
//    }

    /**
     * 查询审批列表明细
     */
    @ApiOperation("查询审批列表明细")
//    @RequiresPermissions("company:check:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(selfCheckService.selectSelfCheckById(id));
    }

    /**
     * 新增审批进度
     */
    @ApiOperation("新增审批进度")
//    @RequiresPermissions("company:check:add")
    @Log(title = "单据审批结果", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SelfCheck selfCheck)
    {
        Date date = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//        String nowDate = formatter.format(date);
        System.out.println("date=="+date);
        selfCheck.setCheckDate(date);

        return toAjax(selfCheckService.insertSelfCheck(selfCheck));
    }

    /**
     * 修改审批进度
     */
    @ApiOperation("修改审批进度")
//    @RequiresPermissions("company:check:edit")
    @Log(title = "单据审批结果", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SelfCheck selfCheck)
    {
        return toAjax(selfCheckService.updateSelfCheck(selfCheck));
    }

    /**
     * 删除审批进度结果
     */
    @ApiOperation("删除审批进度结果")
//    @RequiresPermissions("company:check:remove")
    @Log(title = "单据审批结果", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(selfCheckService.deleteSelfCheckByIds(ids));
    }
}
