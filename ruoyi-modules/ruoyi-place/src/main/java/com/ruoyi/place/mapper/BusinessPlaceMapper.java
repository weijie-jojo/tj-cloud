package com.ruoyi.place.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.ruoyi.place.entity.BusinessPlace;
import com.ruoyi.place.vo.PlaceVo;
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
public interface BusinessPlaceMapper  extends  MPJBaseMapper<BusinessPlace>{
    @Select("SELECT * FROM business_place where place_name=#{placeName}")
    List<BusinessPlace> getByPlaceName(@Param("placeName") String placeName);

    List<BusinessPlace> selectBusinessPlaceJoin(@Param("userIdStr") List<String> userIdStr);
}
