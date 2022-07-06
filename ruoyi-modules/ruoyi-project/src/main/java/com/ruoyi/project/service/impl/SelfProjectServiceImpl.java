package com.ruoyi.project.service.impl;


import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.project.domain.SelfProject;
import com.ruoyi.project.mapper.SelfProjectMapper;
import com.ruoyi.project.service.ISelfProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-07-06
 */
@Service
public class SelfProjectServiceImpl implements ISelfProjectService
{
    @Autowired
    private SelfProjectMapper selfProjectMapper;

    /**
     * 查询项目信息
     *
     * @param projectId 项目信息主键
     * @return 项目信息
     */
    @Override
    public SelfProject selectSelfProjectByProjectId(String projectId)
    {
        return selfProjectMapper.selectSelfProjectByProjectId(projectId);
    }

    /**
     * 查询项目信息列表
     *
     * @param selfProject 项目信息
     * @return 项目信息
     */
    @Override
    public List<SelfProject> selectSelfProjectList(SelfProject selfProject)
    {
        return selfProjectMapper.selectSelfProjectList(selfProject);
    }

    /**
     * 新增项目信息
     *
     * @param selfProject 项目信息
     * @return 结果
     */
    @Override
    public int insertSelfProject(SelfProject selfProject)
    {
        selfProject.setCreateTime(DateUtils.getNowDate());
        return selfProjectMapper.insertSelfProject(selfProject);
    }

    /**
     * 修改项目信息
     *
     * @param selfProject 项目信息
     * @return 结果
     */
    @Override
    public int updateSelfProject(SelfProject selfProject)
    {
        selfProject.setUpdateTime(DateUtils.getNowDate());
        return selfProjectMapper.updateSelfProject(selfProject);
    }

    /**
     * 批量删除项目信息
     *
     * @param projectIds 需要删除的项目信息主键
     * @return 结果
     */
    @Override
    public int deleteSelfProjectByProjectIds(String[] projectIds)
    {
        return selfProjectMapper.deleteSelfProjectByProjectIds(projectIds);
    }

    /**
     * 删除项目信息信息
     *
     * @param projectId 项目信息主键
     * @return 结果
     */
    @Override
    public int deleteSelfProjectByProjectId(String projectId)
    {
        return selfProjectMapper.deleteSelfProjectByProjectId(projectId);
    }
}

