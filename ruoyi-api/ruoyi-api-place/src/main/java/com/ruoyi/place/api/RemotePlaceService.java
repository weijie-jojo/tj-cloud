package com.ruoyi.place.api;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.place.api.domain.vo.BusinessPlaceVo;
import com.ruoyi.place.api.factory.RemotePlaceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 文件服务
 * 
 * @author ruoyi
 */
@FeignClient(contextId = "remotePlaceService", value = ServiceNameConstants.PLACE_SERVICE)
public interface RemotePlaceService
{
    /**
     * 查询平台渠道商
     *
     * @param placeCode 平台渠道商主键
     * @return 平台渠道商
     */
    @GetMapping("/place/getPlaceByCode/{placeCode}")
    public BusinessPlaceVo getPlaceByCode(@PathVariable("placeCode") String placeCode);
    /**
     * 查询平台渠道商
     *
     * @param placeCode 平台渠道商主键
     * @return 平台渠道商
     */
    @GetMapping("/place/tc/getPlaceByCode/{placeCode}")
    public BusinessPlaceVo getPlaceByCodeTc(@PathVariable("placeCode") String placeCode);
    /**
     * 查询平台渠道商
     *
     * @param placeCode 平台渠道商主键
     * @return 平台渠道商
     */
    @GetMapping("/place/tg/getPlaceByCode/{placeCode}")
    public BusinessPlaceVo getPlaceByCodeTg(@PathVariable("placeCode") String placeCode);
}
