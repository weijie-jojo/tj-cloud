package com.ruoyi.place.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.ruoyi.place.entity.BusinessAgencyFee;
import com.ruoyi.place.entity.BusinessPlace;
import com.ruoyi.place.mapper.BusinessAgencyFeeMapper;
import com.ruoyi.place.mapper.BusinessAgencyFeeVoMapper;
import com.ruoyi.place.mapper.BusinessPlaceMapper;
import com.ruoyi.place.qo.PageQo;
import com.ruoyi.place.service.IBusinessPlaceService;
import com.ruoyi.place.util.JudgeNull;
import com.ruoyi.place.vo.BusinessAgencyFeeVo;
import com.ruoyi.place.vo.PlaceVo;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 平台渠道商 服务实现类
 * </p>
 *
 * @author lcc
 * @since 2022-06-03
 */
@Service
public class BusinessPlaceServiceImpl  implements IBusinessPlaceService {

    @Resource
    private BusinessPlaceMapper businessPlaceMapper;
    @Resource
    private BusinessAgencyFeeMapper businessAgencyFeeMapper;
    @Resource
    private BusinessAgencyFeeVoMapper businessAgencyFeeVoMapper;
    @Override
    public IPage<PlaceVo> selectByPage(BusinessPlace businessPlace, PageQo pageQo, String status) {
        String[] statusArr;
        if (!StringUtils.hasText(status)){
            statusArr=null;
            System.out.println("空");
        }else {
            statusArr =status.split(",");
            for(String arr:statusArr){
                System.out.println("array=="+arr);
            }
        }
        businessPlace.setPlaceName(JudgeNull.isNull(businessPlace.getPlaceName()));
        businessPlace.setUserName(JudgeNull.isNull(businessPlace.getUserName()));
        pageQo.setPage(JudgeNull.isNull(pageQo.getPage()));
        pageQo.setSize(JudgeNull.isNull(pageQo.getSize()));
        IPage<PlaceVo> placeVos=businessPlaceMapper.selectJoinPage(new Page<>(pageQo.getPage(),pageQo.getSize()), PlaceVo.class,
                new MPJQueryWrapper<BusinessPlace>()
                        .selectAll(BusinessPlace.class)
                        .select("A.nick_name")
                        .select("B.dict_label")
                        .innerJoin("sys_user A on t.user_id=A.user_id")
                        .innerJoin("sys_dict_data B on t.place_status=B.dict_value")
                        .in(statusArr!=null,"t.place_status",statusArr)
                        .eq("t.is_delete",1)
                        .eq("B.dict_type","place_status")
                        .eq(businessPlace.getUserName()!=null,"t.user_name", businessPlace.getUserName())
                        .like(businessPlace.getUserName()!=null,"t.user_name",businessPlace.getUserName())
                        .like(businessPlace.getPlaceName()!=null,"t.place_name", businessPlace.getPlaceName())
                        .orderByDesc("t.place_id"));
        return placeVos;
    }

    @Override
    public Integer addPlace(BusinessPlace businessPlace) {
        businessPlace.setIsDelete(true);
        businessPlace.setPlaceStatus(0);
        return businessPlaceMapper.insert(businessPlace);
    }

    @Override
    public Integer delPlace(String placeCode) {
        UpdateWrapper<BusinessPlace> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("place_code",placeCode);
        updateWrapper.set("is_delete",false);
        UpdateWrapper<BusinessAgencyFee> updateWrapper2=new UpdateWrapper<>();
        updateWrapper2.eq("place_code",placeCode);
        updateWrapper2.set("is_delete",false);
        Integer num1=businessAgencyFeeMapper.update(null,updateWrapper2);
        Integer num2=businessPlaceMapper.update(null,updateWrapper);
        return  num1+num2;
    }

    @Override
    public Integer editPlace(BusinessPlace businessPlace, BusinessAgencyFee businessAgencyFee) {
        Integer num1=businessAgencyFeeMapper.updateById(businessAgencyFee);
        Integer num2=businessPlaceMapper.updateById(businessPlace);
        return  num1+num2;
    }
    /*
    *
    * 改变状态
    * */
    @Override
    public Integer editPlace2(BusinessPlace businessPlace) {
        Integer num=businessPlaceMapper.updateById(businessPlace);
        return  num;
    }
    /*
     * 查询数据库里面最后一条数据
     *
     * */
    @Override
    public List<BusinessPlace> selectMaxCode(){
        IPage<BusinessPlace> iPage=new Page<>(1,1);
        return businessPlaceMapper.selectPage(iPage,
                new QueryWrapper<BusinessPlace>()
                        .orderByDesc("place_id")).getRecords();
    }
}
