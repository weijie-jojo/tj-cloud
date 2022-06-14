package com.ruoyi.company.mapper;

import java.util.List;
import com.ruoyi.company.domain.SelfEmployed;

/**
 * 个体商户Mapper接口
 * 
 * @author ruoyi
 * @date 2022-06-14
 */
public interface SelfEmployedMapper 
{
    /**
     * 查询个体商户
     * 
     * @param selfId 个体商户主键
     * @return 个体商户
     */
    public SelfEmployed selectSelfEmployedBySelfId(String selfId);

    /**
     * 查询个体商户列表
     * 
     * @param selfEmployed 个体商户
     * @return 个体商户集合
     */
    public List<SelfEmployed> selectSelfEmployedList(SelfEmployed selfEmployed);

    /**
     * 新增个体商户
     * 
     * @param selfEmployed 个体商户
     * @return 结果
     */
    public int insertSelfEmployed(SelfEmployed selfEmployed);

    /**
     * 修改个体商户
     * 
     * @param selfEmployed 个体商户
     * @return 结果
     */
    public int updateSelfEmployed(SelfEmployed selfEmployed);

    /**
     * 删除个体商户
     * 
     * @param selfId 个体商户主键
     * @return 结果
     */
    public int deleteSelfEmployedBySelfId(String selfId);

    /**
     * 批量删除个体商户
     * 
     * @param selfIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSelfEmployedBySelfIds(String[] selfIds);
}
