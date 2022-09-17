package com.ruoyi.place.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.place.entity.BusinessAgencyFee;

/**
 * <p>
 * 渠道商代理费 服务类
 * </p>
 *
 * @author lcc
 * @since 2022-06-07
 */
public interface IBusinessAgencyFeeTcService extends IService<BusinessAgencyFee> {
   BusinessAgencyFee selectFeeByCode(String placeCode);
   Integer add(BusinessAgencyFee businessAgencyFee);
}
