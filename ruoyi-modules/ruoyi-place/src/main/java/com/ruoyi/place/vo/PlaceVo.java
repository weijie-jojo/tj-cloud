package com.ruoyi.place.vo;

import com.ruoyi.place.entity.BusinessPlace;
import lombok.Data;

@Data
public class PlaceVo extends BusinessPlace {
    private String dictLabel;//字典表中状态名
    private String nickName;//用户表中的用户名
}
