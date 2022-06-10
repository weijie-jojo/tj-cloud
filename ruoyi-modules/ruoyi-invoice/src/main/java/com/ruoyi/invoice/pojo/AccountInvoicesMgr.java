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
@Table(name="Account_invoices_mgr")
public class AccountInvoicesMgr implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "id")
    private int id;

    @Column(name = "invoice_code")
    @ApiModelProperty(value = "单据编号")
    private String invoiceCode;

    @Column(name = "invoice_type")
    @ApiModelProperty(value = "单据类型 1.报销单 2.差旅报销单 3.借支单")
    private Integer invoiceType;


    @Column(name = "create_user")
    @ApiModelProperty(value = "填单人")
    private String createUser;

    @Column(name = "create_date")
    @ApiModelProperty(value = "填单日期")
    private String createDate;

    @Column(name = "is_deleted")
    @ApiModelProperty(value = "是否删除;1:删除,0:未删除")
    private Integer isDeleted;

}