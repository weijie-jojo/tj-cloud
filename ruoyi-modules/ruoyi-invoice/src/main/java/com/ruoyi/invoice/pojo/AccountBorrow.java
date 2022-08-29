/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package com.ruoyi.invoice.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author me.zhengjie
* @date 2022-03-23
**/
@Entity
@Data
@Table(name="account_borrow")
public class AccountBorrow implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrow_id")
    private int borrowId;

    @Column(name = "dept_name")
    @ApiModelProperty(value = "部门名")
    private String deptName;

    @Column(name = "borrow_date")
    @ApiModelProperty(value = "借款日期")
    private String borrowDate;


    @Column(name = "pay_date")
    @ApiModelProperty(value = "还款日期")
    private String payDate;

    @Column(name = "total_money")
    @ApiModelProperty(value = "总借款金额")
    private Double totalMoney;

    @Column(name = "payway_id")
    @ApiModelProperty(value = "付款方式")
    private Integer paywayId;

    @Column(name = "job")
    @ApiModelProperty(value = "职务")
    private String job;

    @Column(name = "borrow_name")
    @ApiModelProperty(value = "借款人名")
    private String borrowName;


    @Column(name = "borrow_desc")
    @ApiModelProperty(value = "借支事由")
    private String borrowDesc;

    @Column(name = "is_deleted")
    @ApiModelProperty(value = "是否删除;1:删除,0:未删除")
    private Integer isDeleted;

    @Column(name = "borrow_code")
    @ApiModelProperty(value = "借支单编号")
    private String borrowCode;

    @Column(name = "pay_company_id")
    @ApiModelProperty(value = "借款单位")
    private String payCompanyId;

    @Column(name = "card_pay_id")
    @ApiModelProperty(value = "借款单位银行卡号")
    private String cardPayId;

    @Column(name = "bank_name_pay")
    @ApiModelProperty(value = "借款单位银行名")
    private String bankNamePay;

    @Column(name = "get_user_id")
    @ApiModelProperty(value = "收款人id")
    private String getUserId;

    @Column(name = "card_get_id")
    @ApiModelProperty(value = "收款人银行卡号")
    private String cardGetId;

    @Column(name = "bank_name_get")
    @ApiModelProperty(value = "收款人银行名")
    private String bankNameGet;

    @Column(name = "borrow_image")
    @ApiModelProperty(value = "打款凭证")
    private String borrowImage;

    @Column(name = "borrow_image2")
    @ApiModelProperty(value = "还款凭证")
    private String borrowImage2;

    @Column(name = "gm_check")
    @ApiModelProperty(value = "总经理审核")
    private String gmCheck;

    @Column(name = "dm_check")
    @ApiModelProperty(value = "部门主管审核")
    private String dmCheck;

    @Column(name = "finance_check")
    @ApiModelProperty(value = "财务审核")
    private String financeCheck;

    @Column(name = "invoice_type")
    @ApiModelProperty(value = "单据状态（关联字典表）")
    private Integer invoiceType;

    @Column(name = "create_user")
    @ApiModelProperty(value = "报销人id(关联sys_user表)")
    private Long createUser;

    @Column(name = "dept_id")
    @ApiModelProperty(value = "报销人所属部门id")
    private Integer deptId;

    @Column(name = "step_type")
    @ApiModelProperty(value = "进度状态（1 办理中、2 完成、3 异常）")
    private Integer stepType;
}