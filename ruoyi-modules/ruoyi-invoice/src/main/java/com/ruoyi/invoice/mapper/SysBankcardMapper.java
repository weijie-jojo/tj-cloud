package com.ruoyi.invoice.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.ruoyi.invoice.pojo.SysBankcard;
import com.ruoyi.invoice.vo.SysBankcardVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SysBankcardMapper extends MPJBaseMapper<SysBankcard> {
    @Select("SELECT t.bankcard_id, t.bankcard_code, t.bank_id,t.user_id,B.bank_name FROM sys_bankcard t INNER LEFT JOIN sys_bank B ON t.bank_id=B.id WHERE t.bankcard_id = #{id}")
    SysBankcardVo selectBankNameBycardId(@Param("id") int bankcardId);
}
