<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="auto">
      <el-form-item label="客户全名" prop="placeAliasName">
        <el-input v-model="queryParams.placeAliasName" placeholder="请输入客户全名" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select clearable v-model="queryParams.status" placeholder="请选择">
          <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete">删除
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="employedList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
     
      <el-table-column label="客户全名" width="700" align="center" prop="placeAliasName"/>
      <el-table-column label="联系人" align="center" prop="placeLinkman" :show-overflow-tooltip="true" />
      <el-table-column label="联系方式" prop="placeTel" :show-overflow-tooltip="true" />
      <el-table-column form-item label="业务经理" prop="userName" :show-overflow-tooltip="true" />
      <!-- <el-table-column label="状态" prop="dictLabel" align="center" /> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-s-custom" @click="detail(scope.row)">详情</el-button>


          <el-button v-hasPermi="['place:placeMgr:edit']" size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">编辑</el-button>

          <!-- <el-button size="mini" type="text" v-if="scope.row.placeStatus == 0" @click="isDormancy(scope.row, 2)">
            休眠
          </el-button>
          <el-button size="mini" type="text" v-if="scope.row.placeStatus == 2" @click="isDormancy(scope.row, 0)">
            激活
          </el-button>
          <el-button size="mini" type="text" disabled v-if="scope.row.placeStatus == 1"
            @click="isDormancy(scope.row, 1)">
            禁用
          </el-button> -->
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />



  </div>
</template>

<script>

import crudPlace from "@/api/place/place";
import agencyfee from "@/api/place/agencyfee";
import '@riophae/vue-treeselect/dist/vue-treeselect.css'

export default {
  name: "PlaceMgr",

  data() {
    return {
      
    
      tabelData: [],
      editVisible: false,
      multipleSelection: [],
      addVisible: false,
      userLeaders: [],
      status: [],
      placeStatus: [], // 多选时使用
     
      value: '正常',
      values: '0',
      
      Include: '1',
      users: [],
      isCheck: true,
      checkVisible: false,
      currentRow: {}, selectIndex: '', placeCode: '', urlHistory: '',
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: false,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 个体商户表格数据
      employedList: [],
      // 弹出层标题
      title: "",
      titles: '',
      titleh: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        placeAliasName:null,
        placeName: null,
        userName: null,
        status: '',
      },
      
      option: [
        {
          value: '0',
          label: '免税'
        },
        // {
        //   value: '0.01',
        //   label: '1%'
        // }, {
        //   value: '0.03',
        //   label: '3%'
        // },
      ],
      optiond: [
        // {
        //   value: '0.01',
        //   label: '1%'
        // }, 
        {
          value: '0.03',
          label: '3%'
        },
      ],
      options: [
        {
          value: 0,
          label: '正常'
        },
        {

          value: 2,
          label: '休眠'
        },
        {

          value: 1,
          label: '欠费',
        },
      ],
      rules: { },
      queryTypeOptions: [
        { key: 'placeName', display_name: '客户名称' },
        { key: 'placeStatus', display_name: '客户状态' },
        { key: 'nickName', display_name: '业务经理' }
      ],
      // 表单参数
      form: {},
      // 表单校验
     
    };
  },
 
  mounted() {
    this.getList();
    
 },

  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      crudPlace.getByPage(this.queryParams).then((response) => {
        this.employedList = response.rows;

        this.total = response.total;
        this.loading = false;
      });
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
      this.multipleSelection = selection;
    },

    handleChange(value) {
      console.log(value);
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.$tab.openPage("客户新增", "/place/add")
    },
    /** 修改按钮操作 */
    handleUpdate(item) {
      var placeCode = item.placeCode;
      this.$cache.local.setJSON("placeItem",item);
      this.$modal.loading("正在加载数据，请稍后...");
      agencyfee.selectFeeByCode({ placeCode: placeCode }).then(res => {
      this.$modal.closeLoading();
      this.$cache.local.setJSON("placeItems",res);
      this.$tab.openPage("客户编辑", "/place/edit")
      })

      
    },
    //详情
    detail(item) {
     
      this.$cache.local.setJSON("placeDetailItem",item);
      this.$modal.loading("正在加载数据，请稍后...");
      agencyfee.selectFeeByCode({ placeCode: item.placeCode }).then(res => {
        this.$modal.closeLoading();
        this.$cache.local.setJSON("placeDetailItems",res);
        this.$tab.openPage("客户详情", "/place/detail")
     
     })
   },
    //修改状态
    isDormancy(item, type) {
      var placeStatus;
      if (item.placeStatus == 0) {
        placeStatus = 2;
      }
      if (item.placeStatus == 1) {
        // this.$message({
        //   message: '欠费状态不能点哦',
        //   type: 'warning',
        // });
        this.$alert('欠费状态不能点哦', '系统提示', {
              confirmButtonText: '确定',
             
              type: 'error'
         })
        return;
      }
      if (item.placeStatus == 2) {
        placeStatus = 0;
      }
      let params = {
        placeId: item.placeId,
        placeStatus: placeStatus,
      }
      crudPlace.editPlace2(params).then((res) => {
        if (res.id == 0) {
          this.$message({
            message: res.message,
            type: 'success',
          });
        } else {
          this.$message({
            message: res.message,
            type: 'warning',
          });
        }
        this.$tab.refreshPage();
        
      })
    },
   
   
   


    /** 删除按钮操作 */
    handleDelete(row) {
      let placeCodeStr = [];
     
      this.multipleSelection.map((item) => {
        placeCodeStr.push(item.placeCode);
      })
      let params = {
        placeCodes: placeCodeStr.join(',')//转换成字符串
      };


      this.$confirm('是否确认删除此客户的信息', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        crudPlace.delPlace(params).then((res) => {
          if (res != undefined) {
            if (res.id == 0) {
             this.$modal.msgSuccess('删除成功!');
             this.$tab.refreshPage();
             }else{
                   this.$alert(res.message+'，无法删除!!', '系统提示', {
                    confirmButtonText: '确定',
                 
                    type: 'error'
                  })
             }
            
          }
        })
      }).catch(() => {

      });


    },

  },
};
</script>
<style lang="scss" scoped>
.btnDiv {

  height: 50px;
  margin-top: 20px;

  ::v-deep .el-button {
    margin: 0 10px 0 10px;
  }
}

// 改变input框字体颜色
::v-deep .el-input__inner {
  background-color: transparent !important;
  color: black;
  border-color: none !important;
}

::v-deep .is-disabled {
  color: black;

}

::v-deep .el-radio__input.is-disabled+span.el-radio__label {
  background-color: transparent !important;
  color: black !important;
}

::v-deep .el-input.is-disabled .el-input__inner {
  color: black !important;
}

::v-deep .el-radio {
  background-color: #FFFFFF !important;
}

::v-deep .el-radio__input.is-checked .el-radio__inner {
  border-color: #1890ff !important;
  background: #1890ff !important;
}

::v-deep .el-dialog:not(.is-fullscreen) {
  margin-top: 1vh !important;
}

::v-deep .el-input.is-disabled .el-input__inner {
  background-color: rgba(255, 255, 255, 1.5) !important;
  color: black !important;
  border-color: rgba(135, 206, 250, 0.7) !important;
}

::v-deep .el-input-group__append {
  background-color: rgba(255, 255, 255, 1.5) !important;
  color: black !important;
  border-color: rgba(135, 206, 250, 0.7) !important;
}
</style>

