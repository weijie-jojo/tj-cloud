package com.ruoyi.company.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.redis.util.ListUtil;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.company.domain.SelfEmployed;
import com.ruoyi.company.domain.vo.SelfEmployedVo;
import com.ruoyi.company.domain.vo.SysUserVo;
import com.ruoyi.company.mapper.SysUserMapper;
import com.ruoyi.company.service.ISelfEmployedTgService;
import com.ruoyi.place.api.RemotePlaceService;
import com.ruoyi.place.api.domain.vo.BusinessPlaceVo;
import com.ruoyi.system.api.RemoteUserService;
import com.ruoyi.system.api.domain.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 个体商户Controller
 * 
 * @author ruoyi
 * @date 2022-06-14
 */
@RestController
@RequestMapping("/employed/tg")
@Api(tags = "个体商户（腾工）")
public class SelfEmployedTgController extends BaseController
{
    @Autowired
    private ISelfEmployedTgService selfEmployedService;
    @Resource
    private  SysUserMapper sysUserMapper;
    @Autowired
    private RemoteUserService remoteUserService;
    @Autowired
    private RemotePlaceService remotePlaceService;
    /**
     * 获取所有用户
     */
    @ApiOperation("获取所有用户")
    @GetMapping(value = "/getAllUser")
    public AjaxResult getAllUser()
    {
        List<Long> roleIdArr = new ArrayList<Long>();
        roleIdArr.add(11L);
        roleIdArr.add(118L);
        roleIdArr.add(117L);
        System.out.println("roleIdArr==="+roleIdArr);
        List<SysUser> list=   remoteUserService.getUserByRoles(roleIdArr);
        System.out.println("LoginUser==="+list);
        return AjaxResult.success(list);
    }

    /**
     * 获取三个状态的数量
     */
    @ApiOperation("获取三个状态的数量")
//    @RequiresPermissions("company:employed:list")
    @GetMapping("/getCount")
    public HashMap<String, Integer> selectEmployedJoinCount(SelfEmployedVo selfEmployedVo)
    {
        List<SelfEmployedVo> list1= ListUtil.getInstance().getList1();
        List<SelfEmployedVo> list2= ListUtil.getInstance().getList2();
        List<SelfEmployedVo> list3= ListUtil.getInstance().getList3();
        selfEmployedVo.setInfoStatus(null);
        selfEmployedVo.setNameStatus(null);
        selfEmployedVo.setBusinessStatus(null);
        selfEmployedVo.setTaxStatus(null);
        selfEmployedVo.setBankStatus(null);
        selfEmployedVo.setEndStatus(null);
        List<Long> userIdArr=getUserIdArr(selfEmployedVo);
        List<SelfEmployedVo> list = selfEmployedService.selectEmployedJoinReview(userIdArr,selfEmployedVo);
        if (selfEmployedVo.getType()==1){//注册进度
            list1= list.stream().filter(s->s.getEndStatus()==0).collect(Collectors.toList());
            list2= list.stream().filter(s->s.getEndStatus()==1).collect(Collectors.toList());
            list3= list.stream().filter(s->s.getEndStatus()==2).collect(Collectors.toList());
        }
        if (selfEmployedVo.getType()==2){//名称审核
            list1= list.stream().filter(s->s.getNameStatus()==0).collect(Collectors.toList());
            list2= list.stream().filter(s->s.getNameStatus()==1).collect(Collectors.toList());
            list3= list.stream().filter(s->s.getNameStatus()==2).collect(Collectors.toList());
        }
        if (selfEmployedVo.getType()==3){//信息审核
            list1= list.stream().filter(s->s.getInfoStatus()==0).collect(Collectors.toList());
            list2= list.stream().filter(s->s.getInfoStatus()==1).collect(Collectors.toList());
            list3= list.stream().filter(s->s.getInfoStatus()==2).collect(Collectors.toList());
        }
        if (selfEmployedVo.getType()==4){//工商办理
            list1= list.stream().filter(s->s.getNameStatus()==1&&s.getInfoStatus()==1&&s.getBusinessStatus()==0).collect(Collectors.toList());
            list2= list.stream().filter(s->s.getNameStatus()==1&&s.getInfoStatus()==1&&s.getBusinessStatus()==1).collect(Collectors.toList());
        }
        if (selfEmployedVo.getType()==5){//税务办理
            list1= list.stream().filter(s->s.getNameStatus()==1&&s.getInfoStatus()==1&&s.getBusinessStatus()==1&&s.getTaxStatus()==0).collect(Collectors.toList());
            list2= list.stream().filter(s->s.getNameStatus()==1&&s.getInfoStatus()==1&&s.getBusinessStatus()==1&&s.getTaxStatus()==1).collect(Collectors.toList());
        }
        if (selfEmployedVo.getType()==6){//银行办理
            list1= list.stream().filter(s->s.getNameStatus()==1&&s.getInfoStatus()==1&&s.getBusinessStatus()==1&&s.getTaxStatus()==1&&s.getBankStatus()==0).collect(Collectors.toList());
            list2= list.stream().filter(s->s.getNameStatus()==1&&s.getInfoStatus()==1&&s.getBusinessStatus()==1&&s.getTaxStatus()==1&&s.getBankStatus()==1).collect(Collectors.toList());
        }
        if (selfEmployedVo.getType()==7){
            list1= list.stream().filter(s->s.getNameStatus()==1&&s.getInfoStatus()==1&&s.getBusinessStatus()==1&&s.getTaxStatus()==1&&s.getBankStatus()==1&&s.getEndStatus()==0).collect(Collectors.toList());
            list2= list.stream().filter(s->s.getNameStatus()==1&&s.getInfoStatus()==1&&s.getBusinessStatus()==1&&s.getTaxStatus()==1&&s.getBankStatus()==1&&s.getEndStatus()==1).collect(Collectors.toList());
            list3= list.stream().filter(s->s.getNameStatus()==1&&s.getInfoStatus()==1&&s.getBusinessStatus()==1&&s.getTaxStatus()==1&&s.getBankStatus()==1&&s.getEndStatus()==2).collect(Collectors.toList());
        }
        HashMap<String, Integer> datasMap=new HashMap<String, Integer>();
        datasMap.put("unfinished", list1.size());
        datasMap.put("finished", list2.size());
        datasMap.put("error", list3.size());
        datasMap.put("total", list1.size()+list2.size()+list3.size());
        return datasMap;
    }

    /**
     * 查询登录用户个体户数量
     */
    @ApiOperation("查询登录用户个体户数量")
//    @RequiresPermissions("company:employed:list")
    @GetMapping("/getCounts")
    public Integer selectEmployedJoinCounts(SelfEmployedVo selfEmployedVo)
    {
        List<Long> userIdArr=getUserIdArr(selfEmployedVo);
        List<SelfEmployed> list = selfEmployedService.selectEmployedJoinCount(userIdArr, selfEmployedVo);

        return list.size();
    }

    /**
     * 连表查询
     */
    @ApiOperation("连表查询")
//    @RequiresPermissions("company:employed:list")
    @GetMapping("/joinList")
    public TableDataInfo selectEmployedJoinReview(SelfEmployedVo selfEmployedVo)
    {
        List<Long> userIdArr=getUserIdArr(selfEmployedVo);
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
            if (selfEmployedVo1.getSelfShareIsmoney()==1){//个体户注册费不定额按百分比算
                selfEmployedVo1.setSelfShare(selfEmployedVo1.getSelfShare().movePointRight(2));
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
        List<Long> userIdArr=getUserIdArr(selfEmployedVo);
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
            if (selfEmployedVo1.getSelfShareIsmoney()==1){//个体户注册费不定额按百分比算
                selfEmployedVo1.setSelfShare(selfEmployedVo1.getSelfShare().movePointRight(2));
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
            if (selfEmployed.getSelfShareIsmoney()==1){//个体户注册费不定额按百分比算
                selfEmployed.setSelfShare(selfEmployed.getSelfShare().movePointRight(2));
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
        if (selfEmployed.getSelfShareIsmoney()==1){//个体户注册费不定额按百分比算
            selfEmployed.setSelfShare(selfEmployed.getSelfShare().movePointRight(2));
        }
        return AjaxResult.success(selfEmployed);

    }

    /**
     * 根据selfCode获取个体商户详细信息
     */
    @ApiOperation("根据selfCode获取个体商户详细信息")
//    @RequiresPermissions("company:employed:query")
    @GetMapping(value = "/getInfoByCode/{selfCode}")
    public AjaxResult getInfoByCode(@PathVariable("selfCode") String selfCode)
    {
        SelfEmployed selfEmployed=  selfEmployedService.selectSelfEmployedBySelfCode(selfCode);
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
        if (selfEmployed.getSelfShareIsmoney()==1){//个体户注册费不定额按百分比算
            selfEmployed.setSelfShare(selfEmployed.getSelfShare().movePointRight(2));
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
        selfEmployed.setContributionAmount(selfEmployed.getContributionAmount()*10000);

        if(selfEmployed.getIsSelfCount()==0){//按个体结算
            System.out.println("按个体结算");
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
            if (selfEmployed.getSelfShareIsmoney()==1){//个体户注册费不定额按百分比算
                selfEmployed.setSelfShare(selfEmployed.getSelfShare().movePointLeft(2));
            }
        }
        if(selfEmployed.getIsSelfCount()==1){//按客户结算
            System.out.println("按客户结算");
            BusinessPlaceVo businessPlace= remotePlaceService.getPlaceByCodeTg(selfEmployed.getPlaceCode());
            System.out.println("businessPlace=="+businessPlace);
            //个体注册服务费
            selfEmployed.setIsRegisterMoney(businessPlace.getIsRegisterMoney());
            selfEmployed.setRegisterMoney(businessPlace.getRegisterMoney());
            selfEmployed.setIsSelfShare(businessPlace.getIsSelfShare());
            selfEmployed.setSelfShareIsmoney(businessPlace.getSelfShareIsmoney());
            selfEmployed.setSelfShare(businessPlace.getSelfShare());
            //增值税普通发票
            selfEmployed.setIsSliderOrdinary(businessPlace.getIsSliderOrdinary());
            selfEmployed.setOrdinaryTax(businessPlace.getOrdinaryTax());
            selfEmployed.setOrdinaryProxyIsmoney(businessPlace.getOrdinaryProxyIsmoney());
            selfEmployed.setOrdinarySelfFee(businessPlace.getOrdinarySelfFee());
            selfEmployed.setIsSelfTax(businessPlace.getIsSelfTax());
            selfEmployed.setIsOrdinaryTax(businessPlace.getIsOrdinaryTax());
            selfEmployed.setIsOrdinaryShare(businessPlace.getIsOrdinaryShare());
            selfEmployed.setOrdinaryShareIsmoney(businessPlace.getOrdinaryShareIsmoney());
            selfEmployed.setOrdinaryShare(businessPlace.getOrdinaryShare());
            //增值税专用发票
            selfEmployed.setIsSlider(businessPlace.getIsSlider());
            selfEmployed.setOrdinarySpecialTax(businessPlace.getOrdinarySpecialTax());
            selfEmployed.setSpecialProxyIsmoney(businessPlace.getSpecialProxyIsmoney());
            selfEmployed.setSpecialSelfFee(businessPlace.getSpecialSelfFee());
            selfEmployed.setIsSpecialSelfTax(businessPlace.getIsSpecialSelfTax());
            selfEmployed.setIsSpecialTax(businessPlace.getIsSpecialTax());
            selfEmployed.setIsSpecialShare(businessPlace.getIsSpecialShare());
            selfEmployed.setSpecialShareIsmoney(businessPlace.getSpecialShareIsmoney());
            selfEmployed.setSpecialShare(businessPlace.getSpecialShare());
            //一次性费用
            selfEmployed.setIsDisposable(businessPlace.getIsDisposable());
            selfEmployed.setDisposableFeeIsmoney(businessPlace.getDisposableFeeIsmoney());
            selfEmployed.setDisposableFee(businessPlace.getDisposableFee());
            selfEmployed.setDisposableRemark(businessPlace.getDisposableRemark());
            selfEmployed.setIsDisposableShare(businessPlace.getIsDisposableShare());
            selfEmployed.setDisposableShareIsmoney(businessPlace.getDisposableShareIsmoney());
            selfEmployed.setDisposableShare(businessPlace.getDisposableShare());
        }


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
        SelfEmployed selfEmployed1= selfEmployedService.selectSelfEmployedBySelfId(selfEmployed.getSelfId());
        if(selfEmployed.getIsSelfCount()==0){//按个体结算
            System.out.println("按个体结算");
            if (selfEmployed1.getOrdinaryShareIsmoney()==1){//普票分润不定额按百分比算
                if(selfEmployed.getOrdinaryShare()!=null){
                    selfEmployed.setOrdinaryShare(selfEmployed.getOrdinaryShare().movePointLeft(2));
                }
            }
            if (selfEmployed1.getSpecialShareIsmoney()==1){//专票分润不定额按百分比算
                if(selfEmployed.getSpecialShare()!=null){
                    selfEmployed.setSpecialShare(selfEmployed.getSpecialShare().movePointLeft(2));
                }
            }
            if (selfEmployed1.getOrdinaryProxyIsmoney()==1){//普票平台服务费不定额按百分比算
                if(selfEmployed.getOrdinarySelfFee()!=null){
                    selfEmployed.setOrdinarySelfFee(selfEmployed.getOrdinarySelfFee().movePointLeft(2));
                }

            }
            if (selfEmployed1.getSpecialProxyIsmoney()==1){//专票平台服务费不定额按百分比算
                if(selfEmployed.getSpecialSelfFee()!=null){
                    selfEmployed.setSpecialSelfFee(selfEmployed.getSpecialSelfFee().movePointLeft(2));
                }

            }
            if (selfEmployed1.getSelfShareIsmoney()==1){//个体户注册费不定额按百分比算
                if(selfEmployed.getSelfShare()!=null){
                    selfEmployed.setSelfShare(selfEmployed.getSelfShare().movePointLeft(2));
                }
            }
        }
        if(selfEmployed.getIsSelfCount()==1){//按客户结算
            System.out.println("按客户结算");
            BusinessPlaceVo businessPlace= remotePlaceService.getPlaceByCodeTg(selfEmployed.getPlaceCode());
            System.out.println("businessPlace=="+businessPlace);
            //个体注册服务费
            selfEmployed.setIsRegisterMoney(businessPlace.getIsRegisterMoney());
            selfEmployed.setRegisterMoney(businessPlace.getRegisterMoney());
            selfEmployed.setIsSelfShare(businessPlace.getIsSelfShare());
            selfEmployed.setSelfShareIsmoney(businessPlace.getSelfShareIsmoney());
            selfEmployed.setSelfShare(businessPlace.getSelfShare());
            //增值税普通发票
            selfEmployed.setIsSliderOrdinary(businessPlace.getIsSliderOrdinary());
            selfEmployed.setOrdinaryTax(businessPlace.getOrdinaryTax());
            selfEmployed.setOrdinaryProxyIsmoney(businessPlace.getOrdinaryProxyIsmoney());
            selfEmployed.setOrdinarySelfFee(businessPlace.getOrdinarySelfFee());
            selfEmployed.setIsSelfTax(businessPlace.getIsSelfTax());
            selfEmployed.setIsOrdinaryTax(businessPlace.getIsOrdinaryTax());
            selfEmployed.setIsOrdinaryShare(businessPlace.getIsOrdinaryShare());
            selfEmployed.setOrdinaryShareIsmoney(businessPlace.getOrdinaryShareIsmoney());
            selfEmployed.setOrdinaryShare(businessPlace.getOrdinaryShare());
            //增值税专用发票
            selfEmployed.setIsSlider(businessPlace.getIsSlider());
            selfEmployed.setOrdinarySpecialTax(businessPlace.getOrdinarySpecialTax());
            selfEmployed.setSpecialProxyIsmoney(businessPlace.getSpecialProxyIsmoney());
            selfEmployed.setSpecialSelfFee(businessPlace.getSpecialSelfFee());
            selfEmployed.setIsSpecialSelfTax(businessPlace.getIsSpecialSelfTax());
            selfEmployed.setIsSpecialTax(businessPlace.getIsSpecialTax());
            selfEmployed.setIsSpecialShare(businessPlace.getIsSpecialShare());
            selfEmployed.setSpecialShareIsmoney(businessPlace.getSpecialShareIsmoney());
            selfEmployed.setSpecialShare(businessPlace.getSpecialShare());
            //一次性费用
            selfEmployed.setIsDisposable(businessPlace.getIsDisposable());
            selfEmployed.setDisposableFeeIsmoney(businessPlace.getDisposableFeeIsmoney());
            selfEmployed.setDisposableFee(businessPlace.getDisposableFee());
            selfEmployed.setDisposableRemark(businessPlace.getDisposableRemark());
            selfEmployed.setIsDisposableShare(businessPlace.getIsDisposableShare());
            selfEmployed.setDisposableShareIsmoney(businessPlace.getDisposableShareIsmoney());
            selfEmployed.setDisposableShare(businessPlace.getDisposableShare());
        }
        selfEmployed.setContributionAmount(selfEmployed1.getContributionAmount());
        int num=selfEmployedService.updateSelfEmployed(selfEmployed);
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

    /*
     * 获取用户id集合（查询过滤条件）
     *
     * */
    public  List<Long> getUserIdArr(SelfEmployedVo selfEmployedVo){
        //获取登录用户的部门id
        Integer deptId=sysUserMapper.getDeptByUserId(SecurityUtils.getUserId()).getDeptId();
        //根据部门id获取用户集合
        List<SysUserVo> userVos=sysUserMapper.getUserByDeptId(deptId);
        //根据登录用户获取用户角色信息
        List<SysUserVo> roles= sysUserMapper.getRoleByUserId(SecurityUtils.getUserId());
        //存储username的list集合
        List<Long> userIdArr=new ArrayList<>();
        for (SysUserVo role:roles){
            if (role.getRoleId()==10||role.getRoleId()==12||role.getRoleId()==4||
                    role.getRoleId()==120||role.getRoleId()==122 ||role.getRoleId()==119||role.getRoleId()==121){//部门主管
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
            else if (role.getRoleId()==11||role.getRoleId()==117||role.getRoleId()==118){//行政人员
                System.out.println("行政人员");
                userIdArr=null;
                selfEmployedVo.setApplyName(String.valueOf(SecurityUtils.getUserId()));
            }
            else {
                System.out.println("其他人");
                userIdArr.add(SecurityUtils.getUserId());//显示登录用户的
            }
        }
        System.out.println("userIdArr=="+userIdArr);
        return userIdArr;
    }
}
