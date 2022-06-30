package com.ruoyi.invoice.handler;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.invoice.annotation.Log;
import com.ruoyi.invoice.dto.DataDto;
import com.ruoyi.invoice.mapper.SysBankcardMapper;
import com.ruoyi.invoice.mapper.SysDeptMapper;
import com.ruoyi.invoice.pojo.AccountExpense;
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

    @PutMapping(value ="/editExpenseByExpenseId")
    @Log("修改借支单（审核）")
    @ApiOperation("修改借支单（审核）")
    public DataDto editExpenseByExpenseId(AccountExpense accountExpense){
        int num= accountExpenseService.editExpenseByExpenseId(accountExpense);
        DataDto dataDto = new DataDto();
        if (num>0){
            dataDto.success("审核成功");
        }
        return dataDto;
    }
    @PutMapping(value ="/editExpense2")
    @Log("修改借支单（编辑）")
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
    @Log("查询所有部门")
    @ApiOperation("查询所有部门")
    public DataDto getAllDept(){
        List<SysDept> depts= sysDeptMapper.selectAllDept();
        DataDto<SysDept> dataDto = new DataDto<>();
        return dataDto.success(depts);
    }
    @GetMapping(value ="/getAllUser")
    @Log("查询所有用户")
    @ApiOperation("查询所有用户")
    public List<SysUser> getAllUser(){
        return sysUserService.selectAll();
    }

    @GetMapping(value ="/getCardInfoBycompany")
    @Log("根据用户id查询对应的银行卡信息")
    @ApiOperation("根据用户id查询对应的银行卡信息")
    public SysUser getCardInfoBycompany(Integer userId){
        SysUser sysUser = sysUserService.selectByCompany(userId);
        return sysUser;
    }

    @GetMapping(value ="/getBankNameBycardId")
    @Log("根据银行卡id查询对应的银行名")
    @ApiOperation("根据银行卡id查询对应的银行名")
    public DataDto getBankNameBycardId(int bankcardId){
        SysBankcardVo sysBankcard=sysBankcardMapper.selectBankNameBycardId(bankcardId);
        DataDto dataDto = new DataDto();
        dataDto.success(sysBankcard.getBankName());
        return dataDto;
    }
    @PostMapping(value ="/addExpense")
    @Log("插入报销单")
    @ApiOperation("插入报销单")
    public DataDto addExpense(AccountExpense accountExpense)  {
        accountExpense.setIsDeleted(0);
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
    @Log("查询所有报销单信息（登录用户的）")
    @ApiOperation("查询所有报销单信息（登录用户的）")
    public DataDto selectAllExpense(AccountExpense accountExpense, TimeQo timeQo, Integer currentPage, Integer limit){
        if(accountExpense.getExpenseCode()==null){//不是查看某条单据（查看登录用户的所有单据）
            accountExpense.setCreateUser(SecurityUtils.getUserId());
        }
        IPage<AccountExpenseVo> sysExpenseVoIPage = accountExpenseService.selectAllExpense(accountExpense,timeQo,currentPage,limit);
        DataDto<AccountExpenseVo> dataDto = new DataDto<>();
        dataDto.success(sysExpenseVoIPage.getRecords(),sysExpenseVoIPage.getTotal());
        return dataDto;
    }
    @GetMapping(value ="/getAllExpenses")
    @Log("查询所有报销单信息")
    @ApiOperation("查询所有报销单信息")
    public DataDto selectAllExpenses(AccountExpense accountExpense,TimeQo timeQo, Integer currentPage, Integer limit){
        IPage<AccountExpenseVo> sysExpenseVoIPage = accountExpenseService.selectAllExpense(accountExpense,timeQo,currentPage,limit);
        DataDto<AccountExpenseVo> dataDto = new DataDto<>();
        dataDto.success(sysExpenseVoIPage.getRecords(),sysExpenseVoIPage.getTotal());
        return dataDto;
    }
    @GetMapping(value ="/getCheckExpense")
    @Log("查询所有待审核报销单信息")
    @ApiOperation("查询所有待审核报销单信息")
    public DataDto getCheckExpense(AccountExpense accountExpense,TimeQo timeQo, Integer currentPage, Integer limit){
        List<SysUserVo> sysUserVos=sysUserService.getRoleByUserId(SecurityUtils.getUserId());
        for(SysUserVo sysUserVo:sysUserVos){
            if(sysUserVo.getRoleId()==5||sysUserVo.getRoleId()==6){//总经理或副总经理显示所有审核状态的（部门主管审核过的）
                accountExpense.setInvoiceType(2);
            }
            if(sysUserVo.getRoleId()==7){//财务主管显示所有打款状态的单据
                accountExpense.setInvoiceType(3);
            }
            if(sysUserVo.getRoleId()==10||sysUserVo.getRoleId()==12||sysUserVo.getRoleId()==4||sysUserVo.getRoleId()==8){
                //部门主管（行政主管 业务主管 软开主管 会计）显示他手下人员的单据（发起状态）
                int deptId=sysUserService.getDeptByRoleId(sysUserVo.getRoleId()).getDeptId();
                accountExpense.setInvoiceType(1);
                accountExpense.setDeptId(deptId);
            }
        }

        IPage<AccountExpenseVo> sysExpenseVoIPage = accountExpenseService.selectAllExpense(accountExpense,timeQo,currentPage,limit);
        DataDto<AccountExpenseVo> dataDto = new DataDto<>();
        dataDto.success(sysExpenseVoIPage.getRecords(),sysExpenseVoIPage.getTotal());
        return dataDto;
    }
    @Log("删除报销单（逻辑删除）")
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
    @Log("撤回操作")
    @ApiOperation("撤回操作")
    @PutMapping("/editExpenseType")
    public int editExpenseType(AccountExpense accountExpense){
        return accountExpenseService.editExpenseType(accountExpense);
    }
    @Log("审核报销单")
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
