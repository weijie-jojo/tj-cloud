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
@Table(name="account_expense_item")
public class AccountExpenseItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "id")
    private int id;

    @Column(name = "item_name")
    @ApiModelProperty(value = "报销项目名")
    private String itemName;

    @Column(name = "create_time")
    @ApiModelProperty(value = "createTime")
    private String createTime;

    @Column(name = "update_time")
    @ApiModelProperty(value = "updateTime")
    private String updateTime;

    @Column(name = "item_money")
    @ApiModelProperty(value = "报销费用（每天）")
    private Integer itemMoney;


//    public void copy(AccountExpenseItem source){
//        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
//    }
}