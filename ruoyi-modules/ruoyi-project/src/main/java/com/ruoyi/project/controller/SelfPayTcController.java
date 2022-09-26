package com.ruoyi.project.controller;

import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.project.domain.SelfPay;
import com.ruoyi.project.service.ISelfPayTcService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 出款信息Controller
 *
 * @author ruoyi
 * @date 2022-09-05
 */
@RestController
@RequestMapping("/pay/tc")
@Api(tags = "出款信息表（腾创）")
public class SelfPayTcController extends BaseController
{
    @Autowired
    private ISelfPayTcService selfPayService;

    /**
     * 获取编号
     */
    @GetMapping(value ="/getCode")
    @ApiOperation("获取编码")
    public String getCode(String projectCode){
        SelfPay selfPay=selfPayService.selectLast();
        String payCode="";
        if (selfPay==null){//没有单据时
            payCode=projectCode+"-"+"02"+"-"+"00001";
        }else {
            payCode=  StringUtils.getCode5(projectCode,"02",selfPay.getPaySysCode());
        }
        return  payCode;
    };

    /**
     * 查询出款信息列表
     */
    @RequiresPermissions("company:pay:list")
    @GetMapping("/list")
    @ApiOperation("查询出款信息列表")
    public TableDataInfo list(SelfPay selfPay)
    {
        startPage();
        List<SelfPay> list = selfPayService.selectSelfPayList(selfPay);
        return getDataTable(list);
    }

    /**
     * 导出出款信息列表
     */
    @RequiresPermissions("company:pay:export")
    @Log(title = "出款信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("导出出款信息列表")
    public void export(HttpServletResponse response, SelfPay selfPay)
    {
        List<SelfPay> list = selfPayService.selectSelfPayList(selfPay);
        ExcelUtil<SelfPay> util = new ExcelUtil<SelfPay>(SelfPay.class);
        util.exportExcel(response, list, "出款信息数据");
    }

    /**
     * 获取出款信息详细信息
     */
    @GetMapping(value = "/{payId}")
    @ApiOperation("获取出款信息详细信息")
    public AjaxResult getInfo(@PathVariable("payId") String payId)
    {
        return AjaxResult.success(selfPayService.selectSelfPayByPayId(payId));
    }

    /**
     * 获取出款信息详细信息(根据projectCode)分页
     */
    @GetMapping("/getInfoByCode")
    @ApiOperation("获取出款信息详细信息(根据projectCode)分页")
    public TableDataInfo getInfoByCode(String projectCode)
    {
        startPage();
        List<SelfPay> list=selfPayService.selectSelfPayByProjectCode(projectCode);
        return getDataTable(list);
    }

    /**
     * 获取出款信息详细信息(根据projectCode)不分页
     */
    @GetMapping("/getInfoByCode2")
    @ApiOperation("获取出款信息详细信息(根据projectCode)不分页")
    public AjaxResult getInfoByCode2(String projectCode)
    {
        return AjaxResult.success(selfPayService.selectSelfPayByProjectCode(projectCode));
    }

    /**
     * 新增出款信息
     */
    @RequiresPermissions("company:pay:add")
    @Log(title = "出款信息", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增出款信息")
    public AjaxResult add(@RequestBody SelfPay selfPay)
    {
        try {
            int num=selfPayService.insertSelfPay(selfPay);
            return toAjax(num);
        }catch (DuplicateKeyException ex){
            return error("系统流水号重复，自动返回，请重新创建");
        }
    }

    /**
     * 修改出款信息
     */
    @RequiresPermissions("company:pay:edit")
    @Log(title = "出款信息", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改出款信息")
    public AjaxResult edit(@RequestBody SelfPay selfPay)
    {
        return toAjax(selfPayService.updateSelfPay(selfPay));
    }

    /**
     * 删除出款信息
     */
    @RequiresPermissions("company:pay:remove")
    @Log(title = "出款信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{payIds}")
    @ApiOperation("删除出款信息")
    public AjaxResult remove(@PathVariable String[] payIds)
    {
        Integer count = 0;//删除计数
        for (String payId:payIds){
            String paySysCode= selfPayService.selectSelfPayByPayId(payId).getPaySysCode();
            Integer num= selfPayService.deletePayByCode(paySysCode);
            count+=num;
        };
        System.out.println("count=="+count);
        if (count>=payIds.length*2){
            return toAjax(200);
        }else {
            return error("删除失败");
        }
    }
}
