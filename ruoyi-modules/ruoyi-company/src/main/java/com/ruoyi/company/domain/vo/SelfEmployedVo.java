package com.ruoyi.company.domain.vo;

import com.ruoyi.company.domain.SelfEmployed;
import lombok.Data;

import java.util.Date;

@Data
public class SelfEmployedVo extends SelfEmployed {
    //selfNameReview
    private Integer nameStatus;//个体户名称
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
    //self_application_info
    private String oneselfApply;
    private String applyName;
    private String applyPhone;
    private String applyIdNum;
    private String applyDocumentType;
    private String contactName;
    private String contactPhone;
    private String contactIdNum;
    private String contactDocumentType;
    //self_legal_person
    private String legalPersonName;
    private String documentType;
    private String idCardNum;
    private String politicalStatus;
    private String eduation;
    private String telNum;
    private String mail;
    private String bank;
    private String bankCard;
    private String openingBank;
    private String receivingAddress;
    private String password;
    private Date initial;
    private Date ending;
    private Integer same;
    private String contactTel;
    private String gender;
    private Date dateBirth;
    private String nation;
    private String residence;
    private String personnelType;
    private String occupationalStatus;

}
