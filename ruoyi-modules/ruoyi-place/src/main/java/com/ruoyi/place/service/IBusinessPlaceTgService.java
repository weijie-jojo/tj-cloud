package com.ruoyi.place.service;

import com.ruoyi.place.entity.BusinessAgencyFee;
import com.ruoyi.place.entity.BusinessAgencyFeeTg;
import com.ruoyi.place.entity.BusinessPlace;
import com.ruoyi.place.entity.BusinessPlaceTg;
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

public interface IBusinessPlaceTgService {
    List<BusinessPlace> selectByPage(List<Long> userIdArrs,PlaceVo placeVo);
    Integer addPlace(BusinessPlaceTg businessPlace);
    Integer delPlace(String placeCode);
    Integer editPlace(BusinessPlaceTg businessPlace, BusinessAgencyFeeTg businessAgencyFee);
    Integer editPlace2(BusinessPlaceTg businessPlace);
    List<BusinessPlaceTg> selectMaxCode();
    Integer delPlace2(String placeCode);

}
