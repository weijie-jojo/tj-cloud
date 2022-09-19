package com.ruoyi.place.vo;

import com.ruoyi.place.entity.BusinessPlace;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Transient;
import java.util.List;

@Data
public class PlaceVo extends BusinessPlace {
    @ApiModelProperty(value = "字典表中状态名")
    private String dictLabel;
    @ApiModelProperty(value = "用户表中的用户名")
    private String nickName;
    @ApiModelProperty(value = "状态集合")
    private String status;
}
