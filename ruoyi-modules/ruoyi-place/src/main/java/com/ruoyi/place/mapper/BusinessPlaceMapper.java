package com.ruoyi.place.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.ruoyi.place.entity.BusinessPlace;
import com.ruoyi.place.entity.BusinessPlaceRecycle;
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

    @Select("select place_id, place_code, place_name, place_alias_name, place_type, place_linkman, place_tel, place_email, place_open_bank, place_bank_account, place_star_level, place_status, regist_time, user_id, is_delete, remark, create_time, create_by, update_by, update_time, user_name, place_alias, parent_id from business_place where place_alias_name=#{placeAliasName}")
    List<BusinessPlace> getByPlaceAliasName(@Param("placeAliasName") String placeAliasName);

    List<BusinessPlace> selectBusinessPlaceJoin(@Param("userIdArr") List<Long> userIdArr,
                                                @Param("statusArr") String[] statusArr,
                                                @Param("placeName") String placeName);
}
