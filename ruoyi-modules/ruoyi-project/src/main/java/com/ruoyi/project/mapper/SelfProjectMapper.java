package com.ruoyi.project.mapper;

import com.ruoyi.project.domain.SelfProject;

import java.util.List;


/**
 * 项目信息Mapper接口
 *
 * @author ruoyi
 * @date 2022-07-06
 */
public interface SelfProjectMapper
{
    /**
     * 获取最后一条数据
     *
     * @return 项目信息
     */
    public List<SelfProject> selectLast();
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
     * 删除项目信息
     *
     * @param projectId 项目信息主键
     * @return 结果
     */
    public int deleteSelfProjectByProjectId(String projectId);

    /**
     * 批量删除项目信息
     *
     * @param projectIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSelfProjectByProjectIds(String[] projectIds);
}
