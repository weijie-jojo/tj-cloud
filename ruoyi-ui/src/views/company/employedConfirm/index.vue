<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="auto">

      <el-form-item label="法人姓名" prop="legalPersonName">
        <el-input v-model="queryParams.legalPersonName" placeholder="请输入法人姓名" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="渠道商" prop="placeName">
        <el-input v-model="queryParams.placeName" placeholder="请输入渠道商" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="客户经理">
        <el-input v-model="queryParams.username" placeholder="请输入客户经理" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="15">
        <el-tabs v-model="endStatus" @tab-click="handleClick">
          <el-tab-pane :label="loadingLabel" name="0"></el-tab-pane>
          <el-tab-pane :label="errLabel" name="2"></el-tab-pane>
          <el-tab-pane :label="finishLabel" name="1"></el-tab-pane>
          <el-tab-pane :label="allLabel" name="-1"></el-tab-pane>
        </el-tabs>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    <el-table v-loading="loading" :data="employedList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="法人姓名" align="center" width="180" prop="legalPersonName" :show-overflow-tooltip="true" />
        <el-table-column label="提交时间" align="center" prop="createTime">
        <template slot-scope="scope">
             {{scope.row.createTime | filterTime}}
        </template>
      </el-table-column>
      <el-table-column label="渠道商" align="center" prop="placeName" :show-overflow-tooltip="true" />
      <el-table-column label="业务经理" align="center" prop="username" :show-overflow-tooltip="true" />
      <el-table-column label="办理状态" align="center" prop="">
        <template slot-scope="scope">
          <el-link :underline="false" type="danger" v-if="scope.row.endStatus == 2">异常</el-link>
          <el-link :underline="false" type="primary" v-if="scope.row.endStatus == 0">办理中</el-link>
          <el-link :underline="false" type="success" v-if="scope.row.endStatus == 1">完成</el-link>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-view" @click="look(scope.row)">查看</el-button>
          <el-button size="mini" type="text" v-if="scope.row.endStatus == 2" icon="el-icon-edit"
            @click="edits(scope.row)">编辑</el-button>
          <el-button size="mini" type="text" icon="el-icon-s-goods" v-else
            style="border:0 !important;background-color:rgba(0,0,0,0) !important" plain disabled>编辑</el-button>
          <el-button size="mini" v-if="scope.row.endStatus == 0" type="text" icon="el-icon-s-goods"
            @click="detail(scope.row)">确认注册</el-button>
          <el-button size="mini" v-else icon="el-icon-s-goods"
            style="border:0 !important;background-color:rgba(0,0,0,0) !important" plain disabled>确认注册</el-button>

        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />
  </div>
</template>

<script>
import moment from 'moment'
import { joinList,getCount} from "@/api/company/employed";

export default {
  name: "Employed",
  data() {
    return {
      allLabel: '全部',
      errLabel: '异常',
      loadingLabel: '审核中',
      finishLabel: '完成',
      endStatus: '0',
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
        nameStatus: 1,
        infoStatus: 1,
        businessStatus: 1,
        bankStatus: 1,
        // realnameStatus:1,
        taxStatus: 1,
        endStatus: 0,
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
   filters: {
    filterTime(time) {
     return moment(time).format('YYYY-MM-DD')
    },
  },
  mounted() {
    this.getList();
  },
  methods: {
    handleClick(tab, event) {
      if (this.endStatus == '-1') {
        this.queryParams.endStatus = null;
      } else {
        this.queryParams.endStatus = this.endStatus;
      }
      this.queryParams.pageNum = 1;
      this.getList();
    },
    edits(row) {
       let obj = {
        name:'注册确认',
        backUrl: '/company/customer/employedConfirm',
      };
      this.$cache.local.setJSON('backurls', obj);
      this.$cache.local.setJSON("employedInfo", row);
      this.$tab.closeOpenPage({ path: '/company/customer/manageListDdit' });
    },
    look(row) {
      let obj={
         backUrl:'/company/customer/employedConfirm',
        };
      this.$cache.local.setJSON('backurls',obj);
      this.$cache.local.setJSON("employedInfo", row);
      this.$tab.openPage("注册详情", "/company/customer/confirmS").then(() => { })
    },
    detail(row) {
      let obj={
         backUrl:'/company/customer/employedConfirm',
        };
      this.$cache.local.setJSON('backurls',obj);
      this.$cache.local.setJSON("employedInfo", row);
      this.$tab.closeOpenPage({path: "/company/customer/confirmDetail"}).then(() => { })
    },
      getCount(){
         getCount(this.queryParams).then(res => {
        this.errLabel = "异常(" + res.error + ")";
        this.allLabel = "全部(" + res.total + ")";
        this.loadingLabel = "办理中(" + res.unfinished + ")";
        this.finishLabel = "完成(" + res.finished + ")";
      });
     },
    /** 查询个体商户列表 */
    getList() {
      this.loading = true;
      joinList(this.queryParams).then(response => {

        this.employedList = response.rows;
        this.total = response.total;
        this.loading = false;
        this.getCount();
      });
    },
    
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.endStatus = '0';
      this.queryParams.endStatus = 0;
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.selfId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
   }
};
</script>
<style scoped>
::v-deep .el-tabs__nav-wrap::after {
  background-color: rgba(0, 0, 0, 0) !important;
}
</style>
