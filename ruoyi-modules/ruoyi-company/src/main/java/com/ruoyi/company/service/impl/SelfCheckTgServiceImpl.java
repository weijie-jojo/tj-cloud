package com.ruoyi.company.service.impl;

import com.ruoyi.company.domain.SelfCheck;
import com.ruoyi.company.domain.vo.SelfCheckVo;
import com.ruoyi.company.mapper.SelfCheckMapper;
import com.ruoyi.company.mapper.SelfCheckTgMapper;
import com.ruoyi.company.service.ISelfCheckService;
import com.ruoyi.company.service.ISelfCheckTgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 单据审批结果Service业务层处理
 *
 * @author ruoyi
 * @date 2022-07-14
 */
@Service
public class SelfCheckTgServiceImpl implements ISelfCheckTgService
{
    @Autowired
    private SelfCheckTgMapper selfCheckMapper;

    /**
     * 查询单据审批结果
     *
     * @param id 单据审批结果主键
     * @return 单据审批结果
     */
    @Override
    public SelfCheck selectSelfCheckById(Long id)
    {
        return selfCheckMapper.selectSelfCheckById(id);
    }

    /**
     * 查询单据审批结果列表
     *
     * @param selfCheck 单据审批结果
     * @return 单据审批结果
     */
    @Override
    public List<SelfCheckVo> selectSelfCheckList(SelfCheck selfCheck)
    {
        return selfCheckMapper.selectSelfCheckList(selfCheck);
    }

    /**
     * 新增单据审批结果
     *
     * @param selfCheck 单据审批结果
     * @return 结果
     */
    @Override
    public int insertSelfCheck(SelfCheck selfCheck)
    {
        return selfCheckMapper.insertSelfCheck(selfCheck);
    }

    /**
     * 修改单据审批结果
     *
     * @param selfCheck 单据审批结果
     * @return 结果
     */
    @Override
    public int updateSelfCheck(SelfCheck selfCheck)
    {
        return selfCheckMapper.updateSelfCheck(selfCheck);
    }

    /**
     * 批量删除单据审批结果
     *
     * @param ids 需要删除的单据审批结果主键
     * @return 结果
     */
    @Override
    public int deleteSelfCheckByIds(Long[] ids)
    {
        return selfCheckMapper.deleteSelfCheckByIds(ids);
    }

    /**
     * 删除单据审批结果信息
     *
     * @param id 单据审批结果主键
     * @return 结果
     */
    @Override
    public int deleteSelfCheckById(Long id)
    {
        return selfCheckMapper.deleteSelfCheckById(id);
    }
}
