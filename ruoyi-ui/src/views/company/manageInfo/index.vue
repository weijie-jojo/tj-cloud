<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="auto">

      <el-form-item label="法人姓名">
        <el-input v-model="queryParams.legalPersonName" placeholder="请输入法人姓名" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="客户全名">
        <el-input v-model="queryParams.placeAliasName" placeholder="请输入客户全名" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="业务经理">
        <el-input v-model="queryParams.username" placeholder="请输入业务经理" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="15">
        <el-tabs v-model="infoStatus" @tab-click="handleClick">
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
      <el-table-column label="法人姓名" align="center" prop="legalPersonName" :show-overflow-tooltip="true" />
      <el-table-column label="提交时间" width="120" align="center" prop="createTime">
        <template slot-scope="scope">
          {{ scope.row.createTime | filterTime }}
        </template>
      </el-table-column>
      
      <el-table-column label="客户全名" align="center" prop="placeAliasName" :show-overflow-tooltip="true" />
      <el-table-column label="业务经理" align="center" prop="username" :show-overflow-tooltip="true" />
      <el-table-column label="审核状态" align="center" prop="nameStatus">
        <template slot-scope="scope">
          <el-link :underline="false" type="primary" v-if="scope.row.infoStatus == '0'">审核中</el-link>
          <el-link :underline="false" type="danger" v-if="scope.row.infoStatus == '2'">异常</el-link>
          <el-link :underline="false" type="success" v-if="scope.row.infoStatus == '1'">完成</el-link>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-view" @click="detail(scope.row)">查看</el-button>
          <el-button size="mini" v-hasPermi="['company:info:examine']" v-if="scope.row.infoStatus == 0" type="text" icon="el-icon-info"
            @click="bank(scope.row)">审核信息</el-button>
          <el-button size="mini" v-hasPermi="['company:info:examine']" v-else icon="el-icon-info"
            style="color: #C0C4CC!important;border:0 !important;background-color:rgba(0,0,0,0) !important" plain disabled>审核信息</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />


  </div>
</template>

<script>
import moment from 'moment'
import { joinList, getCount} from "@/api/company/employed";

export default {
  name: "ManageInfo",
  data() {
    return {
      allLabel: '全部',
      errLabel: '异常',
      loadingLabel: '审核中',
      finishLabel: '完成',
      infoStatus: '0',
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
        type:3,
        infoStatus: 0,
        pageNum: 1,
        pageSize: 10,
        placeAliasName: null,
        legalPersonName: null,
        username: null,
      },
      options: [
        {
          value: 0,
          label: '待审核'
        },
        {

          value: 1,
          label: '已完成',
        },
        {

          value: 2,
          label: '未通过',
        },
      ],
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  mounted() {
    this.getList();
  },
  filters: {
    filterTime(time) {
      return moment(time).format('YYYY-MM-DD')
    },
  },
  methods: {

    handleClick(tab, event) {
      if (this.infoStatus == '-1') {
        this.queryParams.infoStatus = null;
      } else {
        this.queryParams.infoStatus = this.infoStatus;
      }
      this.queryParams.pageNum = 1;
      this.getList();
    },
    detail(row) {
      let obj = {
        backUrl: '/company/customer/manageInfo',
        backName:'ManageInfo',
      };
      this.$cache.local.setJSON('backurls', obj);
      this.$cache.local.setJSON('employedInfo', row);
      this.$tab.openPage("个体户信息查看", "/company/customer/infodetail")
      
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
      }).catch(error=>{
          console.log(error);
          this.loading=false;
      });
    },

    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.infoStatus = '0';
        this.queryParams={
        type:3,
        infoStatus: 0,
        pageNum: 1,
        pageSize: 10,
        placeAliasName: null,
        legalPersonName: null,
        username: null,
      };
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.selfId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },

    bank(row) {
      this.$cache.local.setJSON('employedInfo', row);
      let obj = {
        title: '信息审核',
        backUrl: '/company/customer/manageInfo',
        resmsg: '信息审核完成',
        backName: "ManageInfo",
      }
      this.$cache.local.setJSON('successNew', obj);
      this.$tab.openPage("个体户信息审核", "/company/customer/infonew")
     
    },
  }
};
</script>
<style scoped>
::v-deep .el-tabs__nav-wrap::after {
  background-color: rgba(0, 0, 0, 0) !important;
}
</style>