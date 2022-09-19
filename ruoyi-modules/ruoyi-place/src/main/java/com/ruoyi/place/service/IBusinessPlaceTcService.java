package com.ruoyi.place.service;

import com.ruoyi.place.entity.BusinessAgencyFee;
import com.ruoyi.place.entity.BusinessAgencyFeeTc;
import com.ruoyi.place.entity.BusinessPlace;
import com.ruoyi.place.entity.BusinessPlaceTc;
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

public interface IBusinessPlaceTcService {
    List<BusinessPlace> selectByPage(List<Long> userIdArrs, PlaceVo placeVo);
    Integer addPlace(BusinessPlaceTc businessPlace);
    Integer delPlace(String placeCode);
    Integer editPlace(BusinessPlaceTc businessPlace, BusinessAgencyFeeTc businessAgencyFee);
    Integer editPlace2(BusinessPlaceTc businessPlace);
    List<BusinessPlaceTc> selectMaxCode();
    Integer delPlace2(String placeCode);

}
