package com.ruoyi.company.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.company.domain.SelfEmployed;
import com.ruoyi.company.domain.dto.DataDto;
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
import com.ruoyi.company.domain.SelfNameReview;
import com.ruoyi.company.service.ISelfNameReviewService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 个体户名字审核Controller
 * 
 * @author ruoyi
 * @date 2022-06-16
 */
@RestController
@RequestMapping("/review")
@Api(tags = "个体户名字审核")
public class SelfNameReviewController extends BaseController
{
    @Autowired
    private ISelfNameReviewService selfNameReviewService;

    /**
     * 根据编号查询
     */
    @ApiOperation("根据编号查询")
//    @RequiresPermissions("company:review:list")
    @GetMapping("/getByCode")
    public SelfNameReview selectSelfNameReviewBySelfCode(String selfCode)
    {
        System.out.println("selfCode"+selfCode);
        SelfNameReview selfNameReview = selfNameReviewService.selectSelfNameReviewBySelfCode(selfCode);
        return selfNameReview;
    }


    /*
     * 获取编号
     *
     * */
    @ApiOperation("获取编号")
    @GetMapping("/getSelfCode")
    public String getSelfCode(String employeeNumber) {
        String selfCode = "";
        List<SelfNameReview> selfNameReviews=selfNameReviewService.selectMaxCode();
        System.out.println("selfEmployeds111=="+selfNameReviews);
        if (selfNameReviews.size()>0) {
            int num=Integer.parseInt(selfNameReviews.get(0).getSelfCode().substring(8))+1;
            if (num<10){
                selfCode=employeeNumber+"00000"+num;
            }
            if (num>=10&&num<100){
                selfCode=employeeNumber+"0000"+num;
            }
            if (num>=100&&num<1000){
                selfCode=employeeNumber+"000"+num;
            }
        }else {//没有单据时
            selfCode=employeeNumber+"000001";
        }
        System.out.println("selfCode=="+selfCode);
        return  selfCode;
    }

    /**
     * 查询个体户名字审核列表
     */
    @ApiOperation("查询个体户名字审核列表")
//    @RequiresPermissions("company:review:list")
    @GetMapping("/list")
    public TableDataInfo list(SelfNameReview selfNameReview)
    {
        startPage();
        List<SelfNameReview> list = selfNameReviewService.selectSelfNameReviewList(selfNameReview);
        return getDataTable(list);
    }

    /**
     * 导出个体户名字审核列表
     */
    @ApiOperation("导出个体户名字审核列表")
    @RequiresPermissions("company:review:export")
    @Log(title = "个体户名字审核", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SelfNameReview selfNameReview)
    {
        List<SelfNameReview> list = selfNameReviewService.selectSelfNameReviewList(selfNameReview);
        ExcelUtil<SelfNameReview> util = new ExcelUtil<SelfNameReview>(SelfNameReview.class);
        util.exportExcel(response, list, "个体户名字审核数据");
    }

    /**
     * 获取个体户名字审核详细信息
     */
    @ApiOperation("获取个体户名字审核详细信息")
//    @RequiresPermissions("company:review:query")
    @GetMapping(value = "/{selfId}")
    public AjaxResult getInfo(@PathVariable("selfId") Long selfId)
    {
        return AjaxResult.success(selfNameReviewService.selectSelfNameReviewBySelfId(selfId));
    }

    /**
     * 新增个体户名字审核
     */
    @ApiOperation("新增个体户名字审核")
    @RequiresPermissions("company:review:add")
    @Log(title = "个体户名字审核", businessType = BusinessType.INSERT)
    @PostMapping
    public DataDto add(@RequestBody SelfNameReview selfNameReview)
    {
        DataDto dataDto = new DataDto();
        try {
            int num=selfNameReviewService.insertSelfNameReview(selfNameReview);
            if (num>0){
                dataDto.success("填写成功");
            }
            return dataDto;
        }catch (DuplicateKeyException ex){
            return dataDto.err("不允许插入重复单据，自动返回，请重新创建");
        }
    }

    /**
     * 修改个体户名字审核
     */
    @ApiOperation("修改个体户名字审核")
    @RequiresPermissions("company:review:edit")
    @Log(title = "个体户名字审核", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SelfNameReview selfNameReview)
    {
        return toAjax(selfNameReviewService.updateSelfNameReview(selfNameReview));
    }

    /**
     * 删除个体户名字审核
     */
    @ApiOperation("删除个体户名字审核")
//    @RequiresPermissions("company:review:remove")
    @Log(title = "个体户名字审核", businessType = BusinessType.DELETE)
	@DeleteMapping("/{selfIds}")
    public AjaxResult remove(@PathVariable Long[] selfIds)
    {
        return toAjax(selfNameReviewService.deleteSelfNameReviewBySelfIds(selfIds));
    }
}
