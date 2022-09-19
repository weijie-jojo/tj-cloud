package com.ruoyi.place.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.ruoyi.place.entity.BusinessPlaceTcRecycle;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 平台渠道商 Mapper 接口
 * </p>
 *
 * @author lcc
 * @since 2022-06-03
 */
public interface BusinessPlaceTcRecycleMapper extends  MPJBaseMapper<BusinessPlaceTcRecycle>{

    Integer recycle(@Param("placeCode") String placeCode);
}
