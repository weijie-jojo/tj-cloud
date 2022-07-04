package com.ruoyi.place.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.place.entity.BusinessAgencyFee;
import com.ruoyi.place.mapper.BusinessAgencyFeeMapper;
import com.ruoyi.place.service.IBusinessAgencyFeeService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 渠道商代理费 服务实现类
 * </p>
 *
 * @author lcc
 * @since 2022-06-07
 */
@Service
public class BusinessAgencyFeeServiceImpl extends ServiceImpl<BusinessAgencyFeeMapper, BusinessAgencyFee> implements IBusinessAgencyFeeService {

    @Resource
    private BusinessAgencyFeeMapper businessAgencyFeeMapper;
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
