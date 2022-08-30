package com.ruoyi.invoice.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.ruoyi.invoice.pojo.SysUser;
import com.ruoyi.invoice.vo.SysUserVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysUserMapper extends MPJBaseMapper<SysUser> {
    @Select("SELECT user_id, role_id  FROM sys_user_role where user_id=#{userId}")
    List<SysUserVo> getRoleByUserId(@Param("userId") Long userId);
    @Select("SELECT user_id, dept_id, user_name, nick_name, user_type, email, phonenumber, sex, avatar, password, status, del_flag, login_ip, login_date, create_by, create_time, update_by, update_time, remark, Employee_number, id_no, post_id, is_admin, pay_check, pay_check_bank, account_card_bank, pwd_reset_time FROM sys_user where user_id=#{userId}")
    SysUserVo getDeptByUserId(@Param("userId") Long userId);
    @Select("SELECT user_id, dept_id, user_name, nick_name, user_type, email, phonenumber, sex, avatar, password, status, del_flag, login_ip, login_date, create_by, create_time, update_by, update_time, remark, Employee_number, id_no, post_id, is_admin, pay_check, pay_check_bank, account_card_bank, pwd_reset_time FROM sys_user where user_name=#{userName}")
    SysUserVo getUserByUserName(@Param("userName") String userName);
    @Select("SELECT user_id, dept_id, user_name, nick_name, user_type, email, phonenumber, sex, avatar, password, status, del_flag, login_ip, login_date, create_by, create_time, update_by, update_time, remark, Employee_number, id_no, post_id, is_admin, pay_check, pay_check_bank, account_card_bank, pwd_reset_time FROM sys_user where dept_id=#{deptId}")
    List<SysUserVo> getUserByDeptId(@Param("deptId") Integer deptId);
    @Select("  select u.user_id, u.dept_id, u.user_name, u.nick_name, u.email, u.avatar, u.phonenumber, u.password, u.sex, u.status, u.del_flag, u.login_ip, u.login_date, u.create_by, u.create_time, u.remark, \n" +
            "        r.role_id, r.role_name, r.role_key, r.role_sort, r.data_scope, r.status as role_status\n" +
            "        from sys_user u\n" +
            "\t\t    left join sys_user_role ur on u.user_id = ur.user_id\n" +
            "\t\t    left join sys_role r on r.role_id = ur.role_id where r.role_id = #{roleId}")
    List<SysUserVo> getUserByRoleId(@Param("roleId") Integer roleId);
}
