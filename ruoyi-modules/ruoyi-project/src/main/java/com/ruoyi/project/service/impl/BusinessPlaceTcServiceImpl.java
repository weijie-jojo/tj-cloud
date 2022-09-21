package com.ruoyi.project.service.impl;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.project.domain.BusinessPlace;
import com.ruoyi.project.mapper.BusinessPlaceTcMapper;
import com.ruoyi.project.service.IBusinessPlaceTcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 平台渠道商Service业务层处理
 *
 * @author ruoyi
 * @date 2022-07-06
 */
@Service
public class BusinessPlaceTcServiceImpl implements IBusinessPlaceTcService
{
    @Autowired
    private BusinessPlaceTcMapper businessPlaceMapper;

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
     * 查询平台渠道商
     *
     * @param userIdArr 平台渠道商主键
     * @return 平台渠道商
     */
    @Override
    public List<BusinessPlace> selectBusinessPlaceByUserId(List<Long> userIdArr)
    {
        return businessPlaceMapper.selectBusinessPlaceByUserId(userIdArr);
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

    /**
     * 批量删除平台渠道商（伪删）
     *
     * @param placeIds 需要删除的平台渠道商主键
     * @return 结果
     */
    @Override
    public int deleteBusinessPlaceByPlaceIds2(String[] placeIds)
    {
        return businessPlaceMapper.deleteBusinessPlaceByPlaceIds2(placeIds);
    }

    /**
     * 删除平台渠道商信息（伪删）
     *
     * @param placeId 平台渠道商主键
     * @return 结果
     */
    @Override
    public int deleteBusinessPlaceByPlaceId2(String placeId)
    {
        return businessPlaceMapper.deleteBusinessPlaceByPlaceId2(placeId);
    }
}
