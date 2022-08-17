package com.ruoyi.company.controller;

import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.company.domain.SelfEmployed;
import com.ruoyi.company.domain.vo.SelfEmployedVo;
import com.ruoyi.company.domain.vo.SysUserVo;
import com.ruoyi.company.mapper.SysUserMapper;
import com.ruoyi.company.service.ISelfApplicationInfoService;
import com.ruoyi.company.service.ISelfLegalPersonService;
import com.ruoyi.company.service.ISelfNameReviewService;
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
import com.ruoyi.company.service.ISelfEmployedService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 个体商户Controller
 * 
 * @author ruoyi
 * @date 2022-06-14
 */
@RestController
@RequestMapping("/employed")
@Api(tags = "个体商户")
public class SelfEmployedController extends BaseController
{
    @Autowired
    private ISelfEmployedService selfEmployedService;
    @Autowired
    private ISelfLegalPersonService selfLegalPersonService;
    @Autowired
    private ISelfNameReviewService selfNameReviewService;
    @Autowired
    private ISelfApplicationInfoService selfApplicationInfoService;
    @Resource
    private  SysUserMapper sysUserMapper;
    /**
     * 连表查询
     */
    @ApiOperation("获取三个状态的数量")
//    @RequiresPermissions("company:employed:list")
    @GetMapping("/getCount")
    public HashMap<String, Integer> selectEmployedJoinCount(SelfEmployedVo selfEmployedVo)
    {
        //获取登录用户的部门id
        Integer deptId=sysUserMapper.getDeptByUserId(SecurityUtils.getUserId()).getDeptId();
        //根据部门id获取用户集合
        List<SysUserVo> userVos=sysUserMapper.getUserByDeptId(deptId);
        //根据登录用户获取用户角色信息
        List<SysUserVo> roles= sysUserMapper.getRoleByUserId(SecurityUtils.getUserId());
        //存储username的list集合
        List<Long> userIdArr=new ArrayList<>();
        for (SysUserVo role:roles){
            if (role.getRoleId()==10||role.getRoleId()==12||role.getRoleId()==4){//部门主管
                System.out.println("部门主管");
//                for (SysUserVo userVo:userVos){//登录用户所属部门的所有用户名
//                    userIdArr.add(userVo.getUserId());
//                }
                userIdArr=null;//显示所有
            }
            else if (role.getRoleId()==1||role.getRoleId()==5||role.getRoleId()==6){//管理员及总经理 副总经理
                System.out.println("总经理");
                userIdArr=null;//显示所有
            }
            else if (role.getRoleId()==11){//文员
                System.out.println("文员");
                userIdArr=null;
                selfEmployedVo.setApplyName(String.valueOf(SecurityUtils.getUserId()));
            }
            else {
                System.out.println("其他人");
                userIdArr.add(SecurityUtils.getUserId());//显示登录用户的
            }
        }
        List<SelfEmployedVo> list1 =new ArrayList<>();
        List<SelfEmployedVo> list2 =new ArrayList<>();
        List<SelfEmployedVo> list3 =new ArrayList<>();
        if (selfEmployedVo.getType()==1){//注册进度
            selfEmployedVo.setEndStatus(0);
            list1 = selfEmployedService.selectEmployedJoinReview(userIdArr,selfEmployedVo);
            selfEmployedVo.setEndStatus(1);
            list2 = selfEmployedService.selectEmployedJoinReview(userIdArr,selfEmployedVo);
            selfEmployedVo.setEndStatus(2);
            list3 = selfEmployedService.selectEmployedJoinReview(userIdArr,selfEmployedVo);
        }
        if (selfEmployedVo.getType()==2){//名称审核
            selfEmployedVo.setNameStatus(0);
            list1 = selfEmployedService.selectEmployedJoinReview(userIdArr,selfEmployedVo);
            selfEmployedVo.setNameStatus(1);
            list2 = selfEmployedService.selectEmployedJoinReview(userIdArr,selfEmployedVo);
            selfEmployedVo.setNameStatus(2);
            list3 = selfEmployedService.selectEmployedJoinReview(userIdArr,selfEmployedVo);
        }
        if (selfEmployedVo.getType()==3){//信息审核
            selfEmployedVo.setInfoStatus(0L);
            list1 = selfEmployedService.selectEmployedJoinReview(userIdArr,selfEmployedVo);
            selfEmployedVo.setInfoStatus(1L);
            list2 = selfEmployedService.selectEmployedJoinReview(userIdArr,selfEmployedVo);
            selfEmployedVo.setInfoStatus(2L);
            list3 = selfEmployedService.selectEmployedJoinReview(userIdArr,selfEmployedVo);
        }
        if (selfEmployedVo.getType()==4){//工商办理
            selfEmployedVo.setNameStatus(1);
            selfEmployedVo.setInfoStatus(1L);
            selfEmployedVo.setBusinessStatus(0L);
            list1 = selfEmployedService.selectEmployedJoinReview(userIdArr,selfEmployedVo);
            selfEmployedVo.setNameStatus(1);
            selfEmployedVo.setInfoStatus(1L);
            selfEmployedVo.setBusinessStatus(1L);
            list2 = selfEmployedService.selectEmployedJoinReview(userIdArr,selfEmployedVo);
        }
        if (selfEmployedVo.getType()==5){//税务办理
            selfEmployedVo.setNameStatus(1);
            selfEmployedVo.setInfoStatus(1L);
            selfEmployedVo.setBusinessStatus(1L);
            selfEmployedVo.setTaxStatus(0L);
            list1 = selfEmployedService.selectEmployedJoinReview(userIdArr,selfEmployedVo);
            selfEmployedVo.setNameStatus(1);
            selfEmployedVo.setInfoStatus(1L);
            selfEmployedVo.setBusinessStatus(1L);
            selfEmployedVo.setTaxStatus(1L);
            list2 = selfEmployedService.selectEmployedJoinReview(userIdArr,selfEmployedVo);
        }
        if (selfEmployedVo.getType()==6){//银行办理
            selfEmployedVo.setNameStatus(1);
            selfEmployedVo.setInfoStatus(1L);
            selfEmployedVo.setBusinessStatus(1L);
            selfEmployedVo.setTaxStatus(1L);
            selfEmployedVo.setBankStatus(0L);
            list1 = selfEmployedService.selectEmployedJoinReview(userIdArr,selfEmployedVo);
            selfEmployedVo.setNameStatus(1);
            selfEmployedVo.setInfoStatus(1L);
            selfEmployedVo.setBusinessStatus(1L);
            selfEmployedVo.setTaxStatus(1L);
            selfEmployedVo.setBankStatus(1L);
            list2 = selfEmployedService.selectEmployedJoinReview(userIdArr,selfEmployedVo);
        }
        if (selfEmployedVo.getType()==7){
            selfEmployedVo.setNameStatus(1);
            selfEmployedVo.setInfoStatus(1L);
            selfEmployedVo.setBusinessStatus(1L);
            selfEmployedVo.setTaxStatus(1L);
            selfEmployedVo.setBankStatus(1L);
            selfEmployedVo.setEndStatus(0);
            list1 = selfEmployedService.selectEmployedJoinReview(userIdArr,selfEmployedVo);
            selfEmployedVo.setNameStatus(1);
            selfEmployedVo.setInfoStatus(1L);
            selfEmployedVo.setBusinessStatus(1L);
            selfEmployedVo.setTaxStatus(1L);
            selfEmployedVo.setBankStatus(1L);
            selfEmployedVo.setEndStatus(1);
            list2 = selfEmployedService.selectEmployedJoinReview(userIdArr,selfEmployedVo);
            selfEmployedVo.setNameStatus(1);
            selfEmployedVo.setInfoStatus(1L);
            selfEmployedVo.setBusinessStatus(1L);
            selfEmployedVo.setTaxStatus(1L);
            selfEmployedVo.setBankStatus(1L);
            selfEmployedVo.setEndStatus(2);
            list3 = selfEmployedService.selectEmployedJoinReview(userIdArr,selfEmployedVo);
        }
        HashMap<String, Integer> datasMap=new HashMap<String, Integer>();
        datasMap.put("unfinished", list1.size());
        datasMap.put("finished", list2.size());
        datasMap.put("error", list3.size());
        datasMap.put("total", list1.size()+list2.size()+list3.size());
        return datasMap;
    }

    /**
     * 连表查询
     */
    @ApiOperation("连表查询")
//    @RequiresPermissions("company:employed:list")
    @GetMapping("/joinList")
    public TableDataInfo selectEmployedJoinReview(SelfEmployedVo selfEmployedVo)
    {
//        selfEmployedVo.setOrdinaryShare();
        System.out.println("getOrdinaryShare=="+selfEmployedVo.getOrdinaryShare());
        //获取登录用户的部门id
        Integer deptId=sysUserMapper.getDeptByUserId(SecurityUtils.getUserId()).getDeptId();
        //根据部门id获取用户集合
        List<SysUserVo> userVos=sysUserMapper.getUserByDeptId(deptId);
        //根据登录用户获取用户角色信息
        List<SysUserVo> roles= sysUserMapper.getRoleByUserId(SecurityUtils.getUserId());
        //存储username的list集合
        List<Long> userIdArr=new ArrayList<>();
        for (SysUserVo role:roles){
            if (role.getRoleId()==10||role.getRoleId()==12||role.getRoleId()==4){//部门主管
                System.out.println("部门主管");
//                for (SysUserVo userVo:userVos){//登录用户所属部门的所有用户名
//                    userIdArr.add(userVo.getUserId());
//                }
                userIdArr=null;//显示所有
            }
            else if (role.getRoleId()==1||role.getRoleId()==5||role.getRoleId()==6){//管理员及总经理 副总经理
                System.out.println("总经理");
                userIdArr=null;//显示所有
            }
            else if (role.getRoleId()==11){//文员
                System.out.println("文员");
                userIdArr=null;
                selfEmployedVo.setApplyName(String.valueOf(SecurityUtils.getUserId()));
            }
            else {
                System.out.println("其他人");
                userIdArr.add(SecurityUtils.getUserId());//显示登录用户的
            }
        }
        startPage();
        System.out.println("getEndStatus=="+selfEmployedVo.getEndStatus());
        List<SelfEmployedVo> list = selfEmployedService.selectEmployedJoinReview(userIdArr,selfEmployedVo);
        for (SelfEmployedVo selfEmployedVo1:list){
            selfEmployedVo1.setContributionAmount(selfEmployedVo1.getContributionAmount()/10000);

            if (selfEmployedVo1.getOrdinaryShareIsmoney()==1){//普票分润不定额按百分比算
                selfEmployedVo1.setOrdinaryShare(selfEmployedVo1.getOrdinaryShare().movePointRight(2));
            }
            if (selfEmployedVo1.getSpecialShareIsmoney()==1){//专票分润不定额按百分比算
                selfEmployedVo1.setSpecialShare(selfEmployedVo1.getSpecialShare().movePointRight(2));
            }
            if (selfEmployedVo1.getOrdinaryProxyIsmoney()==1){//普票平台服务费不定额按百分比算
                selfEmployedVo1.setOrdinarySelfFee(selfEmployedVo1.getOrdinarySelfFee().movePointRight(2));
            }
            if (selfEmployedVo1.getSpecialProxyIsmoney()==1){//专票平台服务费不定额按百分比算
                selfEmployedVo1.setSpecialSelfFee(selfEmployedVo1.getSpecialSelfFee().movePointRight(2));
            }
        }
        return getDataTable(list);
    }

    /**
     * 连表selfNameReview查询（完结）
     */
    @ApiOperation("连表查询（完结）")
//    @RequiresPermissions("company:employed:list")
    @GetMapping("/joinListEnd")
    public TableDataInfo selectEmployedJoinEnd(SelfEmployedVo selfEmployedVo)
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
            if (role.getRoleId()==10||role.getRoleId()==12||role.getRoleId()==4){//行政,业务部门主管,软开主管
                System.out.println("部门主管");
//                for (SysUserVo userVo:userVos){//登录用户所属部门的所有用户名
//                    userIdArr.add(userVo.getUserId());
//                }
                userIdArr=null;//显示所有
            }
            else if (role.getRoleId()==1||role.getRoleId()==5||role.getRoleId()==6){//管理员及总经理 副总经理
                System.out.println("总经理");
                userIdArr=null;//显示所有
            }
            else if (role.getRoleId()==11){//文员
                System.out.println("文员");
                userIdArr=null;
                selfEmployedVo.setApplyName(String.valueOf(SecurityUtils.getUserId()));
            }
            else {
                System.out.println("其他人");
                userIdArr.add(SecurityUtils.getUserId());//显示登录用户的
            }
        }
        startPage();
        List<SelfEmployedVo> list = selfEmployedService.selectEmployedJoinEnd(userIdArr,selfEmployedVo);
        for (SelfEmployedVo selfEmployedVo1:list){
            selfEmployedVo1.setContributionAmount(selfEmployedVo1.getContributionAmount()/10000);

            if (selfEmployedVo1.getOrdinaryShareIsmoney()==1){//普票分润不定额按百分比算
                selfEmployedVo1.setOrdinaryShare(selfEmployedVo1.getOrdinaryShare().movePointRight(2));
            }
            if (selfEmployedVo1.getSpecialShareIsmoney()==1){//专票分润不定额按百分比算
                selfEmployedVo1.setSpecialShare(selfEmployedVo1.getSpecialShare().movePointRight(2));
            }
            if (selfEmployedVo1.getOrdinaryProxyIsmoney()==1){//普票平台服务费不定额按百分比算
                selfEmployedVo1.setOrdinarySelfFee(selfEmployedVo1.getOrdinarySelfFee().movePointRight(2));
            }
            if (selfEmployedVo1.getSpecialProxyIsmoney()==1){//专票平台服务费不定额按百分比算
                selfEmployedVo1.setSpecialSelfFee(selfEmployedVo1.getSpecialSelfFee().movePointRight(2));
            }
        }
        return getDataTable(list);
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
        for (SelfEmployed selfEmployedVo1:list){
            selfEmployedVo1.setContributionAmount(selfEmployedVo1.getContributionAmount()/10000);

            if (selfEmployedVo1.getOrdinaryShareIsmoney()==1){//普票分润不定额按百分比算
                selfEmployedVo1.setOrdinaryShare(selfEmployedVo1.getOrdinaryShare().movePointRight(2));
            }
            if (selfEmployedVo1.getSpecialShareIsmoney()==1){//专票分润不定额按百分比算
                selfEmployedVo1.setSpecialShare(selfEmployedVo1.getSpecialShare().movePointRight(2));
            }
            if (selfEmployedVo1.getOrdinaryProxyIsmoney()==1){//普票平台服务费不定额按百分比算
                selfEmployedVo1.setOrdinarySelfFee(selfEmployedVo1.getOrdinarySelfFee().movePointRight(2));
            }
            if (selfEmployedVo1.getSpecialProxyIsmoney()==1){//专票平台服务费不定额按百分比算
                selfEmployedVo1.setSpecialSelfFee(selfEmployedVo1.getSpecialSelfFee().movePointRight(2));
            }
        }
        return getDataTable(list);
    }

    /**
     * 导出个体商户列表
     */
    @ApiOperation("导出个体商户列表")
    @RequiresPermissions("company:employed:export")
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
        SelfEmployed selfEmployed=  selfEmployedService.selectSelfEmployedBySelfId(selfId);
        selfEmployed.setContributionAmount(selfEmployed.getContributionAmount()/10000);

        if (selfEmployed.getOrdinaryShareIsmoney()==1){//普票分润不定额按百分比算
            selfEmployed.setOrdinaryShare(selfEmployed.getOrdinaryShare().movePointRight(2));
        }
        if (selfEmployed.getSpecialShareIsmoney()==1){//专票分润不定额按百分比算
            selfEmployed.setSpecialShare(selfEmployed.getSpecialShare().movePointRight(2));
        }
        if (selfEmployed.getOrdinaryProxyIsmoney()==1){//普票平台服务费不定额按百分比算
            selfEmployed.setOrdinarySelfFee(selfEmployed.getOrdinarySelfFee().movePointRight(2));
        }
        if (selfEmployed.getSpecialProxyIsmoney()==1){//专票平台服务费不定额按百分比算
            selfEmployed.setSpecialSelfFee(selfEmployed.getSpecialSelfFee().movePointRight(2));
        }

        return AjaxResult.success(selfEmployed);

    }

    /**
     * 新增个体商户
     */
    @ApiOperation("新增个体商户")
    @RequiresPermissions("company:employed:add")
    @Log(title = "个体商户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SelfEmployed selfEmployed)
    {
        selfEmployed.setEndStatus(0);
        System.out.println("selfEmployed===111111"+selfEmployed);
        if (selfEmployed.getOrdinaryShareIsmoney()==1){//普票分润不定额按百分比算
            selfEmployed.setOrdinaryShare(selfEmployed.getOrdinaryShare().movePointLeft(2));
        }
        if (selfEmployed.getSpecialShareIsmoney()==1){//专票分润不定额按百分比算
            selfEmployed.setSpecialShare(selfEmployed.getSpecialShare().movePointLeft(2));
        }
        if (selfEmployed.getOrdinaryProxyIsmoney()==1){//普票平台服务费不定额按百分比算
            selfEmployed.setOrdinarySelfFee(selfEmployed.getOrdinarySelfFee().movePointLeft(2));
        }
        if (selfEmployed.getSpecialProxyIsmoney()==1){//专票平台服务费不定额按百分比算
            selfEmployed.setSpecialSelfFee(selfEmployed.getSpecialSelfFee().movePointLeft(2));
        }

        selfEmployed.setContributionAmount(selfEmployed.getContributionAmount()*10000);
        try {
            return toAjax(selfEmployedService.insertSelfEmployed(selfEmployed));
        }catch (DuplicateKeyException ex){
            return error("不允许插入重复单据，自动返回，请重新创建");
        }
    }

    /**
     * 修改个体商户
     */
    @ApiOperation("修改个体商户")
    @RequiresPermissions("company:employed:edit")
    @Log(title = "个体商户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SelfEmployed selfEmployed)
    {
        System.out.println("selfEmployed==="+selfEmployed);
        SelfEmployed selfEmployed1= selfEmployedService.selectSelfEmployedBySelfId(selfEmployed.getSelfId());
        if (selfEmployed1.getOrdinaryShareIsmoney()==1){//普票分润不定额按百分比算
            selfEmployed.setOrdinaryShare(selfEmployed1.getOrdinaryShare().movePointLeft(2));
        }
        if (selfEmployed1.getSpecialShareIsmoney()==1){//专票分润不定额按百分比算
            selfEmployed.setSpecialShare(selfEmployed1.getSpecialShare().movePointLeft(2));
        }
        if (selfEmployed1.getOrdinaryProxyIsmoney()==1){//普票平台服务费不定额按百分比算
            selfEmployed.setOrdinarySelfFee(selfEmployed1.getOrdinarySelfFee().movePointLeft(2));
        }
        if (selfEmployed1.getSpecialProxyIsmoney()==1){//专票平台服务费不定额按百分比算
            selfEmployed.setSpecialSelfFee(selfEmployed1.getSpecialSelfFee().movePointLeft(2));
        }

        Long contributionAmount= selfEmployedService.selectSelfEmployedBySelfId(selfEmployed.getSelfId()).getContributionAmount();
        selfEmployed.setContributionAmount(contributionAmount);
        int num=selfEmployedService.updateSelfEmployed(selfEmployed);
//        SelfEmployed selfEmployed2= selfEmployedService.selectSelfEmployedBySelfId(selfEmployed.getSelfId());
//        if (selfEmployed2.getBusinessStatus()==1&&selfEmployed2.getTaxStatus()==1&&selfEmployed2.getBankStatus()==1){
//            //全部完结
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            SelfEmployed selfEmployed1=new SelfEmployed();
//            selfEmployed1.setSelfId(selfEmployed.getSelfId());
//            selfEmployed1.setEndTime(df.format(new Date()));
//            selfEmployed1.setEndStatus(1);
//            selfEmployedService.updateSelfEmployed(selfEmployed1);
//        }
        return toAjax(num);
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
        for (String selfId:selfIds){
            String selfCode= selfEmployedService.selectSelfEmployedBySelfId(selfId).getSelfCode();
            selfEmployedService.deleteSelfNameReviewBySelfCode(selfCode);
            selfEmployedService.deleteSelfLegalPersonBySelfCode(selfCode);
            selfEmployedService.deleteSelfApplicationInfoBySelfCode(selfCode);
            selfEmployedService.deleteSelfEmployedBySelfCode(selfCode);
            selfEmployedService.deleteSelfCheckBySelfCode(selfCode);
        };

        return toAjax(200);
    }
}
