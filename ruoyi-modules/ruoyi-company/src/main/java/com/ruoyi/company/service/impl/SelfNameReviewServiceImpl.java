package com.ruoyi.company.service.impl;

import java.util.List;

import com.ruoyi.company.domain.SelfEmployed;
import com.ruoyi.company.domain.vo.SelfEmployedVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.company.mapper.SelfNameReviewMapper;
import com.ruoyi.company.domain.SelfNameReview;
import com.ruoyi.company.service.ISelfNameReviewService;

/**
 * 个体户名字审核Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-06-16
 */
@Service
public class SelfNameReviewServiceImpl implements ISelfNameReviewService 
{
    @Autowired
    private SelfNameReviewMapper selfNameReviewMapper;

    @Override
    public SelfNameReview selectSelfNameReviewBySelfCode(String selfCode) {
        return selfNameReviewMapper.selectSelfNameReviewBySelfCode(selfCode);
    }

    /*
     * 查询数据库里面最后一条数据
     *
     * */
    @Override
    public  List<SelfNameReview> selectMaxCode(){
        return selfNameReviewMapper.selectMaxCode();
    }

    /**
     * 查询个体户名字审核
     * 
     * @param selfId 个体户名字审核主键
     * @return 个体户名字审核
     */
    @Override
    public SelfNameReview selectSelfNameReviewBySelfId(Long selfId)
    {
        return selfNameReviewMapper.selectSelfNameReviewBySelfId(selfId);
    }

    /**
     * 查询个体户名字审核列表
     * 
     * @param selfNameReview 个体户名字审核
     * @return 个体户名字审核
     */
    @Override
    public List<SelfNameReview> selectSelfNameReviewList(SelfNameReview selfNameReview)
    {
        return selfNameReviewMapper.selectSelfNameReviewList(selfNameReview);
    }
    /**
     * 查询个体户名字审核列表(连表)
     *
     * @param selfEmployedVo 个体户名字审核
     * @return 个体户名字审核集合
     */
    @Override
    public List<SelfEmployedVo> selectSelfNameReviewJoin(SelfEmployedVo selfEmployedVo) {
        return selfNameReviewMapper.selectSelfNameReviewJoin(selfEmployedVo);
    }

    /**
     * 新增个体户名字审核
     * 
     * @param selfNameReview 个体户名字审核
     * @return 结果
     */
    @Override
    public int insertSelfNameReview(SelfNameReview selfNameReview)
    {
        return selfNameReviewMapper.insertSelfNameReview(selfNameReview);
    }

    /**
     * 修改个体户名字审核
     * 
     * @param selfNameReview 个体户名字审核
     * @return 结果
     */
    @Override
    public int updateSelfNameReview(SelfNameReview selfNameReview)
    {
        return selfNameReviewMapper.updateSelfNameReview(selfNameReview);
    }

    /**
     * 批量删除个体户名字审核
     * 
     * @param selfIds 需要删除的个体户名字审核主键
     * @return 结果
     */
    @Override
    public int deleteSelfNameReviewBySelfIds(Long[] selfIds)
    {
        return selfNameReviewMapper.deleteSelfNameReviewBySelfIds(selfIds);
    }

    /**
     * 删除个体户名字审核信息
     * 
     * @param selfId 个体户名字审核主键
     * @return 结果
     */
    @Override
    public int deleteSelfNameReviewBySelfId(Long selfId)
    {
        return selfNameReviewMapper.deleteSelfNameReviewBySelfId(selfId);
    }
}
