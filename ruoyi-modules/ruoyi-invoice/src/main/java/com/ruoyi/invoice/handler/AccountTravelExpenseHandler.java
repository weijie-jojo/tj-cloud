package com.ruoyi.invoice.handler;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.invoice.annotation.Log;
import com.ruoyi.invoice.dto.DataDto;
import com.ruoyi.invoice.pojo.AccountExpenseItem;
import com.ruoyi.invoice.pojo.AccountTravelExpense;
import com.ruoyi.invoice.qo.TimeQo;
import com.ruoyi.invoice.service.AccountTravelExpenseService;
import com.ruoyi.invoice.service.SysUserService;
import com.ruoyi.invoice.vo.AccountExpenseVo;
import com.ruoyi.invoice.vo.AccountTravelExpenseVo;
import com.ruoyi.invoice.vo.SysUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@RestController
@RequiredArgsConstructor //代替了resouse或者Autowrited
@Api(tags = "单据管理差旅报销单")
@RequestMapping("/travelExpense")
public class AccountTravelExpenseHandler {

    private final AccountTravelExpenseService accountTravelExpenseService;
    private final SysUserService sysUserService;

    @PutMapping(value ="/editTravelExpense2")
    @Log("修改借支单（编辑）")
    @ApiOperation("修改借支单（编辑）")
    public DataDto editTravelExpense2(AccountTravelExpense accountTravelExpense){
        int num= accountTravelExpenseService.editTravelExpense(accountTravelExpense);
        DataDto dataDto = new DataDto();
        if (num>0){
            dataDto.success("修改成功");
        }
        return dataDto;
    }

    @PutMapping(value ="/editTravelExpenseById")
    @Log("修改借支单(审核)")
    @ApiOperation("修改借支单(审核)")
    public DataDto editTravelExpenseById(AccountTravelExpense accountTravelExpense){
        int num= accountTravelExpenseService.editTravelExpenseById(accountTravelExpense);
        DataDto dataDto = new DataDto();
        if (num>0){
            dataDto.success("审核成功");
        }
        return dataDto;
    }

    @PostMapping(value ="/addTravelExpense")
    @Log("插入差旅报销单")
    @ApiOperation("插入差旅报销单")
    public DataDto addTravelExpense(AccountTravelExpense accountTravelExpense){

        DataDto dataDto = new DataDto();
        try {
            int num= accountTravelExpenseService.addTravelExpense(accountTravelExpense);
            if (num>0){
                dataDto.success("差旅报销单填写成功");
            }
            return dataDto;
        }catch (DuplicateKeyException ex){
            return dataDto.err("不允许插入重复单据，自动返回，请重新创建");
        }
    }
    @GetMapping(value ="/getTravelExpense")
    @Log("查询所有差旅报销单信息(登录用户的)")
    @ApiOperation("查询所有差旅报销单信息(登录用户的)")
    public DataDto getTravelExpense(AccountTravelExpense accountTravelExpense, TimeQo timeQo, Integer currentPage, Integer limit){
        if(accountTravelExpense.getTravelExpenseCode()==null){//不是查看某条单据（查看登录用户的所有单据）
            accountTravelExpense.setCreateUser(SecurityUtils.getUserId());
        }
        IPage<AccountTravelExpenseVo> accountTravelExpenseIPage = accountTravelExpenseService.getAllTravelExpense(accountTravelExpense,timeQo,currentPage,limit);
        List<AccountTravelExpenseVo> list=accountTravelExpenseIPage.getRecords();
//        for (int i=0;i<list.size();i++){//把驳回的放在最前面
//            if (list.get(i).getInvoiceType()==6){
//                Collections.swap(list,i,0);
//            }
//        }
        DataDto<AccountTravelExpenseVo> dataDto = new DataDto<>();
        dataDto.success(list,accountTravelExpenseIPage.getTotal());
        return dataDto;
    }
    @GetMapping(value ="/getAllTravelExpense")
    @Log("查询所有差旅报销单信息")
    @ApiOperation("查询所有差旅报销单信息")
    public DataDto getAllTravelExpense(AccountTravelExpense accountTravelExpense,TimeQo timeQo, Integer currentPage, Integer limit){
        IPage<AccountTravelExpenseVo> accountTravelExpenseIPage = accountTravelExpenseService.getAllTravelExpense(accountTravelExpense,timeQo,currentPage,limit);
        DataDto<AccountTravelExpenseVo> dataDto = new DataDto<>();
        dataDto.success(accountTravelExpenseIPage.getRecords(),accountTravelExpenseIPage.getTotal());
        return dataDto;
    }
    @GetMapping(value ="/getCheckTravelExpense")
    @Log("查询所有待审核差旅报销单信息")
    @ApiOperation("查询所有待审核差旅报销单信息")
    public DataDto getCheckTravelExpense(AccountTravelExpense accountTravelExpense,TimeQo timeQo, Integer currentPage, Integer limit){
        List<SysUserVo> sysUserVos=sysUserService.getRoleByUserId(SecurityUtils.getUserId());
        for(SysUserVo sysUserVo:sysUserVos){
            if(sysUserVo.getRoleId()==5||sysUserVo.getRoleId()==6){//总经理或副总经理显示所有审核状态的（部门主管审核过的）
                accountTravelExpense.setInvoiceType(2);
            }
            if(sysUserVo.getRoleId()==7){//财务主管显示所有打款状态的单据
                accountTravelExpense.setInvoiceType(3);
            }
            if(sysUserVo.getRoleId()==10||sysUserVo.getRoleId()==12||sysUserVo.getRoleId()==4||sysUserVo.getRoleId()==8){
                //部门主管（行政主管 业务主管 软开主管 会计）显示他手下人员的单据（发起状态）
                int deptId=sysUserService.getDeptByUserId(sysUserVo.getUserId()).getDeptId();
                accountTravelExpense.setInvoiceType(1);
                accountTravelExpense.setDeptId(deptId);
            }
        }
        IPage<AccountTravelExpenseVo> accountTravelExpenseIPage = accountTravelExpenseService.getAllTravelExpense(accountTravelExpense,timeQo,currentPage,limit);
        DataDto<AccountTravelExpenseVo> dataDto = new DataDto<>();
        dataDto.success(accountTravelExpenseIPage.getRecords(),accountTravelExpenseIPage.getTotal());
        return dataDto;
    }
    @Log("删除报销单（逻辑删除）")
    @ApiOperation("删除菜单（逻辑删除）")
    @PutMapping("/editTravelExpense")
//    @PreAuthorize("@el.check('invoice:del')")
    public DataDto editTravelExpense(String travelExpenseIds){
        DataDto dataDto = new DataDto();
        String[] travelExpenseIdStr=travelExpenseIds.split(",");
        int num=0;//删除的次数
        for (String travelExpenseId:travelExpenseIdStr) {
            int count=accountTravelExpenseService.editExpenseById(Integer.parseInt(travelExpenseId));
            num+=count;
        }
        if (num>=travelExpenseIdStr.length){
            dataDto.success("删除成功");
        }else {
            dataDto.err("只有发起状态能删除");
        }
        return dataDto;
    }
    @Log("撤回操作")
    @ApiOperation("撤回操作")
    @PutMapping("/editTravelExpenseType")
    public int editTravelExpenseType(AccountTravelExpense accountTravelExpense){
        return accountTravelExpenseService.editTravelExpenseType(accountTravelExpense);
    }

    @GetMapping(value ="/getExpenseItem")
    @Log("查询所有报销项目")
    @ApiOperation("查询所有报销项目")
    public  List<AccountExpenseItem> getExpenseItem(){
        List<AccountExpenseItem> accountExpenseItems = accountTravelExpenseService.getAllExpenseItem();
        return accountExpenseItems;
    }

    /*
     * 获取差旅报销单编号
     *
     * */
    @GetMapping("getTravelExpenseCode")
    public DataDto getTravelExpenseCode() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String nowDate = sdf.format(date);
        String travelExpenseCode = "";
        if (accountTravelExpenseService.selectMaxCode().size()>0) {
            for(AccountTravelExpense accountTravelExpense: accountTravelExpenseService.selectMaxCode()){
                if(accountTravelExpense.getTravelExpenseCode().contains(nowDate)){
                    Long orser=Long.valueOf(accountTravelExpense.getTravelExpenseCode().substring(4));
                    orser++;
                    travelExpenseCode=String.valueOf("CLBX"+orser);
                }else {
                    travelExpenseCode="CLBX"+nowDate+"0001";
                }
            }
        }else {//没有单据时
            travelExpenseCode="CLBX"+nowDate+"0001";
        }

        DataDto dto=new DataDto();
        dto.success(travelExpenseCode);
        return  dto;
    }
}
