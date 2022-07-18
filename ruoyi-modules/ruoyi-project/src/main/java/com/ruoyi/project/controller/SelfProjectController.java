package com.ruoyi.project.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.project.domain.BusinessPlace;
import com.ruoyi.project.domain.SelfProject;
import com.ruoyi.project.domain.vo.ProjectJoinTicketVo;
import com.ruoyi.project.service.ISelfProjectService;
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
    private ISelfProjectService selfProjectService;

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
        System.out.println("projectTimeStart=="+selfProject.getStart());
        startPage();
        List<SelfProject> list = selfProjectService.selectSelfProjectList(selfProject);
        return getDataTable(list);
    }

    /**
     * 导出项目信息列表
     */
//    @RequiresPermissions("project:project:export")
    @ApiOperation("导出项目信息列表")
    @Log(title = "项目信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SelfProject selfProject)
    {
        List<SelfProject> list = selfProjectService.selectSelfProjectList(selfProject);
        ExcelUtil<SelfProject> util = new ExcelUtil<SelfProject>(SelfProject.class);
        util.exportExcel(response, list, "项目信息数据");
    }

    /**
     * 获取项目信息详细信息
     */
//    @RequiresPermissions("project:project:query")
    @ApiOperation("获取项目信息详细信息")
    @GetMapping(value = "/{projectId}")
    public AjaxResult getInfo(@PathVariable("projectId") String projectId)
    {
        return AjaxResult.success(selfProjectService.selectSelfProjectByProjectId(projectId));
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
        return toAjax(selfProjectService.deleteSelfProjectByProjectIds(projectIds));
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
        String code="";
        if (selfProjects.size()>0){
            code=  StringUtils.getCode(selfCode,selfProjects.get(0).getProjectCode(),"yyyyMMdd");
        }else {//没有单据时
            code="TJ-TG"+"-"+nowDate+"-"+"0001";
        }
        return code;
    };

}

