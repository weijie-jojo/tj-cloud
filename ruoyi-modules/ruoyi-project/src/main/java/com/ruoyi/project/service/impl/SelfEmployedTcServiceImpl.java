package com.ruoyi.project.service.impl;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.project.domain.SelfEmployed;
import com.ruoyi.project.domain.vo.SelfEmployedVo;
import com.ruoyi.project.mapper.SelfEmployedMapper;
import com.ruoyi.project.mapper.SelfEmployedTcMapper;
import com.ruoyi.project.service.ISelfEmployedService;
import com.ruoyi.project.service.ISelfEmployedTcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 个体商户Service业务层处理
 *
 * @author ruoyi
 * @date 2022-07-06
 */
@Service
public class SelfEmployedTcServiceImpl implements ISelfEmployedTcService
{
    @Autowired
    private SelfEmployedTcMapper selfEmployedMapper;

    /**
     * 查询个体商户列表(根据业务员跟行业类型)
     *
     * @param userIdArr,industryType
     * @return 个体商户集合
     */
    @Override
    public List<SelfEmployedVo> selectSelfEmployedByUser(List<Long> userIdArr, String industryType) {
        return selfEmployedMapper.selectSelfEmployedByUser(userIdArr,industryType);
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
        selfEmployed.setCreateTime(DateUtils.getNowDate());
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
        selfEmployed.setUpdateTime(DateUtils.getNowDate());
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
}
