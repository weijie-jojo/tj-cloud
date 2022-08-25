package com.ruoyi.invoice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.ruoyi.invoice.mapper.AccountExpenseMapper;
import com.ruoyi.invoice.mapper.SysUserMapper;
import com.ruoyi.invoice.pojo.AccountExpense;
import com.ruoyi.invoice.qo.TimeQo;
import com.ruoyi.invoice.util.JudgeNull;
import com.ruoyi.invoice.vo.AccountExpenseVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class AccountExpenseServiceImpl implements AccountExpenseService {
    @Resource
    private AccountExpenseMapper accountExpenseMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    /*
     * 查询数据库里面最后一条数据
     *
     * */
    @Override
    public List<AccountExpense> selectMaxCode(){
        IPage<AccountExpense> iPage=new Page<>(1,1);
        return accountExpenseMapper.selectPage(iPage,
                new QueryWrapper<AccountExpense>()
                        .orderByDesc("expense_id")).getRecords();
    }
    @Override
    public int editExpenseByExpenseId(AccountExpense accountExpense) {
        UpdateWrapper<AccountExpense> updateWrapper =new UpdateWrapper<>();
        updateWrapper.eq("expense_id", accountExpense.getExpenseId());
        updateWrapper.set("invoice_type",accountExpense.getInvoiceType());
        updateWrapper.set(accountExpense.getExpenseImage2()!=null,"expense_image2",accountExpense.getExpenseImage2());
        accountExpense.setGmCheck(JudgeNull.isNull(accountExpense.getGmCheck()));
        accountExpense.setDmCheck(JudgeNull.isNull(accountExpense.getDmCheck()));
        accountExpense.setFinanceCheck(JudgeNull.isNull(accountExpense.getFinanceCheck()));
        accountExpense.setStepType(JudgeNull.isNull(accountExpense.getStepType()));
        updateWrapper.set(accountExpense.getStepType()!=null,"step_type",accountExpense.getStepType());
        if (accountExpense.getGmCheck()!=null){
            updateWrapper.set("gm_check", accountExpense.getGmCheck());
        }
        if (accountExpense.getDmCheck()!=null){
            updateWrapper.set("dm_check", accountExpense.getDmCheck());
        }
        if (accountExpense.getFinanceCheck()!=null){
            updateWrapper.set("finance_check", accountExpense.getFinanceCheck());
        }
        return accountExpenseMapper.update(null,updateWrapper);
    }
    @Override
    public int addExpense(AccountExpense accountExpense) {
        System.out.println("getFilePath=="+ accountExpense.getExpenseImage());
        return accountExpenseMapper.insert(accountExpense);
    }
    /*
    *编辑修改
    * */
    @Override
    public int editExpense(AccountExpense accountExpense) {
        UpdateWrapper<AccountExpense> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("expense_id",accountExpense.getExpenseId());
        return accountExpenseMapper.update(accountExpense,updateWrapper);
    }
    /*
    * 撤回操作
    * */
    @Override
    public int editExpenseType(AccountExpense accountExpense) {
//        List<SysUserVo> sysUserVos=sysUserMapper.getRoleByUserId(SecurityUtils.getCurrentUserId());
        UpdateWrapper<AccountExpense> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("expense_id",accountExpense.getExpenseId());
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
//            }
//            if(sysUserVo.getRoleId()==10||sysUserVo.getRoleId()==12){//部门主管撤回动作
//                updateWrapper.set("invoice_type",1);
//                updateWrapper.set("dm_check",null);
//                updateWrapper.set("expense_image2",null);
//            }
//        }
        return accountExpenseMapper.update(null,updateWrapper);
    }
    /*
    *
    * 删除
    * */
    @Override
    public int editExpenseById(int expenseId) {
        UpdateWrapper<AccountExpense> updateWrapper =new UpdateWrapper<>();
        updateWrapper.eq("expense_id",expenseId);
        updateWrapper.set("is_deleted",1);

        QueryWrapper<AccountExpense> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("expense_id",expenseId);
        AccountExpense accountExpense= accountExpenseMapper.selectOne(queryWrapper);

        if(accountExpense.getInvoiceType()==1){//只有发起状态的能删除
            return  accountExpenseMapper.update(null,updateWrapper);
        }else {
            return 0;
        }

    }
    @Override
    public int checkExpenseById(int expenseId) {
        UpdateWrapper<AccountExpense> updateWrapper =new UpdateWrapper<>();
        updateWrapper.eq("expense_id",expenseId);
        updateWrapper.set("check",1);
        return accountExpenseMapper.update(null,updateWrapper);
    }
    @Override
    public IPage<AccountExpenseVo> selectAllExpense(AccountExpense accountExpense, TimeQo timeQo, Integer currentPage, Integer limit) {
        accountExpense.setInvoiceType(JudgeNull.isNull(accountExpense.getInvoiceType()));
        accountExpense.setDeptId(JudgeNull.isNull(accountExpense.getDeptId()));
        accountExpense.setExpenseCode(JudgeNull.isNull(accountExpense.getExpenseCode()));
        timeQo.setStartTime(JudgeNull.isNull(timeQo.getStartTime()));
        timeQo.setEndTime(JudgeNull.isNull(timeQo.getEndTime()));
        Integer currentPages=JudgeNull.isNull(currentPage);
        Integer limits=JudgeNull.isNull(limit);
        accountExpense.setStepType(JudgeNull.isNull(accountExpense.getStepType()));
        System.out.println("getInvoiceType=="+accountExpense.getInvoiceType());
        IPage<AccountExpenseVo> sysExpenseVoIPage = accountExpenseMapper.selectJoinPage(new Page(currentPages,limits),AccountExpenseVo.class,
                new MPJQueryWrapper<AccountExpense>()
                        .selectAll(AccountExpense.class)
                        .select("A.dict_label")
                        .select("B.user_name")
                        .select("B.nick_name")
                        .select("C.dept_name")
                        .innerJoin("sys_dict_data A on t.invoice_type=A.dict_value")
                        .innerJoin("sys_user B on t.create_user=B.user_id")
                        .innerJoin("sys_dept C on t.dept_id=C.dept_id")
                        .eq("t.is_deleted",0)
                        .eq("A.dict_type","invoice_type")
                        .eq(accountExpense.getStepType()!=null,"t.step_type",accountExpense.getStepType())
                        .eq(accountExpense.getExpenseCode()!=null,"t.expense_code", accountExpense.getExpenseCode())
                        .eq(accountExpense.getDeptId()!=null,"t.dept_id", accountExpense.getDeptId())
                        .eq(accountExpense.getInvoiceType()!=null,"t.invoice_type", accountExpense.getInvoiceType())
                        .eq(accountExpense.getCreateUser()!=null,"t.create_user", accountExpense.getCreateUser())
                        .ge(timeQo.getStartTime()!=null,"t.create_time",timeQo.getStartTime())
                        .le(timeQo.getEndTime()!=null,"t.create_time",timeQo.getEndTime())
                        .orderByDesc("t.expense_id"));
        return sysExpenseVoIPage;
    }

}
