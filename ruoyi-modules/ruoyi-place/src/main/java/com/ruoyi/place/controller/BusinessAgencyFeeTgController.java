package com.ruoyi.place.controller;


import com.ruoyi.place.entity.BusinessAgencyFeeTg;
import com.ruoyi.place.service.IBusinessAgencyFeeTgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 渠道商代理费 前端控制器
 * </p>
 *
 * @author lcc
 * @since 2022-06-07
 */
@RestController
@RequestMapping("/agencyFee/tg")
@Api(tags = "渠道商代理费管理腾工")
public class BusinessAgencyFeeTgController {

    @Autowired
    private  IBusinessAgencyFeeTgService iBusinessAgencyFeeService;

    @GetMapping("/selectFeeByCode")
    @ApiOperation("根据渠道商编号查询代理费详情")
    public BusinessAgencyFeeTg selectFeeByCode(String placeCode){
        BusinessAgencyFeeTg businessAgencyFee = iBusinessAgencyFeeService.selectFeeByCode(placeCode);

        if (businessAgencyFee.getOrdinaryShareIsmoney()==1){//普票分润不定额按百分比算
            businessAgencyFee.setOrdinaryShare(businessAgencyFee.getOrdinaryShare().movePointRight(2));
        }
        if (businessAgencyFee.getSpecialShareIsmoney()==1){//专票分润不定额按百分比算
            businessAgencyFee.setSpecialShare(businessAgencyFee.getSpecialShare().movePointRight(2));
        }
        if (businessAgencyFee.getOrdinaryProxyIsmoney()==1){//普票平台服务费不定额按百分比算
            businessAgencyFee.setOrdinaryProxyFee(businessAgencyFee.getOrdinaryProxyFee().movePointRight(2));
        }
        if (businessAgencyFee.getSpecialProxyIsmoney()==1){//专票平台服务费不定额按百分比算
            businessAgencyFee.setSpecialProxyFee(businessAgencyFee.getSpecialProxyFee().movePointRight(2));
        }

        return businessAgencyFee;
    };

}
