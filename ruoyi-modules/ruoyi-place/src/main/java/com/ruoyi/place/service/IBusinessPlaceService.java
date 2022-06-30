package com.ruoyi.place.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.place.entity.BusinessAgencyFee;
import com.ruoyi.place.entity.BusinessPlace;
import com.ruoyi.place.qo.PageQo;
import com.ruoyi.place.vo.PlaceVo;

import java.util.List;

/**
 * <p>
 * 平台渠道商 服务类
 * </p>
 *
 * @author lcc
 * @since 2022-06-03
 */

public interface IBusinessPlaceService  {
    IPage<PlaceVo> selectByPage(BusinessPlace businessPlace, PageQo pageQo, String status);
    Integer addPlace(BusinessPlace businessPlace);
    Integer delPlace(String placeCode);
    Integer editPlace(BusinessPlace businessPlace, BusinessAgencyFee businessAgencyFee);
    Integer editPlace2(BusinessPlace businessPlace);
    List<BusinessPlace> selectMaxCode();

}