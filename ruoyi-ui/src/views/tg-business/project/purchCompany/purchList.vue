<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="auto"
    >
      <el-form-item label="名称">
        <el-input
          v-model="queryParams.purchCompany"
          placeholder="请输入名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

    

      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>


    <el-table
      v-loading="loading"
      :data="projectList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />

      <el-table-column
        label="名称"
        align="center"
        prop="purchCompany"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="纳税识别号"
        align="center"
        prop="purchCompanyTaxid"
      
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="客户全名"
        align="center"
        prop="placeAliasName"
        width="700"
        :show-overflow-tooltip="true"
      />
     
      <el-table-column
        label="手机号"
        align="center"
        prop="purchCompanyPhone"
        :show-overflow-tooltip="true"
      />
      <el-table-column  label="地址" prop="purchCompanyAddress"  align="center"  :show-overflow-tooltip="true"/>
   
     
    
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-s-custom"
            @click="detail(scope.row)"
            >详情</el-button
          >
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">修改
                    </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
   
  </div>
</template>

<script>

import moment from "moment";

import { getPurchPage} from "@/api/tg-api/project/list";

export default {
  name: "PurchList",
  data() {
    return {
      
     
      
      progressList: [],
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
     
      // 项目列表表格数据
      projectList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        type: 1,
        pageNum: 1,
        pageSize: 10,
        purchCompany: null, //销货单位（乙方）
       
      },
      projectTime: null,
      pickerOptions: {
        shortcuts: [
          {
            text: "最近一周",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近一个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近三个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit("pick", [start, end]);
            },
          },
        ],
      },
      options: [
        {
          value: 0,
          label: "进行",
        },
        {
          value: 1,
          label: "异常",
        },
        {
          value: 2,
          label: "完成",
        },
      ],
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
    };
  },
  filters: {
    filterTime(time) {
      return moment(time).format("YYYY-MM-DD");
    },
  },
  mounted() {
   
    this.getList();
  },
  methods: {
    
  //详情
    findList() {
      
    },
   
    //返回当前时间
    returnTime(time2) {
      var time = new Date(time2);
      return time.toLocaleString();
    },
    filterTime(time) {
      var date = new Date(time);
      var y = date.getFullYear();
      var m = date.getMonth() + 1;
      m = m < 10 ? "0" + m : m;
      var d = date.getDate();
      d = d < 10 ? "0" + d : d;
      var h = date.getHours();
      h = h < 10 ? "0" + h : h;
      var minute = date.getMinutes();
      minute = minute < 10 ? "0" + minute : minute;
      var s = date.getSeconds();
      s = s < 10 ? "0" + s : s;
      return y + "-" + m + "-" + d + " " + h + ":" + minute + ":" + s;
    },
    
    /** 查询项目列表 */
    getList() {
      this.loading = true;
     
      //    console.log( "projectTimeStart1==",this.queryParams.projectTimeStart[1]);
      getPurchPage(this.queryParams)
        .then((response) => {
          this.projectList = response.rows;
          this.total = response.total;
          this.loading = false;
         
        })
        .catch((error) => {
          this.loading = false;
        });
    },
    handleUpdate(scope){
      this.$cache.local.setJSON("tg-purchId", scope.purchId);
      this.$tab.refreshPage({path:"/tg-business/project/purchEdit",name:'PurchEdit'});
    },
    detail(scope) {
     this.$cache.local.setJSON("tg-purchId", scope.purchId); 
     this.$tab.refreshPage({path:"/tg-business/project/purchDetail",name:'PurchDetail'});
    },

    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
     
      this.queryParams = {
        type: 1,
        pageNum: 1,
        pageSize: 10,
        purchCompany: null, //销货单位（乙方）
        
      };
      this.handleQuery();
    },

    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.projectId);
      console.log(this.ids);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const projectIds = this.ids;
      this.$modal
        .confirm("是否确认删除该项目")
        .then(function () {
          return del(projectIds);
        })
        .then((res) => {
          if (res.code == 200) {
            this.getList();
            this.$modal.msgSuccess("删除成功");
          } else {
            this.$alert(res.msg, "提示", {
              confirmButtonText: "确定",
            });
          }
        })
        .catch(() => {});
    },
  },
};
</script>

<style scoped>
::v-deep .el-message-box__content {
  height: 200px !important;
}

::v-deep .el-tabs__nav-wrap::after {
  background-color: rgba(0, 0, 0, 0) !important;
}
</style>
