package com.ruoyi.place.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.ruoyi.place.entity.BusinessPlaceRecycle;
import com.ruoyi.place.entity.SelfEmployed;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 平台渠道商 Mapper 接口
 * </p>
 *
 * @author lcc
 * @since 2022-06-03
 */
public interface SelfEmployedMapper extends  MPJBaseMapper<SelfEmployed>{
    @Select("SELECT *FROM self_employed where place_code=#{placeCode}")
    List<SelfEmployed> selectEmployedByPlaceCode(@Param("placeCode") String placeCode);
}
