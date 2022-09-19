package com.ruoyi.place.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.place.entity.BusinessAgencyFeeTgRecycle;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 渠道商代理费 Mapper 接口
 * </p>
 *
 * @author lcc
 * @since 2022-06-07
 */
public interface BusinessAgencyFeeTgRecycleMapper extends BaseMapper<BusinessAgencyFeeTgRecycle> {

    Integer recycle(@Param("placeCode") String placeCode);
}
