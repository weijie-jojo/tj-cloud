package com.ruoyi.project.service;

import java.util.List;
import com.ruoyi.project.domain.SelfProject;

/**
 * 项目信息Service接口
 *
 * @author ruoyi
 * @date 2022-07-06
 */
public interface ISelfProjectService
{
    /**
     * 查询项目信息
     *
     * @param projectId 项目信息主键
     * @return 项目信息
     */
    public SelfProject selectSelfProjectByProjectId(String projectId);

    /**
     * 查询项目信息列表
     *
     * @param selfProject 项目信息
     * @return 项目信息集合
     */
    public List<SelfProject> selectSelfProjectList(SelfProject selfProject);

    /**
     * 新增项目信息
     *
     * @param selfProject 项目信息
     * @return 结果
     */
    public int insertSelfProject(SelfProject selfProject);

    /**
     * 修改项目信息
     *
     * @param selfProject 项目信息
     * @return 结果
     */
    public int updateSelfProject(SelfProject selfProject);

    /**
     * 批量删除项目信息
     *
     * @param projectIds 需要删除的项目信息主键集合
     * @return 结果
     */
    public int deleteSelfProjectByProjectIds(String[] projectIds);

    /**
     * 删除项目信息信息
     *
     * @param projectId 项目信息主键
     * @return 结果
     */
    public int deleteSelfProjectByProjectId(String projectId);
}

