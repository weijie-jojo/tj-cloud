package com.ruoyi.project.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.project.domain.SelfReceive;
import com.ruoyi.project.domain.vo.SelfPayReceiveVo;
import com.ruoyi.project.service.ISelfReceiveService;
import com.ruoyi.project.util.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 收付款信息Controller
 *
 * @author ruoyi
 * @date 2022-09-06
 */
@RestController
@RequestMapping("/receive")
public class SelfReceiveController extends BaseController
{
    @Autowired
    private ISelfReceiveService selfReceiveService;

    /**
     * 获取编号
     */
    @GetMapping(value ="/getCode")
    @ApiOperation("获取编码")
    public String getCode(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String nowDate = sdf.format(date);
        SelfReceive selfReceive =selfReceiveService.selectLast();
        System.out.println("selfPayReceive=="+ selfReceive);
        String code="";
        if (selfReceive !=null){
            code=  StringUtils.getCode("PRSYS", selfReceive.getReceiveSysCode(),"yyyyMMdd");
        }else {//没有数据时
            code="PRSYS"+"-"+nowDate+"-"+"0001";
        }
        return code;
    };

    /**
     * 根据receiveSysCode获取收付款流水
     */
    @GetMapping("/getInfoByReceiveCode")
    @ApiOperation("根据receiveSysCode获取收付款流水")
    public TableDataInfo getInfoByReceiveCode(String receiveSysCode)
    {
        startPage();
        List<SelfPayReceiveVo> list = selfReceiveService.selectSelfPayReceiveJoinByCode(receiveSysCode);
        return getDataTable(list);
    }

    /**
     * 获取出款信息详细信息(根据projectCode)分页
     */
    @GetMapping("/getInfoByCode")
    @ApiOperation("获取出款信息详细信息(根据projectCode)分页")
    public AjaxResult getInfoByCode(String projectCode)
    {
        startPage();
        List<SelfReceive> list= selfReceiveService.selectSelfReceiveByProjectCode(projectCode);
        return AjaxResult.success(list);
    }

    /**
     * 获取出款信息详细信息(根据projectCode)不分页
     */
    @GetMapping("/getInfoByCode2")
    @ApiOperation("获取出款信息详细信息(根据projectCode)不分页")
    public AjaxResult getInfoByCode2(String projectCode)
    {
        return AjaxResult.success(selfReceiveService.selectSelfReceiveByProjectCode(projectCode));
    }

    /**
     * 查询收付款信息列表
     */
    @RequiresPermissions("company:receive:list")
    @GetMapping("/list")
    public TableDataInfo list(SelfReceive selfReceive)
    {
        startPage();
        List<SelfReceive> list = selfReceiveService.selectSelfReceiveList(selfReceive);
        return getDataTable(list);
    }

    /**
     * 导出收付款信息列表
     */
    @RequiresPermissions("company:receive:export")
    @Log(title = "收付款信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SelfReceive selfReceive)
    {
        List<SelfReceive> list = selfReceiveService.selectSelfReceiveList(selfReceive);
        ExcelUtil<SelfReceive> util = new ExcelUtil<SelfReceive>(SelfReceive.class);
        util.exportExcel(response, list, "收付款信息数据");
    }

    /**
     * 获取收付款信息详细信息
     */
    @GetMapping(value = "/{receiveId}")
    public AjaxResult getInfo(@PathVariable("receiveId") String receiveId)
    {
        return AjaxResult.success(selfReceiveService.selectSelfReceiveByReceiveId(receiveId));
    }

    /**
     * 新增收付款信息
     */
    @RequiresPermissions("company:receive:add")
    @Log(title = "收付款信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SelfReceive selfReceive)
    {
        return toAjax(selfReceiveService.insertSelfReceive(selfReceive));
    }

    /**
     * 修改收付款信息
     */
    @RequiresPermissions("company:receive:edit")
    @Log(title = "收付款信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SelfReceive selfReceive)
    {
        return toAjax(selfReceiveService.updateSelfReceive(selfReceive));
    }

    /**
     * 删除收付款信息
     */
    @RequiresPermissions("company:receive:remove")
    @Log(title = "收付款信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{receiveIds}")
    public AjaxResult remove(@PathVariable String[] receiveIds)
    {
        return toAjax(selfReceiveService.deleteSelfReceiveByReceiveIds(receiveIds));
    }
}
