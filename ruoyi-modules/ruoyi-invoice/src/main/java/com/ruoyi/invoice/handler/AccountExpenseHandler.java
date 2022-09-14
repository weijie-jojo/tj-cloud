package com.ruoyi.invoice.handler;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.invoice.dto.DataDto;
import com.ruoyi.invoice.mapper.EmployeeInformationMapper;
import com.ruoyi.invoice.mapper.SysBankcardMapper;
import com.ruoyi.invoice.mapper.SysDeptMapper;
import com.ruoyi.invoice.mapper.SysUserMapper;
import com.ruoyi.invoice.pojo.AccountExpense;
import com.ruoyi.invoice.pojo.EmployeeInformation;
import com.ruoyi.invoice.pojo.SysDept;
import com.ruoyi.invoice.pojo.SysUser;
import com.ruoyi.invoice.qo.TimeQo;
import com.ruoyi.invoice.service.AccountExpenseService;
import com.ruoyi.invoice.service.SysUserService;
import com.ruoyi.invoice.vo.AccountExpenseVo;
import com.ruoyi.invoice.vo.SysBankcardVo;
import com.ruoyi.invoice.vo.SysUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequiredArgsConstructor //代替了resouse或者Autowrited
@Api(tags = "单据管理报销单")
@RequestMapping("/expense")
public class AccountExpenseHandler {

    private final SysUserService sysUserService;

    private final SysBankcardMapper sysBankcardMapper;

    private final AccountExpenseService accountExpenseService;

    private final SysDeptMapper sysDeptMapper;

    private final EmployeeInformationMapper employeeInformationMapper;

    private final SysUserMapper sysUserMapper;

    @GetMapping(value ="/getLeaderByUserId")
    @ApiOperation("获取领导信息（日志用）")
    public List<SysUserVo> getLeaderByUserId(Integer type){
        //获取登录用户的部门id
//        Integer deptId=sysUserMapper.getDeptByUserId(SecurityUtils.getUserId()).getDeptId();
        //根据部门id获取用户集合
//        List<SysUserVo> userVos=sysUserMapper.getUserByDeptId(deptId);
        //根据登录用户获取用户角色信息
        List<SysUserVo> roles= sysUserMapper.getRoleByUserId(SecurityUtils.getUserId());
        List<SysUserVo> leaders=new ArrayList<>();
        if (type==1){//主管
            System.out.println(111);
            for (SysUserVo role:roles){
                if (role.getRoleId()==9){//出纳
                    leaders=sysUserMapper.getUserByRoleId(8);
                }
                if (role.getRoleId()==11){//文员
                    leaders=sysUserMapper.getUserByRoleId(10);
                }
                if (role.getRoleId()==3){//业务人员
                    leaders=sysUserMapper.getUserByRoleId(12);
                }
                if (role.getRoleId()==110){//程序员
                    leaders=sysUserMapper.getUserByRoleId(4);
                }
                if (role.getRoleId()==1||role.getRoleId()==5||role.getRoleId()==6||role.getRoleId()==7||role.getRoleId()==8||role.getRoleId()==10||role.getRoleId()==12||role.getRoleId()==4){//各主管 总经理 及管理员
                    leaders.add(sysUserMapper.getDeptByUserId(SecurityUtils.getUserId()));
                }
            }
        }else if (type==2){//总经办
            System.out.println(222);
            leaders= sysUserMapper.getUserByRoleId(5);//总经理
            List<SysUserVo> sysUserVos= sysUserMapper.getUserByRoleId(6);//副总
            for(SysUserVo sysUserVo:sysUserVos){
                leaders.add(sysUserVo);
            }
        }else if(type==3){//财务
            System.out.println(333);
            leaders= sysUserMapper.getUserByRoleId(7);
        }
        return leaders;
    }

    @GetMapping(value ="/getPost")
    @ApiOperation("查询岗位信息")
    public EmployeeInformation getPost(Integer userId){
        EmployeeInformation employeeInformation= employeeInformationMapper.getPost(userId);
        return employeeInformation;
    }

    @PutMapping(value ="/editExpenseByExpenseId")
    @Log(title = "修改借支单（审核）",businessType = BusinessType.UPDATE)
    @ApiOperation("修改借支单（审核）")
    public DataDto editExpenseByExpenseId(AccountExpense accountExpense){
        if (accountExpense.getInvoiceType()<5){//办理中
            accountExpense.setStepType(1);
        }
        if (accountExpense.getInvoiceType()==5){//完成
            accountExpense.setStepType(2);
        }
        if (accountExpense.getInvoiceType()==6){//异常
            accountExpense.setStepType(3);
        }
        int num= accountExpenseService.editExpenseByExpenseId(accountExpense);
        DataDto dataDto = new DataDto();
        if (num>0){
            dataDto.success("审核成功");
        }
        return dataDto;
    }

    @PutMapping(value ="/editExpense2")
    @Log(title = "修改借支单（编辑）",businessType = BusinessType.UPDATE)
    @ApiOperation("修改借支单（编辑）")
    public DataDto editExpense2(AccountExpense accountExpense){
        System.out.println("accountExpense==="+accountExpense);
        int num= accountExpenseService.editExpense(accountExpense);
        DataDto dataDto = new DataDto();
        if (num>0){
            dataDto.success("修改成功");
        }
        return dataDto;
    }

    @GetMapping(value ="/getAllDept")
    @ApiOperation("查询所有部门")
    public DataDto getAllDept(){
        List<SysDept> depts= sysDeptMapper.selectAllDept();
        DataDto<SysDept> dataDto = new DataDto<>();
        return dataDto.success(depts);
    }

    @GetMapping(value ="/getAllUser")
    @ApiOperation("查询所有用户")
    public List<SysUser> getAllUser(){
        return sysUserService.selectAll();
    }

    @GetMapping(value ="/getCardInfoBycompany")
    @ApiOperation("根据用户id查询对应的银行卡信息")
    public SysUser getCardInfoBycompany(Integer userId){
        SysUser sysUser = sysUserService.selectByCompany(userId);
        return sysUser;
    }

    @GetMapping(value ="/getUserByUserName")
    @ApiOperation("根据用户id查询对应的银行卡信息")
    public SysUserVo getUserByUserName(String userName){
        SysUserVo sysUser = sysUserService.getUserByUserName(userName);
        return sysUser;
    }

    @GetMapping(value ="/getBankNameBycardId")
    @ApiOperation("根据银行卡id查询对应的银行名")
    public DataDto getBankNameBycardId(int bankcardId){
        SysBankcardVo sysBankcard=sysBankcardMapper.selectBankNameBycardId(bankcardId);
        DataDto dataDto = new DataDto();
        dataDto.success(sysBankcard.getBankName());
        return dataDto;
    }

    @PostMapping
    @Log(title = "插入报销单",businessType = BusinessType.INSERT)
    @ApiOperation("插入报销单")
    public DataDto addExpense(AccountExpense accountExpense)  {
//        System.out.println("accountExpense"+ accountExpense.getExpenseImage().split(",").toString());
        accountExpense.setIsDeleted(0);
        accountExpense.setStepType(1);
        DataDto dataDto = new DataDto();
        try {
            int num=accountExpenseService.addExpense(accountExpense);
            if (num>0){
                dataDto.success("报销单填写成功");
            }
            return dataDto;
        }catch (DuplicateKeyException ex){
            return dataDto.err("不允许插入重复单据，自动返回，请重新创建");
        }
    }

    @GetMapping(value ="/getAllExpense")
    @ApiOperation("查询所有报销单信息（登录用户的）")
    public DataDto selectAllExpense(AccountExpense accountExpense, TimeQo timeQo, Integer currentPage, Integer limit){
        if(accountExpense.getExpenseCode()==null){//不是查看某条单据（查看登录用户的所有单据）
            accountExpense.setCreateUser(SecurityUtils.getUserId());
            accountExpense.setStepType(1);//只显示未完成跟异常的
        }
        IPage<AccountExpenseVo> sysExpenseVoIPage = accountExpenseService.selectAllExpense2(accountExpense,timeQo,currentPage,limit);
        List<AccountExpenseVo> list=sysExpenseVoIPage.getRecords();
//        for (int i=0;i<list.size();i++){//把驳回的放在最前面
//            if (list.get(i).getInvoiceType()==6){
//                Collections.swap(list,i,0);
//            }
//        }
        DataDto<AccountExpenseVo> dataDto = new DataDto<>();
        dataDto.success(list,sysExpenseVoIPage.getTotal());
        return dataDto;
    }
    @GetMapping(value ="/getAllExpense2")
    @ApiOperation("查询所有报销单信息（登录用户历史单据）")
    public DataDto selectAllExpense2(AccountExpense accountExpense, TimeQo timeQo, Integer currentPage, Integer limit){
        accountExpense.setCreateUser(SecurityUtils.getUserId());
        accountExpense.setStepType(2);
        IPage<AccountExpenseVo> sysExpenseVoIPage = accountExpenseService.selectAllExpense(accountExpense,timeQo,currentPage,limit);
        List<AccountExpenseVo> list=sysExpenseVoIPage.getRecords();
        DataDto<AccountExpenseVo> dataDto = new DataDto<>();
        dataDto.success(list,sysExpenseVoIPage.getTotal());
        return dataDto;
    }
    @GetMapping(value ="/getAllExpenses")
    @ApiOperation("查询所有报销单信息")
    public DataDto selectAllExpenses(AccountExpense accountExpense,TimeQo timeQo, Integer currentPage, Integer limit){
        IPage<AccountExpenseVo> sysExpenseVoIPage = accountExpenseService.selectAllExpense(accountExpense,timeQo,currentPage,limit);
        DataDto<AccountExpenseVo> dataDto = new DataDto<>();
        dataDto.success(sysExpenseVoIPage.getRecords(),sysExpenseVoIPage.getTotal());
        return dataDto;
    }

    @GetMapping(value ="/getCheckExpense")
    @ApiOperation("查询所有待审核报销单信息")
    public DataDto getCheckExpense(AccountExpense accountExpense,TimeQo timeQo, Integer currentPage, Integer limit){
        List<SysUserVo> sysUserVos=sysUserService.getRoleByUserId(SecurityUtils.getUserId());
        for(SysUserVo sysUserVo:sysUserVos){
            if(sysUserVo.getRoleId()==5||sysUserVo.getRoleId()==6){//总经理或副总经理显示所有审核状态的（部门主管审核过的）
                System.out.println("经理");
                accountExpense.setInvoiceType(2);
            }
            if(sysUserVo.getRoleId()==7){//财务主管显示所有打款状态的单据
                System.out.println("财务主管");
                accountExpense.setInvoiceType(3);
            }
            if(sysUserVo.getRoleId()==10||sysUserVo.getRoleId()==12||sysUserVo.getRoleId()==4||sysUserVo.getRoleId()==8){
                //部门主管（行政主管 业务主管 软开主管 会计）显示他手下人员的单据（发起状态）
                System.out.println("部门主管");
                int deptId=sysUserService.getDeptByUserId(sysUserVo.getUserId()).getDeptId();
                System.out.println("deptId"+deptId);
                accountExpense.setInvoiceType(1);
                accountExpense.setDeptId(deptId);
            }
        }

        IPage<AccountExpenseVo> sysExpenseVoIPage = accountExpenseService.selectAllExpense(accountExpense,timeQo,currentPage,limit);
        DataDto<AccountExpenseVo> dataDto = new DataDto<>();
        dataDto.success(sysExpenseVoIPage.getRecords(),sysExpenseVoIPage.getTotal());
        return dataDto;
    }

    @Log(title = "删除报销单（逻辑删除）",businessType = BusinessType.UPDATE)
    @ApiOperation("删除菜单（逻辑删除）")
    @PutMapping("/editExpense")
//    @PreAuthorize("@el.check('invoice:del')")
    public DataDto editExpense(String expenseIds){
        DataDto dataDto=new DataDto();
        System.out.println("deviceIds==="+expenseIds);
        String[] expenseIdStr=expenseIds.split(",");
        int num=0;//删除的次数
        for (String expenseId:expenseIdStr) {
            int count=accountExpenseService.editExpenseById(Integer.parseInt(expenseId));
            num+=count;
        }
        if (num>=expenseIdStr.length){
            dataDto.success("删除成功");
        }else {
            dataDto.err("只有发起状态的能删除");
        }
        return dataDto;
    }

    @Log(title = "撤回操作",businessType = BusinessType.UPDATE)
    @ApiOperation("撤回操作")
    @PutMapping("/editExpenseType")
    public int editExpenseType(AccountExpense accountExpense){
        return accountExpenseService.editExpenseType(accountExpense);
    }

    @Log(title = "审核报销单",businessType = BusinessType.UPDATE)
    @ApiOperation("审核报销单")
    @PutMapping("/checkExpense")
//    @PreAuthorize("@el.check('invoice:check')")
    public DataDto checkExpense(int expenseId){
        int num= accountExpenseService.checkExpenseById(expenseId);
        DataDto dataDto = new DataDto();
        if (num>0){
            dataDto.success("审核成功");
        }

        return dataDto;
    }

    /*
     * 获取报销单编号
     *
     * */
    @GetMapping("getExpenseCode")
    @ApiOperation("获取报销单编号")
    public DataDto getExpenseCode() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String nowDate = sdf.format(date);
        String expenseCode = "";
        if (accountExpenseService.selectMaxCode().size()>0) {
            for (AccountExpense accountExpense : accountExpenseService.selectMaxCode()) {
                if (accountExpense.getExpenseCode().contains(nowDate)) {
                    Long orser = Long.valueOf(accountExpense.getExpenseCode().substring(2));
                    orser++;
                    expenseCode = String.valueOf("BX" + orser);
                } else {
                    expenseCode = "BX" + nowDate + "0001";
                }
            }
        }else {//没有单据时
            expenseCode="BX"+nowDate+"0001";
        }
        DataDto dto=new DataDto();
        dto.success(expenseCode);
        return  dto;
    }
}
