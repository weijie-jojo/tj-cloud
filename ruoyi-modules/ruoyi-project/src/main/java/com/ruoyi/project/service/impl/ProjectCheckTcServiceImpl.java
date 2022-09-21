package com.ruoyi.project.service.impl;

import com.ruoyi.project.domain.ProjectCheck;
import com.ruoyi.project.domain.vo.ProjectCheckVo;
import com.ruoyi.project.mapper.ProjectCheckTcMapper;
import com.ruoyi.project.service.IProjectCheckTcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 个体户审批进度Service业务层处理
 *
 * @author ruoyi
 * @date 2022-08-01
 */
@Service
public class ProjectCheckTcServiceImpl implements IProjectCheckTcService
{
    @Autowired
    private ProjectCheckTcMapper projectCheckMapper;

    /**
     * 查询个体户审批进度
     *
     * @param id 个体户审批进度主键
     * @return 个体户审批进度
     */
    @Override
    public ProjectCheck selectProjectCheckById(Long id)
    {
        return projectCheckMapper.selectProjectCheckById(id);
    }

    /**
     * 查询个体户审批进度列表
     *
     * @param projectCheck 个体户审批进度
     * @return 个体户审批进度
     */
    @Override
    public List<ProjectCheckVo> selectProjectCheckList(ProjectCheck projectCheck,List<Long> projectTypes)
    {
        return projectCheckMapper.selectProjectCheckList(projectCheck,projectTypes);
    }

    /**
     * 新增个体户审批进度
     *
     * @param projectCheck 个体户审批进度
     * @return 结果
     */
    @Override
    public int insertProjectCheck(ProjectCheck projectCheck)
    {
        return projectCheckMapper.insertProjectCheck(projectCheck);
    }

    /**
     * 修改个体户审批进度
     *
     * @param projectCheck 个体户审批进度
     * @return 结果
     */
    @Override
    public int updateProjectCheck(ProjectCheck projectCheck)
    {
        return projectCheckMapper.updateProjectCheck(projectCheck);
    }

    /**
     * 批量删除个体户审批进度
     *
     * @param ids 需要删除的个体户审批进度主键
     * @return 结果
     */
    @Override
    public int deleteProjectCheckByIds(Long[] ids)
    {
        return projectCheckMapper.deleteProjectCheckByIds(ids);
    }

    /**
     * 删除个体户审批进度信息
     *
     * @param id 个体户审批进度主键
     * @return 结果
     */
    @Override
    public int deleteProjectCheckById(Long id)
    {
        return projectCheckMapper.deleteProjectCheckById(id);
    }
}

