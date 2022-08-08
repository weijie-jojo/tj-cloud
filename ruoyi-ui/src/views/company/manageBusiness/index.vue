<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="auto">
      
      <el-form-item label="法人姓名">
        <el-input v-model="queryParams.legalPersonName" placeholder="请输入法人姓名" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="渠道商">
        <el-input v-model="queryParams.placeName" placeholder="请输入渠道商" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
       <el-form-item label="客户经理">
        <el-input v-model="queryParams.username" placeholder="请输入渠道商" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
     <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
     <el-row :gutter="10" class="mb8">
      <el-col :span="15">
     <el-tabs v-model="businessStatus" @tab-click="handleClick">
     <el-tab-pane :label="alabels" name="-1"></el-tab-pane>
     <el-tab-pane :label="alabels2" name="0"></el-tab-pane>
     <el-tab-pane :label="alabels3" name="1"></el-tab-pane>
      </el-tabs>
     </el-col>
      
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    <el-table v-loading="loading" :data="employedList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
       <el-table-column label="法人姓名" align="center" prop="legalPersonName" :show-overflow-tooltip="true"  />
       <el-table-column label="提交时间" align="center" prop="createTime" width="180" />
       <el-table-column label="渠道商" align="center"  prop="placeName" :show-overflow-tooltip="true" />
       <el-table-column label="业务经理" align="center" prop="username" :show-overflow-tooltip="true" />
       <el-table-column label="办理状态" align="center" prop="">
         <template slot-scope="scope">
            <el-link :underline="false" type="primary" v-if=" scope.row.businessStatus==0">办理中</el-link>
           <el-link :underline="false" type="success"  v-if="  scope.row.businessStatus==1">完成</el-link>
         </template>
       </el-table-column>
       <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text"  icon="el-icon-view" @click="detail(scope.row)">查看</el-button>
          <!-- <el-button size="mini" type="text" icon="el-icon-table-lamp"
            @click="businessTable(scope.row)">工商表格</el-button> -->
          <el-button size="mini" v-if="scope.row.businessStatus==0" type="text" icon="el-icon-s-goods"
            @click="business(scope.row)">办理工商</el-button>
          <el-button size="mini" v-else icon="el-icon-s-goods" style="border:0 !important;background-color:rgba(0,0,0,0) !important" plain disabled>办理工商</el-button>
       </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total >0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />
 </div>
</template>

<script>

import { joinList2,listEmployed, getEmployed, delEmployed, addEmployed, updateEmployed } from "@/api/company/employed";
export default {
  name: "Employed",
  data() {
    return {
      alabels: '全部',
      alabels1: '异常',
      alabels2: '办理中',
      alabels3: '完成',
      businessStatus:'-1',
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
        nameStatus:1,
        infoStatus:1,
        // realnameStatus:1,
        businessStatus:null,
        pageNum: 1,
        pageSize: 10,
        placeName: null,
        legalPersonName: null,
        username: null,
      }, 
        options: [
        {
          value: 0,
          label: '待办理'
        },
       {

          value: 1,
          label: '已完成',
        },
      ],
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getBang();
  
    this.getList();
  },
  methods: {
    getBang() {
      let params = {
        nameStatus:1,
        infoStatus:1,
        businessStatus: 0
      }
      joinList2(params).then(res => {
        this.alabels2 = "办理中(" + res.total + ")";
      });
    },
    
      handleClick(tab, event) {
     if(this.businessStatus=='-1'){
      this.queryParams.businessStatus=null;
     }else{
      this.queryParams.businessStatus=this.businessStatus;
     }
      this.queryParams.pageNum=1;
      this.getList();
      },
    detail(row){
        let obj = {
          backUrl: '/company/customer/manageBusiness',
         };
         this.$cache.local.setJSON('backurls', obj);
         this.$cache.local.setJSON('employednewlist', row);
         this.$tab.openPage("工商信息","/company/customer/detailBusiness");

    },
    /** 查询可办理的工商列表 */
    getList() {
      this.loading = true;
      joinList2(this.queryParams).then(response => {
        
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
      this.businessStatus='-1';
      this.queryParams.businessStatus=null;
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
      this.$cache.local.setJSON('employednewlist', row);
      this.$tab.closeOpenPage({ path: "/company/customer/addBusiness"});
   },
   //工商表格
   businessTable(row){
       //  this.$cache.local.setJSON('employednewlist', row);
      //  this.$tab.closeOpenPage({ path: "/company/customer/businessTable"});
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
<style scoped>
   ::v-deep .el-tabs__nav-wrap::after{
        background-color:rgba(0,0,0,0) !important;
   }
</style>

