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
            <el-form-item label="提交时间">
                  <el-date-picker
                     v-model="projectTime"
                     value-format="yyyy-MM-dd HH:mm:ss"
                     type="datetimerange"
                    :picker-options="pickerOptions"
                    range-separator="至"
                   start-placeholder="开始日期"
                  end-placeholder="结束日期"
                   :default-time="['00:00:00', '23:59:59']"
                    align="right">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    
    <el-row :gutter="10" class="mb8">
   
    <el-col :span="15">
    <el-tabs v-model="endStatus" @tab-click="handleClick">
     <el-tab-pane label="全部" name="-1"></el-tab-pane>
     <el-tab-pane label="异常" name="2"></el-tab-pane>
     <el-tab-pane label="办理中" name="0"></el-tab-pane>
     <el-tab-pane label="完成" name="1"></el-tab-pane>
     
   </el-tabs>

    </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['company:employed:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['company:employed:edit']">修改</el-button>
      </el-col> -->
     
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['company:employed:export']">导出</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
  
       
   <el-button style="margin-top:-8px;margin-bottom:16px" type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete">删除</el-button>
    
     
   

    <el-table v-loading="loading" :data="employedList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="法人姓名" align="center" prop="legalPersonName" :show-overflow-tooltip="true" />
      <el-table-column label="个体名称" align="center" prop="selfName" :show-overflow-tooltip="true" />
      <el-table-column label="提交时间" align="center" prop="createTime" width="180" />
      <el-table-column width="400" label="渠道商" align="center" prop="placeName" />
      <el-table-column label="业务经理" align="center" prop="username" :show-overflow-tooltip="true" />
      <el-table-column label="进度状态" align="center" prop="endStatus">
        <template slot-scope="scope">
          <el-link @click="progressNew(scope.row.selfCode)" :underline="false" type="primary"
            v-if="scope.row.endStatus == '0' && scope.row.nameStatus != 2 && scope.row.infoStatus != 2">办理中</el-link>
          <el-link @click="progressNew(scope.row.selfCode)" :underline="false" type="danger"
            v-if="scope.row.endStatus == 2">异常</el-link>
          <el-link @click="progressNew(scope.row.selfCode)" :underline="false" type="success"
            v-if="scope.row.endStatus == '1'">完成</el-link>
        </template>
      </el-table-column>

      <el-table-column label="名称审核" align="center">
        <template slot-scope="scope">
          <el-link @click="examine(scope.row.applyName, scope.row, 1)" :underline="false" type="primary"
            v-if="scope.row.nameStatus == '0'">审核中
          </el-link>
          <el-link :underline="false" type="danger" @click="errName(scope.row, scope.row.selfCode)"
            v-if="scope.row.nameStatus == '2'">异常</el-link>
          <el-link @click="finishName(scope.row, scope.row.selfCode)" :underline="false" type="success"
            v-if="scope.row.nameStatus == '1'">完成</el-link>
        </template>
      </el-table-column>
      <el-table-column label="信息审核" align="center">
        <template slot-scope="scope">
          <el-link @click="examine(scope.row.applyName, scope.row, 2)" :underline="false" type="primary"
            v-if="scope.row.infoStatus == '0'">审核中
          </el-link>
          <el-link :underline="false" type="danger" @click="errInfo(scope.row, scope.row.selfCode)"
            v-if="scope.row.infoStatus == '2'">异常</el-link>
          <el-link @click="finishInfo(scope.row, scope.row.selfCode)" :underline="false" type="success"
            v-if="scope.row.infoStatus == '1'">完成</el-link>
        </template>
      </el-table-column>
      <!-- <el-table-column label="实名办理" align="center">
        <template slot-scope="scope">
          <el-link :underline="false" type="info"
            v-if="scope.row.nameStatus == 0 || scope.row.infoStatus == 0 || scope.row.nameStatus == 2 || scope.row.infoStatus == 2">
            未开始</el-link>
          <el-link :underline="false" type="primary" @click="examine(scope.row.applyName, scope.row, 3)"
            v-if="scope.row.nameStatus == 1 && scope.row.infoStatus == 1 && scope.row.realnameStatus == 0">待办理</el-link>
          <el-link :underline="false" @click="finishCer(scope.row, scope.row.selfCode)" type="success"
            v-if="scope.row.nameStatus == 1 && scope.row.infoStatus == 1 && scope.row.realnameStatus == 1">已完成</el-link>
        </template>
      </el-table-column> -->
      <el-table-column label="工商办理" align="center">
        <template slot-scope="scope">
          <el-link :underline="false" type="info"
            v-if="scope.row.nameStatus == 0 || scope.row.infoStatus == 0 || scope.row.nameStatus == 2 || scope.row.infoStatus == 2">
            未开始</el-link>
          <el-link :underline="false" type="primary" @click="examine(scope.row.applyName, scope.row, 4)"
            v-if="scope.row.nameStatus == 1 && scope.row.infoStatus == 1  && scope.row.businessStatus == 0">
            办理中</el-link>
          <el-link :underline="false" @click="finishBus(scope.row, scope.row.selfCode)" type="success"
            v-if="scope.row.nameStatus == 1 && scope.row.infoStatus == 1  && scope.row.businessStatus == 1">
            完成</el-link>
        </template>
      </el-table-column>

      <el-table-column label="税务办理" align="center">
        <template slot-scope="scope">
          <el-link :underline="false" type="info"
            v-if="scope.row.nameStatus == 0 || scope.row.infoStatus == 0 || scope.row.nameStatus == 2 || scope.row.infoStatus == 2 || scope.row.businessStatus == 0">
            未开始</el-link>
          <el-link :underline="false" type="primary" @click="examine(scope.row.applyName, scope.row, 5)"
            v-if="scope.row.nameStatus == 1 && scope.row.infoStatus == 1  && scope.row.businessStatus == 1 && scope.row.taxStatus == 0">
            办理中</el-link>
          <el-link :underline="false" @click="finishTax(scope.row, scope.row.selfCode)" type="success"
            v-if="scope.row.nameStatus == 1 && scope.row.infoStatus == 1  && scope.row.businessStatus == 1 && scope.row.taxStatus == 1">
            完成</el-link>
        </template>
      </el-table-column>
      <el-table-column label="银行办理" align="center">
        <template slot-scope="scope">
          <el-link :underline="false" type="info"
            v-if="scope.row.nameStatus == 0 || scope.row.infoStatus == 0 || scope.row.nameStatus == 2  || scope.row.infoStatus == 2 || scope.row.businessStatus == 0 || scope.row.taxStatus == 0">
            未开始</el-link>
          <el-link :underline="false" type="primary" @click="examine(scope.row.applyName, scope.row, 6)"
            v-if="scope.row.nameStatus == 1 && scope.row.infoStatus == 1  && scope.row.businessStatus == 1 && scope.row.taxStatus == 1 && scope.row.bankStatus == 0">
            办理中</el-link>
          <el-link :underline="false" @click="finishBank(scope.row, scope.row.selfCode)" type="success"
            v-if="scope.row.nameStatus == 1 && scope.row.infoStatus == 1  && scope.row.businessStatus == 1 && scope.row.taxStatus == 1 && scope.row.bankStatus == 1">
            完成</el-link>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />
    <!-- 进度提示 -->
    <el-dialog
    :closeOnClickModal=false
    :closeOnPressEscape=false
    title="进度详情" :visible.sync="dialogVisible" width="70%">
      <el-table :data="progressList">
        <el-table-column label="步骤" align="center" prop="dictLabel" :show-overflow-tooltip="true" />
        <el-table-column label="时间" align="center" prop="checkDate" width="180" />
        <el-table-column label="用户" align="center" prop="checkUser" />
        <el-table-column label="说明" align="center" prop="checkReasult" :show-overflow-tooltip="true" />
      </el-table>
    </el-dialog>

    <el-dialog
    :closeOnClickModal=false
    :closeOnPressEscape=false
    title="名称详情" :visible.sync="nameVisible" width="70%">
      <el-table :data="nameList">
        <el-table-column label="步骤" align="center" prop="dictLabel" :show-overflow-tooltip="true" />
        <el-table-column label="时间" align="center" prop="checkDate" width="180" />
        <el-table-column label="用户" align="center" prop="checkUser" />
        <el-table-column label="说明" align="center" prop="checkReasult" :show-overflow-tooltip="true" />
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="nameVisible = false">关闭</el-button>
        <el-button type="primary" @click="nameDetail">查看</el-button>
      </span>
    </el-dialog>

    <el-dialog
    :closeOnClickModal=false
    :closeOnPressEscape=false
    title="名称异常说明" :visible.sync="errsnameVisible" width="70%">
      <el-table :data="errsnameList">
        <el-table-column label="步骤" align="center" prop="dictLabel" :show-overflow-tooltip="true" />
        <el-table-column label="时间" align="center" prop="checkDate" width="180" />
        <el-table-column label="用户" align="center" prop="checkUser" />
        <el-table-column label="说明" align="center" prop="checkReasult" :show-overflow-tooltip="true" />
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="errsnameVisible = false">关闭</el-button>
        <el-button type="primary" @click="errsnameDetail">修改</el-button>
      </span>
    </el-dialog>

    <el-dialog
    :closeOnClickModal=false
    :closeOnPressEscape=false
    title="信息异常说明" :visible.sync="errsinfoVisible" width="70%">
      <el-table :data="errsinfoList">
        <el-table-column label="步骤" align="center" prop="dictLabel" :show-overflow-tooltip="true" />
        <el-table-column label="时间" align="center" prop="checkDate" width="180" />
        <el-table-column label="用户" align="center" prop="checkUser" />
        <el-table-column label="说明" align="center" prop="checkReasult" :show-overflow-tooltip="true" />
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="errsinfoVisible = false">关闭</el-button>
        <el-button type="primary" @click="errsinfoDetail">修改</el-button>
      </span>
    </el-dialog>

    <el-dialog
    :closeOnClickModal=false
    :closeOnPressEscape=false
    title="信息详情" :visible.sync="infoVisible" width="70%">
      <el-table :data="infoList">
        <el-table-column label="步骤" align="center" prop="dictLabel" :show-overflow-tooltip="true" />
        <el-table-column label="时间" align="center" prop="checkDate" width="180" />
        <el-table-column label="用户" align="center" prop="checkUser" />
        <el-table-column label="说明" align="center" prop="checkReasult" :show-overflow-tooltip="true" />
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="infoVisible = false">关闭</el-button>
        <el-button type="primary" @click="infoDetail">查看</el-button>
      </span>
    </el-dialog>

    <el-dialog
    :closeOnClickModal=false
    :closeOnPressEscape=false
    title="实名详情" :visible.sync="cerVisible" width="70%">
      <el-table :data="cerList">
        <el-table-column label="步骤" align="center" prop="dictLabel" :show-overflow-tooltip="true" />
        <el-table-column label="时间" align="center" prop="checkDate" width="180" />
        <el-table-column label="用户" align="center" prop="checkUser" />
        <el-table-column label="说明" align="center" prop="checkReasult" :show-overflow-tooltip="true" />
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cerVisible = false">关闭</el-button>
        <el-button type="primary" @click="cerDetail">查看</el-button>
      </span>
    </el-dialog>


    <el-dialog
    :closeOnClickModal=false
    :closeOnPressEscape=false
    title="工商详情" :visible.sync="busVisible" width="70%">
      <el-table :data="busList">
        <el-table-column label="步骤" align="center" prop="dictLabel" :show-overflow-tooltip="true" />
        <el-table-column label="时间" align="center" prop="checkDate" width="180" />
        <el-table-column label="用户" align="center" prop="checkUser" />
        <el-table-column label="说明" align="center" prop="checkReasult" :show-overflow-tooltip="true" />
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="busVisible = false">关闭</el-button>
        <el-button type="primary" @click="busDetail">查看</el-button>
      </span>
    </el-dialog>


    <el-dialog
    :closeOnClickModal=false
    :closeOnPressEscape=false
    title="税务详情" :visible.sync="taxVisible" width="70%">
      <el-table :data="taxList">
        <el-table-column label="步骤" align="center" prop="dictLabel" :show-overflow-tooltip="true" />
        <el-table-column label="时间" align="center" prop="checkDate" width="180" />
        <el-table-column label="用户" align="center" prop="checkUser" />
        <el-table-column label="说明" align="center" prop="checkReasult" :show-overflow-tooltip="true" />
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="taxVisible = false">关闭</el-button>
        <el-button type="primary" @click="taxDetail">查看</el-button>
      </span>
    </el-dialog>

    <el-dialog
    :closeOnClickModal=false
    :closeOnPressEscape=false
    title="银行详情" :visible.sync="bankVisible" width="70%">
      <el-table :data="bankList">
        <el-table-column label="步骤" align="center" prop="dictLabel" :show-overflow-tooltip="true" />
        <el-table-column label="时间" align="center" prop="checkDate" width="180" />
        <el-table-column label="用户" align="center" prop="checkUser" />
        <el-table-column label="说明" align="center" prop="checkReasult" :show-overflow-tooltip="true" />
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="bankVisible = false">关闭</el-button>
        <el-button type="primary" @click="bankDetail">查看</el-button>
      </span>
    </el-dialog>



  </div>
</template>

<script>

import { joinList, listEmployed, getEmployed, delEmployed, addEmployed, updateEmployed, checkdetail, getLeaderByUserId } from "@/api/company/employed";
// import axios from 'axios'
export default {
  name: "Employed",
  data() {
    return {
      projectTime:'',
      pickerOptions: {
                shortcuts: [{
                    text: '最近一周',
                    onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                        picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '最近一个月',
                    onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                        picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '最近三个月',
                    onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                        picker.$emit('pick', [start, end]);
                    }
                }]
       },
      multipleSelection:[],
      exmList: [],//等待数据
      errsinfoList: [],//信息数据异常
      errsnameList: [],//名称数据异常
      infoList: [],//信息数据
      nameList: [],//名称数据
      progressList: [],  //进度数据
      cerList: [],//实名数据
      busList: [],//工商数据
      taxList: [],//税务数据
      bankList: [],//银行数据
      exmVisible: false, //等待弹框
      bankVisible: false, //银行弹框
      taxVisible: false, //税务弹框
      busVisible: false, //工商弹框
      cerVisible: false, //实名弹框
      nameVisible: false, //名称弹框
      infoVisible: false, //信息弹框
      dialogVisible: false, //进度弹框
      errsnameVisible: false, //名称弹框异常
      errsinfoVisible: false, //信息弹框异常
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
      endStatus:'-1',
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        placeName: null,
        legalPersonName: null,
        userId: null,
        endStatus:'',
        start:null,
        end:null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },

  methods: {
     handleClick(tab, event) {
     if(this.endStatus=='-1'){
      this.queryParams.endStatus=null;
     }else{
      this.queryParams.endStatus=this.endStatus;
     }
      this.queryParams.pageNum=1;
      this.getList();
      },
    //获取审核中的数据

    examine(applyName, scope, type) {
      var msg = '审核';
      if (type < 3) {
        msg = '审核';
      } else {
        msg = '办理';
      }
      getLeaderByUserId({
        userId: applyName
      }).then(res => {
        const h = this.$createElement
        this.$confirm(
          '', {
  message:h('div',null, [
    h('i',{ class:'el-icon-question',style:'color:#f90;font-size:30px;' }),
    h('span',{ style:'margin-left:10px;font-size:16px;line-height:30px;font-weight:600;vertical-align:top;'}, '温馨提示'),
    h('p',{ style:'margin:40px 0 0 40px;height:80px' },'请等待' + res[0].userName + '(' + res[0].phonenumber + ')' + msg)
  ]),


      
          confirmButtonText: '查看',
          cancelButtonText: '关闭',
          closeOnClickModal:false,
          closeOnPressEscape:false,

        }).then(() => {
         switch (type) {
            case 1:
              this.$cache.local.setJSON('employedName', scope);
              this.$router.push("namedetail");
              break;
            case 2:
              this.$cache.local.setJSON('employedInfo', scope);
              this.$router.push("infodetail");
              break;
            case 3:
              this.$cache.local.setJSON('employednewlist', scope);
              this.$router.push("detailCer");
              break;

            case 4:
              this.$cache.local.setJSON('employednewlist', scope);
              this.$router.push("detailBusiness");
              break;

            case 5:
              this.$cache.local.setJSON('employednewlist', scope);
              this.$router.push("detailTax");
              break;

            case 6:
              this.$cache.local.setJSON('employednewlist', scope);
              this.$router.push("detailBank");
              break;

          }
        }).catch(() => {

        });




      }).catch(error => {
        console.log(error);
      })





    },
    //进度弹框
    progressNew(code) {
      this.checkdetail(code);
    },
    //进度详情列表
    checkdetail(arr) {
      this.progressList = [];
      let parms = {
        selfCode: arr
      }
      checkdetail(parms).then(res => {
        this.dialogVisible = true;
        this.progressList = res.rows;

      });
    },
    //名称进度
    checkName(arr) {
      this.nameList = [];
      let parms = {
        selfCode: arr,
        selfType: 2,
      }
      //名称进度
      checkdetail(parms).then(res => {
        this.nameList = res.rows;
        this.nameVisible = true;
      });
    },

    //信息进度
    checkInfo(arr) {
      this.infoList = [];
      let parms = {
        selfCode: arr,
        selfType: 3,
      }
      //信息进度
      checkdetail(parms).then(res => {
        this.infoList = res.rows;
        this.infoVisible = true;
      });
    },



    //异常名称进度
    checkNames(arr) {
      this.errsnameList = [];
      let parms = {
        selfCode: arr,
        selfType: 2,
      }
      //异常名称进度
      checkdetail(parms).then(res => {
        this.errsnameList = res.rows;
        this.errsnameVisible = true;
      });
    },

    //异常信息进度
    checkInfos(arr) {
      this.errsinfoList = [];
      let parms = {
        selfCode: arr,
        selfType: 3,
      }
      //异常信息进度
      checkdetail(parms).then(res => {
        this.errsinfoList = res.rows;
        this.errsinfoVisible = true;
      });
    },





    //实名进度
    checkCer(arr) {
      this.cerList = [];
      let parms = {
        selfCode: arr,
        selfType: 4,
      }
      //实名进度
      checkdetail(parms).then(res => {
        this.cerList = res.rows;
        this.cerVisible = true;
      });
    },

    //工商进度
    checkBus(arr) {
      this.busList = [];
      let parms = {
        selfCode: arr,
        selfType: 5,
      }
      //工商进度
      checkdetail(parms).then(res => {
        this.busList = res.rows;
        this.busVisible = true;
      });
    },

    //税务进度
    checkTax(arr) {
      this.taxList = [];
      let parms = {
        selfCode: arr,
        selfType: 6,
      }
      //税务进度
      checkdetail(parms).then(res => {
        this.taxList = res.rows;
        this.taxVisible = true;
      });
    },

    //银行进度
    checkBank(arr) {
      this.bankList = [];
      let parms = {
        selfCode: arr,
        selfType: 7,
      }
      //银行进度
      checkdetail(parms).then(res => {
        this.bankList = res.rows;
        this.bankVisible = true;
      });
    },

    unfinish() {

    },


    //异常名称
    errName(scope, selfCode) {
      this.checkNames(selfCode);
      this.$cache.local.setJSON('employedName', scope);
      // this.$router.push("editEmployedName");   
    },
    //异常信息
    errInfo(scope, selfCode) {
      this.checkInfos(selfCode);
      this.$cache.local.setJSON('employedInfo', scope);
      //  this.$router.push("editEmployedInfo");  
    },

    errsnameDetail() {
      this.errsnameVisible = false;
      this.$router.push("editEmployedName");
    },
    errsinfoDetail() {
      this.errsinfoVisible = false;
      this.$router.push("editEmployedInfo");
    },
    check(self_code, self_type) {
      let parms = {
        selfCode: self_code,
        selfType: self_type
      }
      checkdetail(parms).then(res => {

      });
    },

    //名称审核 已完成
    finishName(scope, selfCode) {
      this.checkName(selfCode);
      this.$cache.local.setJSON('employedInfo', scope);
    },
    //名称详情
    nameDetail() {
        let obj={
         backUrl:'/company/customer/employed',
        };
      this.$cache.local.setJSON('backurls', obj);
      this.nameVisible = false;
      this.$router.push("namedetail");
    },
    //信息审核 已完成
    finishInfo(scope, selfCode) {
      this.checkInfo(selfCode);
      this.$cache.local.setJSON('employedInfo', scope);

    },

    //信息详情
    infoDetail() {
      this.infoVisible = false;
        let obj={
         backUrl:'/company/customer/employed',
        };
      this.$cache.local.setJSON('backurls', obj);
      this.$router.push("infodetail");
    },

    //办理实名 已完成
    finishCer(scope, selfCode) {
      this.checkCer(selfCode);
      this.$cache.local.setJSON('employedInfo', scope);

    },

    //实名详情
    cerDetail() {
      this.cerVisible = false;
      this.$router.push("detailCer");
    },

    //办理工商 已完成
    finishBus(scope, selfCode) {
      this.checkBus(selfCode);
      this.$cache.local.setJSON('employedInfo', scope);

    },

    //工商详情
    busDetail() {
      this.busVisible = false;
      this.$router.push("detailBusiness");
    },

    //办理税务 已完成
    finishTax(scope, selfCode) {
      this.checkTax(selfCode);
      this.$cache.local.setJSON('employedInfo', scope);

    },

    //税务详情
    taxDetail() {
      this.taxVisible = false;
      this.$router.push("detailTax");
    },

    //办理银行 已完成
    finishBank(scope, selfCode) {
      this.checkBank(selfCode);
      this.$cache.local.setJSON('employedInfo', scope);

    },

    //银行详情
    bankDetail() {
      this.bankVisible = false;
      this.$router.push("detailBank");
    },


    /** 查询个体商户列表 */
    getList() {
       if(this.projectTime!= null){//如果不选择时间，或者选择时间再将时间清除，直接点击查询，会报错，所以要判断一下，这个为时间不为空走这个。
                this.queryParams.start =this.projectTime[0];
                this.queryParams.end =this.projectTime[1];
                console.log("start",this.queryParams.start);
                console.log("end",this.queryParams.end);
            }else {//判断选择时间再将时间清除
                    this.projectTime=null;
            };
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
        placeName: null,
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
      this.endStatus='-1';
      this.queryParams.endStatus='';
      this.queryParams.start=null;
      this.queryParams.end=null;
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.multipleSelection = selection;


      this.ids = selection.map(item => item.selfId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
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
    handleDelete() {

      this.$confirm('是否确认删除seifId为'+this.ids+'个体户注册信息吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
       delEmployed(this.ids).then(res=>{
        
          
            if (res.code == 200) {
              this.$modal.msgSuccess("删除成功");
            } else {
              this.$modal.msgError("删除失败");
            }
            this.$tab.refreshPage();
            // this.getList();
            // this.$modal.msgSuccess("删除成功");
          
       })
        
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });



    //  this.$modal.confirm('是否确认删除seifId为'+this.ids+'个体户注册信息吗？','提示', {
    //       confirmButtonText: '确定',
    //       cancelButtonText: '取消',
    //       type: 'warning'
    //     }).then(function () {
    //       console.log(22222222);
    //     let params = {
    //     selfIds: this.ids.join(',')//转换成字符串
    //    };
    //    delEmployed(params).then(res=>{
        
    //       if (res != undefined) {
    //         if (res.id == 0) {
    //           this.$message({
    //             message: res.message,
    //             type: 'success',
    //           });
    //         } else {
    //           this.$message({
    //             message: res.message,
    //             type: 'warning',
    //           });
    //         }
    //         this.$tab.refreshPage();
    //           // this.getList();
    //          this.$modal.msgSuccess("删除成功");
    //       }
    //    })
    //   }).catch(() => {
    //        console.log(22222222);

    //    });
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
   ::v-deep .el-message-box__content{
     height: 200px !important;
   }
     ::v-deep .el-tabs__nav-wrap::after{
        background-color:rgba(0,0,0,0) !important;
   }
</style>
