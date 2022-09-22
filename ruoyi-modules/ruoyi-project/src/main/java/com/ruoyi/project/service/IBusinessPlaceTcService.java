package com.ruoyi.project.service;

import com.ruoyi.project.domain.BusinessPlace;
import com.ruoyi.project.domain.vo.BusinessPlaceVo;

import java.util.List;

/**
 * 平台渠道商Service接口
 *
 * @author ruoyi
 * @date 2022-07-06
 */
public interface IBusinessPlaceTcService
{
    /**
     * 查询平台渠道商
     *
     * @param placeId 平台渠道商主键
     * @return 平台渠道商
     */
    public BusinessPlace selectBusinessPlaceByPlaceId(String placeId);

    /**
     * 查询平台渠道商
     *
     * @param placeCode 平台渠道商主键
     * @return 平台渠道商
     */
    public BusinessPlaceVo selectBusinessPlaceByPlaceCode(String placeCode);

    /**
     * 查询平台渠道商
     *
     * @param userIdArr 平台渠道商主键
     * @return 平台渠道商
     */
    public List<BusinessPlace> selectBusinessPlaceByUserId(List<Long> userIdArr);

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
     * 批量删除平台渠道商
     *
     * @param placeIds 需要删除的平台渠道商主键集合
     * @return 结果
     */
    public int deleteBusinessPlaceByPlaceIds(String[] placeIds);

    /**
     * 删除平台渠道商信息
     *
     * @param placeId 平台渠道商主键
     * @return 结果
     */
    public int deleteBusinessPlaceByPlaceId(String placeId);


    /**
     * 批量删除平台渠道商(伪删)
     *
     * @param placeIds 需要删除的平台渠道商主键集合
     * @return 结果
     */
    public int deleteBusinessPlaceByPlaceIds2(String[] placeIds);

    /**
     * 删除平台渠道商信息(伪删)
     *
     * @param placeId 平台渠道商主键
     * @return 结果
     */
    public int deleteBusinessPlaceByPlaceId2(String placeId);
}
