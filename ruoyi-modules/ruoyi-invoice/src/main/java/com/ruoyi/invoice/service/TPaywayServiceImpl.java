package com.ruoyi.invoice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.invoice.mapper.TPaywayMapper;
import com.ruoyi.invoice.pojo.SysDictDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class TPaywayServiceImpl implements TPaywayService {
    @Resource
    private TPaywayMapper tPaywayMapper;


    @Override
    public List<SysDictDetail> selectAll() {
        QueryWrapper<SysDictDetail> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("dict_id",10);
        return tPaywayMapper.selectList(queryWrapper);
    }
}
