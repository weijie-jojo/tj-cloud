package com.ruoyi.place.entity.vo;

import lombok.Data;

@Data
public class SysUserVo {
    private Long userId;
    private String nickName;
    private String accountCard;
    private String accountCardBank;
    private int roleId;
    private int deptId;
}
