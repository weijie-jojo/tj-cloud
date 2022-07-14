<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      
      <el-form-item label="法人姓名" prop="legalPersonName">
        <el-input v-model="queryParams.legalPersonName" placeholder="请输入法人姓名" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="渠道商" prop="placeName">
        <el-input v-model="queryParams.placeName" placeholder="请输入渠道商" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <!-- <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['company:employed:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['company:employed:edit']">修改</el-button>
      </el-col> -->
      <!-- <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['company:employed:remove']">删除</el-button>
      </el-col> -->
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['company:employed:export']">导出</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="employedList" @selection-change="handleSelectionChange">
       <el-table-column type="selection" width="55" align="center" />
       <el-table-column label="法人姓名"  align="center" prop="legalPersonName" :show-overflow-tooltip="true" />
       <el-table-column label="个体名称"  align="center" prop="selfName" :show-overflow-tooltip="true" />
       <el-table-column label="提交时间" align="center" prop="createTime" width="180" />
       <el-table-column width="400" label="渠道商"   align="center"  prop="placeName"  />
       <el-table-column label="业务经理" align="center" prop="username" :show-overflow-tooltip="true" />
       <el-table-column label="完结状态" align="center" prop="endStatus">
      <template slot-scope="scope">
          <el-link :underline="false" type="danger" v-if="scope.row.endStatus == '0'">未完结</el-link>
          <el-link :underline="false" type="success" v-if="scope.row.endStatus == '1'">已完结</el-link>
      </template>
      </el-table-column>

      <el-table-column label="名称审核" align="center" prop="selfCode">
       <template slot-scope="scope">
          <el-link :underline="false" type="primary" @click="shenloading1(scope.row)" v-if="scope.row.nameStatus == '0'">审核中</el-link>
          <el-link :underline="false" type="danger" @click="errorsinfo(scope.row.remarkName)" v-if="scope.row.nameStatus == '2'">异常</el-link>
          <el-link @click="nameisok(scope.row)" :underline="false" type="success" v-if="scope.row.nameStatus == '1'">已通过</el-link>
      </template>
      </el-table-column>
      <el-table-column label="信息审核" align="center">

        <template slot-scope="scope">
          <el-link :underline="false" type="primary"  v-if="scope.row.infoStatus == '0'"  @click="shenloading2(scope.row)">审核中</el-link>
          <el-link :underline="false" type="danger" @click="errorsinfo(scope.row.remark)" v-if="scope.row.infoStatus == '2'">异常</el-link>
          <el-link @click="newisok(scope.row)" :underline="false" type="success" v-if="scope.row.infoStatus == '1'">已通过</el-link>

        </template>
      </el-table-column>
      <el-table-column label="工商办理" align="center">

        <template slot-scope="scope">
          <el-link :underline="false" type="info"  v-if="scope.row.nameStatus==0 || scope.row.infoStatus==0 || scope.row.nameStatus==2 || scope.row.infoStatus==2  " >未开始</el-link>
          <el-link :underline="false" type="primary" @click="shenloading" v-if=" scope.row.nameStatus==1 && scope.row.infoStatus==1 && scope.row.businessStatus==0" >审核中</el-link>
          <!-- <el-link :underline="false" type="danger" @click="errorsinfo(scope.row.remarkBus)" v-if="scope.row.businessStatus == '2'">异常</el-link> -->
          <el-link :underline="false" @click="newbusiness(scope.row)" type="success" v-if="scope.row.nameStatus==1 && scope.row.infoStatus==1 && scope.row.businessStatus==1" >已通过</el-link>
 
        </template>
      </el-table-column>
        <el-table-column label="实名办理" align="center">

        <template slot-scope="scope">
          <el-link :underline="false" type="info"  v-if="scope.row.nameStatus==0 || scope.row.infoStatus==0 ||scope.row.businessStatus==0 || scope.row.nameStatus==2  || scope.row.infoStatus==2 " >未开始</el-link>
          <el-link :underline="false" type="primary" @click="shenloading" v-if=" scope.row.nameStatus==1 && scope.row.infoStatus==1 && scope.row.businessStatus==1 && scope.row.realnameStatus==0" >审核中</el-link>
          <!-- <el-link :underline="false" type="danger" @click="errorsinfo(scope.row.remarkBus)" v-if="scope.row.businessStatus == '2'">异常</el-link> -->
          <el-link :underline="false" @click="newbusiness(scope.row)" type="success" v-if="scope.row.nameStatus==1 && scope.row.infoStatus==1 && scope.row.businessStatus==1 && scope.row.realnameStatus==1" >已通过</el-link>
 
        </template>
      </el-table-column>
      <el-table-column label="税务办理" align="center">

        <template slot-scope="scope">
          <el-link :underline="false" type="info"  v-if="scope.row.nameStatus==0 || scope.row.infoStatus==0 ||scope.row.businessStatus==0 || scope.row.nameStatus==2  || scope.row.infoStatus==2 " >未开始</el-link>
          <el-link :underline="false" type="primary" @click="shenloading"  v-if="scope.row.nameStatus==1 && scope.row.infoStatus==1 && scope.row.businessStatus==1 && scope.row.taxStatus == 0">审核中</el-link>
          <!-- <el-link :underline="false" type="danger" @click="errorsinfo(scope.row.remarkTax)" v-if="scope.row.taxStatus == '2'">异常</el-link> -->
          <el-link :underline="false" @click="newtax(scope.row)" type="success" v-if="scope.row.nameStatus==1 && scope.row.infoStatus==1 && scope.row.businessStatus==1 && scope.row.taxStatus == '1'">已通过</el-link>

        </template>
      </el-table-column>
      <el-table-column label="银行办理" align="center">

        <template slot-scope="scope">
          <el-link :underline="false" type="info"  v-if="scope.row.nameStatus==0 || scope.row.infoStatus==0 || scope.row.businessStatus==0  || scope.row.nameStatus==2 || scope.row.infoStatus==2" >未开始</el-link>
          <el-link :underline="false" type="primary" @click="shenloading"  v-if="scope.row.businessStatus==1 && scope.row.bankStatus == '0'">审核中</el-link>
          <!-- <el-link :underline="false" type="danger" @click="errorsinfo(scope.row.remarkBank)" v-if="scope.row.bankStatus == '2'">异常</el-link> -->
          <el-link :underline="false" @click="newbank(scope.row)" type="success" v-if="scope.row.bankStatus == '1' && scope.row.businessStatus==1">已通过</el-link>
        </template>

      </el-table-column>
      
    </el-table>

    <pagination v-show="total >0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改个体商户对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="个体户编码" prop="selfKey">
          <el-input v-model="form.selfKey" placeholder="请输入个体户编码" />
        </el-form-item>
        <el-form-item label="渠道商编码" prop="placeCode">
          <el-input v-model="form.placeCode" placeholder="请输入渠道商编码" />
        </el-form-item>
        <el-form-item label="税号" prop="taxId">
          <el-input v-model="form.taxId" placeholder="请输入税号" />
        </el-form-item>
        <el-form-item label="个体户注册地址" prop="selfAddress">
          <el-input v-model="form.selfAddress" placeholder="请输入个体户注册地址" />
        </el-form-item>
        <el-form-item label="个体户名称" prop="selfName">
          <el-input v-model="form.selfName" placeholder="请输入个体户名称" />
        </el-form-item>
        <el-form-item label="法人姓名" prop="legalPersonName">
          <el-input v-model="form.legalPersonName" placeholder="请输入法人姓名" />
        </el-form-item>
        <el-form-item label="法人身份证" prop="idCardNum">
          <el-input v-model="form.idCardNum" placeholder="请输入法人身份证" />
        </el-form-item>
        <el-form-item label="登录密码" prop="password">
          <el-input v-model="form.password" placeholder="请输入登录密码" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="每月可开票金额" prop="maximum">
          <el-input v-model="form.maximum" placeholder="请输入每月可开票金额" />
        </el-form-item>
        <el-form-item label="注册时间" prop="registerTime">
          <el-date-picker clearable v-model="form.registerTime" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择注册时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="组织形式" prop="organizationalForm">
          <el-input v-model="form.organizationalForm" placeholder="请输入组织形式" />
        </el-form-item>
        <el-form-item label="从业人数" prop="numberEmployees">
          <el-input v-model="form.numberEmployees" placeholder="请输入从业人数" />
        </el-form-item>
        <el-form-item label="出资金额" prop="contributionAmount">
          <el-input v-model="form.contributionAmount" placeholder="请输入出资金额" />
        </el-form-item>
        <el-form-item label="城市" prop="city">
          <el-input v-model="form.city" placeholder="请输入城市" />
        </el-form-item>
        <el-form-item label="区县" prop="county">
          <el-input v-model="form.county" placeholder="请输入区县" />
        </el-form-item>
        <el-form-item label="电子商务经营者" prop="electronicCommerce">
          <el-input v-model="form.electronicCommerce" placeholder="请输入电子商务经营者" />
        </el-form-item>
        <el-form-item label="所属自贸区" prop="freeTradeZone">
          <el-input v-model="form.freeTradeZone" placeholder="请输入所属自贸区" />
        </el-form-item>
        <el-form-item label="所属自贸片区" prop="freeTradeArea">
          <el-input v-model="form.freeTradeArea" placeholder="请输入所属自贸片区" />
        </el-form-item>
        <el-form-item label="产权" prop="propertyRight">
          <el-input v-model="form.propertyRight" placeholder="请输入产权" />
        </el-form-item>
        <el-form-item label="行业" prop="industry">
          <el-input v-model="form.industry" placeholder="请输入行业" />
        </el-form-item>
        <el-form-item label="经营范围" prop="natureBusiness">
          <el-input v-model="form.natureBusiness" placeholder="请输入经营范围" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

import { joinList,listEmployed, getEmployed, delEmployed, addEmployed, updateEmployed } from "@/api/company/employed";
// import axios from 'axios'
export default {
  name: "Employed",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 个体商户表格数据
      employedList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        placeName: null,
        legalPersonName: null,
        userId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    console.log(444444);
    this.getList();
  },
  mounted(){
//  this.$tab.refreshPage();
  },
  methods: {
    //审核中
    shenloading(){
         this.$alert('审核中,请耐心等待...', '审核说明', {
          confirmButtonText: '确定',
          callback: action => {
            // this.$message({
            //   type: 'info',
            //   message: `action: ${ action }`
            // });
          }
        });
    },
    //审核中  名称
    shenloading1(scope){
      //  this.$cache.local.setJSON('employedName', scope);
      //  console.log(scope);
           this.$confirm('审核中,请耐心等待...', '审核说明', {
          confirmButtonText: '修改',
          cancelButtonText: '确定',
          type: 'warning'
        }).then(() => {
         this.$cache.local.setJSON('employedName', scope);
         this.$router.push("editEmployedName");   
    
        }).catch(() => {
        });
      
        
    },
    //审核中  信息
    shenloading2(scope){
           this.$confirm('审核中,请耐心等待...', '审核说明', {
          confirmButtonText: '修改',
          cancelButtonText: '确定',
          type: 'warning'
        }).then(() => {
             this.$cache.local.setJSON('employedInfo', scope);
             this.$router.push("editEmployedInfo");  
        }).catch(() => {
      
        });
    },
    //异常说明
     errorsinfo(error){
       this.$alert(error, '异常说明', {
          confirmButtonText: '确定',
          callback: action => {
            // this.$message({
            //   type: 'info',
            //   message: `action: ${ action }`
            // });
          }
        });
     },
    newbusiness(){
        this.$alert('审核成功！', '审核说明', {
          confirmButtonText: '确定',
          callback: action => {
            // this.$message({
            //   type: 'info',
            //   message: `action: ${ action }`
            // });
          }
        });
    },
     newtax(){
         this.$alert('审核成功！', '审核说明', {
          confirmButtonText: '确定',
          callback: action => {
            // this.$message({
            //   type: 'info',
            //   message: `action: ${ action }`
            // });
          }
        });
    },
     newbank(){
         this.$alert('审核成功！', '审核说明', {
          confirmButtonText: '确定',
          callback: action => {
            // this.$message({
            //   type: 'info',
            //   message: `action: ${ action }`
            // });
          }
        });
    },
    
    //名称审核
    nameisok(scope){
      this.$alert('审核成功！', '审核说明', {
          confirmButtonText: '确定',
          callback: action => {
            // this.$message({
            //   type: 'info',
            //   message: `action: ${ action }`
            // });
          }
        });
    },
    //信息审核
    newisok(scope){
           this.$confirm('审核中,请耐心等待...', '审核说明', {
          confirmButtonText: '查看',
          cancelButtonText: '确定',
          type: 'warning'
        }).then(() => {
             this.$cache.local.setJSON('employedInfo', scope);
             this.$router.push("infodetail");  
        }).catch(() => {
      
        });
        // this.$alert('审核成功！', '审核说明', {
        //   confirmButtonText: '确定',
        //   callback: action => {
        //     // this.$message({
        //     //   type: 'info',
        //     //   message: `action: ${ action }`
        //     // });
        //   }
        // });
    },
    /** 查询个体商户列表 */
    getList() {
      this.loading = true;
      joinList(this.queryParams).then(response => {
        
        this.employedList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        placeName:null,
        selfId: null,
        selfKey: null,
        placeCode: null,
        taxId: null,
        selfAddress: null,
        selfName: null,
        legalPersonName: null,
        idCardNum: null,
        password: null,
        status: 0,
        remark: null,
        userId: null,
        expStatus: 0,
        maximum: null,
        registerTime: null,
        industryType: null,
        organizationalForm: null,
        numberEmployees: null,
        contributionAmount: null,
        city: null,
        county: null,
        electronicCommerce: null,
        freeTradeZone: null,
        freeTradeArea: null,
        propertyRight: null,
        industry: null,
        natureBusiness: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.selfId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    //工商管理
    business(row) {
     
    },
    //税务管理
    atx(row) {
    
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加个体商户";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const selfId = row.selfId || this.ids
      getEmployed(selfId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改个体商户";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.selfId != null) {
            updateEmployed(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addEmployed(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const selfIds = row.selfId || this.ids;
      this.$modal.confirm('是否确认删除个体商户编号为"' + selfIds + '"的数据项？').then(function () {
        return delEmployed(selfIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('company/employed/export', {
        ...this.queryParams
      }, `employed_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

