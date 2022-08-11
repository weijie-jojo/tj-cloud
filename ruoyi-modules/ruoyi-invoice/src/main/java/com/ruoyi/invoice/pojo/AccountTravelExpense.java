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
@Table(name="account_travel_expense")
public class AccountTravelExpense implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "id")
    private int id;

    @Column(name = "job")
    @ApiModelProperty(value = "job")
    private String job;

    @Column(name = "together_num")
    @ApiModelProperty(value = "togetherNum")
    private int togetherNum;

    @Column(name = "reason")
    @ApiModelProperty(value = "reason")
    private String reason;

    @Column(name = "create_time")
    @ApiModelProperty(value = "报销时间")
    private String createTime;

    @Column(name = "total_all_money")
    @ApiModelProperty(value = "总费用")
    private Double totalAllMoney;

    @Column(name = "is_deleted")
    @ApiModelProperty(value = "是否显示")
    private Integer isDeleted;

    @Column(name = "travel_expense_code")
    @ApiModelProperty(value = "travelExpenseCode")
    private String travelExpenseCode;

    @Column(name = "invoice_type")
    @ApiModelProperty(value = "单据状态（关联字典表）")
    private Integer invoiceType;

    @Column(name = "create_user")
    @ApiModelProperty(value = "报销人id(关联sys_user表)")
    private Long createUser;

    @Column(name = "user_getid")
    @ApiModelProperty(value = "user_getid")
    private String userGetid;

    @Column(name = "bankcard_getid")
    @ApiModelProperty(value = "bankcard_getid")
    private String bankcardGetid;

    @Column(name = "bank_getname")
    @ApiModelProperty(value = "bank_getname")
    private String bankGetname;

    @Column(name = "company_payId")
    @ApiModelProperty(value = "company_payId")
    private String companyPayId;

    @Column(name = "bank_paycode")
    @ApiModelProperty(value = "bank_paycode")
    private String bankPaycode;

    @Column(name = "bank_payname")
    @ApiModelProperty(value = "bank_payname")
    private String bankPayname;

    @Column(name = "travel_date1")
    @ApiModelProperty(value = "travel_date1")
    private String travelDate1;
    @Column(name = "place1")
    @ApiModelProperty(value = "place1")
    private String place1;
    @Column(name = "travel_way1")
    @ApiModelProperty(value = "travelWay1")
    private String travelWay1;
    @Column(name = "days1")
    @ApiModelProperty(value = "days1")
    private String days1;
    @Column(name = "traffic1")
    @ApiModelProperty(value = "traffic1")
    private String traffic1;
    @Column(name = "stay1")
    @ApiModelProperty(value = "stay1")
    private String stay1;
    @Column(name = "subsidy1")
    @ApiModelProperty(value = "subsidy1")
    private String subsidy1;
    @Column(name = "other1")
    @ApiModelProperty(value = "other1")
    private String other1;

    @Column(name = "travel_date2")
    @ApiModelProperty(value = "travel_date2")
    private String travelDate2;
    @Column(name = "place2")
    @ApiModelProperty(value = "place2")
    private String place2;
    @Column(name = "travel_way2")
    @ApiModelProperty(value = "travelWay2")
    private String travelWay2;
    @Column(name = "days2")
    @ApiModelProperty(value = "days2")
    private String days2;
    @Column(name = "traffic2")
    @ApiModelProperty(value = "traffic2")
    private String traffic2;
    @Column(name = "stay2")
    @ApiModelProperty(value = "stay2")
    private String stay2;
    @Column(name = "subsidy2")
    @ApiModelProperty(value = "subsidy2")
    private String subsidy2;
    @Column(name = "other2")
    @ApiModelProperty(value = "other2")
    private String other2;

    @Column(name = "travel_date3")
    @ApiModelProperty(value = "travel_date3")
    private String travelDate3;
    @Column(name = "place3")
    @ApiModelProperty(value = "place3")
    private String place3;
    @Column(name = "travel_way3")
    @ApiModelProperty(value = "travelWay3")
    private String travelWay3;
    @Column(name = "days3")
    @ApiModelProperty(value = "days3")
    private String days3;
    @Column(name = "traffic3")
    @ApiModelProperty(value = "traffic3")
    private String traffic3;
    @Column(name = "stay3")
    @ApiModelProperty(value = "stay3")
    private String stay3;
    @Column(name = "subsidy3")
    @ApiModelProperty(value = "subsidy3")
    private String subsidy3;
    @Column(name = "other3")
    @ApiModelProperty(value = "other3")
    private String other3;

    @Column(name = "dept_id")
    @ApiModelProperty(value = "报销人所属部门id")
    private Integer deptId;

    @Column(name = "gm_check")
    @ApiModelProperty(value = "总经理审核")
    private String gmCheck;

    @Column(name = "dm_check")
    @ApiModelProperty(value = "部门主管审核")
    private String dmCheck;

    @Column(name = "finance_check")
    @ApiModelProperty(value = "财务审核")
    private String financeCheck;

    @Column(name = "expense_image")
    @ApiModelProperty(value = "附件图片")
    private String expenseImage;

    @Column(name = "expense_image2")
    @ApiModelProperty(value = "财务影像")
    private String expenseImage2;

    @Column(name = "step_type")
    @ApiModelProperty(value = "进度状态（1 办理中、2 完成、3 异常）")
    private Integer stepType;

//    public void copy(AccountTravelExpense source){
//        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
//    }
}