package com.ruoyi.company.service.impl;

import java.util.List;

import com.ruoyi.company.domain.SelfEmployed;
import com.ruoyi.company.domain.vo.SelfEmployedVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.company.mapper.SelfEmployedMapper;
import com.ruoyi.company.service.ISelfEmployedService;

/**
 * 个体商户Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-06-14
 */
@Service
public class SelfEmployedServiceImpl implements ISelfEmployedService 
{
    @Autowired
    private SelfEmployedMapper selfEmployedMapper;

    /**
     * 连表selfNameReview查询
     *
     * @param selfEmployedVo 个体商户
     * @return 个体商户
     */
    @Override
    public List<SelfEmployedVo> selectEmployedJoinReview(SelfEmployedVo selfEmployedVo) {
        return selfEmployedMapper.selectEmployedJoinReview(selfEmployedVo);
    }

    /**
     * 连表selfNameReview查询（完结）
     *
     * @param selfEmployedVo 个体商户
     * @return 个体商户
     */
    @Override
    public List<SelfEmployedVo> selectEmployedJoinEnd(SelfEmployedVo selfEmployedVo) {
        return selfEmployedMapper.selectEmployedJoinEnd(selfEmployedVo);
    }

    /**
     * 查询个体商户
     * 
     * @param selfId 个体商户主键
     * @return 个体商户
     */
    @Override
    public SelfEmployed selectSelfEmployedBySelfId(String selfId)
    {
        return selfEmployedMapper.selectSelfEmployedBySelfId(selfId);
    }

    /**
     * 查询个体商户列表
     * 
     * @param selfEmployed 个体商户
     * @return 个体商户
     */
    @Override
    public List<SelfEmployed> selectSelfEmployedList(SelfEmployed selfEmployed)
    {
        return selfEmployedMapper.selectSelfEmployedList(selfEmployed);
    }

    /**
     * 新增个体商户
     * 
     * @param selfEmployed 个体商户
     * @return 结果
     */
    @Override
    public int insertSelfEmployed(SelfEmployed selfEmployed)
    {
        return selfEmployedMapper.insertSelfEmployed(selfEmployed);
    }

    /**
     * 修改个体商户
     * 
     * @param selfEmployed 个体商户
     * @return 结果
     */
    @Override
    public int updateSelfEmployed(SelfEmployed selfEmployed)
    {
        return selfEmployedMapper.updateSelfEmployed(selfEmployed);
    }

    /**
     * 批量删除个体商户
     * 
     * @param selfIds 需要删除的个体商户主键
     * @return 结果
     */
    @Override
    public int deleteSelfEmployedBySelfIds(String[] selfIds)
    {
        return selfEmployedMapper.deleteSelfEmployedBySelfIds(selfIds);
    }

    /**
     * 删除个体商户信息
     * 
     * @param selfId 个体商户主键
     * @return 结果
     */
    @Override
    public int deleteSelfEmployedBySelfId(String selfId)
    {
        return selfEmployedMapper.deleteSelfEmployedBySelfId(selfId);
    }

    /**
     * 根据selfCode删除个体户名字审核
     *
     * @param selfCode 需要删除的数据主键
     * @return 结果
     */
    @Override
    public int deleteSelfNameReviewBySelfCode(String selfCode)
    {
        return selfEmployedMapper.deleteSelfNameReviewBySelfCode(selfCode);
    }

    /**
     * 根据selfCode删除个体商户法人
     *
     * @param selfCode 需要删除的数据主键
     * @return 结果
     */
    @Override
    public int deleteSelfLegalPersonBySelfCode(String selfCode)
    {
        return selfEmployedMapper.deleteSelfLegalPersonBySelfCode(selfCode);
    }

    /**
     * 根据selfCode删除个体户注册申请信息
     *
     * @param selfCode 需要删除的数据主键
     * @return 结果
     */
    @Override
    public int deleteSelfApplicationInfoBySelfCode(String selfCode)
    {
        return selfEmployedMapper.deleteSelfApplicationInfoBySelfCode(selfCode);
    }

}
