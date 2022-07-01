package com.ruoyi.place.controller;


import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.place.entity.BusinessAgencyFee;
import com.ruoyi.place.service.IBusinessAgencyFeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("api/agencyFee")
public class BusinessAgencyFeeController {
    private final IBusinessAgencyFeeService iBusinessAgencyFeeService;

    @GetMapping(value ="/selectFeeByCode")
    @Log(title = "根据渠道商编号查询代理费详情")
    @ApiOperation("根据渠道商编号查询代理费详情")
    public BusinessAgencyFee selectFeeByCode(String placeCode){
        BusinessAgencyFee businessAgencyFee = iBusinessAgencyFeeService.selectFeeByCode(placeCode);
        return businessAgencyFee;
    };
    @PostMapping
    @Log(title = "新增渠道")
    @ApiOperation("新增渠道")
//    @PreAuthorize("@el.check('place:add')")
    public ResponseEntity<Object> addPlace(@Validated @RequestBody BusinessAgencyFee businessAgencyFee){
        Integer num=iBusinessAgencyFeeService.add(businessAgencyFee);
        if (num>0){
            return new ResponseEntity<>(num, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(-1, HttpStatus.CREATED);
        }

    };

}
