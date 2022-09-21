package com.ruoyi.project.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.project.domain.BusinessPlace;
import com.ruoyi.project.domain.vo.SysUserVo;
import com.ruoyi.project.mapper.SysUserMapper;
import com.ruoyi.project.service.IBusinessPlaceTcService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 平台渠道商Controller
 *
 * @author ruoyi
 * @date 2022-07-06
 */
@RestController
@Api(tags = "渠道管理（腾创）")
@RequestMapping("/place")
public class BusinessPlaceTcController extends BaseController
{
    @Autowired
    private IBusinessPlaceTcService businessPlaceService;
    @Resource
    private SysUserMapper sysUserMapper;
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
     * 获取平台渠道商详细信息(根据业务经理userId)
     */
    @ApiOperation("获取平台渠道商详细信息(根据业务经理userId)")
//    @RequiresPermissions("company:place:query")
    @GetMapping(value = "/getInfoByUserId")
    public AjaxResult getInfoByUserId()
    {
        //获取登录用户的部门id
        Integer deptId=sysUserMapper.getDeptByUserId(SecurityUtils.getUserId()).getDeptId();
        //根据部门id获取用户集合
        List<SysUserVo> userVos=sysUserMapper.getUserByDeptId(deptId);
        //存储用户id的list集合
        List<Long> userIdArr=new ArrayList<>();
//        String userIdStr= Joiner.on(",").join(userIdArr);
        //获取登录用户id获取用户角色信息
        List<SysUserVo> roles= sysUserMapper.getRoleByUserId(SecurityUtils.getUserId());
        System.out.println("roles==="+roles);
        for (SysUserVo role:roles){
            if (role.getRoleId()==10||role.getRoleId()==12){//行政跟业务部门主管获取他们部门的渠道信息
                System.out.println("部门主管");
                for (SysUserVo userVo:userVos){//登录用户所属部门的所有用户id
                    userIdArr.add(userVo.getUserId());
                }
            }
            else if (role.getRoleId()==1||role.getRoleId()==5||role.getRoleId()==6){//管理员及总经理 副总经理
                System.out.println("总经理");
                userIdArr=null;//显示所有
            }
            else {
                System.out.println("其他人");
                userIdArr.add(SecurityUtils.getUserId());//登录用户的id
            }
        }
        List<BusinessPlace> businessPlaces=businessPlaceService.selectBusinessPlaceByUserId(userIdArr);
        return AjaxResult.success(businessPlaces);
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

    /**
     * 删除平台渠道商（伪删）
     */
    @ApiOperation("删除平台渠道商（伪删）")
//    @RequiresPermissions("company:place:remove")
    @Log(title = "平台渠道商", businessType = BusinessType.UPDATE)
    @PutMapping("/del/{placeIds}")
    public AjaxResult remove2(@PathVariable String[] placeIds)
    {
        return toAjax(businessPlaceService.deleteBusinessPlaceByPlaceIds2(placeIds));
    }
}

