package com.ruoyi.place.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.place.entity.BusinessAgencyFee;
import com.ruoyi.place.entity.BusinessAgencyFeeTc;
import com.ruoyi.place.mapper.BusinessAgencyFeeTcMapper;
import com.ruoyi.place.service.IBusinessAgencyFeeTcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 渠道商代理费 服务实现类
 * </p>
 *
 * @author lcc
 * @since 2022-06-07
 */
@Service
public class BusinessAgencyFeeTcServiceImpl extends ServiceImpl<BusinessAgencyFeeTcMapper, BusinessAgencyFeeTc> implements IBusinessAgencyFeeTcService {

    @Autowired
    private BusinessAgencyFeeTcMapper businessAgencyFeeMapper;
    /*
    * 通过code查询明细
    * */
    @Override
    public BusinessAgencyFeeTc selectFeeByCode(String placeCode) {
        QueryWrapper<BusinessAgencyFeeTc> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("place_code",placeCode);
        queryWrapper.eq("is_delete",1);
        return businessAgencyFeeMapper.selectOne(queryWrapper);
    }

    @Override
    public Integer add(BusinessAgencyFeeTc businessAgencyFee) {
        businessAgencyFee.setIsDelete(true);
        return businessAgencyFeeMapper.insert(businessAgencyFee);

    }
}
