package com.ruoyi.project.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.project.domain.SelfEmployed;
import com.ruoyi.project.domain.vo.SelfEmployedVo;
import com.ruoyi.project.domain.vo.SysUserVo;
import com.ruoyi.project.mapper.SysUserMapper;
import com.ruoyi.project.service.ISelfEmployedTgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 个体商户Controller
 *
 * @author ruoyi
 * @date 2022-07-06
 */
@RestController
@Api(tags = "个体户管理（腾工）")
@RequestMapping("/employed/tg")
public class SelfEmployedTgController extends BaseController
{
    @Autowired
    private ISelfEmployedTgService selfEmployedService;

    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 查询个体商户列表(根据登录业务员跟行业类型)
     *
     * @param industryType
     * @return 个体商户集合
     */
    @ApiOperation("查询个体商户列表(根据登录业务员跟行业类型)")
//    @RequiresPermissions("company:employed:query")
    @GetMapping("/getByUserIndusty")
    public List<SelfEmployedVo> getByUserIndusty(String industryType)
    {
        //获取登录用户的部门id
        Integer deptId=sysUserMapper.getDeptByUserId(SecurityUtils.getUserId()).getDeptId();
        //根据部门id获取用户集合
        List<SysUserVo> userVos=sysUserMapper.getUserByDeptId(deptId);
        //根据登录用户id获取用户角色信息
        List<SysUserVo> roles= sysUserMapper.getRoleByUserId(SecurityUtils.getUserId());
        //存储username的list集合
        List<Long> userIdArr=new ArrayList<>();
        for (SysUserVo role:roles){
            if (role.getRoleId()==10||role.getRoleId()==12){//行政跟业务部门主管获取他们部门的渠道信息
                System.out.println("部门主管");
                for (SysUserVo userVo:userVos){//登录用户所属部门的所有用户名
                    userIdArr.add(userVo.getUserId());
                }
            }
            else if (role.getRoleId()==1||role.getRoleId()==5||role.getRoleId()==6){//管理员及总经理 副总经理
                System.out.println("总经理");
                userIdArr=null;//显示所有
            }
            else {
                System.out.println("其他人");
                userIdArr.add(SecurityUtils.getUserId());//登录用户名
            }
        }
        return selfEmployedService.selectSelfEmployedByUser(userIdArr);
    }

    /**
     * 查询个体商户列表
     */
    @ApiOperation("查询个体商户列表")
//    @RequiresPermissions("company:employed:list")
    @GetMapping("/list")
    public TableDataInfo list(SelfEmployed selfEmployed)
    {
        startPage();
        List<SelfEmployed> list = selfEmployedService.selectSelfEmployedList(selfEmployed);
        return getDataTable(list);
    }

    /**
     * 导出个体商户列表
     */
    @ApiOperation("导出个体商户列表")
//    @RequiresPermissions("company:employed:export")
    @Log(title = "个体商户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SelfEmployed selfEmployed)
    {
        List<SelfEmployed> list = selfEmployedService.selectSelfEmployedList(selfEmployed);
        ExcelUtil<SelfEmployed> util = new ExcelUtil<SelfEmployed>(SelfEmployed.class);
        util.exportExcel(response, list, "个体商户数据");
    }

    /**
     * 获取个体商户详细信息
     */
    @ApiOperation("获取个体商户详细信息")
//    @RequiresPermissions("company:employed:query")
    @GetMapping(value = "/{selfId}")
    public AjaxResult getInfo(@PathVariable("selfId") String selfId)
    {
        return AjaxResult.success(selfEmployedService.selectSelfEmployedBySelfId(selfId));
    }

    /**
     * 新增个体商户
     */
    @ApiOperation("新增个体商户")
//    @RequiresPermissions("company:employed:add")
    @Log(title = "个体商户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SelfEmployed selfEmployed)
    {
        return toAjax(selfEmployedService.insertSelfEmployed(selfEmployed));
    }

    /**
     * 修改个体商户
     */
    @ApiOperation("修改个体商户")
//    @RequiresPermissions("company:employed:edit")
    @Log(title = "个体商户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SelfEmployed selfEmployed)
    {
        return toAjax(selfEmployedService.updateSelfEmployed(selfEmployed));
    }

    /**
     * 删除个体商户
     */
    @ApiOperation("删除个体商户")
//    @RequiresPermissions("company:employed:remove")
    @Log(title = "个体商户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{selfIds}")
    public AjaxResult remove(@PathVariable String[] selfIds)
    {
        return toAjax(selfEmployedService.deleteSelfEmployedBySelfIds(selfIds));
    }
}
