package com.ruoyi.system.controller;

import com.ruoyi.common.core.constant.CacheConstants;
import com.ruoyi.common.core.constant.UserConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.security.annotation.InnerAuth;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.domain.SysRole;
import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 用户信息
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class SysUserController extends BaseController
{
    private final static long expireTime = CacheConstants.EXPIRATION;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPostService postService;

    @Autowired
    private ISysPermissionService permissionService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private RedisService redisService;

    /**
     * 根据userId获取他的部门领导信息
     */
    @GetMapping("/getLeaderByUserId")
    @ApiOperation("根据userId获取他的部门领导信息")
    public List<SysUser> getLeaderByUserId(Long userId)
    {
        Long roleId= userService.selectUserById(userId).getRoles().get(0).getRoleId();
        System.out.println("roleId=="+roleId);
        List<SysUser> leaders=new ArrayList<>();
        if(roleId==1||roleId==5||roleId==6){//管理员及总经理 副总经理
            leaders= null;
        }else if(roleId==3||roleId==12){//当业务员及业务主管
            Long roleLong=new Long(12);
            leaders= userService.selectUserByRoleId(roleLong);
        }else if(roleId==11||roleId==10){//行政文员及行政主管
            Long roleLong=new Long(10);
            leaders= userService.selectUserByRoleId(roleLong);
        }else {
            Long roleLong=new Long(1);//其他人由管理员审核
            leaders= userService.selectUserByRoleId(roleLong);
        }
        System.out.println("leaders=="+leaders);
        return leaders;
    }

    /**
     * 获取用户列表
     */
    @RequiresPermissions("system:user:list")
    @GetMapping("/list")
    public TableDataInfo list(SysUser user)
    {
        List<Long> userIdArr=this.getUserIdArr();
        startPage();
        List<SysUser> list = userService.selectUserList2(userIdArr,user);
        return getDataTable(list);
    }
    /**
     * 获取用户列表
     */
    @RequiresPermissions("system:user:list")
    @GetMapping()
    public List<SysUser> getAllUser()
    {
        List<SysUser> list = userService.selectAllUser();
        return list;
    }

    /**
     * 获取用户列表(排除删除的)
     */
    @GetMapping("/getAllUser")
    public List<SysUser> getAllUser2()
    {
        List<SysUser> list = userService.selectAllUser2();
        return list;
    }

    /**
     * 根据角色集合获取用户列表
     */
    @GetMapping("/getUserByRoles")
    public List<SysUser> getUserByRoles(@RequestParam("roleIdArr") List<Long> roleIdArr)
    {
        List<SysUser> list = userService.selectUserByRoles(roleIdArr);
        return list;
    }

    /**
     * 根据角色获取用户列表
     */
    @GetMapping("/getUserByRole/{roleId}")
    public List<SysUser> getUserByRole(@PathVariable("roleId") Long roleId )
    {
        List<SysUser> list = userService.selectUserByRoleId(roleId);
        return list;
    }

    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:user:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysUser user)
    {
        List<Long> userIdArr=this.getUserIdArr();
        List<SysUser> list = userService.selectUserList2(userIdArr,user);
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        util.exportExcel(response, list, "用户数据");
    }

    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:user:import")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        List<SysUser> userList = util.importExcel(file.getInputStream());
        String operName = SecurityUtils.getUsername();
        String message = userService.importUser(userList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) throws IOException
    {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        util.importTemplateExcel(response, "用户数据");
    }

    /**
     * 获取当前用户信息
     */
    @InnerAuth
    @GetMapping("/info/{username}")
    public R<LoginUser> info(@PathVariable("username") String username)
    {
        SysUser sysUser = userService.selectUserByUserName(username);
        if (StringUtils.isNull(sysUser))
        {
            return R.fail("用户名或密码错误");
        }
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(sysUser.getUserId());
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(sysUser.getUserId());
        LoginUser sysUserVo = new LoginUser();
        sysUserVo.setSysUser(sysUser);
        sysUserVo.setRoles(roles);
        sysUserVo.setPermissions(permissions);
        return R.ok(sysUserVo);
    }

    /**
     * 注册用户信息
     */
    @InnerAuth
    @PostMapping("/register")
    public R<Boolean> register(@RequestBody SysUser sysUser)
    {
        String username = sysUser.getUserName();
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser"))))
        {
            return R.fail("当前系统没有开启注册功能！");
        }
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(username)))
        {
            return R.fail("保存用户'" + username + "'失败，注册账号已存在");
        }
        return R.ok(userService.registerUser(sysUser));
    }

    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        Long userId = SecurityUtils.getUserId();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(userId);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(userId);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", userService.selectUserById(userId));
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 根据用户编号获取详细信息
     */
    @RequiresPermissions("system:user:query")
    @GetMapping(value = { "/", "/{userId}" })
    public AjaxResult getInfo(@PathVariable(value = "userId", required = false) Long userId)
    {
        userService.checkUserDataScope(userId);
        AjaxResult ajax = AjaxResult.success();
        List<SysRole> roles = roleService.selectRoleAll();
        ajax.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        ajax.put("posts", postService.selectPostAll());
        if (StringUtils.isNotNull(userId))
        {
            SysUser sysUser = userService.selectUserById(userId);
            ajax.put(AjaxResult.DATA_TAG, sysUser);
            ajax.put("postIds", postService.selectPostListByUserId(userId));
            ajax.put("roleIds", sysUser.getRoles().stream().map(SysRole::getRoleId).collect(Collectors.toList()));
        }
        return ajax;
    }

    /**
     * 新增用户
     */
    @RequiresPermissions("system:user:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysUser user)
    {
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName())))
        {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        }
        else if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
        {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setCreateBy(SecurityUtils.getUsername());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        Integer res=userService.insertUser(user);
        List<SysUser> list = userService.selectAllUser2();
        System.out.println("list=="+list);
        redisService.setCacheObject("users", list, expireTime, TimeUnit.MINUTES);
        return toAjax(res);
    }

    /**
     * 修改用户
     */
    @RequiresPermissions("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysUser user)
    {
        userService.checkUserAllowed(user);
        userService.checkUserDataScope(user.getUserId());
        if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
        {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setUpdateBy(SecurityUtils.getUsername());
        Integer res=userService.updateUser(user);
        List<SysUser> list = userService.selectAllUser2();
        System.out.println("list=="+list);
        redisService.setCacheObject("users", list, expireTime, TimeUnit.MINUTES);
        return toAjax(res);
    }

    /**
     * 删除用户
     */
    @RequiresPermissions("system:user:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        if (ArrayUtils.contains(userIds, SecurityUtils.getUserId()))
        {
            return AjaxResult.error("当前用户不能删除");
        }
        Integer res=userService.deleteUserByIds(userIds);
        List<SysUser> list = userService.selectAllUser2();
        System.out.println("list=="+list);
        redisService.setCacheObject("users", list, expireTime, TimeUnit.MINUTES);
        return toAjax(res);
    }

    /**
     * 重置密码
     */
    @RequiresPermissions("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(@RequestBody SysUser user)
    {
        userService.checkUserAllowed(user);
        userService.checkUserDataScope(user.getUserId());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.resetPwd(user));
    }

    /**
     * 状态修改
     */
    @RequiresPermissions("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysUser user)
    {
        userService.checkUserAllowed(user);
        userService.checkUserDataScope(user.getUserId());
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.updateUserStatus(user));
    }

    /**
     * 根据用户编号获取授权角色
     */
    @RequiresPermissions("system:user:query")
    @GetMapping("/authRole/{userId}")
    public AjaxResult authRole(@PathVariable("userId") Long userId)
    {
        AjaxResult ajax = AjaxResult.success();
        SysUser user = userService.selectUserById(userId);
        List<SysRole> roles = roleService.selectRolesByUserId(userId);
        ajax.put("user", user);
        ajax.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        return ajax;
    }

    /**
     * 用户授权角色
     */
    @RequiresPermissions("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.GRANT)
    @PutMapping("/authRole")
    public AjaxResult insertAuthRole(Long userId, Long[] roleIds)
    {
        userService.checkUserDataScope(userId);
        userService.insertUserAuth(userId, roleIds);
        return success();
    }

    /**
     * 获取员工编号
     */
    @GetMapping("/getCode")
    @ApiOperation("获取员工编号")
    public AjaxResult getCode(String deptNick)
    {
       List<SysUser> sysUsers = userService.selectMaxCode();
        String userCode="";
        if (sysUsers.size()>0){
            userCode=  StringUtils.getCode2("TJ",deptNick,sysUsers.get(0).getEmployeeNumber());
        }else {//没有单据时
            userCode="TJ"+deptNick+"0001";
        }
        return success(userCode);
    }

    /*
    * 获取用户id集合（查询过滤条件）
    *
    * */
    public  List<Long> getUserIdArr(){
        System.out.println("getUserId=="+SecurityUtils.getUserId());
        //获取登录用户
        SysUser sysUser = userService.selectUserById(SecurityUtils.getUserId());
        //登录用户部门id
        Long deptId= sysUser.getDeptId();
        //根据部门id获取用户集合
        List<SysUser> users= userService.selectUserByDeptId(deptId);
        //查询登录用户所属角色
        List<SysUserRole> roles= userService.selectRoleByUserId(SecurityUtils.getUserId());
        //存储userId的list集合
        List<Long> userIdArr=new ArrayList<>();
        for (SysUserRole role:roles){
            if (role.getRoleId()==10||role.getRoleId()==12||role.getRoleId()==4||role.getRoleId()==8){//部门主管
                System.out.println("部门主管");
                for (SysUser user1:users){//登录用户所属部门的所有用户名
                    userIdArr.add(user1.getUserId());
                }
            }
            else if (role.getRoleId()==1||role.getRoleId()==5||role.getRoleId()==6){//管理员及总经理 副总经理
                System.out.println("总经理");
                userIdArr=null;//显示所有
            }
            else {
                System.out.println("其他人");
                userIdArr.add(SecurityUtils.getUserId());//显示登录用户的
            }
        }
        System.out.println("userIdArr=="+userIdArr);
        return userIdArr;
    }

}
