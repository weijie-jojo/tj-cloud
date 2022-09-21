package com.ruoyi.project.service;

import com.ruoyi.project.domain.SelfProject;
import com.ruoyi.project.domain.vo.ProjectJoinTicketVo;

import java.util.List;

/**
 * 项目信息Service接口
 *
 * @author ruoyi
 * @date 2022-07-06
 */
public interface ISelfProjectTcService
{
    /**
     * 查询项目信息
     *
     * @param projectCode 项目编号
     * @return 项目信息
     */
    public ProjectJoinTicketVo selectProjectJoinTicketByCode(String projectCode);
    /**
     * 查询最后一条数据
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
    public List<SelfProject> selectSelfProjectList(List<Long> userIdArr,SelfProject selfProject);

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

    /**
     * 批量删除项目信息（伪删）
     *
     * @param projectIds 需要删除的项目信息主键集合
     * @return 结果
     */
    public int deleteSelfProjectByProjectIds2(String[] projectIds);

    /**
     * 删除项目信息信息
     *
     * @param projectId 项目信息主键（伪删）
     * @return 结果
     */
    public int deleteSelfProjectByProjectId2(String projectId);



    /**
     * 回收
     *
     */
    public int deleteProjectByCode(String selfCode);


    public int deleteCheckByCode(String selfCode);

}

