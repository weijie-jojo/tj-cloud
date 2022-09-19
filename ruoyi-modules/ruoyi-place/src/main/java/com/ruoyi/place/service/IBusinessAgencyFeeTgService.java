package com.ruoyi.place.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.place.entity.BusinessAgencyFee;
import com.ruoyi.place.entity.BusinessAgencyFeeTg;

/**
 * <p>
 * 渠道商代理费 服务类
 * </p>
 *
 * @author lcc
 * @since 2022-06-07
 */
public interface IBusinessAgencyFeeTgService extends IService<BusinessAgencyFeeTg> {
   BusinessAgencyFeeTg selectFeeByCode(String placeCode);
   Integer add(BusinessAgencyFeeTg businessAgencyFee);
}
