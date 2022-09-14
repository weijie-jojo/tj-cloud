package com.ruoyi.invoice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.invoice.pojo.AccountBorrow;
import com.ruoyi.invoice.qo.TimeQo;
import com.ruoyi.invoice.vo.AccountBorrowVo;
import java.util.List;

public interface AccountBorrowService {
    int editBorrow(AccountBorrow accountBorrow);
    int editBorrowType(AccountBorrow accountBorrow);
    int editBorrowByBorrowId(AccountBorrow accountBorrow);
    int addBorrow(AccountBorrow accountBorrow);
    IPage<AccountBorrowVo> getAllBorrow(AccountBorrow accountBorrow, TimeQo timeQo, Integer currentPage, Integer limit);
    int editBorrowById(int borrowId);
    List<AccountBorrow> selectMaxCode();
    IPage<AccountBorrowVo> getAllBorrow2(AccountBorrow accountBorrow, TimeQo timeQo, Integer currentPage, Integer limit);

}
