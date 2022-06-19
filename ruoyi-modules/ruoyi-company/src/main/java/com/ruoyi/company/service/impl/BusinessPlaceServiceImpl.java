package com.ruoyi.company.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.company.mapper.BusinessPlaceMapper;
import com.ruoyi.company.domain.BusinessPlace;
import com.ruoyi.company.service.IBusinessPlaceService;

/**
 * 平台渠道商Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-06-18
 */
@Service
public class BusinessPlaceServiceImpl implements IBusinessPlaceService 
{
    @Autowired
    private BusinessPlaceMapper businessPlaceMapper;

    /**
     * 查询平台渠道商
     *
     * @param userId 平台渠道商主键
     * @return 平台渠道商
     */
    @Override
    public List<BusinessPlace> selectBusinessPlaceByUserId(int userId)
    {
        return businessPlaceMapper.selectBusinessPlaceByUserId(userId);
    }

    /**
     * 查询平台渠道商
     * 
     * @param placeId 平台渠道商主键
     * @return 平台渠道商
     */
    @Override
    public BusinessPlace selectBusinessPlaceByPlaceId(String placeId)
    {
        return businessPlaceMapper.selectBusinessPlaceByPlaceId(placeId);
    }

    /**
     * 查询平台渠道商列表
     * 
     * @param businessPlace 平台渠道商
     * @return 平台渠道商
     */
    @Override
    public List<BusinessPlace> selectBusinessPlaceList(BusinessPlace businessPlace)
    {
        return businessPlaceMapper.selectBusinessPlaceList(businessPlace);
    }

    /**
     * 新增平台渠道商
     * 
     * @param businessPlace 平台渠道商
     * @return 结果
     */
    @Override
    public int insertBusinessPlace(BusinessPlace businessPlace)
    {
        businessPlace.setCreateTime(DateUtils.getNowDate());
        return businessPlaceMapper.insertBusinessPlace(businessPlace);
    }

    /**
     * 修改平台渠道商
     * 
     * @param businessPlace 平台渠道商
     * @return 结果
     */
    @Override
    public int updateBusinessPlace(BusinessPlace businessPlace)
    {
        businessPlace.setUpdateTime(DateUtils.getNowDate());
        return businessPlaceMapper.updateBusinessPlace(businessPlace);
    }

    /**
     * 批量删除平台渠道商
     * 
     * @param placeIds 需要删除的平台渠道商主键
     * @return 结果
     */
    @Override
    public int deleteBusinessPlaceByPlaceIds(String[] placeIds)
    {
        return businessPlaceMapper.deleteBusinessPlaceByPlaceIds(placeIds);
    }

    /**
     * 删除平台渠道商信息
     * 
     * @param placeId 平台渠道商主键
     * @return 结果
     */
    @Override
    public int deleteBusinessPlaceByPlaceId(String placeId)
    {
        return businessPlaceMapper.deleteBusinessPlaceByPlaceId(placeId);
    }
}
