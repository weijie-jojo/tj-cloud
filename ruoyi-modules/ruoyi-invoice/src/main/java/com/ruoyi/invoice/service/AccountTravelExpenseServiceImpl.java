package com.ruoyi.invoice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.ruoyi.invoice.mapper.AccountExpenseItemMapper;
import com.ruoyi.invoice.mapper.AccountTravelExpenseMapper;
import com.ruoyi.invoice.mapper.SysUserMapper;
import com.ruoyi.invoice.pojo.AccountExpenseItem;
import com.ruoyi.invoice.pojo.AccountTravelExpense;
import com.ruoyi.invoice.qo.TimeQo;
import com.ruoyi.invoice.util.JudgeNull;
import com.ruoyi.invoice.vo.AccountTravelExpenseVo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AccountTravelExpenseServiceImpl implements AccountTravelExpenseService {

    private final AccountTravelExpenseMapper accountTravelExpenseMapper;
    private final AccountExpenseItemMapper accountExpenseItemMapper;
    private final SysUserMapper sysUserMapper;
    /*
     *编辑修改
     * */
    @Override
    public int editTravelExpense(AccountTravelExpense accountTravelExpense) {
        UpdateWrapper<AccountTravelExpense> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",accountTravelExpense.getId());
        return accountTravelExpenseMapper.update(accountTravelExpense,updateWrapper);
    }
    @Override
    public int editTravelExpenseById(AccountTravelExpense accountTravelExpense) {
        UpdateWrapper<AccountTravelExpense> updateWrapper =new UpdateWrapper<>();
        updateWrapper.eq("id", accountTravelExpense.getId());
        updateWrapper.set("invoice_type",accountTravelExpense.getInvoiceType());
        updateWrapper.set(accountTravelExpense.getExpenseImage2()!=null,"expense_image2",accountTravelExpense.getExpenseImage2());
        accountTravelExpense.setGmCheck(JudgeNull.isNull(accountTravelExpense.getGmCheck()));
        accountTravelExpense.setDmCheck(JudgeNull.isNull(accountTravelExpense.getDmCheck()));
        accountTravelExpense.setFinanceCheck(JudgeNull.isNull(accountTravelExpense.getFinanceCheck()));
        System.out.println("getGmCheck=="+ accountTravelExpense.getGmCheck());
        System.out.println("getDmCheck=="+ accountTravelExpense.getDmCheck());
        System.out.println("getFinanceCheck=="+ accountTravelExpense.getFinanceCheck());
        if (accountTravelExpense.getGmCheck()!=null){
            updateWrapper.set("gm_check", accountTravelExpense.getGmCheck());
        }
        if (accountTravelExpense.getDmCheck()!=null){
            updateWrapper.set("dm_check", accountTravelExpense.getDmCheck());
        }
        if (accountTravelExpense.getFinanceCheck()!=null){
            updateWrapper.set("finance_check", accountTravelExpense.getFinanceCheck());
        }
        return accountTravelExpenseMapper.update(null,updateWrapper);
    }

    @Override
    public int addTravelExpense(AccountTravelExpense accountTravelExpense) {
        if(accountTravelExpense.getTravelDate1().contains("Invalid")){
            accountTravelExpense.setTravelDate1(null);
        };
        if(accountTravelExpense.getTravelDate2().contains("Invalid")){
            accountTravelExpense.setTravelDate2(null);
        };
        if(accountTravelExpense.getTravelDate3().contains("Invalid")){
            accountTravelExpense.setTravelDate3(null);
        };
        System.out.println("getTravelDate1=="+accountTravelExpense.getTravelDate1());
        System.out.println("getTravelDate3=="+accountTravelExpense.getTravelDate3());
        accountTravelExpense.setIsDeleted(0);
        return accountTravelExpenseMapper.insert(accountTravelExpense);
    }

    @Override
    public IPage<AccountTravelExpenseVo> getAllTravelExpense(AccountTravelExpense accountTravelExpense, TimeQo timeQo, Integer currentPage, Integer limit) {
        timeQo.setStartTime(JudgeNull.isNull(timeQo.getStartTime()));
        timeQo.setEndTime(JudgeNull.isNull(timeQo.getEndTime()));
        accountTravelExpense.setInvoiceType(JudgeNull.isNull(accountTravelExpense.getInvoiceType()));
        accountTravelExpense.setDeptId(JudgeNull.isNull(accountTravelExpense.getDeptId()));
        Integer currentPages=JudgeNull.isNull(currentPage);
        Integer limits=JudgeNull.isNull(limit);
        IPage<AccountTravelExpenseVo> sysTravelExpenseIPage = accountTravelExpenseMapper.selectJoinPage(new Page<>(currentPages,limits), AccountTravelExpenseVo.class,
                new MPJQueryWrapper<AccountTravelExpense>()
                        .selectAll(AccountTravelExpense.class)
                        .select("A.dict_label")
                        .select("B.user_name")
                        .select("B.nick_name")
                        .innerJoin("sys_dict_data A on t.invoice_type=A.dict_value")
                        .innerJoin("sys_user B on t.create_user=B.user_id")
                        .eq("t.is_deleted",0)
                        .eq("A.dict_type","invoice_type")
                        .eq(accountTravelExpense.getTravelExpenseCode()!=null,"t.travel_expense_code", accountTravelExpense.getTravelExpenseCode())
                        .eq(accountTravelExpense.getDeptId()!=null,"t.dept_id", accountTravelExpense.getDeptId())
                        .eq(accountTravelExpense.getCreateUser()!=null,"t.create_user", accountTravelExpense.getCreateUser())
                        .eq(accountTravelExpense.getInvoiceType()!=null,"t.invoice_type", accountTravelExpense.getInvoiceType())
                        .ge(timeQo.getStartTime()!=null,"t.create_time",timeQo.getStartTime())
                        .le(timeQo.getEndTime()!=null,"t.create_time",timeQo.getEndTime())
                        .orderByDesc("t.id"));
        return sysTravelExpenseIPage;
    }
    /*
     * 撤回操作
     * */
    @Override
    public int editTravelExpenseType(AccountTravelExpense accountTravelExpense) {
//        List<SysUserVo> sysUserVos=sysUserMapper.getRoleByUserId(SecurityUtils.getCurrentUserId());
        UpdateWrapper<AccountTravelExpense> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("id",accountTravelExpense.getId());
        updateWrapper.set("invoice_type",1);
        updateWrapper.set("gm_check",null);
        updateWrapper.set("dm_check",null);
//        for(SysUserVo sysUserVo:sysUserVos){
//            if(sysUserVo.getRoleId()==5){//总经理显示所有审核状态的（部门主管审核过的）
//                updateWrapper.set("gm_check",null);
//            }
//            if(sysUserVo.getRoleId()==7){//财务主管撤回动作
//                updateWrapper.set("invoice_type",2);
//                updateWrapper.set("finance_check",null);
//                updateWrapper.set("expense_image2",null);
//            }
//            if(sysUserVo.getRoleId()==10||sysUserVo.getRoleId()==12){//部门主管撤回动作
//                updateWrapper.set("invoice_type",1);
//                updateWrapper.set("dm_check",null);
//            }
//        }
        return accountTravelExpenseMapper.update(null,updateWrapper);
    }

    /*
    *
    * 删除报销单（逻辑删除）
    * */
    @Override
    public int editExpenseById(int travelExpenseId) {
        UpdateWrapper<AccountTravelExpense> updateWrapper =new UpdateWrapper<>();
        updateWrapper.eq("id",travelExpenseId);
        updateWrapper.set("is_deleted",1);

        QueryWrapper<AccountTravelExpense> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",travelExpenseId);
        AccountTravelExpense accountTravelExpense= accountTravelExpenseMapper.selectOne(queryWrapper);

        if(accountTravelExpense.getInvoiceType()==1){//只有发起状态的能删除
            return  accountTravelExpenseMapper.update(null,updateWrapper);
        }else {
            return 0;
        }
    }

    @Override
    public List<AccountExpenseItem> getAllExpenseItem() {
        QueryWrapper<AccountExpenseItem> queryWrapper=new QueryWrapper<>();
        return accountExpenseItemMapper.selectList(queryWrapper);
    }

    /*
     * 查询数据库里面最后一条数据
     *
     * */
    @Override
    public List<AccountTravelExpense> selectMaxCode(){
        IPage<AccountTravelExpense> iPage=new Page<>(1,1);
        return accountTravelExpenseMapper.selectPage(iPage,
                new QueryWrapper<AccountTravelExpense>()
                        .orderByDesc("id")).getRecords();
    }
}
