package com.ruoyi.invoice.handler;


import com.ruoyi.common.security.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.invoice.annotation.Log;
import com.ruoyi.invoice.dto.DataDto;
import com.ruoyi.invoice.mapper.CompanyMapper;
import com.ruoyi.invoice.pojo.AccountBorrow;
import com.ruoyi.invoice.pojo.SysUser;
import com.ruoyi.invoice.pojo.sysGroup;
import com.ruoyi.invoice.qo.TimeQo;
import com.ruoyi.invoice.service.AccountBorrowService;
import com.ruoyi.invoice.service.SysUserService;
import com.ruoyi.invoice.vo.AccountBorrowVo;
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
@Api(tags = "单据管理差旅报销单")
@RequestMapping("/api/borrow")
public class AccountBorrowHandler {

    private final AccountBorrowService accountBorrowService;
    private final CompanyMapper companyMapper;
    private final SysUserService sysUserService;

    @PutMapping(value ="/editBorrow2")
    @Log("修改借支单（编辑）")
    @ApiOperation("修改借支单（编辑）")
    public DataDto editBorrow2(AccountBorrow accountBorrow){
        int num= accountBorrowService.editBorrow(accountBorrow);
        DataDto dataDto = new DataDto();
        if (num>0){
            dataDto.success("修改成功");
        }
        return dataDto;
    }

    @PutMapping(value ="/editBorrowByBorrowId")
    @Log("修改借支单")
    @ApiOperation("修改借支单")
    public DataDto editBorrowByBorrowId(AccountBorrow accountBorrow){
        int num= accountBorrowService.editBorrowByBorrowId(accountBorrow);
        DataDto dataDto = new DataDto();
        if (num>0){
            dataDto.success("审核成功");
        }
        return dataDto;
    }

    @PostMapping(value ="/addBorrow")
    @Log("插入差旅报销单")
    @ApiOperation("插入借支单")
    public DataDto addTravelExpense(AccountBorrow accountBorrow){

        DataDto dataDto = new DataDto();
        try {
            int num= accountBorrowService.addBorrow(accountBorrow);
            if (num>0){
                dataDto.success("借支单填写成功");
            }
            return dataDto;
        }catch (DuplicateKeyException ex){
            return dataDto.err("不允许插入重复单据，自动返回，请重新创建");
        }

    }
    @GetMapping(value ="/getAllBorrow")
    @Log("查询所有借支单信息（登录用户的）")
    @ApiOperation("查询所有借支单信息（登录用户的）")
//    @PreAuthorize("@el.check('getAllBorrow:list')")
    public DataDto getAllBorrow(AccountBorrowVo sysBorrowVo, TimeQo timeQo, Integer currentPage, Integer limit){
        if(sysBorrowVo.getBorrowCode()==null){//不是查看某条单据（查看登录用户的所有单据）
            sysBorrowVo.setCreateUser(SecurityUtils.getUserId());
        }
        IPage<AccountBorrowVo> sysBorrowVoIPage = accountBorrowService.getAllBorrow(sysBorrowVo,timeQo,currentPage,limit);
        DataDto<AccountBorrowVo> dataDto = new DataDto<>();
        dataDto.success(sysBorrowVoIPage.getRecords(),sysBorrowVoIPage.getTotal());
        return dataDto;
    }
    @GetMapping(value ="/getAllBorrows")
    @Log("查询所有借支单信息")
    @ApiOperation("查询所有借支单信息")
//    @PreAuthorize("@el.check('getAllBorrow:list')")
    public DataDto getAllBorrows(AccountBorrowVo sysBorrowVo, TimeQo timeQo, Integer currentPage, Integer limit){
        IPage<AccountBorrowVo> sysBorrowVoIPage = accountBorrowService.getAllBorrow(sysBorrowVo,timeQo,currentPage,limit);
        DataDto<AccountBorrowVo> dataDto = new DataDto<>();
        dataDto.success(sysBorrowVoIPage.getRecords(),sysBorrowVoIPage.getTotal());
        return dataDto;
    }
    @GetMapping(value ="/getCheckBorrow")
    @Log("查询所有待审核借支单信息")
    @ApiOperation("查询所有待审核借支单信息")
    public DataDto getCheckBorrow(AccountBorrowVo sysBorrowVo, TimeQo timeQo, Integer currentPage, Integer limit){

        List<SysUserVo> sysUserVos=sysUserService.getRoleByUserId(SecurityUtils.getUserId());
        for(SysUserVo sysUserVo:sysUserVos){
            if(sysUserVo.getRoleId()==5){//总经理显示所有审核状态的（部门主管审核过的）
                sysBorrowVo.setInvoiceType(2);
            }
            if(sysUserVo.getRoleId()==7){//财务主管显示所有打款状态的单据
                sysBorrowVo.setInvoiceType(3);
            }
            if(sysUserVo.getRoleId()==10||sysUserVo.getRoleId()==12){//部门主管显示他手下人员的单据（发起状态）
                int deptId=sysUserService.getDeptByRoleId(sysUserVo.getRoleId()).getDeptId();
                sysBorrowVo.setInvoiceType(1);
                sysBorrowVo.setDeptId(deptId);
            }
        }
        DataDto<AccountBorrowVo> dataDto = new DataDto<>();
        IPage<AccountBorrowVo> sysBorrowVoIPage = accountBorrowService.getAllBorrow(sysBorrowVo,timeQo,currentPage,limit);
        dataDto.success(sysBorrowVoIPage.getRecords(),sysBorrowVoIPage.getTotal());
        return dataDto;
    }
    @Log("删除报销单（逻辑删除）")
    @ApiOperation("删除菜单（逻辑删除）")
    @PutMapping("/editBorrow")
//    @PreAuthorize("@el.check('invoice:del')")
    public DataDto editBorrow(String borrowIds){
        DataDto dataDto = new DataDto();
        String[] borrowIdStr=borrowIds.split(",");
        int num=0;//删除的次数
        for (String borrowId:borrowIdStr) {
            int count=accountBorrowService.editBorrowById(Integer.parseInt(borrowId));
            num+=count;
        }
        if (num>borrowIdStr.length){
            dataDto.success("删除成功");
        }else {
            dataDto.err("只有发起状态的能删除");
        }
        return dataDto;
    }
    @Log("撤回操作")
    @ApiOperation("撤回操作")
    @PutMapping("/editBorrowType")
    public int editBorrowType(AccountBorrow accountBorrow){
        return accountBorrowService.editBorrowType(accountBorrow);
    }
    /*
     * 获取借支单编号
     *
     * */
    @GetMapping("getBorrowCode")
    public DataDto getBorrowCode() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String nowDate = sdf.format(date);
        String borrowCode = "";
        if (accountBorrowService.selectMaxCode().size()>0) {
            for(AccountBorrow accountBorrow : accountBorrowService.selectMaxCode()){
                if(accountBorrow.getBorrowCode().contains(nowDate)){
                    Long orser=Long.valueOf(accountBorrow.getBorrowCode().substring(2));
                    orser++;
                    borrowCode=String.valueOf("JZ"+orser);
                }else {
                    borrowCode="JZ"+nowDate+"0001";
                }
            }
        }else {//没有单据时
            borrowCode="JZ"+nowDate+"0001";
        }
        DataDto dto=new DataDto();
        dto.success(borrowCode);
        return  dto;
    }

    @GetMapping(value ="/getAllCompany")
    @Log("查询所有公司信息")
    @ApiOperation("查询所有公司信息")
    public DataDto getAllCompany(){
        List<sysGroup> companies= companyMapper.selectAllCompany();
        DataDto<sysGroup> dataDto = new DataDto<>();
        return dataDto.success(companies);
    }
    /*
     * 获取收款单位信息（用户表）
     *
     * */
    @GetMapping(value ="/getAllGetUser")
    @Log("查询所有收款公司或者个人")
    @ApiOperation("查询所有收款公司或者个人")
    public DataDto getAllGetUser(){
        List<SysUser> tCompanies= sysUserService.selectAll();
        DataDto<SysUser> dataDto = new DataDto<>();
        return dataDto.success(tCompanies);
    }
}
