package com.ruoyi.place.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.place.entity.BusinessAgencyFee;
import com.ruoyi.place.entity.BusinessAgencyFeeTc;
import com.ruoyi.place.entity.BusinessPlace;
import com.ruoyi.place.entity.BusinessPlaceTc;
import com.ruoyi.place.entity.vo.BusinessPlaceVo;
import com.ruoyi.place.mapper.BusinessAgencyFeeTcMapper;
import com.ruoyi.place.mapper.BusinessAgencyFeeTcRecycleMapper;
import com.ruoyi.place.mapper.BusinessPlaceTcMapper;
import com.ruoyi.place.mapper.BusinessPlaceTcRecycleMapper;
import com.ruoyi.place.service.IBusinessPlaceTcService;
import com.ruoyi.place.vo.PlaceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 平台渠道商 服务实现类
 * </p>
 *
 * @author lcc
 * @since 2022-06-03
 */
@Service
public class BusinessPlaceTcServiceImpl implements IBusinessPlaceTcService {

    @Autowired
    private BusinessPlaceTcMapper businessPlaceMapper;
    @Autowired
    private BusinessPlaceTcRecycleMapper businessPlaceRecycleMapper;
    @Autowired
    private BusinessAgencyFeeTcMapper businessAgencyFeeMapper;
    @Autowired
    private BusinessAgencyFeeTcRecycleMapper businessAgencyFeeRecycleMapper;
    @Override
    public List<BusinessPlace> selectByPage(List<Long> userIdArr,PlaceVo placeVo) {
        System.out.println("statusArr=="+placeVo.getStatus());
        String[] statusArr;
        if (!StringUtils.hasText(placeVo.getStatus())){
            statusArr=null;
            System.out.println("statusArr是空的");
        }else {
            statusArr =placeVo.getStatus().split(",");
        }
        String placeName=placeVo.getPlaceAliasName();
        return businessPlaceMapper.selectBusinessPlaceJoin(userIdArr,statusArr,placeName);
    }
    /**
     * 查询平台渠道商
     *
     * @param placeCode 平台渠道商主键
     * @return 平台渠道商
     */
    @Override
    public BusinessPlaceVo selectBusinessPlaceByPlaceCode(String placeCode)
    {
        return businessPlaceMapper.selectBusinessPlaceByPlaceCode(placeCode);
    }
    @Override
    public Integer addPlace(BusinessPlaceTc businessPlace) {
        businessPlace.setIsDelete(true);
        businessPlace.setPlaceStatus(0);
        return businessPlaceMapper.insert(businessPlace);
    }

    @Override
    public Integer delPlace(String placeCode) {
        UpdateWrapper<BusinessPlaceTc> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("place_code",placeCode);
        updateWrapper.set("is_delete",false);
        UpdateWrapper<BusinessAgencyFeeTc> updateWrapper2=new UpdateWrapper<>();
        updateWrapper2.eq("place_code",placeCode);
        updateWrapper2.set("is_delete",false);
        Integer num1=businessAgencyFeeMapper.update(null,updateWrapper2);
        Integer num2=businessPlaceMapper.update(null,updateWrapper);
        return  num1+num2;
    }
    /*
    * 真删
    * */
    @Override
    public Integer delPlace2(String placeCode) {
        //插入回收表
        businessPlaceRecycleMapper.recycle(placeCode);
        businessAgencyFeeRecycleMapper.recycle(placeCode);
        //再删除
        QueryWrapper<BusinessPlaceTc> queryWrapper1=new QueryWrapper<>();
        QueryWrapper<BusinessAgencyFeeTc> queryWrapper2=new QueryWrapper<>();
        queryWrapper1.eq("place_code",placeCode);
        queryWrapper2.eq("place_code",placeCode);
        Integer num1=businessAgencyFeeMapper.delete(queryWrapper2);
        Integer num2=businessPlaceMapper.delete(queryWrapper1);
        return  num1+num2;
    }
    @Override
    public Integer editPlace(BusinessPlaceTc businessPlace, BusinessAgencyFeeTc businessAgencyFee) {
        QueryWrapper<BusinessPlaceTc> queryWrapper1=new QueryWrapper<>();
        QueryWrapper<BusinessAgencyFeeTc> queryWrapper2=new QueryWrapper<>();
        queryWrapper1.eq("place_code",businessPlace.getPlaceCode());
        queryWrapper2.eq("place_code",businessAgencyFee.getPlaceCode());
        System.out.println("businessAgencyFee"+businessAgencyFee);
        Integer num1=businessAgencyFeeMapper.update(businessAgencyFee,queryWrapper2);
        Integer num2=businessPlaceMapper.update(businessPlace,queryWrapper1);
        return  num1+num2;
    }
    /*
    *
    * 改变状态
    * */
    @Override
    public Integer editPlace2(BusinessPlaceTc businessPlace) {
        Integer num=businessPlaceMapper.updateById(businessPlace);
        return  num;
    }
    /*
     * 查询数据库里面最后一条数据
     *
     * */
    @Override
    public List<BusinessPlaceTc> selectMaxCode(){
        IPage<BusinessPlaceTc> iPage=new Page<>(1,1);
        return businessPlaceMapper.selectPage(iPage,
                new QueryWrapper<BusinessPlaceTc>()
                        .orderByDesc("place_id")).getRecords();
    }
}
