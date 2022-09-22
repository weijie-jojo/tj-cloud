package com.ruoyi.place.controller;


import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.place.dto.DataDto;
import com.ruoyi.place.entity.BusinessAgencyFeeTg;
import com.ruoyi.place.entity.BusinessPlace;
import com.ruoyi.place.entity.BusinessPlaceTg;
import com.ruoyi.place.entity.SelfEmployed;
import com.ruoyi.place.entity.vo.SysUserVo;
import com.ruoyi.place.mapper.BusinessPlaceTgMapper;
import com.ruoyi.place.mapper.SelfEmployedTgMapper;
import com.ruoyi.place.mapper.SysUserMapper;
import com.ruoyi.place.service.IBusinessAgencyFeeTgService;
import com.ruoyi.place.service.IBusinessPlaceTgService;
import com.ruoyi.place.vo.PlaceVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 平台渠道商 前端控制器
 * </p>
 *
 * @author lcc
 * @since 2022-06-03
 */
@RestController
@RequestMapping("/place/tg")
@Api(tags = "平台渠道商管理滕工")
public class BusinessPlaceTgController extends BaseController {

    @Autowired
    private  IBusinessPlaceTgService iBusinessPlaceTgService;
    @Autowired
    private  BusinessPlaceTgMapper businessPlaceMapper;
    @Autowired
    private  IBusinessAgencyFeeTgService iBusinessAgencyFeeService;
    @Autowired
    private  SysUserMapper sysUserMapper;
    @Autowired
    private  SelfEmployedTgMapper selfEmployedMapper;

    @GetMapping(value ="/getCount")
    @ApiOperation("获取登录用户的渠道数量")
    public Integer getCount(PlaceVo placeVo){
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
            if (role.getRoleId()==120||role.getRoleId()==122){//行政跟业务部门主管获取他们部门的渠道信息
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
        List<BusinessPlace> placeVos = iBusinessPlaceTgService.selectByPage(userIdArr,placeVo);
        return placeVos.size();
    };

    @GetMapping(value ="/getByPage")
    @ApiOperation("分页条件查询")
    public TableDataInfo getByPage(PlaceVo placeVo){
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
            if (role.getRoleId()==120||role.getRoleId()==122){//行政跟业务部门主管获取他们部门的渠道信息
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
        startPage();
        List<BusinessPlace> placeVos = iBusinessPlaceTgService.selectByPage(userIdArr,placeVo);
        return getDataTable(placeVos);
    };
    @PostMapping
    @Log(title = "新增渠道",businessType = BusinessType.INSERT)
    @ApiOperation("新增渠道")
//    @PreAuthorize("@el.check('place:add')")
    public DataDto addPlace(@Validated @RequestBody Map map){
        BusinessAgencyFeeTg businessAgencyFee=JSON.parseObject(JSON.toJSONString(map.get("businessAgencyFee")),BusinessAgencyFeeTg.class);
        BusinessPlaceTg businessPlace=JSON.parseObject(JSON.toJSONString(map.get("businessPlace")),BusinessPlaceTg.class);

        if(businessAgencyFee.getOrdinaryShareIsmoney()!=null){
            if (businessAgencyFee.getOrdinaryShareIsmoney()==1){//普票分润不定额按百分比算
                businessAgencyFee.setOrdinaryShare(businessAgencyFee.getOrdinaryShare().movePointLeft(2));
            }
        }
        if(businessAgencyFee.getSpecialShareIsmoney()!=null){
            if (businessAgencyFee.getSpecialShareIsmoney()==1){//专票分润不定额按百分比算
                businessAgencyFee.setSpecialShare(businessAgencyFee.getSpecialShare().movePointLeft(2));
            }
        }
        if(businessAgencyFee.getOrdinaryProxyIsmoney()!=null){
            if (businessAgencyFee.getOrdinaryProxyIsmoney()==1){//普票平台服务费不定额按百分比算
                businessAgencyFee.setOrdinarySelfFee(businessAgencyFee.getOrdinarySelfFee().movePointLeft(2));
            }
        }
        if(businessAgencyFee.getSpecialProxyIsmoney()!=null){
            if (businessAgencyFee.getSpecialProxyIsmoney()==1){//专票平台服务费不定额按百分比算
                businessAgencyFee.setSpecialProxyFee(businessAgencyFee.getSpecialProxyFee().movePointLeft(2));
            }
        }
        //插入渠道全名
        businessPlace.setPlaceAliasName(businessPlace.getPlaceName()+'-'+businessPlace.getPlaceAlias());
        //根据渠道全名查询渠道信息
        List<BusinessPlace> businessPlaces= businessPlaceMapper.getByPlaceAliasName(businessPlace.getPlaceAliasName());
        DataDto dataDto = new DataDto();
        if(businessPlaces.size()>0){
            System.out.println("客户全名重复");
            return dataDto.err("客户全名重复");
        }else {
            try {
                iBusinessPlaceTgService.addPlace(businessPlace);
                iBusinessAgencyFeeService.add(businessAgencyFee);
                return dataDto.success("添加成功");
            }catch (DuplicateKeyException duplicateKeyException){
                return dataDto.err("客户编号重复");
            }
        }
    };
    @ApiOperation("删除渠道")
    @Log(title = "删除渠道",businessType = BusinessType.UPDATE)
    @PutMapping(value ="/delPlace")
    public DataDto delPlace(String placeCodes) {
        System.out.println("placeCodes=="+placeCodes);
        DataDto dataDto=new DataDto();
        String[] placeCodeStr=placeCodes.split(",");
        System.out.println("长度"+placeCodeStr.length);
        Integer num=0;//删除的次数
        Integer num2=0;
        List<SelfEmployed> list=new ArrayList<>();//存储存在个体户的渠道个体户信息
        for (String placeCode:placeCodeStr) {
            List<SelfEmployed> selfEmployeds= selfEmployedMapper.selectEmployedByPlaceCode(placeCode);
            if (selfEmployeds.size()<=0){//此渠道不存在个体户
                Integer count=iBusinessPlaceTgService.delPlace2(placeCode);
                num+=count;
            }else {
                num2=1;
                list.add(selfEmployeds.get(0));
            }
        }
        if (num>=placeCodeStr.length*2){
            dataDto.success("删除成功");
        }else {
            StringBuilder sb=new StringBuilder();
            if (num2==1){
                System.out.println("list"+list);
                for (SelfEmployed selfEmployed:list){
                     sb.append(selfEmployed.getPlaceAliasName()+",");
                }
//                String msg= StringUtils.getDistinct(sb.toString());
                dataDto.err(sb+"存在个体户");
                System.out.println(sb+"存在个体户不能删除");
            }else {
                dataDto.err("删除失败");
            }
        }
        return dataDto;
    }
    @ApiOperation("修改渠道")
    @PutMapping("/editPlace")
    @Log(title = "修改渠道",businessType = BusinessType.UPDATE)
    public DataDto editPlace(BusinessPlaceTg businessPlace, BusinessAgencyFeeTg businessAgencyFee) {

//        BusinessAgencyFee businessAgencyFee1= iBusinessAgencyFeeService.selectFeeByCode(businessAgencyFee.getPlaceCode());

        if (businessAgencyFee.getOrdinaryShareIsmoney()==1){//普票分润不定额按百分比算
            businessAgencyFee.setOrdinaryShare(businessAgencyFee.getOrdinaryShare().movePointLeft(2));
        }
        if (businessAgencyFee.getSpecialShareIsmoney()==1){//专票分润不定额按百分比算
            businessAgencyFee.setSpecialShare(businessAgencyFee.getSpecialShare().movePointLeft(2));
        }
        if (businessAgencyFee.getOrdinaryProxyIsmoney()==1){//普票平台服务费不定额按百分比算
            businessAgencyFee.setOrdinarySelfFee(businessAgencyFee.getOrdinarySelfFee().movePointLeft(2));
        }
        if (businessAgencyFee.getSpecialProxyIsmoney()==1){//专票平台服务费不定额按百分比算
            businessAgencyFee.setSpecialProxyFee(businessAgencyFee.getSpecialProxyFee().movePointLeft(2));
        }
        //渠道全名也要变更
        businessPlace.setPlaceAliasName(businessPlace.getPlaceName()+businessPlace.getPlaceAlias());

        DataDto dataDto=new DataDto();
        int num=iBusinessPlaceTgService.editPlace(businessPlace,businessAgencyFee);
        System.out.println("num===="+num);
        if (num>1){
            dataDto.success("修改成功");
        }else {
            dataDto.err("修改失败");
        }
        return dataDto;
    }
    @ApiOperation("改变状态")
    @PutMapping("/editPlace2")
    @Log(title = "改变状态",businessType = BusinessType.UPDATE)
    public DataDto editPlace2(BusinessPlaceTg businessPlace) {
        DataDto dataDto=new DataDto();
        int num=iBusinessPlaceTgService.editPlace2(businessPlace);
        if (num>0){
            dataDto.success("变更成功");
        }else {
            dataDto.err("变更失败");
        }
        return dataDto;
    }

    @GetMapping(value ="/getCode")
    @ApiOperation("获取编码")
    public DataDto getCode(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String nowDate = sdf.format(date);
        List<BusinessPlaceTg> businessPlaces=iBusinessPlaceTgService.selectMaxCode();
        String employeeNumber= sysUserMapper.getDeptByUserId(SecurityUtils.getUserId()).getEmployeeNumber();
        System.out.println("employeeNumber==="+employeeNumber);
        String code="";
        if (businessPlaces.size()>0){
            code=  StringUtils.getCode4("TGKH",employeeNumber,businessPlaces.get(0).getPlaceCode());
        }else {//没有单据时
            code="TGKH"+employeeNumber+"0001";
        }
        System.out.println("code==="+code);
        DataDto dataDto=new DataDto();
        return dataDto.success(code);
    };
}
