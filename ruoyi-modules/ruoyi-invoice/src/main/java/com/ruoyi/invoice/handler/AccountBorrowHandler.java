package com.ruoyi.invoice.handler;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.utils.SecurityUtils;
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
@Api(tags = "单据管理借支单")
@RequestMapping("/borrow")
public class AccountBorrowHandler {

    private final AccountBorrowService accountBorrowService;
    private final CompanyMapper companyMapper;
    private final SysUserService sysUserService;

    @PutMapping(value ="/editBorrow2")
    @Log(title = "修改借支单（编辑）",businessType= BusinessType.UPDATE)
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
    @Log(title = "修改借支单",businessType= BusinessType.UPDATE)
    @ApiOperation("修改借支单")
    public DataDto editBorrowByBorrowId(AccountBorrow accountBorrow){
        if (accountBorrow.getInvoiceType()<5){//办理中
            accountBorrow.setStepType(1);
        }
        if (accountBorrow.getInvoiceType()==5){//完成
            accountBorrow.setStepType(2);
        }
        if (accountBorrow.getInvoiceType()==6){//异常
            accountBorrow.setStepType(3);
        }
        int num= accountBorrowService.editBorrowByBorrowId(accountBorrow);
        DataDto dataDto = new DataDto();
        if (num>0){
            dataDto.success("审核成功");
        }
        return dataDto;
    }

    @PostMapping(value ="/addBorrow")
    @Log(title = "插入借支单",businessType= BusinessType.INSERT)
    @ApiOperation("插入借支单")
    public DataDto addTravelExpense(AccountBorrow accountBorrow){
        accountBorrow.setStepType(1);
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
    @ApiOperation("查询所有借支单信息（登录用户的）")
//    @PreAuthorize("@el.check('getAllBorrow:list')")
    public DataDto getAllBorrow(AccountBorrowVo sysBorrowVo, TimeQo timeQo, Integer currentPage, Integer limit){
        if(sysBorrowVo.getBorrowCode()==null){//不是查看某条单据（查看登录用户的所有单据）区分是根据code查看还是登陆用户的
            sysBorrowVo.setCreateUser(SecurityUtils.getUserId());
            sysBorrowVo.setStepType(1);//只显示未完成跟异常的
        }
        IPage<AccountBorrowVo> sysBorrowVoIPage = accountBorrowService.getAllBorrow(sysBorrowVo,timeQo,currentPage,limit);
        List<AccountBorrowVo> list=sysBorrowVoIPage.getRecords();
//        for (int i=0;i<list.size();i++){//把驳回的放在最前面
//            if (list.get(i).getInvoiceType()==6){
//                Collections.swap(list,i,0);
//            }
//        }
        DataDto<AccountBorrowVo> dataDto = new DataDto<>();
        dataDto.success(list,sysBorrowVoIPage.getTotal());
        return dataDto;
    }
    @GetMapping(value ="/getAllBorrow2")
    @ApiOperation("查询所有借支单信息（登录用户历史单据）")
    public DataDto getAllBorrow2(AccountBorrowVo sysBorrowVo, TimeQo timeQo, Integer currentPage, Integer limit){
        sysBorrowVo.setCreateUser(SecurityUtils.getUserId());
        sysBorrowVo.setStepType(2);
        IPage<AccountBorrowVo> sysBorrowVoIPage = accountBorrowService.getAllBorrow(sysBorrowVo,timeQo,currentPage,limit);
        List<AccountBorrowVo> list=sysBorrowVoIPage.getRecords();
        DataDto<AccountBorrowVo> dataDto = new DataDto<>();
        dataDto.success(list,sysBorrowVoIPage.getTotal());
        return dataDto;
    }
    @GetMapping(value ="/getAllBorrows")
    @ApiOperation("查询所有借支单信息")
//    @PreAuthorize("@el.check('getAllBorrow:list')")
    public DataDto getAllBorrows(AccountBorrowVo sysBorrowVo, TimeQo timeQo, Integer currentPage, Integer limit){
        IPage<AccountBorrowVo> sysBorrowVoIPage = accountBorrowService.getAllBorrow(sysBorrowVo,timeQo,currentPage,limit);
        DataDto<AccountBorrowVo> dataDto = new DataDto<>();
        dataDto.success(sysBorrowVoIPage.getRecords(),sysBorrowVoIPage.getTotal());
        return dataDto;
    }
    @GetMapping(value ="/getCheckBorrow")
    @ApiOperation("查询所有待审核借支单信息")
    public DataDto getCheckBorrow(AccountBorrowVo sysBorrowVo, TimeQo timeQo, Integer currentPage, Integer limit){

        List<SysUserVo> sysUserVos=sysUserService.getRoleByUserId(SecurityUtils.getUserId());
        for(SysUserVo sysUserVo:sysUserVos){
            if(sysUserVo.getRoleId()==5||sysUserVo.getRoleId()==6){//总经理或副总经理显示所有审核状态的（部门主管审核过的）
                sysBorrowVo.setInvoiceType(2);
            }
            if(sysUserVo.getRoleId()==7){//财务主管显示所有打款状态的单据
                sysBorrowVo.setInvoiceType(3);
            }
            if(sysUserVo.getRoleId()==10||sysUserVo.getRoleId()==12||sysUserVo.getRoleId()==4||sysUserVo.getRoleId()==8){
                //部门主管（行政主管 业务主管 软开主管 会计）显示他手下人员的单据（发起状态）
                int deptId=sysUserService.getDeptByUserId(sysUserVo.getUserId()).getDeptId();
                sysBorrowVo.setInvoiceType(1);
                sysBorrowVo.setDeptId(deptId);
            }
        }
        DataDto<AccountBorrowVo> dataDto = new DataDto<>();
        IPage<AccountBorrowVo> sysBorrowVoIPage = accountBorrowService.getAllBorrow(sysBorrowVo,timeQo,currentPage,limit);
        dataDto.success(sysBorrowVoIPage.getRecords(),sysBorrowVoIPage.getTotal());
        return dataDto;
    }
    @Log(title = "删除报销单（逻辑删除）",businessType= BusinessType.DELETE)
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
    @Log(title = "撤回操作",businessType= BusinessType.UPDATE)
    @ApiOperation("撤回操作")
    @PutMapping("/editBorrowType")
    public int editBorrowType(AccountBorrow accountBorrow){
        return accountBorrowService.editBorrowType(accountBorrow);
    }

    @GetMapping("getBorrowCode")
    @ApiOperation("获取借支单编号")
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
    @ApiOperation("查询所有公司信息")
    public DataDto getAllCompany(){
        List<sysGroup> companies= companyMapper.selectAllCompany();
        DataDto<sysGroup> dataDto = new DataDto<>();
        return dataDto.success(companies);
    }

    @GetMapping(value ="/getAllGetUser")
    @ApiOperation("获取收款单位信息（用户表）")
    public DataDto getAllGetUser(){
        List<SysUser> tCompanies= sysUserService.selectAll();
        DataDto<SysUser> dataDto = new DataDto<>();
        return dataDto.success(tCompanies);
    }
}
