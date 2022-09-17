package com.ruoyi.place.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.place.entity.BusinessAgencyFee;
import com.ruoyi.place.mapper.BusinessAgencyFeeMapper;
import com.ruoyi.place.mapper.BusinessAgencyFeeTgMapper;
import com.ruoyi.place.service.IBusinessAgencyFeeTgService;
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
public class BusinessAgencyFeeTgServiceImpl extends ServiceImpl<BusinessAgencyFeeTgMapper, BusinessAgencyFee> implements IBusinessAgencyFeeTgService {

    @Autowired
    private BusinessAgencyFeeTgMapper businessAgencyFeeMapper;
    /*
    * 通过code查询明细
    * */
    @Override
    public BusinessAgencyFee selectFeeByCode(String placeCode) {
        QueryWrapper<BusinessAgencyFee> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("place_code",placeCode);
        queryWrapper.eq("is_delete",1);
        return businessAgencyFeeMapper.selectOne(queryWrapper);
    }

    @Override
    public Integer add(BusinessAgencyFee businessAgencyFee) {
        businessAgencyFee.setIsDelete(true);
        return businessAgencyFeeMapper.insert(businessAgencyFee);

    }
}
