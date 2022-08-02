package com.ruoyi.project.mapper;

import java.util.List;
import com.ruoyi.project.domain.ProjectCheck;
import com.ruoyi.project.domain.vo.ProjectCheckVo;

/**
 * 个体户审批进度Mapper接口
 *
 * @author ruoyi
 * @date 2022-08-01
 */
public interface ProjectCheckMapper
{
    /**
     * 查询个体户审批进度
     *
     * @param id 个体户审批进度主键
     * @return 个体户审批进度
     */
    public ProjectCheck selectProjectCheckById(Long id);

    /**
     * 查询个体户审批进度列表
     *
     * @param projectCheck 个体户审批进度
     * @return 个体户审批进度集合
     */
    public List<ProjectCheckVo> selectProjectCheckList(ProjectCheck projectCheck);

    /**
     * 新增个体户审批进度
     *
     * @param projectCheck 个体户审批进度
     * @return 结果
     */
    public int insertProjectCheck(ProjectCheck projectCheck);

    /**
     * 修改个体户审批进度
     *
     * @param projectCheck 个体户审批进度
     * @return 结果
     */
    public int updateProjectCheck(ProjectCheck projectCheck);

    /**
     * 删除个体户审批进度
     *
     * @param id 个体户审批进度主键
     * @return 结果
     */
    public int deleteProjectCheckById(Long id);

    /**
     * 批量删除个体户审批进度
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectCheckByIds(Long[] ids);
}
