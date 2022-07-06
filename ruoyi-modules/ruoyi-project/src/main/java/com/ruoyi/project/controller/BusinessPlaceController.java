package com.ruoyi.project.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.project.domain.BusinessPlace;
import com.ruoyi.project.service.IBusinessPlaceService;
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
 * 平台渠道商Controller
 *
 * @author ruoyi
 * @date 2022-07-06
 */
@RestController
@Api(tags = "渠道管理")
@RequestMapping("/place")
public class BusinessPlaceController extends BaseController
{
    @Autowired
    private IBusinessPlaceService businessPlaceService;

    /**
     * 查询平台渠道商列表
     */
    @ApiOperation("查询平台渠道商列表")
//    @RequiresPermissions("company:place:list")
    @GetMapping("/list")
    public TableDataInfo list(BusinessPlace businessPlace)
    {
        startPage();
        List<BusinessPlace> list = businessPlaceService.selectBusinessPlaceList(businessPlace);
        return getDataTable(list);
    }

    /**
     * 导出平台渠道商列表
     */
    @ApiOperation("导出平台渠道商列表")
//    @RequiresPermissions("company:place:export")
    @Log(title = "平台渠道商", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusinessPlace businessPlace)
    {
        List<BusinessPlace> list = businessPlaceService.selectBusinessPlaceList(businessPlace);
        ExcelUtil<BusinessPlace> util = new ExcelUtil<BusinessPlace>(BusinessPlace.class);
        util.exportExcel(response, list, "平台渠道商数据");
    }

    /**
     * 获取平台渠道商详细信息
     */
    @ApiOperation("获取平台渠道商详细信息")
//    @RequiresPermissions("company:place:query")
    @GetMapping(value = "/{placeId}")
    public AjaxResult getInfo(@PathVariable("placeId") String placeId)
    {
        return AjaxResult.success(businessPlaceService.selectBusinessPlaceByPlaceId(placeId));
    }

    /**
     * 新增平台渠道商
     */
    @ApiOperation("新增平台渠道商")
//    @RequiresPermissions("company:place:add")
    @Log(title = "平台渠道商", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusinessPlace businessPlace)
    {
        return toAjax(businessPlaceService.insertBusinessPlace(businessPlace));
    }

    /**
     * 修改平台渠道商
     */
    @ApiOperation("修改平台渠道商")
//    @RequiresPermissions("company:place:edit")
    @Log(title = "平台渠道商", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusinessPlace businessPlace)
    {
        return toAjax(businessPlaceService.updateBusinessPlace(businessPlace));
    }

    /**
     * 删除平台渠道商
     */
    @ApiOperation("删除平台渠道商")
//    @RequiresPermissions("company:place:remove")
    @Log(title = "平台渠道商", businessType = BusinessType.DELETE)
    @DeleteMapping("/{placeIds}")
    public AjaxResult remove(@PathVariable String[] placeIds)
    {
        return toAjax(businessPlaceService.deleteBusinessPlaceByPlaceIds(placeIds));
    }
}

