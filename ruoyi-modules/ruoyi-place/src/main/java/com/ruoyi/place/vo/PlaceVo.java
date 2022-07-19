package com.ruoyi.place.vo;

import com.ruoyi.place.entity.BusinessPlace;
import lombok.Data;

import javax.persistence.Transient;
import java.util.List;

@Data
public class PlaceVo extends BusinessPlace {
    private String dictLabel;//字典表中状态名
    private String nickName;//用户表中的用户名
    private String status;//状态集合
//    @Transient
//    private String[] userIdStr;//用户id集合

}
