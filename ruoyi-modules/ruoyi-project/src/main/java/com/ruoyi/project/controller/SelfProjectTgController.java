package com.ruoyi.project.controller;

import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.redis.util.ListUtil;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.place.api.RemotePlaceService;
import com.ruoyi.place.api.domain.vo.BusinessPlaceVo;
import com.ruoyi.project.domain.SelfEmployed;
import com.ruoyi.project.domain.SelfProject;
import com.ruoyi.project.domain.SelfReceive;
import com.ruoyi.project.domain.SelfTicket;
import com.ruoyi.project.domain.vo.ProjectJoinTicketVo;
import com.ruoyi.project.domain.vo.SysUserVo;
import com.ruoyi.project.mapper.SysUserMapper;
import com.ruoyi.project.service.ISelfEmployedTgService;
import com.ruoyi.project.service.ISelfProjectTgService;
import com.ruoyi.project.service.ISelfReceiveTgService;
import com.ruoyi.project.service.ISelfTicketTgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 项目信息Controller
 *
 * @author ruoyi
 * @date 2022-07-06
 */
@RestController
@Api(tags = "项目管理（腾工）")
@RequestMapping("/project/tg")
public class SelfProjectTgController extends BaseController
{
    @Autowired
    private ISelfTicketTgService selfTicketService;
    @Autowired
    private ISelfProjectTgService selfProjectService;
    @Autowired
    private ISelfReceiveTgService selfReceiveService;
    @Resource
    private SysUserMapper sysUserMapper;
    @Autowired
    private ISelfEmployedTgService selfEmployedService;
    @Autowired
    private RemotePlaceService remotePlaceService;
    /**
     * 获取三个状态的数量
     */
    @ApiOperation("获取三个状态的数量")
    @GetMapping("/getCount3")
    public HashMap<String, Integer> selectProjectJoinCount(SelfProject selfProject)
    {

        List<SelfProject> list1= ListUtil.getInstance().getList1();
        List<SelfProject> list2= ListUtil.getInstance().getList2();
        List<SelfProject> list3= ListUtil.getInstance().getList3();
        List<SelfProject> list4= ListUtil.getInstance().getList4();
        selfProject.setProjectStatus(null);
        selfProject.setProjectCheckStatus(null);
        selfProject.setProjectContractStatus(null);
        selfProject.setProjectAcceptanceStatus(null);
        selfProject.setProjectDutypaidStatus(null);
        selfProject.setProjectTicketStatus(null);
        selfProject.setProjectReceiveStatus(null);
        selfProject.setProjectPayStatus(null);
        List<Long> userIdArr=getUserIdArr(selfProject);
        List<SelfProject> list = selfProjectService.selectSelfProjectList(userIdArr,selfProject);
        if (selfProject.getType()==1){//项目进度列表
            list1= list.stream().filter(s->s.getProjectStatus()==0).collect(Collectors.toList());
            list2= list.stream().filter(s->s.getProjectStatus()==2).collect(Collectors.toList());
            list3= list.stream().filter(s->s.getProjectStatus()==1).collect(Collectors.toList());
        }
        if (selfProject.getType()==2){//项目审核
            list1= list.stream().filter(s->s.getProjectCheckStatus()==0).collect(Collectors.toList());
            list2= list.stream().filter(s->s.getProjectCheckStatus()==1).collect(Collectors.toList());
            list3= list.stream().filter(s->s.getProjectCheckStatus()==2).collect(Collectors.toList());
        }
        if (selfProject.getType()==3){//发票审核
            list1= list.stream().filter(s->s.getProjectTicketStatus()==0).collect(Collectors.toList());
            list2= list.stream().filter(s->s.getProjectTicketStatus()==1).collect(Collectors.toList());
            list3= list.stream().filter(s->s.getProjectTicketStatus()==2).collect(Collectors.toList());
        }
        if (selfProject.getType()==4){//合同 验收审核
            list1= list.stream().filter(s->s.getProjectAcceptanceStatus()==0).collect(Collectors.toList());
            list2= list.stream().filter(s->s.getProjectAcceptanceStatus()==1).collect(Collectors.toList());
            list3= list.stream().filter(s->s.getProjectAcceptanceStatus()==2).collect(Collectors.toList());
            list4= list.stream().filter(s->s.getProjectAcceptanceStatus()==-1).collect(Collectors.toList());
        }
        if (selfProject.getType()==5){//完税审核
            list1= list.stream().filter(s->s.getProjectDutypaidStatus()==0).collect(Collectors.toList());
            list2= list.stream().filter(s->s.getProjectDutypaidStatus()==1).collect(Collectors.toList());
            list3= list.stream().filter(s->s.getProjectDutypaidStatus()==2).collect(Collectors.toList());
            list4= list.stream().filter(s->s.getProjectDutypaidStatus()==-1).collect(Collectors.toList());
        }
        if (selfProject.getType()==6){//收款审核
            list1= list.stream().filter(s->s.getProjectReceiveStatus()==0).collect(Collectors.toList());
            list2= list.stream().filter(s->s.getProjectReceiveStatus()==1).collect(Collectors.toList());
            list3= list.stream().filter(s->s.getProjectReceiveStatus()==2).collect(Collectors.toList());
        }
        if (selfProject.getType()==7){//出款审核
            list1= list.stream().filter(s->s.getProjectPayStatus()==0).collect(Collectors.toList());
            list2= list.stream().filter(s->s.getProjectPayStatus()==1).collect(Collectors.toList());
            list3= list.stream().filter(s->s.getProjectPayStatus()==2).collect(Collectors.toList());
        }
        HashMap<String, Integer> datasMap=new HashMap<String, Integer>();
        datasMap.put("unfinished", list1.size());
        datasMap.put("finished", list2.size());
        datasMap.put("error", list3.size());
        datasMap.put("bl", list4.size());
        datasMap.put("total", list1.size()+list2.size()+list3.size()+list4.size());
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
        List<Long> userIdArr=getUserIdArr(selfProject);
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
        ProjectJoinTicketVo selfProject=selfProjectService.selectProjectJoinTicketByCode(projectCode);

        if(selfProject!=null){
            selfProject.setTicketTax(selfProject.getTicketTax().movePointRight(2));
            if (selfProject.getOrdinaryShareIsmoney()==1){//普票分润不定额按百分比算
                selfProject.setOrdinaryShare(selfProject.getOrdinaryShare().movePointRight(2));
            }
            if (selfProject.getSpecialShareIsmoney()==1){//专票分润不定额按百分比算
                selfProject.setSpecialShare(selfProject.getSpecialShare().movePointRight(2));
            }
            if (selfProject.getOrdinaryProxyIsmoney()==1){//普票平台服务费不定额按百分比算
                selfProject.setOrdinarySelfFee(selfProject.getOrdinarySelfFee().movePointRight(2));
            }
            if (selfProject.getSpecialProxyIsmoney()==1){//专票平台服务费不定额按百分比算
                selfProject.setSpecialSelfFee(selfProject.getSpecialSelfFee().movePointRight(2));
            }
            if (selfProject.getSelfShareIsmoney()==1){//个体户注册费不定额按百分比算
                selfProject.setSelfShare(selfProject.getSelfShare().movePointRight(2));
            }
        }

        return AjaxResult.success(selfProject);
    }


    /**
     * 查询项目信息列表
     */
//    @RequiresPermissions("project:project:list")
    @ApiOperation("查询项目信息列表")
    @GetMapping("/list")
    public TableDataInfo list(SelfProject selfProject)
    {
        List<Long> userIdArr=getUserIdArr(selfProject);
        startPage();
        List<SelfProject> list = selfProjectService.selectSelfProjectList(userIdArr,selfProject);
        for (SelfProject selfProject1:list){
            selfProject1.setTicketTax(selfProject1.getTicketTax().movePointRight(2));
            if (selfProject1.getOrdinaryShareIsmoney()==1){//普票分润不定额按百分比算
                selfProject1.setOrdinaryShare(selfProject1.getOrdinaryShare().movePointRight(2));
            }
            if (selfProject1.getSpecialShareIsmoney()==1){//专票分润不定额按百分比算
                selfProject1.setSpecialShare(selfProject1.getSpecialShare().movePointRight(2));
            }
            if (selfProject1.getOrdinaryProxyIsmoney()==1){//普票平台服务费不定额按百分比算
                selfProject1.setOrdinarySelfFee(selfProject1.getOrdinarySelfFee().movePointRight(2));
            }
            if (selfProject1.getSpecialProxyIsmoney()==1){//专票平台服务费不定额按百分比算
                selfProject1.setSpecialSelfFee(selfProject1.getSpecialSelfFee().movePointRight(2));
            }
            if (selfProject1.getSelfShareIsmoney()==1){//个体户注册费不定额按百分比算
                selfProject1.setSelfShare(selfProject1.getSelfShare().movePointRight(2));
            }
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
        if (selfProject.getOrdinaryShareIsmoney()==1){//普票分润不定额按百分比算
            selfProject.setOrdinaryShare(selfProject.getOrdinaryShare().movePointRight(2));
        }
        if (selfProject.getSpecialShareIsmoney()==1){//专票分润不定额按百分比算
            selfProject.setSpecialShare(selfProject.getSpecialShare().movePointRight(2));
        }
        if (selfProject.getOrdinaryProxyIsmoney()==1){//普票平台服务费不定额按百分比算
            selfProject.setOrdinarySelfFee(selfProject.getOrdinarySelfFee().movePointRight(2));
        }
        if (selfProject.getSpecialProxyIsmoney()==1){//专票平台服务费不定额按百分比算
            selfProject.setSpecialSelfFee(selfProject.getSpecialSelfFee().movePointRight(2));
        }
        if (selfProject.getSelfShareIsmoney()==1){//个体户注册费不定额按百分比算
            selfProject.setSelfShare(selfProject.getSelfShare().movePointRight(2));
        }
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

        if(selfProject.getIsSelfCount()==0){//按个体结算
            System.out.println("按个体结算");
            SelfEmployed selfEmployed= selfEmployedService.selectSelfEmployedBySelfCode(selfProject.getProjectOwner());
            //个体注册服务费
            selfProject.setIsRegisterMoney(selfEmployed.getIsRegisterMoney());
            selfProject.setRegisterMoney(selfEmployed.getRegisterMoney());
            selfProject.setIsSelfShare(selfEmployed.getIsSelfShare());
            selfProject.setSelfShareIsmoney(selfEmployed.getSelfShareIsmoney());
            selfProject.setSelfShare(selfEmployed.getSelfShare());
            //增值税普通发票
            selfProject.setIsSliderOrdinary(selfEmployed.getIsSliderOrdinary());
            selfProject.setOrdinaryTax(selfEmployed.getOrdinaryTax());
            selfProject.setOrdinaryProxyIsmoney(selfEmployed.getOrdinaryProxyIsmoney());
            selfProject.setOrdinarySelfFee(selfEmployed.getOrdinarySelfFee());
            selfProject.setIsSelfTax(selfEmployed.getIsSelfTax());
            selfProject.setIsOrdinaryTax(selfEmployed.getIsOrdinaryTax());
            selfProject.setIsOrdinaryShare(selfEmployed.getIsOrdinaryShare());
            selfProject.setOrdinaryShareIsmoney(selfEmployed.getOrdinaryShareIsmoney());
            selfProject.setOrdinaryShare(selfEmployed.getOrdinaryShare());
            //增值税专用发票
            selfProject.setIsSlider(selfEmployed.getIsSlider());
            selfProject.setOrdinarySpecialTax(selfEmployed.getOrdinarySpecialTax());
            selfProject.setSpecialProxyIsmoney(selfEmployed.getSpecialProxyIsmoney());
            selfProject.setSpecialSelfFee(selfEmployed.getSpecialSelfFee());
            selfProject.setIsSpecialSelfTax(selfEmployed.getIsSpecialSelfTax());
            selfProject.setIsSpecialTax(selfEmployed.getIsSpecialTax());
            selfProject.setIsSpecialShare(selfEmployed.getIsSpecialShare());
            selfProject.setSpecialShareIsmoney(selfEmployed.getSpecialShareIsmoney());
            selfProject.setSpecialShare(selfEmployed.getSpecialShare());
            //一次性费用
            selfProject.setIsDisposable(selfEmployed.getIsDisposable());
            selfProject.setDisposableFeeIsmoney(selfEmployed.getDisposableFeeIsmoney());
            selfProject.setDisposableFee(selfEmployed.getDisposableFee());
            selfProject.setDisposableRemark(selfEmployed.getDisposableRemark());
            selfProject.setIsDisposableShare(selfEmployed.getIsDisposableShare());
            selfProject.setDisposableShareIsmoney(selfEmployed.getDisposableShareIsmoney());
            selfProject.setIsDisposableShare(selfEmployed.getIsDisposableShare());
        }
        if(selfProject.getIsSelfCount()==1){//按客户结算
            System.out.println("按客户结算");
            BusinessPlaceVo businessPlace= remotePlaceService.getPlaceByCodeTg(selfProject.getPlaceCode());
            System.out.println("businessPlace=="+businessPlace);
            //个体注册服务费
            selfProject.setIsRegisterMoney(businessPlace.getIsRegisterMoney());
            selfProject.setRegisterMoney(businessPlace.getRegisterMoney());
            selfProject.setIsSelfShare(businessPlace.getIsSelfShare());
            selfProject.setSelfShareIsmoney(businessPlace.getSelfShareIsmoney());
            selfProject.setSelfShare(businessPlace.getSelfShare());
            //增值税普通发票
            selfProject.setIsSliderOrdinary(businessPlace.getIsSliderOrdinary());
            selfProject.setOrdinaryTax(businessPlace.getOrdinaryTax());
            selfProject.setOrdinaryProxyIsmoney(businessPlace.getOrdinaryProxyIsmoney());
            selfProject.setOrdinarySelfFee(businessPlace.getOrdinarySelfFee());
            selfProject.setIsSelfTax(businessPlace.getIsSelfTax());
            selfProject.setIsOrdinaryTax(businessPlace.getIsOrdinaryTax());
            selfProject.setIsOrdinaryShare(businessPlace.getIsOrdinaryShare());
            selfProject.setOrdinaryShareIsmoney(businessPlace.getOrdinaryShareIsmoney());
            selfProject.setOrdinaryShare(businessPlace.getOrdinaryShare());
            //增值税专用发票
            selfProject.setIsSlider(businessPlace.getIsSlider());
            selfProject.setOrdinarySpecialTax(businessPlace.getOrdinarySpecialTax());
            selfProject.setSpecialProxyIsmoney(businessPlace.getSpecialProxyIsmoney());
            selfProject.setSpecialSelfFee(businessPlace.getSpecialSelfFee());
            selfProject.setIsSpecialSelfTax(businessPlace.getIsSpecialSelfTax());
            selfProject.setIsSpecialTax(businessPlace.getIsSpecialTax());
            selfProject.setIsSpecialShare(businessPlace.getIsSpecialShare());
            selfProject.setSpecialShareIsmoney(businessPlace.getSpecialShareIsmoney());
            selfProject.setSpecialShare(businessPlace.getSpecialShare());
            //一次性费用
            selfProject.setIsDisposable(businessPlace.getIsDisposable());
            selfProject.setDisposableFeeIsmoney(businessPlace.getDisposableFeeIsmoney());
            selfProject.setDisposableFee(businessPlace.getDisposableFee());
            selfProject.setDisposableRemark(businessPlace.getDisposableRemark());
            selfProject.setIsDisposableShare(businessPlace.getIsDisposableShare());
            selfProject.setDisposableShareIsmoney(businessPlace.getDisposableShareIsmoney());
            selfProject.setIsDisposableShare(businessPlace.getIsDisposableShare());
        }
        if(selfProject.getIsSelfCount()==2){//按项目结算
            System.out.println("按项目结算");
            selfProject.setTicketTax(selfProject.getTicketTax().movePointLeft(2));

            if (selfProject.getOrdinaryShareIsmoney()==1){//普票分润不定额按百分比算
                selfProject.setOrdinaryShare(selfProject.getOrdinaryShare().movePointLeft(2));
            }
            if (selfProject.getSpecialShareIsmoney()==1){//专票分润不定额按百分比算
                selfProject.setSpecialShare(selfProject.getSpecialShare().movePointLeft(2));
            }
            if (selfProject.getOrdinaryProxyIsmoney()==1){//普票平台服务费不定额按百分比算
                selfProject.setOrdinarySelfFee(selfProject.getOrdinarySelfFee().movePointLeft(2));
            }
            if (selfProject.getSpecialProxyIsmoney()==1){//专票平台服务费不定额按百分比算
                selfProject.setSpecialSelfFee(selfProject.getSpecialSelfFee().movePointLeft(2));
            }
            if (selfProject.getSelfShareIsmoney()==1){//个体户注册费不定额按百分比算
                selfProject.setSelfShare(selfProject.getSelfShare().movePointLeft(2));
            }
        }

        try {
            int num=selfProjectService.insertSelfProject(selfProject);
            return toAjax(num);
        }catch (DuplicateKeyException ex){
//            return error("不允许插入重复项目，自动返回，请重新创建");
            return null;
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
        if(selfProject.getIsSelfCount()==0){//按个体结算
            System.out.println("按个体结算");
            SelfEmployed selfEmployed= selfEmployedService.selectSelfEmployedBySelfCode(selfProject.getProjectOwner());
            //个体注册服务费
            selfProject.setIsRegisterMoney(selfEmployed.getIsRegisterMoney());
            selfProject.setRegisterMoney(selfEmployed.getRegisterMoney());
            selfProject.setIsSelfShare(selfEmployed.getIsSelfShare());
            selfProject.setSelfShareIsmoney(selfEmployed.getSelfShareIsmoney());
            selfProject.setSelfShare(selfEmployed.getSelfShare());
            //增值税普通发票
            selfProject.setIsSliderOrdinary(selfEmployed.getIsSliderOrdinary());
            selfProject.setOrdinaryTax(selfEmployed.getOrdinaryTax());
            selfProject.setOrdinaryProxyIsmoney(selfEmployed.getOrdinaryProxyIsmoney());
            selfProject.setOrdinarySelfFee(selfEmployed.getOrdinarySelfFee());
            selfProject.setIsSelfTax(selfEmployed.getIsSelfTax());
            selfProject.setIsOrdinaryTax(selfEmployed.getIsOrdinaryTax());
            selfProject.setIsOrdinaryShare(selfEmployed.getIsOrdinaryShare());
            selfProject.setOrdinaryShareIsmoney(selfEmployed.getOrdinaryShareIsmoney());
            selfProject.setOrdinaryShare(selfEmployed.getOrdinaryShare());
            //增值税专用发票
            selfProject.setIsSlider(selfEmployed.getIsSlider());
            selfProject.setOrdinarySpecialTax(selfEmployed.getOrdinarySpecialTax());
            selfProject.setSpecialProxyIsmoney(selfEmployed.getSpecialProxyIsmoney());
            selfProject.setSpecialSelfFee(selfEmployed.getSpecialSelfFee());
            selfProject.setIsSpecialSelfTax(selfEmployed.getIsSpecialSelfTax());
            selfProject.setIsSpecialTax(selfEmployed.getIsSpecialTax());
            selfProject.setIsSpecialShare(selfEmployed.getIsSpecialShare());
            selfProject.setSpecialShareIsmoney(selfEmployed.getSpecialShareIsmoney());
            selfProject.setSpecialShare(selfEmployed.getSpecialShare());
            //一次性费用
            selfProject.setIsDisposable(selfEmployed.getIsDisposable());
            selfProject.setDisposableFeeIsmoney(selfEmployed.getDisposableFeeIsmoney());
            selfProject.setDisposableFee(selfEmployed.getDisposableFee());
            selfProject.setDisposableRemark(selfEmployed.getDisposableRemark());
            selfProject.setIsDisposableShare(selfEmployed.getIsDisposableShare());
            selfProject.setDisposableShareIsmoney(selfEmployed.getDisposableShareIsmoney());
            selfProject.setDisposableShare(selfEmployed.getDisposableShare());
        }
        if(selfProject.getIsSelfCount()==1){//按客户结算
            System.out.println("按客户结算");
            BusinessPlaceVo businessPlace= remotePlaceService.getPlaceByCode(selfProject.getPlaceCode());
            System.out.println("businessPlace=="+businessPlace);
            //个体注册服务费
            selfProject.setIsRegisterMoney(businessPlace.getIsRegisterMoney());
            selfProject.setRegisterMoney(businessPlace.getRegisterMoney());
            selfProject.setIsSelfShare(businessPlace.getIsSelfShare());
            selfProject.setSelfShareIsmoney(businessPlace.getSelfShareIsmoney());
            selfProject.setSelfShare(businessPlace.getSelfShare());
            //增值税普通发票
            selfProject.setIsSliderOrdinary(businessPlace.getIsSliderOrdinary());
            selfProject.setOrdinaryTax(businessPlace.getOrdinaryTax());
            selfProject.setOrdinaryProxyIsmoney(businessPlace.getOrdinaryProxyIsmoney());
            selfProject.setOrdinarySelfFee(businessPlace.getOrdinarySelfFee());
            selfProject.setIsSelfTax(businessPlace.getIsSelfTax());
            selfProject.setIsOrdinaryTax(businessPlace.getIsOrdinaryTax());
            selfProject.setIsOrdinaryShare(businessPlace.getIsOrdinaryShare());
            selfProject.setOrdinaryShareIsmoney(businessPlace.getOrdinaryShareIsmoney());
            selfProject.setOrdinaryShare(businessPlace.getOrdinaryShare());
            //增值税专用发票
            selfProject.setIsSlider(businessPlace.getIsSlider());
            selfProject.setOrdinarySpecialTax(businessPlace.getOrdinarySpecialTax());
            selfProject.setSpecialProxyIsmoney(businessPlace.getSpecialProxyIsmoney());
            selfProject.setSpecialSelfFee(businessPlace.getSpecialSelfFee());
            selfProject.setIsSpecialSelfTax(businessPlace.getIsSpecialSelfTax());
            selfProject.setIsSpecialTax(businessPlace.getIsSpecialTax());
            selfProject.setIsSpecialShare(businessPlace.getIsSpecialShare());
            selfProject.setSpecialShareIsmoney(businessPlace.getSpecialShareIsmoney());
            selfProject.setSpecialShare(businessPlace.getSpecialShare());
            //一次性费用
            selfProject.setIsDisposable(businessPlace.getIsDisposable());
            selfProject.setDisposableFeeIsmoney(businessPlace.getDisposableFeeIsmoney());
            selfProject.setDisposableFee(businessPlace.getDisposableFee());
            selfProject.setDisposableRemark(businessPlace.getDisposableRemark());
            selfProject.setIsDisposableShare(businessPlace.getIsDisposableShare());
            selfProject.setDisposableShareIsmoney(businessPlace.getDisposableShareIsmoney());
            selfProject.setDisposableShare(businessPlace.getDisposableShare());
        }
        if(selfProject.getIsSelfCount()==2){//按项目结算
            System.out.println("按项目结算");
            SelfProject selfProject1= selfProjectService.selectSelfProjectByProjectId(selfProject.getProjectId());
            if (selfProject.getTicketTax()!=null){
                selfProject.setTicketTax(selfProject.getTicketTax().movePointLeft(2));
            }
            if (selfProject1.getOrdinaryShareIsmoney()==1){//普票分润不定额按百分比算
                if(selfProject.getOrdinaryShare()!=null){
                    selfProject.setOrdinaryShare(selfProject.getOrdinaryShare().movePointLeft(2));
                }
            }
            if (selfProject1.getSpecialShareIsmoney()==1){//专票分润不定额按百分比算
                if(selfProject.getSpecialShare()!=null){
                    selfProject.setSpecialShare(selfProject.getSpecialShare().movePointLeft(2));
                }
            }
            if (selfProject1.getOrdinaryProxyIsmoney()==1){//普票平台服务费不定额按百分比算
                if(selfProject.getOrdinarySelfFee()!=null){
                    selfProject.setOrdinarySelfFee(selfProject.getOrdinarySelfFee().movePointLeft(2));
                }
            }
            if (selfProject1.getSpecialProxyIsmoney()==1){//专票平台服务费不定额按百分比算
                if(selfProject.getSpecialSelfFee()!=null){
                    selfProject.setSpecialSelfFee(selfProject.getSpecialSelfFee().movePointLeft(2));
                }
            }
            if (selfProject1.getSelfShareIsmoney()==1){//个体户注册费不定额按百分比算
                if(selfProject.getSelfShare()!=null){
                    selfProject.setSelfShare(selfProject.getSelfShare().movePointLeft(2));
                }
            }
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
        Integer count = 0;//删除的次数
        Integer num3=0;
        List<String> list=new ArrayList<>();//存储项目信息
        for (String projectId:projectIds){
            String projectCode= selfProjectService.selectSelfProjectByProjectId(projectId).getProjectCode();
            List<SelfTicket> selfTickets= selfTicketService.selectSelfTicketByProjectCode(projectCode);
            List<SelfReceive> selfReceives =selfReceiveService.selectSelfReceiveByProjectCode(projectCode);
            if (selfTickets.size()>0){
                System.out.println("存在发票不能删除！");
                list.add(projectCode);
                num3=1;
            } else if (selfReceives.size()>0){
                System.out.println("存在收付款信息不能删除！");
                list.add(projectCode);
                num3=1;
            } else {
                Integer num1 = selfProjectService.deleteProjectByCode(projectCode);
                Integer num2 = selfProjectService.deleteCheckByCode(projectCode);
                count+=(num1+num2);
            }
        };
        System.out.println("count=="+count);
        if (count>=projectIds.length*4){
            return toAjax(200);
        }else {
            if (num3==1){
                StringBuilder sb=new StringBuilder();
                for (String str:list){
                    sb.append(str+",");
                }
                return error(sb+"存在发票或收付款信息，请先删除出款信息！");
            }else {
                return error("删除失败");
            }
        }

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
            code=  StringUtils.getCode3(selfCode,selfProjects.get(0).getProjectCode(),"yyyyMMdd");
        }else {//没有单据时
            code=selfCode+"-"+nowDate+"-"+"0001";
        }
        return code;
    };

    /*
     * 获取用户id集合（查询过滤条件）
     *
     * */
    public  List<Long> getUserIdArr(SelfProject selfProject){
        //获取登录用户的部门id
        Integer deptId=sysUserMapper.getDeptByUserId(SecurityUtils.getUserId()).getDeptId();
        //根据部门id获取用户集合
        List<SysUserVo> userVos=sysUserMapper.getUserByDeptId(deptId);
        //存储用户id的list集合
        List<Long> userIdArr=new ArrayList<>();
        //获取登录用户id获取用户角色信息
        List<SysUserVo> roles= sysUserMapper.getRoleByUserId(SecurityUtils.getUserId());
        System.out.println("roles==="+roles);
        for (SysUserVo role:roles){
            if (role.getRoleId()==10||role.getRoleId()==12||role.getRoleId()==4||
                    role.getRoleId()==120||role.getRoleId()==122 ||role.getRoleId()==119||role.getRoleId()==121){//行政跟业务部门主管获取他们部门的渠道信息
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

        System.out.println("userIdArr=="+userIdArr);
        return userIdArr;
    }

}

