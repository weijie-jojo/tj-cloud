package com.ruoyi.company.mapper;



import com.ruoyi.company.domain.SelfLegalPerson;

import java.util.List;

/**
 * 个体商户法人Mapper接口
 * 
 * @author ruoyi
 * @date 2022-06-17
 */
public interface SelfLegalPersonMapper 
{
    /**
     * 查询个体商户法人列表（不分页）
     *
     * @return 员工信息集合
     */
    public List<SelfLegalPerson> selectSelfLegalPerson();

    /**
     * 查询个体商户法人
     * 
     * @param legalPersonId 个体商户法人主键
     * @return 个体商户法人
     */
    public SelfLegalPerson selectSelfLegalPersonByLegalPersonId(Long legalPersonId);

    /**
     * 查询个体商户法人列表
     * 
     * @param selfLegalPerson 个体商户法人
     * @return 个体商户法人集合
     */
    public List<SelfLegalPerson> selectSelfLegalPersonList(SelfLegalPerson selfLegalPerson);

    /**
     * 新增个体商户法人
     * 
     * @param selfLegalPerson 个体商户法人
     * @return 结果
     */
    public int insertSelfLegalPerson(SelfLegalPerson selfLegalPerson);

    /**
     * 修改个体商户法人
     * 
     * @param selfLegalPerson 个体商户法人
     * @return 结果
     */
    public int updateSelfLegalPerson(SelfLegalPerson selfLegalPerson);

    /**
     * 删除个体商户法人
     * 
     * @param legalPersonId 个体商户法人主键
     * @return 结果
     */
    public int deleteSelfLegalPersonByLegalPersonId(Long legalPersonId);

    /**
     * 批量删除个体商户法人
     * 
     * @param legalPersonIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSelfLegalPersonByLegalPersonIds(Long[] legalPersonIds);
}
