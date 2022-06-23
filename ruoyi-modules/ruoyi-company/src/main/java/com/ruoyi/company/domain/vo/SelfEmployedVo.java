package com.ruoyi.company.domain.vo;

import com.ruoyi.company.domain.SelfEmployed;
import lombok.Data;

@Data
public class SelfEmployedVo extends SelfEmployed {
    //selfNameReview表
    private String nameStatus;//个体户名称
    private String titleType;
    private String administrativeDivision;
    private String industry;
    private String organizationalForm;
    private String administrativeRegion;
    private String registrationAuthority;
    private String random;
    private String fontSize1;
    private String fontSize2;
    private String fontSize3;
    private String poposedName1;
    private String poposedName2;
    private String poposedName3;
    private String userName;
}
