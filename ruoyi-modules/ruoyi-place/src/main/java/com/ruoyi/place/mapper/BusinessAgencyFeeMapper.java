package com.ruoyi.place.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.place.entity.BusinessAgencyFee;
import com.ruoyi.place.entity.vo.BusinessPlaceVo;

/**
 * <p>
 * 渠道商代理费 Mapper 接口
 * </p>
 *
 * @author lcc
 * @since 2022-06-07
 */
public interface BusinessAgencyFeeMapper extends BaseMapper<BusinessAgencyFee> {

    /**
     * 查询平台渠道商
     *
     * @param placeCode 平台渠道商主键
     * @return 平台渠道商
     */
    public BusinessPlaceVo selectBusinessPlaceByPlaceCode(String placeCode);

}
