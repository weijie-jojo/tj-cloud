package com.ruoyi.project.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.ruoyi.project.domain.vo.SysUserVo;
import com.ruoyi.system.api.domain.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysUserMapper extends MPJBaseMapper<SysUser> {
    @Select("SELECT user_id, role_id  FROM sys_user_role where user_id=#{userId}")
    List<SysUserVo> getRoleByUserId(@Param("userId") Long userId);
    @Select("SELECT user_id, dept_id, user_name, nick_name, user_type, email, phonenumber, sex, avatar, password, status, del_flag, login_ip, login_date, create_by, create_time, update_by, update_time, remark, Employee_number, id_no, post_id, is_admin, pay_check, pay_check_bank, account_card_bank, pwd_reset_time FROM sys_user where user_id=#{userId}")
    SysUserVo getDeptByUserId(@Param("userId") Long userId);
    @Select("SELECT user_id, dept_id, user_name, nick_name, user_type, email, phonenumber, sex, avatar, password, status, del_flag, login_ip, login_date, create_by, create_time, update_by, update_time, remark, Employee_number, id_no, post_id, is_admin, pay_check, pay_check_bank, account_card_bank, pwd_reset_time FROM sys_user where dept_id=#{deptId}")
    List<SysUserVo> getUserByDeptId(@Param("deptId") Integer deptId);
}
