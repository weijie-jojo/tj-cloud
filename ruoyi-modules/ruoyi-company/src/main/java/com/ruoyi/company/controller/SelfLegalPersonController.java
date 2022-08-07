package com.ruoyi.company.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.company.domain.SelfLegalPerson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
import com.ruoyi.company.service.ISelfLegalPersonService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 个体商户法人Controller
 * 
 * @author ruoyi
 * @date 2022-06-17
 */
@RestController
@RequestMapping("/person")
@Api(tags = "个体商户法人")
public class SelfLegalPersonController extends BaseController
{
    @Autowired
    private ISelfLegalPersonService selfLegalPersonService;

    /**
     * 查询个体商户法人列表（不分页）
     */
    @ApiOperation("查询个体商户法人列表（不分页）")
//    @RequiresPermissions("company:person:list")
    @GetMapping("/all")
    public TableDataInfo all()
    {
        List<SelfLegalPerson> list = selfLegalPersonService.selectSelfLegalPerson();
        return getDataTable(list);
    }

    /**
     * 查询个体商户法人列表
     */
    @ApiOperation("查询个体商户法人列表")
//    @RequiresPermissions("company:person:list")
    @GetMapping("/list")
    public TableDataInfo list(SelfLegalPerson selfLegalPerson)
    {
        startPage();
        List<SelfLegalPerson> list = selfLegalPersonService.selectSelfLegalPersonList(selfLegalPerson);
        return getDataTable(list);
    }

    /**
     * 导出个体商户法人列表
     */
    @ApiOperation("导出个体商户法人列表")
    @RequiresPermissions("company:person:export")
    @Log(title = "个体商户法人", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SelfLegalPerson selfLegalPerson)
    {
        List<SelfLegalPerson> list = selfLegalPersonService.selectSelfLegalPersonList(selfLegalPerson);
        ExcelUtil<SelfLegalPerson> util = new ExcelUtil<SelfLegalPerson>(SelfLegalPerson.class);
        util.exportExcel(response, list, "个体商户法人数据");
    }

    /**
     * 获取个体商户法人详细信息
     */
    @ApiOperation("获取个体商户法人详细信息")
//    @RequiresPermissions("company:person:query")
    @GetMapping(value = "/{legalPersonId}")
    public AjaxResult getInfo(@PathVariable("legalPersonId") Long legalPersonId)
    {
        return AjaxResult.success(selfLegalPersonService.selectSelfLegalPersonByLegalPersonId(legalPersonId));
    }

    /**
     * 新增个体商户法人
     */
    @ApiOperation("新增个体商户法人")
    @RequiresPermissions("company:employed:add")
    @Log(title = "个体商户法人", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SelfLegalPerson selfLegalPerson)
    {
        try {
            return toAjax(selfLegalPersonService.insertSelfLegalPerson(selfLegalPerson));
        }catch (DuplicateKeyException ex){
            return error("不允许插入重复单据，自动返回，请重新创建");
        }
    }

    /**
     * 修改个体商户法人
     */
    @ApiOperation("修改个体商户法人")
    @RequiresPermissions("company:employed:edit")
    @Log(title = "个体商户法人", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SelfLegalPerson selfLegalPerson)
    {
        return toAjax(selfLegalPersonService.updateSelfLegalPerson(selfLegalPerson));
    }

    /**
     * 删除个体商户法人
     */
    @ApiOperation("删除个体商户法人")
//    @RequiresPermissions("company:person:remove")
    @Log(title = "个体商户法人", businessType = BusinessType.DELETE)
	@DeleteMapping("/{legalPersonIds}")
    public AjaxResult remove(@PathVariable Long[] legalPersonIds)
    {
        return toAjax(selfLegalPersonService.deleteSelfLegalPersonByLegalPersonIds(legalPersonIds));
    }
}
