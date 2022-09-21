<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="auto">
      <el-form-item label="个体户名称">
        <el-input v-model="queryParams.selfName" placeholder="请输入个体户名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
        <el-form-item label="法人名称">
        <el-input v-model="queryParams.legalPersonName" placeholder="请输入法人名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
     
      <el-form-item label="客户全名" prop="placeAliasName">
        <el-input v-model="queryParams.placeAliasName" placeholder="请输入客户全名" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <!-- <el-form-item label="状态" prop="placeName">
        <el-select clearable v-model="queryParams.isActive" placeholder="请选择">
          <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
      </el-form-item> -->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
         >删除</el-button>
      </el-col>
      
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="employedList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="个体名称" align="center" prop="selfName" width="300"/>
      <el-table-column label="法人名称" align="center" prop="legalPersonName" :show-overflow-tooltip="true" />
      
      <el-table-column label="客户全名" width="700" align="center" prop="placeAliasName"/>
      <el-table-column label="业务经理" align="center" prop="username" :show-overflow-tooltip="true" />
      <!-- <el-table-column label="状态" align="center" prop="isActive">
        <template slot-scope="scope">
          <span v-if="scope.row.isActive == 0">休眠</span>
          <span v-if="scope.row.isActive == 1">激活</span>
          <span v-if="scope.row.isActive == 2">报警</span>
        </template>
      </el-table-column> -->
      <!-- <el-table-column label="休眠/激活" align="center">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.isActive" active-color="#13ce66" active-text="激活" active-value="1"
            inactive-color="rgb(233,235,239)" inactive-text="休眠" inactive-value="0" @change="changeSwitch(scope.row)">
          </el-switch>

        </template>
      </el-table-column> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" v-hasPermi="['reg:endlist:detail']"  type="text" icon="el-icon-s-custom" @click="detail(scope.row)">详情</el-button>
          <el-button size="mini" v-hasPermi="['reg:endlist:edit']"    type="text" icon="el-icon-edit" @click="edits(scope.row)">修改</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

  
  </div>
</template>

<script>
import {
  joinListEnd,
  delEmployed,
  addEmployed,
  updateEmployed,
} from "@/api/tc-api/company/employed";

export default {
  name: "ManageList",
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
        selfName:null,
        endStatus: 1,
        pageNum: 1,
        pageSize: 10,
        placeAliasName: null,
        legalPersonName: null,
        userId: null,
        isActive: null,
      },
      options: [
        {
          value: 0,
          label: '正常'
        },
        {

          value: 2,
          label: '预警'
        },
        {

          value: 1,
          label: '休眠',
        },
      ],
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
    };
  },

  mounted(){
    this.getList();
  },
  methods: {
    //激活休眠
    changeSwitch(scope) {
      let isActive = scope.isActive;
      console.log(isActive);


      let obj = {
        isActive: isActive,
        selfId: scope.selfId
      };
      updateEmployed(obj).then(res => {
        if (res != undefined) {
          if (res.code === 200) {
            if (obj.isActive == 1) {
              this.$modal.msgSuccess("激活成功");
            } else {
              this.$modal.msgSuccess("休眠成功");
            }

            this.$nextTick(function () {
              this.$tab.refreshPage().then(() => {
               })
                });
          } else {
            this.$modal.msgError(res.msg);
          }

        }

      }).catch(error => {
        this.$modal.msgError(error);
      });
    },
    edits(row){
        let obj = {
        name:'个体户信息',
        backUrl: '/tc-business/manageList',
        backName: "ManageList",
      };
      this.$cache.local.setJSON('tc-backurls', obj);
      this.$cache.local.setJSON("tc-findlist", row.selfCode);
      this.$tab.refreshPage({path:"/tc-business/reg/manageListDdit",name:'ManageListDdit'});
     
     },
    detail(scope) {
      this.$cache.local.setJSON("tc-findlist", scope.selfCode);
      this.$tab.refreshPage({path:"/tc-business/reg/manageListDetail",name:'ManageListDetail'});
    },
   

    /** 查询个体商户列表 */
    getList() {
      this.loading = true;
      joinListEnd(this.queryParams).then((response) => {
        this.employedList = response.rows;
        let isActive = '0';
        for (let i in this.employedList) {
          if (this.employedList[i].isActive == 0) {
            isActive = '0';
          } else if (this.employedList[i].isActive == 1) {
            isActive = '1';
          } else if (this.employedList[i].isActive == 2) {
            isActive = "2";
          } else if (this.employedList[i].isActive == null) {
            isActive = 'null';
          }

          this.employedList[i].isActive = isActive;
        }
        this.total = response.total;
        this.loading = false;
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
     
     this.queryParams={
        selfName:null,
        endStatus: 1,
        pageNum: 1,
        pageSize: 10,
        placeAliasName: null,
        legalPersonName: null,
        userId: null,
        isActive: null,
      };
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.selfId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    
    
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加个体商户";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
    
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.selfId != null) {
            updateEmployed(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addEmployed(this.form).then((response) => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete() {
      const selfIds = this.ids;
      this.$modal
        .confirm('是否确认删除此个体户的注册信息')
        .then(function () {
          return delEmployed(selfIds);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "company/employed/export",
        {
          ...this.queryParams,
        },
        `employed_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>

