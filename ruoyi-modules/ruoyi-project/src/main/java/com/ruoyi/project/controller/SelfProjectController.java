package com.ruoyi.project.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.project.domain.BusinessPlace;
import com.ruoyi.project.domain.SelfProject;
import com.ruoyi.project.domain.SelfTicket;
import com.ruoyi.project.domain.vo.ProjectJoinTicketVo;
import com.ruoyi.project.domain.vo.SysUserVo;
import com.ruoyi.project.mapper.SysUserMapper;
import com.ruoyi.project.service.ISelfProjectService;
import com.ruoyi.project.service.ISelfTicketService;
import com.ruoyi.project.util.StringUtils;
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
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 项目信息Controller
 *
 * @author ruoyi
 * @date 2022-07-06
 */
@RestController
@Api(tags = "项目管理")
@RequestMapping("/project")
public class SelfProjectController extends BaseController
{
    @Autowired
    private ISelfTicketService selfTicketService;
    @Autowired
    private ISelfProjectService selfProjectService;
    @Resource
    private SysUserMapper sysUserMapper;
    /**
     * 获取三个状态的数量
     */
    @ApiOperation("获取三个状态的数量")
    @GetMapping("/getCount3")
    public HashMap<String, Integer> selectProjectJoinCount(SelfProject selfProject)
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
        List<SelfProject> list1 =new ArrayList<>();
        List<SelfProject> list2 =new ArrayList<>();
        List<SelfProject> list3 =new ArrayList<>();
        if (selfProject.getType()==1){//项目进度列表
            selfProject.setProjectStatus(0L);
            list1 = selfProjectService.selectSelfProjectList(userIdArr,selfProject);
            selfProject.setProjectStatus(2L);
            list2 = selfProjectService.selectSelfProjectList(userIdArr,selfProject);
            selfProject.setProjectStatus(1L);
            list3 =selfProjectService.selectSelfProjectList(userIdArr,selfProject);
        }
        if (selfProject.getType()==2){//项目审核
            selfProject.setProjectCheckStatus(0);
            list1 = selfProjectService.selectSelfProjectList(userIdArr,selfProject);
            selfProject.setProjectCheckStatus(1);
            list2 = selfProjectService.selectSelfProjectList(userIdArr,selfProject);
            selfProject.setProjectCheckStatus(2);
            list3 =selfProjectService.selectSelfProjectList(userIdArr,selfProject);
        }
        if (selfProject.getType()==3){//合同审核
            selfProject.setProjectCheckStatus(1);
            selfProject.setProjectContractStatus(0);
            list1 = selfProjectService.selectSelfProjectList(userIdArr,selfProject);
            selfProject.setProjectCheckStatus(1);
            selfProject.setProjectContractStatus(1);
            list2 = selfProjectService.selectSelfProjectList(userIdArr,selfProject);
            selfProject.setProjectCheckStatus(1);
            selfProject.setProjectContractStatus(2);
            list3 =selfProjectService.selectSelfProjectList(userIdArr,selfProject);
        }
        if (selfProject.getType()==4){//验收审核
            selfProject.setProjectCheckStatus(1);
            selfProject.setProjectAcceptanceStatus(0);
            list1 = selfProjectService.selectSelfProjectList(userIdArr,selfProject);
            selfProject.setProjectCheckStatus(1);
            selfProject.setProjectAcceptanceStatus(1);
            list2 = selfProjectService.selectSelfProjectList(userIdArr,selfProject);
            selfProject.setProjectCheckStatus(1);
            selfProject.setProjectAcceptanceStatus(2);
            list3 =selfProjectService.selectSelfProjectList(userIdArr,selfProject);
        }
        if (selfProject.getType()==5){//完税审核
            selfProject.setProjectCheckStatus(1);
            selfProject.setProjectDutypaidStatus(0);
            list1 = selfProjectService.selectSelfProjectList(userIdArr,selfProject);
            selfProject.setProjectCheckStatus(1);
            selfProject.setProjectDutypaidStatus(1);
            list2 = selfProjectService.selectSelfProjectList(userIdArr,selfProject);
            selfProject.setProjectCheckStatus(1);
            selfProject.setProjectDutypaidStatus(2);
            list3 =selfProjectService.selectSelfProjectList(userIdArr,selfProject);
        }
        HashMap<String, Integer> datasMap=new HashMap<String, Integer>();
        datasMap.put("unfinished", list1.size());
        datasMap.put("finished", list2.size());
        datasMap.put("error", list3.size());
        datasMap.put("total", list1.size()+list2.size()+list3.size());
        return datasMap;
    }
    /**
     * 查询项目信息列表
     */
//    @RequiresPermissions("project:project:list")
    @ApiOperation("查询登录用户项目信息数量")
    @GetMapping("/getCount")
    public Integer getCount(SelfProject selfProject)
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
        List<SelfProject> list = selfProjectService.selectSelfProjectList(userIdArr,selfProject);
        return list.size();
    }

    /**
     * 查询项目信息
     *
     * @param projectCode 项目编号
     * @return 项目信息
     */
    @ApiOperation("连表获取查询项目信息(根据projectCode)")
//    @RequiresPermissions("company:place:query")
    @GetMapping(value = "/selectProjectJoinTicketByCode")
    public AjaxResult selectProjectJoinTicketByCode(String projectCode)
    {
        List<ProjectJoinTicketVo> selfProjects=selfProjectService.selectProjectJoinTicketByCode(projectCode);
        for (ProjectJoinTicketVo selfProject:selfProjects){
            selfProject.setTicketTax(selfProject.getTicketTax().movePointRight(2));
        }
        return AjaxResult.success(selfProjects);
    }


    /**
     * 查询项目信息列表
     */
//    @RequiresPermissions("project:project:list")
    @ApiOperation("查询项目信息列表")
    @GetMapping("/list")
    public TableDataInfo list(SelfProject selfProject)
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
        startPage();
        List<SelfProject> list = selfProjectService.selectSelfProjectList(userIdArr,selfProject);
        for (SelfProject selfProject1:list){
            selfProject1.setTicketTax(selfProject1.getTicketTax().movePointRight(2));
        }

        return getDataTable(list);
    }

    /**
     * 导出项目信息列表
     */
//    @RequiresPermissions("project:project:export")
//    @ApiOperation("导出项目信息列表")
//    @Log(title = "项目信息", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, SelfProject selfProject)
//    {
//        List<SelfProject> list = selfProjectService.selectSelfProjectList(selfProject);
//        ExcelUtil<SelfProject> util = new ExcelUtil<SelfProject>(SelfProject.class);
//        util.exportExcel(response, list, "项目信息数据");
//    }

    /**
     * 获取项目信息详细信息
     */
//    @RequiresPermissions("project:project:query")
    @ApiOperation("获取项目信息详细信息")
    @GetMapping(value = "/{projectId}")
    public AjaxResult getInfo(@PathVariable("projectId") String projectId)
    {
        SelfProject selfProject=  selfProjectService.selectSelfProjectByProjectId(projectId);
        selfProject.setTicketTax(selfProject.getTicketTax().movePointRight(2));
        return AjaxResult.success(selfProject);
    }

    /**
     * 新增项目信息
     */
//    @RequiresPermissions("project:project:add")
    @ApiOperation("新增项目信息")
    @Log(title = "项目信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SelfProject selfProject)
    {
        selfProject.setUserId(SecurityUtils.getUserId());
        selfProject.setEndStatus(0);
        selfProject.setTicketTax(selfProject.getTicketTax().movePointLeft(2));
        try {
            int num=selfProjectService.insertSelfProject(selfProject);
            return toAjax(num);
        }catch (DuplicateKeyException ex){
            return error("不允许插入重复单据，自动返回，请重新创建");
        }

    }

    /**
     * 修改项目信息
     */
//    @RequiresPermissions("project:project:edit")
    @ApiOperation("修改项目信息")
    @Log(title = "项目信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SelfProject selfProject)
    {
        if (selfProject.getTicketTax()!=null){
            selfProject.setTicketTax(selfProject.getTicketTax().movePointLeft(2));
        }
        return toAjax(selfProjectService.updateSelfProject(selfProject));
    }

    /**
     * 删除项目信息
     */
//    @RequiresPermissions("project:project:remove")
    @ApiOperation("删除项目信息")
    @Log(title = "项目信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{projectIds}")
    public AjaxResult remove(@PathVariable String[] projectIds)
    {
        for (String projectId:projectIds){
            String projectCode= selfProjectService.selectSelfProjectByProjectId(projectId).getProjectCode();
            selfProjectService.deleteProjectByCode(projectCode);
            selfProjectService.deleteCheckByCode(projectCode);
            List<SelfTicket> selfTickets= selfTicketService.selectSelfTicketByProjectCode(projectCode);
            if (selfTickets.size()>0){
                return error("存在发票不能删除！");
            }
        };
        return toAjax(200);
    }

    /**
     * 删除项目信息（伪删）
     */
    @ApiOperation("删除项目信息（伪删）")
//    @RequiresPermissions("company:place:remove")
    @Log(title = "项目信息", businessType = BusinessType.UPDATE)
    @PutMapping("/del/{projectIds}")
    public AjaxResult remove2(@PathVariable String[] projectIds)
    {
        return toAjax(selfProjectService.deleteSelfProjectByProjectIds2(projectIds));
    }

    /**
     * 获取编号
     */
    @GetMapping(value ="/getCode")
    @ApiOperation("获取编码")
    public String getCode(String selfCode){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String nowDate = sdf.format(date);
        List<SelfProject> selfProjects=selfProjectService.selectLast();
        System.out.println("selfProjects=="+selfProjects);
        String code="";
        if (selfProjects.size()>0){
            code=  StringUtils.getCode(selfCode,selfProjects.get(0).getProjectCode(),"yyyyMMdd");
        }else {//没有单据时
            code=selfCode+"-"+nowDate+"-"+"0001";
        }
        return code;
    };

}

