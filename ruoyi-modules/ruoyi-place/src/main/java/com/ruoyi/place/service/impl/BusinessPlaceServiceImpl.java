package com.ruoyi.place.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.place.entity.BusinessAgencyFee;
import com.ruoyi.place.entity.BusinessPlace;
import com.ruoyi.place.mapper.BusinessAgencyFeeMapper;
import com.ruoyi.place.mapper.BusinessAgencyFeeVoMapper;
import com.ruoyi.place.mapper.BusinessPlaceMapper;
import com.ruoyi.place.service.IBusinessPlaceService;
import com.ruoyi.place.vo.PlaceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private BusinessPlaceMapper businessPlaceMapper;
//    @Resource
//    private BusinessPlaceVoMapper businessPlaceVoMapper;
    @Resource
    private BusinessAgencyFeeMapper businessAgencyFeeMapper;
    @Resource
    private BusinessAgencyFeeVoMapper businessAgencyFeeVoMapper;
    @Override
    public List<BusinessPlace> selectByPage(List<String> userIdStr) {
//        String[] statusArr;
//        if (!StringUtils.hasText(status)){
//            statusArr=null;
//            System.out.println("空");
//        }else {
//            statusArr =status.split(",");
//            for(String arr:statusArr){
//                System.out.println("array=="+arr);
//            }
//        }
//        businessPlace.setPlaceName(JudgeNull.isNull(businessPlace.getPlaceName()));
//        businessPlace.setUserName(JudgeNull.isNull(businessPlace.getUserName()));
//        pageQo.setPage(JudgeNull.isNull(pageQo.getPage()));
//        pageQo.setSize(JudgeNull.isNull(pageQo.getSize()));
//        System.out.println("pageQo"+pageQo);
//        IPage<PlaceVo> placeVos=businessPlaceMapper.selectJoinPage(new Page<>(pageQo.getPage(),pageQo.getSize()), PlaceVo.class,
//                new MPJQueryWrapper<BusinessPlace>()
//                        .select("t.place_id,t.place_code,t.place_name,t.place_alias_name,t.place_type,t.place_linkman," +
//                                "t.place_tel,t.place_email,t.place_open_bank,t.place_bank_account,t.place_star_level," +
//                                "t.place_status,t.regist_time,t.user_id,t.is_delete,t.remark,t.create_time,t.create_by," +
//                                "t.update_by,t.update_time,t.user_name,A.nick_name,B.dict_label")
//                        .innerJoin("sys_user A on t.user_id=A.user_id")
//                        .innerJoin("sys_dict_data B on t.place_status=B.dict_value")
//                        .in(statusArr!=null,"t.place_status",statusArr)
//                        .eq("t.is_delete",1)
//                        .eq("B.dict_type","place_status")
////                        .in(businessPlace.getUserId()!=null,"t.user_id", businessPlace.getUserId())
//                        .in(userIdStr!=null,"t.user_id", userIdStr)
////                        .eq(businessPlace.getUserId()!=null,"t.user_id", businessPlace.getUserId())
//                        .like(businessPlace.getPlaceName()!=null,"t.place_name", businessPlace.getPlaceName())
//                        .orderByDesc("t.place_id"));

//        return placeVos;
//        System.out.println("userIdStr=="+userIdStr);
        return businessPlaceMapper.selectBusinessPlaceJoin(userIdStr);
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
