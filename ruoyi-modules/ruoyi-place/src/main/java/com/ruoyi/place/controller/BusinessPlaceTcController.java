package com.ruoyi.place.controller;


import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.place.dto.DataDto;
import com.ruoyi.place.entity.BusinessAgencyFeeTc;
import com.ruoyi.place.entity.BusinessPlace;
import com.ruoyi.place.entity.BusinessPlaceTc;
import com.ruoyi.place.entity.SelfEmployed;
import com.ruoyi.place.entity.vo.BusinessPlaceVo;
import com.ruoyi.place.entity.vo.SysUserVo;
import com.ruoyi.place.mapper.BusinessPlaceTcMapper;
import com.ruoyi.place.mapper.SelfEmployedTcMapper;
import com.ruoyi.place.mapper.SysUserMapper;
import com.ruoyi.place.service.IBusinessAgencyFeeTcService;
import com.ruoyi.place.service.IBusinessPlaceTcService;
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
@RequestMapping("/place/tc")
@Api(tags = "平台渠道商管理腾创")
public class BusinessPlaceTcController extends BaseController {

    @Autowired
    private  IBusinessPlaceTcService iBusinessPlaceTcService;
    @Autowired
    private  BusinessPlaceTcMapper businessPlaceMapper;
    @Autowired
    private  IBusinessAgencyFeeTcService iBusinessAgencyFeeService;
    @Autowired
    private  SysUserMapper sysUserMapper;
    @Autowired
    private  SelfEmployedTcMapper selfEmployedMapper;

    @GetMapping("/getPlaceByCode/{placeCode}")
    @ApiOperation("根据placeVo查询渠道信息")
    public BusinessPlaceVo getPlaceByCode(@PathVariable("placeCode") String placeVo){
        return  businessPlaceMapper.selectBusinessPlaceByPlaceCode(placeVo);
    }

    @GetMapping(value ="/getCount")
    @ApiOperation("获取登录用户的渠道数量")
    public Integer getCount(PlaceVo placeVo){
        List<Long> userIdArr=getUserIdArr(placeVo);
        List<BusinessPlace> placeVos = iBusinessPlaceTcService.selectByPage(userIdArr,placeVo);
        return placeVos.size();
    };

    @GetMapping(value ="/getByPage")
    @ApiOperation("分页条件查询")
    public TableDataInfo getByPage(PlaceVo placeVo){
        List<Long> userIdArr=getUserIdArr(placeVo);
        startPage();
        List<BusinessPlace> placeVos = iBusinessPlaceTcService.selectByPage(userIdArr,placeVo);
        return getDataTable(placeVos);
    };
    @PostMapping
    @Log(title = "新增渠道",businessType = BusinessType.INSERT)
    @ApiOperation("新增渠道")
//    @PreAuthorize("@el.check('place:add')")
    public DataDto addPlace(@Validated @RequestBody Map map){
        BusinessAgencyFeeTc businessAgencyFee=JSON.parseObject(JSON.toJSONString(map.get("businessAgencyFee")),BusinessAgencyFeeTc.class);
        BusinessPlaceTc businessPlace=JSON.parseObject(JSON.toJSONString(map.get("businessPlace")),BusinessPlaceTc.class);
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
                businessAgencyFee.setSpecialSelfFee(businessAgencyFee.getSpecialSelfFee().movePointLeft(2));
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
                iBusinessPlaceTcService.addPlace(businessPlace);
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
                Integer count=iBusinessPlaceTcService.delPlace2(placeCode);
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
    public DataDto editPlace(BusinessPlaceTc businessPlace, BusinessAgencyFeeTc businessAgencyFee) {

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
            businessAgencyFee.setSpecialSelfFee(businessAgencyFee.getSpecialSelfFee().movePointLeft(2));
        }
        //渠道全名也要变更
        businessPlace.setPlaceAliasName(businessPlace.getPlaceName()+businessPlace.getPlaceAlias());

        DataDto dataDto=new DataDto();
        int num=iBusinessPlaceTcService.editPlace(businessPlace,businessAgencyFee);
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
    public DataDto editPlace2(BusinessPlaceTc businessPlace) {
        DataDto dataDto=new DataDto();
        int num=iBusinessPlaceTcService.editPlace2(businessPlace);
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
        List<BusinessPlaceTc> businessPlaces=iBusinessPlaceTcService.selectMaxCode();
        String employeeNumber= sysUserMapper.getDeptByUserId(SecurityUtils.getUserId()).getEmployeeNumber();
        System.out.println("employeeNumber==="+employeeNumber);
        String code="";
        if (businessPlaces.size()>0){
            code=  StringUtils.getCode4("TCKH",employeeNumber,businessPlaces.get(0).getPlaceCode());
        }else {//没有单据时
            code="TCKH"+employeeNumber+"0001";
        }
        System.out.println("code==="+code);
        DataDto dataDto=new DataDto();
        return dataDto.success(code);
    };
    /*
     * 获取用户id集合（查询过滤条件）
     *
     * */
    public  List<Long> getUserIdArr(PlaceVo placeVo){
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
