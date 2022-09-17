package com.ruoyi.company.service.impl;

import com.ruoyi.company.domain.SelfLegalPerson;
import com.ruoyi.company.mapper.SelfLegalPersonTcMapper;
import com.ruoyi.company.service.ISelfLegalPersonTcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 个体商户法人Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-06-17
 */
@Service
public class SelfLegalPersonTcServiceImpl implements ISelfLegalPersonTcService
{
    @Autowired
    private SelfLegalPersonTcMapper selfLegalPersonMapper;

    /**
     * 查询个体商户法人列表（不分页）
     *
     * @return 员工信息集合
     */
    @Override
    public List<SelfLegalPerson> selectSelfLegalPerson() {
        return selfLegalPersonMapper.selectSelfLegalPerson();
    }

    /**
     * 查询个体商户法人
     * 
     * @param legalPersonId 个体商户法人主键
     * @return 个体商户法人
     */
    @Override
    public SelfLegalPerson selectSelfLegalPersonByLegalPersonId(Long legalPersonId)
    {
        return selfLegalPersonMapper.selectSelfLegalPersonByLegalPersonId(legalPersonId);
    }

    /**
     * 查询个体商户法人列表
     * 
     * @param selfLegalPerson 个体商户法人
     * @return 个体商户法人
     */
    @Override
    public List<SelfLegalPerson> selectSelfLegalPersonList(SelfLegalPerson selfLegalPerson)
    {
        return selfLegalPersonMapper.selectSelfLegalPersonList(selfLegalPerson);
    }

    /**
     * 新增个体商户法人
     * 
     * @param selfLegalPerson 个体商户法人
     * @return 结果
     */
    @Override
    public int insertSelfLegalPerson(SelfLegalPerson selfLegalPerson)
    {
        return selfLegalPersonMapper.insertSelfLegalPerson(selfLegalPerson);
    }

    /**
     * 修改个体商户法人
     * 
     * @param selfLegalPerson 个体商户法人
     * @return 结果
     */
    @Override
    public int updateSelfLegalPerson(SelfLegalPerson selfLegalPerson)
    {
        return selfLegalPersonMapper.updateSelfLegalPerson(selfLegalPerson);
    }

    /**
     * 批量删除个体商户法人
     * 
     * @param legalPersonIds 需要删除的个体商户法人主键
     * @return 结果
     */
    @Override
    public int deleteSelfLegalPersonByLegalPersonIds(Long[] legalPersonIds)
    {
        return selfLegalPersonMapper.deleteSelfLegalPersonByLegalPersonIds(legalPersonIds);
    }

    /**
     * 删除个体商户法人信息
     * 
     * @param legalPersonId 个体商户法人主键
     * @return 结果
     */
    @Override
    public int deleteSelfLegalPersonByLegalPersonId(Long legalPersonId)
    {
        return selfLegalPersonMapper.deleteSelfLegalPersonByLegalPersonId(legalPersonId);
    }
}
