package com.ruoyi.company.mapper;

import com.ruoyi.company.domain.BusinessPlace;

import java.util.List;

/**
 * 平台渠道商Mapper接口
 * 
 * @author ruoyi
 * @date 2022-06-18
 */
public interface BusinessPlaceTgMapper
{
    /**
     * 查询平台渠道商
     *
     * @param userId 平台渠道商主键
     * @return 平台渠道商
     */
    public List<BusinessPlace> selectBusinessPlaceByUserId(int userId);

    /**
     * 查询平台渠道商
     * 
     * @param placeId 平台渠道商主键
     * @return 平台渠道商
     */
    public BusinessPlace selectBusinessPlaceByPlaceId(String placeId);

    /**
     * 查询平台渠道商列表
     * 
     * @param businessPlace 平台渠道商
     * @return 平台渠道商集合
     */
    public List<BusinessPlace> selectBusinessPlaceList(BusinessPlace businessPlace);

    /**
     * 新增平台渠道商
     * 
     * @param businessPlace 平台渠道商
     * @return 结果
     */
    public int insertBusinessPlace(BusinessPlace businessPlace);

    /**
     * 修改平台渠道商
     * 
     * @param businessPlace 平台渠道商
     * @return 结果
     */
    public int updateBusinessPlace(BusinessPlace businessPlace);

    /**
     * 删除平台渠道商
     * 
     * @param placeId 平台渠道商主键
     * @return 结果
     */
    public int deleteBusinessPlaceByPlaceId(String placeId);

    /**
     * 批量删除平台渠道商
     * 
     * @param placeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusinessPlaceByPlaceIds(String[] placeIds);
}
