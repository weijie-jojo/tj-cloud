package com.ruoyi.place.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.base.Joiner;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.place.dto.DataDto;
import com.ruoyi.place.entity.BusinessAgencyFee;
import com.ruoyi.place.entity.BusinessPlace;
import com.ruoyi.place.entity.vo.SysUserVo;
import com.ruoyi.place.mapper.BusinessPlaceMapper;
import com.ruoyi.place.mapper.SysUserMapper;
import com.ruoyi.place.qo.PageQo;
import com.ruoyi.place.service.IBusinessAgencyFeeService;
import com.ruoyi.place.service.IBusinessPlaceService;
import com.ruoyi.place.util.JudgeNull;
import com.ruoyi.place.util.StringUtils;
import com.ruoyi.place.vo.BusinessAgencyFeeVo;
import com.ruoyi.place.vo.PlaceVo;
import com.ruoyi.system.api.domain.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 平台渠道商 前端控制器
 * </p>
 *
 * @author lcc
 * @since 2022-06-03
 */
@RestController
@RequiredArgsConstructor //代替了resouse或者Autowrited
@Api(tags = "平台渠道商管理")
@RequestMapping("/place")
public class BusinessPlaceController {

    private final IBusinessPlaceService iBusinessPlaceService;
    private final BusinessPlaceMapper businessPlaceMapper;
    private final IBusinessAgencyFeeService iBusinessAgencyFeeService;
    private final SysUserMapper sysUserMapper;

    @GetMapping(value ="/getByPage")
    @Log(title = "分页条件查询")
    @ApiOperation("分页条件查询")
    public List<BusinessPlace> getByPage(PlaceVo placeVo){
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
        List<BusinessPlace> placeVos = iBusinessPlaceService.selectByPage(userIdArr,placeVo);
        System.out.println("placeVos"+placeVos.size());
//        HashMap<String, Object> datasMap=new HashMap<String, Object>();
//        System.out.println("getRecords"+placeVos.getRecords());
//        System.out.println("getTotal"+placeVos.getTotal());
//        System.out.println("getSize"+placeVos.getSize());
//        datasMap.put("content", placeVos.getRecords());
//        datasMap.put("totalElements", placeVos.getTotal());

//        return datasMap;
        return placeVos;
    };
    @PostMapping
    @Log(title = "新增渠道")
    @ApiOperation("新增渠道")
//    @PreAuthorize("@el.check('place:add')")
    public DataDto addPlace(@Validated @RequestBody Map map){
        BusinessAgencyFee businessAgencyFee=JSON.parseObject(JSON.toJSONString(map.get("businessAgencyFee")),BusinessAgencyFee.class);
        BusinessPlace businessPlace=JSON.parseObject(JSON.toJSONString(map.get("businessPlace")),BusinessPlace.class);
        System.out.println("businessAgencyFee=="+businessAgencyFee);
        System.out.println("businessPlace=="+businessPlace);
        //根据渠道名查询渠道信息
        List<BusinessPlace> businessPlaces= businessPlaceMapper.getByPlaceName(businessPlace.getPlaceName());
        DataDto dataDto = new DataDto();
        if(businessPlaces.size()>0){
            return dataDto.err("渠道名重复");
        }else {
            try {
                iBusinessPlaceService.addPlace(businessPlace);
                iBusinessAgencyFeeService.add(businessAgencyFee);
                return dataDto.success("添加成功");
            }catch (DuplicateKeyException duplicateKeyException){
                return dataDto.err("渠道编号重复");
            }
        }
    };
    @ApiOperation("删除渠道")
    @Log(title = "删除渠道")
    @PutMapping(value ="/delPlace")
    public DataDto delPlace(String placeCodes) {
        System.out.println("placeCodes=="+placeCodes);
        DataDto dataDto=new DataDto();
        String[] placeCodeStr=placeCodes.split(",");
        Integer num=0;//删除的次数
        for (String placeCode:placeCodeStr) {
            Integer count=iBusinessPlaceService.delPlace(placeCode);
            num+=count;
        }
        System.out.println("num=="+num);
        if (num>1){
            dataDto.success("删除成功");
        }else {
            dataDto.err("删除失败");
        }
        return dataDto;
    }
    @ApiOperation("修改渠道")
    @PutMapping("/editPlace")
    public DataDto editPlace(BusinessPlace businessPlace, BusinessAgencyFee businessAgencyFee) {
        DataDto dataDto=new DataDto();
        int num=iBusinessPlaceService.editPlace(businessPlace,businessAgencyFee);
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
    public DataDto editPlace2(BusinessPlace businessPlace) {
        DataDto dataDto=new DataDto();
        int num=iBusinessPlaceService.editPlace2(businessPlace);
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
        List<BusinessPlace> businessPlaces=iBusinessPlaceService.selectMaxCode();
        String code="";
        if (businessPlaces.size()>0){
            code=  StringUtils.getCode("QD",businessPlaces.get(0).getPlaceCode(),"yyyyMMdd");
        }else {//没有单据时
            code="QD"+"-"+nowDate+"-"+"0001";
        }
        DataDto dataDto=new DataDto();
        return dataDto.success(code);
    };
}
