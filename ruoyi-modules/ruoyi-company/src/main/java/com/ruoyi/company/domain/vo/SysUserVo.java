package com.ruoyi.company.domain.vo;

import lombok.Data;

@Data
public class SysUserVo {
    private Long userId;
    private String nickName;
    private String userName;
    private String accountCard;
    private String accountCardBank;
    private int roleId;
    private int deptId;
}
