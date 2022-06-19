package com.ruoyi.company.mapper;

import java.util.List;
import com.ruoyi.company.domain.SelfNameReview;

/**
 * 个体户名字审核Mapper接口
 * 
 * @author ruoyi
 * @date 2022-06-16
 */
public interface SelfNameReviewMapper 
{
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
     * 删除个体户名字审核
     * 
     * @param selfId 个体户名字审核主键
     * @return 结果
     */
    public int deleteSelfNameReviewBySelfId(Long selfId);

    /**
     * 批量删除个体户名字审核
     * 
     * @param selfIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSelfNameReviewBySelfIds(Long[] selfIds);
}
