package com.ruoyi.place.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.place.dto.DataDto;
import com.ruoyi.place.entity.BusinessAgencyFee;
import com.ruoyi.place.entity.BusinessPlace;
import com.ruoyi.place.qo.PageQo;
import com.ruoyi.place.service.IBusinessPlaceService;
import com.ruoyi.place.util.StringUtils;
import com.ruoyi.place.vo.PlaceVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

    @GetMapping(value ="/getByPage")
    @Log(title = "分页条件查询")
    @ApiOperation("分页条件查询")
    public HashMap<String, Object> getByPage(BusinessPlace businessPlace, PageQo pageQo, String status){
        pageQo.setPage(pageQo.getPage()+1);
        System.out.println("pageQo=="+pageQo);
        IPage<PlaceVo> placeVos = iBusinessPlaceService.selectByPage(businessPlace,pageQo,status);
        HashMap<String, Object> datasMap=new HashMap<String, Object>();
        datasMap.put("content", placeVos.getRecords());
        datasMap.put("totalElements", placeVos.getTotal());
        return datasMap;
    };
    @PostMapping
    @Log(title = "新增渠道")
    @ApiOperation("新增渠道")
//    @PreAuthorize("@el.check('place:add')")
    public ResponseEntity<Object> addPlace(@Validated @RequestBody BusinessPlace businessPlace){
        System.out.println("resources=="+businessPlace);
        businessPlace.setPlaceStatus(0);
        return new ResponseEntity<>(iBusinessPlaceService.addPlace(businessPlace),HttpStatus.CREATED);
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