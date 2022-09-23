package com.ruoyi.place.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.place.entity.BusinessAgencyFee;
import com.ruoyi.place.entity.BusinessPlace;
import com.ruoyi.place.entity.vo.BusinessPlaceVo;
import com.ruoyi.place.qo.PageQo;
import com.ruoyi.place.vo.BusinessAgencyFeeVo;
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
    List<BusinessPlace> selectByPage(List<Long> userIdArrs,PlaceVo placeVo);
    Integer addPlace(BusinessPlace businessPlace);
    Integer delPlace(String placeCode);
    Integer editPlace(BusinessPlace businessPlace, BusinessAgencyFee businessAgencyFee);
    Integer editPlace2(BusinessPlace businessPlace);
    List<BusinessPlace> selectMaxCode();
    Integer delPlace2(String placeCode);

    /**
     * 查询平台渠道商
     *
     * @param placeCode 平台渠道商主键
     * @return 平台渠道商
     */
    public BusinessPlaceVo selectBusinessPlaceByPlaceCode(String placeCode);

}
