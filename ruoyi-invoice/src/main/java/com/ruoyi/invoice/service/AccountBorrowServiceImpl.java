package com.ruoyi.invoice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.ruoyi.invoice.mapper.AccountBorrowMapper;
import com.ruoyi.invoice.mapper.SysUserMapper;
import com.ruoyi.invoice.pojo.AccountBorrow;
import com.ruoyi.invoice.qo.TimeQo;
import com.ruoyi.invoice.util.JudgeNull;
import com.ruoyi.invoice.vo.AccountBorrowVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class AccountBorrowServiceImpl implements AccountBorrowService {
    @Resource
    private AccountBorrowMapper accountBorrowMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    private static final Logger logger = LoggerFactory.getLogger(AccountBorrowServiceImpl.class);

    /*
     *编辑修改
     * */
    @Override
    public int editBorrow(AccountBorrow accountBorrow) {
        UpdateWrapper<AccountBorrow> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("borrow_id",accountBorrow.getBorrowId());
        return accountBorrowMapper.update(accountBorrow,updateWrapper);
    }

    @Override
    public int editBorrowByBorrowId(AccountBorrow accountBorrow) {
        UpdateWrapper<AccountBorrow> updateWrapper =new UpdateWrapper<>();
        updateWrapper.eq("borrow_id", accountBorrow.getBorrowId());
        updateWrapper.set("invoice_type",accountBorrow.getInvoiceType());
        updateWrapper.set(accountBorrow.getBorrowImage()!=null,"borrow_image",accountBorrow.getBorrowImage());
        accountBorrow.setGmCheck(JudgeNull.isNull(accountBorrow.getGmCheck()));
        accountBorrow.setDmCheck(JudgeNull.isNull(accountBorrow.getDmCheck()));
        accountBorrow.setFinanceCheck(JudgeNull.isNull(accountBorrow.getFinanceCheck()));
        System.out.println("getGmCheck=="+ accountBorrow.getGmCheck());
        System.out.println("getDmCheck=="+ accountBorrow.getDmCheck());
        System.out.println("getFinanceCheck=="+ accountBorrow.getFinanceCheck());
        if (accountBorrow.getGmCheck()!=null){
            updateWrapper.set("gm_check", accountBorrow.getGmCheck());
        }
        if (accountBorrow.getDmCheck()!=null){
            updateWrapper.set("dm_check", accountBorrow.getDmCheck());
        }
        if (accountBorrow.getFinanceCheck()!=null){
            updateWrapper.set("finance_check", accountBorrow.getFinanceCheck());
        }
        return accountBorrowMapper.update(null,updateWrapper);
    }

    @Override
    public int addBorrow(AccountBorrow accountBorrow) {
        accountBorrow.setIsDeleted(0);
        return accountBorrowMapper.insert(accountBorrow);
    }

    @Override
    public IPage<AccountBorrowVo> getAllBorrow(AccountBorrow accountBorrow, TimeQo timeQo, Integer currentPage, Integer limit) {
        timeQo.setStartTime(JudgeNull.isNull(timeQo.getStartTime()));
        timeQo.setEndTime(JudgeNull.isNull(timeQo.getEndTime()));
        accountBorrow.setInvoiceType(JudgeNull.isNull(accountBorrow.getInvoiceType()));
        accountBorrow.setDeptId(JudgeNull.isNull(accountBorrow.getDeptId()));
        accountBorrow.setBorrowCode(JudgeNull.isNull(accountBorrow.getBorrowCode()));
        Integer currentPages=JudgeNull.isNull(currentPage);
        Integer limits=JudgeNull.isNull(limit);
        IPage<AccountBorrowVo> sysTravelExpenseIPage = accountBorrowMapper.selectJoinPage(new Page<>(currentPages,limits), AccountBorrowVo.class,
                new MPJQueryWrapper<AccountBorrow>()
                        .selectAll(AccountBorrow.class)
                        .select("A.label")
                        .select("B.username")
                        .select("B.nick_name")
                        .innerJoin("sys_dict_detail A on t.invoice_type=A.value")
                        .innerJoin("sys_user B on t.create_user=B.user_id")
                        .eq("t.is_deleted",0)
                        .eq("A.dict_id",7)
                        .eq(accountBorrow.getBorrowCode()!=null,"t.borrow_code", accountBorrow.getBorrowCode())
                        .eq(accountBorrow.getDeptId()!=null,"t.dept_id", accountBorrow.getDeptId())
                        .eq(accountBorrow.getCreateUser()!=null,"t.create_user", accountBorrow.getCreateUser())
                        .eq(accountBorrow.getInvoiceType()!=null,"t.invoice_type", accountBorrow.getInvoiceType())
                        .ge(timeQo.getStartTime()!=null,"t.create_time",timeQo.getStartTime())
                        .le(timeQo.getEndTime()!=null,"t.create_time",timeQo.getEndTime()));
        return sysTravelExpenseIPage;
    }

    /*
     *
     * 删除报销单（逻辑删除）
     * */
    @Override
    public int editBorrowById(int borrowId) {
        logger.info("sa");
        UpdateWrapper<AccountBorrow> updateWrapper =new UpdateWrapper<>();
        updateWrapper.eq("borrow_id",borrowId);
        updateWrapper.set("is_deleted",1);

        QueryWrapper<AccountBorrow> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("borrow_id",borrowId);
        AccountBorrow accountBorrow= accountBorrowMapper.selectOne(queryWrapper);

        if(accountBorrow.getInvoiceType()==1){//只有发起状态的能删除
            return  accountBorrowMapper.update(null,updateWrapper);
        }else {
            return 0;
        }
    }
    /*
     * 撤回操作
     * */
    @Override
    public int editBorrowType(AccountBorrow accountBorrow) {
//        List<SysUserVo> sysUserVos=sysUserMapper.getRoleByUserId(SecurityUtils.getCurrentUserId());
        UpdateWrapper<AccountBorrow> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("borrow_id",accountBorrow.getBorrowId());
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
        return accountBorrowMapper.update(null,updateWrapper);
    }

    /*
     * 查询数据库里面最后一条数据
     *
     * */
    @Override
    public List<AccountBorrow> selectMaxCode(){
        IPage<AccountBorrow> iPage=new Page<>(1,1);
        return accountBorrowMapper.selectPage(iPage,
                new QueryWrapper<AccountBorrow>()
                        .orderByDesc("borrow_id")).getRecords();
    }

}
