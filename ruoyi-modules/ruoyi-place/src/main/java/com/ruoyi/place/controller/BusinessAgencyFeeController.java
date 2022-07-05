package com.ruoyi.place.controller;


import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.place.dto.DataDto;
import com.ruoyi.place.entity.BusinessAgencyFee;
import com.ruoyi.place.entity.BusinessPlace;
import com.ruoyi.place.mapper.BusinessPlaceMapper;
import com.ruoyi.place.service.IBusinessAgencyFeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 渠道商代理费 前端控制器
 * </p>
 *
 * @author lcc
 * @since 2022-06-07
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "渠道商代理费管理")
@RequestMapping("/agencyFee")
public class BusinessAgencyFeeController {
    private final IBusinessAgencyFeeService iBusinessAgencyFeeService;
    private final BusinessPlaceMapper businessPlaceMapper;

    @GetMapping(value ="/selectFeeByCode")
    @Log(title = "根据渠道商编号查询代理费详情")
    @ApiOperation("根据渠道商编号查询代理费详情")
    public BusinessAgencyFee selectFeeByCode(String placeCode){
        BusinessAgencyFee businessAgencyFee = iBusinessAgencyFeeService.selectFeeByCode(placeCode);
        return businessAgencyFee;
    };
//    @PostMapping
//    @Log(title = "新增渠道")
//    @ApiOperation("新增渠道")
////    @PreAuthorize("@el.check('place:add')")
//    public DataDto addPlace(@Validated @RequestBody BusinessAgencyFee businessAgencyFee){
//        System.out.println("getPlaceName=="+businessAgencyFee.getPlaceName());
//        List<BusinessPlace> businessPlaces= businessPlaceMapper.getByPlaceName(businessAgencyFee.getPlaceName());
//        DataDto dataDto = new DataDto();
//
//        if(businessPlaces.size()>0){
//            return dataDto.err("渠道名重复");
//        }else {
//            try {
//                iBusinessAgencyFeeService.add(businessAgencyFee);
//                return dataDto.success("添加成功");
//            }catch (DuplicateKeyException duplicateKeyException){
//                return dataDto.err("渠道编号重复");
//            }
//        }
//
//    };

}
