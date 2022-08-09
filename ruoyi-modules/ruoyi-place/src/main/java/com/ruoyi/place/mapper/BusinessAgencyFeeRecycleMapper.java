package com.ruoyi.place.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.place.entity.BusinessAgencyFee;
import com.ruoyi.place.entity.BusinessAgencyFeeRecycle;
import com.ruoyi.place.entity.BusinessPlaceRecycle;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 渠道商代理费 Mapper 接口
 * </p>
 *
 * @author lcc
 * @since 2022-06-07
 */
public interface BusinessAgencyFeeRecycleMapper extends BaseMapper<BusinessAgencyFeeRecycle> {

    Integer recycle(@Param("placeCode") String placeCode);
}
