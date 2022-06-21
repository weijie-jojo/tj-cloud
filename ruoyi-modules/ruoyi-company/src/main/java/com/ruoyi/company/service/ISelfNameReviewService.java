package com.ruoyi.company.service;

import java.util.List;

import com.ruoyi.company.domain.SelfEmployed;
import com.ruoyi.company.domain.SelfNameReview;

/**
 * 个体户名字审核Service接口
 * 
 * @author ruoyi
 * @date 2022-06-16
 */
public interface ISelfNameReviewService 
{
    /**
     * 查询数据库里面最后一条数据
     *
     * @return 个体商户集合
     */
    public List<SelfEmployed> selectMaxCode();
    /**
     * 查询个体户名字审核
     * 
     * @param selfId 个体户名字审核主键
     * @return 个体户名字审核
     */
    public SelfNameReview selectSelfNameReviewBySelfId(Long selfId);

    /**
     * 查询个体户名字审核列表
     * 
     * @param selfNameReview 个体户名字审核
     * @return 个体户名字审核集合
     */
    public List<SelfNameReview> selectSelfNameReviewList(SelfNameReview selfNameReview);

    /**
     * 新增个体户名字审核
     * 
     * @param selfNameReview 个体户名字审核
     * @return 结果
     */
    public int insertSelfNameReview(SelfNameReview selfNameReview);

    /**
     * 修改个体户名字审核
     * 
     * @param selfNameReview 个体户名字审核
     * @return 结果
     */
    public int updateSelfNameReview(SelfNameReview selfNameReview);

    /**
     * 批量删除个体户名字审核
     * 
     * @param selfIds 需要删除的个体户名字审核主键集合
     * @return 结果
     */
    public int deleteSelfNameReviewBySelfIds(Long[] selfIds);

    /**
     * 删除个体户名字审核信息
     * 
     * @param selfId 个体户名字审核主键
     * @return 结果
     */
    public int deleteSelfNameReviewBySelfId(Long selfId);
}
