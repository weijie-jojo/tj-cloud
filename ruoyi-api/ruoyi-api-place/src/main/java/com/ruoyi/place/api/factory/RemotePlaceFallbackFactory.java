package com.ruoyi.place.api.factory;

import com.ruoyi.place.api.RemotePlaceService;
import com.ruoyi.place.api.domain.vo.BusinessPlaceVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 文件服务降级处理
 * 
 * @author ruoyi
 */
@Component
public class RemotePlaceFallbackFactory implements FallbackFactory<RemotePlaceService>
{
    private static final Logger log = LoggerFactory.getLogger(RemotePlaceFallbackFactory.class);

    @Override
    public RemotePlaceService create(Throwable throwable)
    {
        log.error("客户服务调用失败:{}", throwable.getMessage());
        return new RemotePlaceService()
        {
            @Override
            public BusinessPlaceVo getPlaceByCode(String placeVo) {
                return null;
            }

            @Override
            public BusinessPlaceVo getPlaceByCodeTc(String placeCode) {
                return null;
            }

            @Override
            public BusinessPlaceVo getPlaceByCodeTg(String placeCode) {
                return null;
            }

        };
    }
}
