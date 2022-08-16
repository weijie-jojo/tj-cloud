package com.ruoyi.project.service.impl;


import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.project.domain.SelfProject;
import com.ruoyi.project.domain.vo.ProjectJoinTicketVo;
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
     * @param projectCode 项目编号
     * @return 项目信息
     */
    @Override
    public List<ProjectJoinTicketVo> selectProjectJoinTicketByCode(String projectCode) {
        return selfProjectMapper.selectProjectJoinTicketByCode(projectCode);
    }

    /*
    *
    * 查询最后一条数据
    * */
    @Override
    public List<SelfProject> selectLast() {
        return selfProjectMapper.selectLast();
    }

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
    public List<SelfProject> selectSelfProjectList(List<Long> userIdArr,SelfProject selfProject)
    {
        return selfProjectMapper.selectSelfProjectList(userIdArr,selfProject);
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

    /**
     * 批量删除项目信息（伪删）
     *
     * @param projectIds 需要删除的项目信息主键
     * @return 结果
     */
    @Override
    public int deleteSelfProjectByProjectIds2(String[] projectIds)
    {
        return selfProjectMapper.deleteSelfProjectByProjectIds2(projectIds);
    }

    /**
     * 删除项目信息信息（伪删）
     *
     * @param projectId 项目信息主键
     * @return 结果
     */
    @Override
    public int deleteSelfProjectByProjectId2(String projectId)
    {
        return selfProjectMapper.deleteSelfProjectByProjectId2(projectId);
    }
    /*
    *
    * 回收
    * */
    @Override
    public int deleteProjectByCode(String selfCode) {
        selfProjectMapper.recycle1(selfCode);
        return selfProjectMapper.deleteProjectByCode(selfCode);
    }
    @Override
    public int deleteCheckByCode(String selfCode) {
        selfProjectMapper.recycle2(selfCode);
        return selfProjectMapper.deleteCheckByCode(selfCode);
    }

}

