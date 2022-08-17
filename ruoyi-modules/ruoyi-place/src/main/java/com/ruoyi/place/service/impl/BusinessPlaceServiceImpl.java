package com.ruoyi.place.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.place.entity.BusinessAgencyFee;
import com.ruoyi.place.entity.BusinessPlace;
import com.ruoyi.place.mapper.BusinessAgencyFeeMapper;
import com.ruoyi.place.mapper.BusinessAgencyFeeRecycleMapper;
import com.ruoyi.place.mapper.BusinessPlaceMapper;
import com.ruoyi.place.mapper.BusinessPlaceRecycleMapper;
import com.ruoyi.place.service.IBusinessPlaceService;
import com.ruoyi.place.vo.PlaceVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class BusinessPlaceServiceImpl  implements IBusinessPlaceService {

    @Resource
    private BusinessPlaceMapper businessPlaceMapper;
    @Resource
    private BusinessPlaceRecycleMapper businessPlaceRecycleMapper;
    @Resource
    private BusinessAgencyFeeMapper businessAgencyFeeMapper;
    @Resource
    private BusinessAgencyFeeRecycleMapper businessAgencyFeeRecycleMapper;
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
        String placeName=placeVo.getPlaceName();
        System.out.println("statusArr=="+statusArr);
        return businessPlaceMapper.selectBusinessPlaceJoin(userIdArr,statusArr,placeName);
    }

    @Override
    public Integer addPlace(BusinessPlace businessPlace) {
        businessPlace.setIsDelete(true);
        businessPlace.setPlaceStatus(0);
        return businessPlaceMapper.insert(businessPlace);
    }

    @Override
    public Integer delPlace(String placeCode) {
        UpdateWrapper<BusinessPlace> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("place_code",placeCode);
        updateWrapper.set("is_delete",false);
        UpdateWrapper<BusinessAgencyFee> updateWrapper2=new UpdateWrapper<>();
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
        QueryWrapper<BusinessPlace> queryWrapper1=new QueryWrapper<>();
        QueryWrapper<BusinessAgencyFee> queryWrapper2=new QueryWrapper<>();
        queryWrapper1.eq("place_code",placeCode);
        queryWrapper2.eq("place_code",placeCode);
        Integer num1=businessAgencyFeeMapper.delete(queryWrapper2);
        Integer num2=businessPlaceMapper.delete(queryWrapper1);
        return  num1+num2;
    }
    @Override
    public Integer editPlace(BusinessPlace businessPlace, BusinessAgencyFee businessAgencyFee) {
        System.out.println("businessAgencyFee"+businessAgencyFee);
        Integer num1=businessAgencyFeeMapper.updateById(businessAgencyFee);
        Integer num2=businessPlaceMapper.updateById(businessPlace);
        return  num1+num2;
    }
    /*
    *
    * 改变状态
    * */
    @Override
    public Integer editPlace2(BusinessPlace businessPlace) {
        Integer num=businessPlaceMapper.updateById(businessPlace);
        return  num;
    }
    /*
     * 查询数据库里面最后一条数据
     *
     * */
    @Override
    public List<BusinessPlace> selectMaxCode(){
        IPage<BusinessPlace> iPage=new Page<>(1,1);
        return businessPlaceMapper.selectPage(iPage,
                new QueryWrapper<BusinessPlace>()
                        .orderByDesc("place_id")).getRecords();
    }
}
