<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="auto">

      <el-form-item label="法人姓名" prop="legalPersonName">
        <el-input v-model="queryParams.legalPersonName" placeholder="请输入法人姓名" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="客户全名" prop="placeAliasName">
        <el-input v-model="queryParams.placeAliasName" placeholder="请输入客户全名" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="提交时间">
        <el-date-picker @change="prjecs" v-model="projectTime" value-format="yyyy-MM-dd" type="daterange"
          :picker-options="pickerOptions" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期"
          :default-time="['00:00:00', '23:59:59']" align="right">
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
          <el-tab-pane :label="loadingLabel" name="0"></el-tab-pane>
          <el-tab-pane :label="errLabel" name="2"></el-tab-pane>
          <el-tab-pane :label="finishLabel" name="1"></el-tab-pane>
          <el-tab-pane :label="allLabel" name="-1"></el-tab-pane>

        </el-tabs>

      </el-col>




      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-button v-hasPermi="['company:employed:add1']" type="primary" plain icon="el-icon-plus" size="mini"
      @click="handleAdd">新增</el-button>
    <el-button style="margin-top:-8px;margin-bottom:16px" type="danger" plain icon="el-icon-delete" size="mini"
      :disabled="multiple" v-hasPermi="['company:employed:del']" @click="handleDelete">注销</el-button>




    <el-table v-loading="loading" :data="employedList" @selection-change="handleSelectionChange">
      <el-table-column  type="selection" width="55" align="center" />
      <el-table-column  label="法人姓名" align="center" prop="legalPersonName" :show-overflow-tooltip="true" />
      <el-table-column  label="个体名称" width="250" align="center" prop="selfName" :show-overflow-tooltip="true" />
      <el-table-column  label="提交时间" align="center" width="120" prop="createTime">
        <template slot-scope="scope">
          {{ scope.row.createTime | filterTime }}
        </template>
      </el-table-column>
      
      <el-table-column  width="350" label="客户全名" align="center" prop="placeAliasName" :show-overflow-tooltip="true" />
      <el-table-column label="业务经理" fixed align="center" prop="username" :show-overflow-tooltip="true" />
     
      <el-table-column  label="进度状态" align="center" prop="endStatus">
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
          <el-link :underline="false" type="danger" @click="errName(scope.row, scope.row.selfCode, scope.row.userId)"
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
          <el-link :underline="false" type="danger" @click="errInfo(scope.row, scope.row.selfCode, scope.row.userId)"
            v-if="scope.row.infoStatus == '2'">异常</el-link>
          <el-link @click="finishInfo(scope.row, scope.row.selfCode)" :underline="false" type="success"
            v-if="scope.row.infoStatus == '1'">完成</el-link>
        </template>
      </el-table-column>
     
      <el-table-column label="工商办理" align="center">
        <template slot-scope="scope">
          <el-link :underline="false" type="info"
            v-if="scope.row.nameStatus == 0 || scope.row.infoStatus == 0 || scope.row.nameStatus == 2 || scope.row.infoStatus == 2">
            未开始</el-link>
          <el-link :underline="false" type="primary" @click="examine(scope.row.applyName, scope.row, 4)"
            v-if="scope.row.nameStatus == 1 && scope.row.infoStatus == 1 && scope.row.businessStatus == 0">
            办理中</el-link>
          <el-link :underline="false" @click="finishBus(scope.row, scope.row.selfCode)" type="success"
            v-if="scope.row.nameStatus == 1 && scope.row.infoStatus == 1 && scope.row.businessStatus == 1">
            完成</el-link>
        </template>
      </el-table-column>

      <el-table-column label="税务办理" align="center">
        <template slot-scope="scope">
          <el-link :underline="false" type="info"
            v-if="scope.row.nameStatus == 0 || scope.row.infoStatus == 0 || scope.row.nameStatus == 2 || scope.row.infoStatus == 2 || scope.row.businessStatus == 0">
            未开始</el-link>
          <el-link :underline="false" type="primary" @click="examine(scope.row.applyName, scope.row, 5)"
            v-if="scope.row.nameStatus == 1 && scope.row.infoStatus == 1 && scope.row.businessStatus == 1 && scope.row.taxStatus == 0">
            办理中</el-link>
          <el-link :underline="false" @click="finishTax(scope.row, scope.row.selfCode)" type="success"
            v-if="scope.row.nameStatus == 1 && scope.row.infoStatus == 1 && scope.row.businessStatus == 1 && scope.row.taxStatus == 1">
            完成</el-link>
        </template>
      </el-table-column>
      <el-table-column label="银行办理" align="center">
        <template slot-scope="scope">
          <el-link :underline="false" type="info"
            v-if="scope.row.nameStatus == 0 || scope.row.infoStatus == 0 || scope.row.nameStatus == 2 || scope.row.infoStatus == 2 || scope.row.businessStatus == 0 || scope.row.taxStatus == 0">
            未开始</el-link>
          <el-link :underline="false" type="primary" @click="examine(scope.row.applyName, scope.row, 6)"
            v-if="scope.row.nameStatus == 1 && scope.row.infoStatus == 1 && scope.row.businessStatus == 1 && scope.row.taxStatus == 1 && scope.row.bankStatus == 0">
            办理中</el-link>
          <el-link :underline="false" @click="finishBank(scope.row, scope.row.selfCode)" type="success"
            v-if="scope.row.nameStatus == 1 && scope.row.infoStatus == 1 && scope.row.businessStatus == 1 && scope.row.taxStatus == 1 && scope.row.bankStatus == 1">
            完成</el-link>
        </template>
      </el-table-column>
      <el-table-column label="注册确认" align="center">
        <template slot-scope="scope">
          <el-link :underline="false" type="info"
            v-if="scope.row.nameStatus == 0 || scope.row.infoStatus == 0 || scope.row.nameStatus == 2 || scope.row.infoStatus == 2 || scope.row.businessStatus == 0 || scope.row.taxStatus == 0 || scope.row.bankStatus == 0">
            未开始</el-link>
          <el-link :underline="false" type="primary" @click="examine(scope.row.userId, scope.row, 7)"
            v-if="scope.row.nameStatus == 1 && scope.row.infoStatus == 1 && scope.row.businessStatus == 1 && scope.row.taxStatus == 1 && scope.row.bankStatus == 1 && scope.row.endStatus == 0">
            办理中</el-link>
          <el-link :underline="false" @click="finishConfirms(scope.row, scope.row.selfCode)" type="success"
            v-if="scope.row.endStatus == 1">
            完成</el-link>
          <el-link @click="errConfirms(scope.row, scope.row.selfCode, scope.row.applyName)" :underline="false"
            type="danger"
            v-if="scope.row.nameStatus == 1 && scope.row.infoStatus == 1 && scope.row.businessStatus == 1 && scope.row.taxStatus == 1 && scope.row.bankStatus == 1 && scope.row.endStatus == 2">
            异常</el-link>
        </template>
      </el-table-column>
  
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />
    <!-- 进度提示 -->
    <el-dialog :closeOnClickModal=false :closeOnPressEscape=false title="进度跟踪" :visible.sync="dialogVisible"
      width="70%">
      <el-table :data="progressList">
        <el-table-column label="操作步骤" align="center" prop="dictLabel" :show-overflow-tooltip="true" />
        <el-table-column label="操作时间" align="center" prop="checkDate" width="180" />
        <el-table-column label="操作用户" align="center" prop="checkUser" />
        <el-table-column label="结果说明" align="center" prop="checkReasult" :show-overflow-tooltip="true" />
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">关闭</el-button>

      </span>
    </el-dialog>

    <el-dialog :closeOnClickModal=false :closeOnPressEscape=false title="名称进度跟踪" :visible.sync="nameVisible"
      width="70%">
      <el-table :data="nameList">
        <el-table-column label="操作步骤" align="center" prop="dictLabel" :show-overflow-tooltip="true" />
        <el-table-column label="操作时间" align="center" prop="checkDate" width="180" />
        <el-table-column label="操作用户" align="center" prop="checkUser" />
        <el-table-column label="结果说明" align="center" prop="checkReasult" :show-overflow-tooltip="true" />
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="nameVisible = false">关闭</el-button>
        <el-button type="primary" @click="nameDetail">查看</el-button>
      </span>
    </el-dialog>


    <el-dialog :closeOnClickModal=false :closeOnPressEscape=false title="信息进度跟踪" :visible.sync="infoVisible"
      width="70%">
      <el-table :data="infoList">
        <el-table-column label="操作步骤" align="center" prop="dictLabel" :show-overflow-tooltip="true" />
        <el-table-column label="操作时间" align="center" prop="checkDate" width="180" />
        <el-table-column label="操作用户" align="center" prop="checkUser" />
        <el-table-column label="结果说明" align="center" prop="checkReasult" :show-overflow-tooltip="true" />
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="infoVisible = false">关闭</el-button>
        <el-button type="primary" @click="infoDetail">查看</el-button>
      </span>
    </el-dialog>

    <el-dialog :closeOnClickModal=false :closeOnPressEscape=false title="实名进度跟踪" :visible.sync="cerVisible"
      width="70%">
      <el-table :data="cerList">
        <el-table-column label="操作步骤" align="center" prop="dictLabel" :show-overflow-tooltip="true" />
        <el-table-column label="操作时间" align="center" prop="checkDate" width="180" />
        <el-table-column label="操作用户" align="center" prop="checkUser" />
        <el-table-column label="结果说明" align="center" prop="checkReasult" :show-overflow-tooltip="true" />
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cerVisible = false">关闭</el-button>
        <el-button type="primary" @click="cerDetail">查看</el-button>
      </span>
    </el-dialog>


    <el-dialog :closeOnClickModal=false :closeOnPressEscape=false title="工商进度跟踪" :visible.sync="busVisible"
      width="70%">
      <el-table :data="busList">
        <el-table-column label="操作步骤" align="center" prop="dictLabel" :show-overflow-tooltip="true" />
        <el-table-column label="操作时间" align="center" prop="checkDate" width="180" />
        <el-table-column label="操作用户" align="center" prop="checkUser" />
        <el-table-column label="结果说明" align="center" prop="checkReasult" :show-overflow-tooltip="true" />
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="busVisible = false">关闭</el-button>
        <el-button type="primary" @click="busDetail">查看</el-button>
      </span>
    </el-dialog>


    <el-dialog :closeOnClickModal=false :closeOnPressEscape=false title="税务进度跟踪" :visible.sync="taxVisible"
      width="70%">
      <el-table :data="taxList">
        <el-table-column label="操作步骤" align="center" prop="dictLabel" :show-overflow-tooltip="true" />
        <el-table-column label="操作时间" align="center" prop="checkDate" width="180" />
        <el-table-column label="操作用户" align="center" prop="checkUser" />
        <el-table-column label="结果说明" align="center" prop="checkReasult" :show-overflow-tooltip="true" />
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="taxVisible = false">关闭</el-button>
        <el-button type="primary" @click="taxDetail">查看</el-button>
      </span>
    </el-dialog>

    <el-dialog :closeOnClickModal=false :closeOnPressEscape=false title="银行进度跟踪" :visible.sync="bankVisible"
      width="70%">
      <el-table :data="bankList">
        <el-table-column label="操作步骤" align="center" prop="dictLabel" :show-overflow-tooltip="true" />
        <el-table-column label="操作时间" align="center" prop="checkDate" width="180" />
        <el-table-column label="操作用户" align="center" prop="checkUser" />
        <el-table-column label="结果说明" align="center" prop="checkReasult" :show-overflow-tooltip="true" />
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="bankVisible = false">关闭</el-button>
        <el-button type="primary" @click="bankDetail">查看</el-button>
      </span>
    </el-dialog>

    <el-dialog :closeOnClickModal=false :closeOnPressEscape=false title="注册确认进度跟踪" :visible.sync="confirmVisible"
      width="70%">
      <el-table :data="confirmList">
        <el-table-column label="操作步骤" align="center" prop="dictLabel" :show-overflow-tooltip="true" />
        <el-table-column label="操作时间" align="center" prop="checkDate" width="180" />
        <el-table-column label="操作用户" align="center" prop="checkUser" />
        <el-table-column label="结果说明" align="center" prop="checkReasult" :show-overflow-tooltip="true" />
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="confirmVisible = false">关闭</el-button>
        <el-button type="primary" @click="confirmDetail">查看</el-button>
      </span>
    </el-dialog>



  </div>
</template>

<script>
import moment from 'moment'
import { joinList, getCount, delEmployed, checkdetail } from "@/api/company/employed";
import { getInfo } from '@/api/login'
import { getUser } from '@/api/system/user'
export default {
  name: "Employed",
  data() {
    return {
      errConfirmsMsg: '查看',
      errNameMsg: '查看',
      errInfoMsg: '查看',
      allLabel: '全部',
      errLabel: '异常',
      loadingLabel: '办理中',
      finishLabel: '完成',
      projectTime: null,
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
      errArrName: [], //名称异常数组
      errArrInfo: [], //信息异常数组
      errArrConfirm: [], //信息确认异常数组
      multipleSelection: [],
      confirmList: [],
      errsConfirmList: [],//注册数据异常
      exmList: [],//等待数据
      errsinfoList: [],//信息数据异常
      errsinfoList1: [],//信息数据异常
      errsinfoList2: [],//信息数据异常
      errsnameList: [],//名称数据异常
      errsnameList1: [],//名称数据异常
      errsnameList2: [],//名称数据异常
      infoList: [],//信息数据
      infoList1: [],//信息数据
      infoList2: [],//信息数据
      nameList: [],//名称数据
      nameList1: [],//名称数据
      nameList2: [],//名称数据
      progressList: [],  //进度数据
      cerList: [],//实名数据
      busList: [],//工商数据
      taxList: [],//税务数据
      bankList: [],//银行数据
      confirmVisible: false, //注册确认
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
      errsConfirmVisible: false, //注册异常
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
      endStatus: '0',
      userinfo: {},
      queryParams: {
        pageNum: 1,
        type: 1,
        pageSize: 10,
        placeAliasName: null,
        legalPersonName: null,
        userId: null,
        endStatus: 0,
        start: null,
        end: null,
      },

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
    this.getLoginInfo();
    this.getList();
  },

  methods: {
    prjecs(e) {
      if (!this.projectTime) {
        this.queryParams.start = null;
        this.queryParams.end = null;
      }
    },
    getLoginInfo() {
      getInfo().then(res => {
        this.userinfo = res.user;
      })
    },
    handleClick(tab, event) {
      if (this.endStatus == '-1') {
        this.queryParams.endStatus = null;
      } else {
        this.queryParams.endStatus = this.endStatus;
      }
      this.queryParams.pageNum = 1;
      this.getList();
    },
    //获取审核中的数据

    examine(applyName, scope, type) {
      let obj = {
        backUrl: '/company/customer/employed',
      };
      this.$cache.local.setJSON('backurls', obj);
      var msg = '审核';
      if (type < 3) {
        msg = '审核';
      } else {
        msg = '办理';
      }
      getUser(applyName).then(res => {
        console.log(res);
        let msgs;
        if (this.userinfo.userId == res.data.userId) {
          msgs = msg;
        } else {
          msgs = '查看';
        }
        const h = this.$createElement
        this.$confirm(
          '', {
          message: h('div', null, [
            h('i', { class: 'el-icon-question', style: 'color:#f90;font-size:30px;' }),
            h('span', { style: 'margin-left:10px;font-size:16px;line-height:30px;font-weight:600;vertical-align:top;' }, '温馨提示'),
            h('p', { style: 'margin:40px 0 0 40px;height:80px' }, '请等待' + res.data.userName + '(' + res.data.phonenumber + ')' + msg)
          ]),



          confirmButtonText: msgs,
          cancelButtonText: '关闭',
          closeOnClickModal: false,
          closeOnPressEscape: false,

        }).then(() => {
          if (msgs == '查看') {


            switch (type) {
              case 1:
                this.$cache.local.setJSON('employedName', scope);
                 this.$tab.openPage("个体户名称查看", "/company/customer/namedetail")
                break;
              case 2:
                this.$cache.local.setJSON('employedInfo', scope);
                 this.$tab.openPage("个体户信息查看", "/company/customer/infodetail")
                 break;
              case 3:
                this.$cache.local.setJSON('employednewlist', scope);
                 this.$tab.openPage("个体户实名查看", "/company/customer/detailCer")
                 break;

              case 4:
                 this.$cache.local.setJSON('employednewlist', scope);
                 this.$tab.openPage("个体户工商查看", "/company/customer/detailBusiness")
                
                break;

              case 5:
                this.$cache.local.setJSON('employednewlist', scope);
                this.$tab.openPage("个体户税务查看", "/company/customer/detailTax")
                 break;
              case 6:
                this.$cache.local.setJSON('employednewlist', scope);
                this.$tab.openPage("个体户银行查看", "/company/customer/detailBank")
                break;
              case 7:
                this.$cache.local.setJSON("employedInfo", scope);
                this.$tab.openPage("个体户注册确认查看", "/company/customer/RegReviewDetail")
                 break;

            }
          } else {

            switch (type) {
              case 1:
                let obj = {
                  title: '名称审核',
                  backUrl: '/company/customer/employed',
                  resmsg: '名称审核完成',
                  backName:'Employed'
                }
                this.$cache.local.setJSON('successNew', obj);
                this.$cache.local.setJSON('employedName', scope);
                this.$tab.openPage("个体户名称审核", "/company/customer/namenew")
                 break;
              case 2:
                let obj1 = {
                  title: '信息审核',
                  backUrl: '/company/customer/employed',
                  resmsg: '信息审核完成',
                  backName:'Employed'
                }
                this.$cache.local.setJSON('successNew', obj1);
                this.$cache.local.setJSON('employedInfo', scope);
                this.$tab.openPage("个体户信息审核", "/company/customer/infonew")
                break;
                case 3:
                break;
                case 4:
                let obj2 = {
                  title: '工商办理',
                  backUrl: '/company/customer/employed',
                  resmsg: '工商办理完成',
                  backName:'Employed'
                }
                this.$cache.local.setJSON('successNew', obj2);
                this.$cache.local.setJSON('employednewlist', scope);
                this.$tab.openPage("个体户工商办理", "/company/customer/addBusiness")
                break;
                case 5:
                let obj3 = {
                  title: '税务办理',
                  backUrl: '/company/customer/employed',
                  resmsg: '税务办理完成',
                  backName:'Employed'
                }
                this.$cache.local.setJSON('successNew', obj3);
                this.$cache.local.setJSON('employednewlist', scope);
                this.$tab.openPage("个体户税务办理", "/company/customer/addTax")
                break;
                case 6:
                let obj4 = {
                  title: '银行办理',
                  backUrl: '/company/customer/employed',
                  resmsg: '银行办理完成',
                  backName:'Employed'
                }
                this.$cache.local.setJSON('successNew', obj4);
                this.$cache.local.setJSON('employednewlist', scope);
                this.$tab.openPage("个体户银行办理", "/company/customer/addBank")
                break;
                case 7:
                let obj5 = {
                  title: '注册确认',
                  backUrl: '/company/customer/employed',
                  resmsg: '注册确认完成',
                  backName:'Employed'
                }
                this.$cache.local.setJSON('successNew', obj5);
                this.$cache.local.setJSON('employedInfo', scope);
                this.$tab.openPage("个体户注册确认办理", "/company/customer/confirmDetail")
                break;

            }
          }

        }).catch(() => {

        });

      }).catch(error => {
        console.log(error);
      })
    },
    //注册确认完成
    finishConfirms(scope, selfCode) {
      this.checkConfirms(selfCode);
      this.$cache.local.setJSON("employedInfo", scope);
    },
    //注册确认
    checkConfirms(arr) {
      this.confirmList = [];
      let parms = {
        selfCode: arr,
        selfType: 8,
      }
      //注册进度
      checkdetail(parms).then(res => {
        this.confirmList = res;
        this.confirmVisible = true;
      });
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
        this.progressList = res;

      });
    },

    //名称进度
    checkName(arr) {
      this.nameList = [];
      this.nameList1 = [];
      this.nameList2 = [];
      let parms = {
        selfCode: arr,
        selfType: 2,
      };
      let parmss = {
        selfCode: arr,
        selfType: 9,
      };
      //名称进度
      checkdetail(parms).then(res => {
        this.nameList2 = res;
        checkdetail(parmss).then(res => {
          this.nameList1 = res;
          this.nameList = this.nameList1.concat(this.nameList2);
          this.nameVisible = true;

        });

      });
    },

    //信息进度
    checkInfo(arr) {
      this.infoList = [];
      this.infoList1 = [];
      this.infoList2 = [];
      let parms = {
        selfCode: arr,
        selfType: 3,
      };
      let parmss = {
        selfCode: arr,
        selfType: 10,
      };
      //信息进度
      checkdetail(parms).then(res => {
        this.infoList2 = res;
        checkdetail(parmss).then(res => {
          this.infoList1 = res;
          this.infoList = this.infoList1.concat(this.infoList2);
          this.infoVisible = true;
        });
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
        this.cerList = res;
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
        this.busList = res;
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
        this.taxList = res;
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
        this.bankList = res;
        this.bankVisible = true;
      });
    },


    //异常注册确认
    errConfirms(scope, selfCode, applyName) {
      this.$cache.local.setJSON('employedInfo', scope);
      let obj = {
        backUrl: '/company/customer/employed',
        backName:'Employed',
      };
      this.$cache.local.setJSON('backurls', obj);
      getUser(applyName).then(res => {
        if (this.userinfo.userId == res.data.userId) {
          this.errConfirmsMsg = '修改';
        } else {
          this.errConfirmsMsg = '查看';
        }


        let parms = {
          selfCode: selfCode,
          selfType: 8,
        }
        checkdetail(parms).then(err => {
          this.errArrConfirm = err[err.length - 1].checkReasult;
          const h = this.$createElement;
          this.$confirm(
            '', {
            message: h('div', null, [
              h('i', { class: 'el-icon-question', style: 'color:#f90;font-size:30px;' }),
              h('span', { style: 'margin-left:10px;font-size:16px;line-height:30px;font-weight:600;vertical-align:top;' }, '温馨提示'),
              h('P', { style: 'margin:20px 0 0 40px;' }, this.errArrConfirm),
              h('p', { style: 'margin:5px 0 0 40px;' }, '请等待' + res.data.userName + '(' + res.data.phonenumber + ')' + '修改')
            ]),



            confirmButtonText: this.errConfirmsMsg,
            cancelButtonText: '关闭',
            closeOnClickModal: false,
            closeOnPressEscape: false,

          }).then(() => {
            if (this.errConfirmsMsg == '修改') {
                 this.$tab.openPage("个体户注册确认修改", "/company/customer/regReviewDdit")
             } else {
               this.$tab.openPage("个体户注册确认查看", "/company/customer/RegReviewDetail")
                
            }

          }).catch(() => {

          });

        })
      });
    },

    //异常名称进度
    checkConfirmss(arr) {
      this.errsConfirmList = [];
      let parms = {
        selfCode: arr,
        selfType: 8,
      }
      //异常名称进度
      checkdetail(parms).then(res => {
        this.errsConfirmList = res;
        this.errsConfirmVisible = true;
      });
    },

    //异常名称
    errName(scope, selfCode, applyName) {
      this.$cache.local.setJSON('employedName', scope);
      let obj = {
        backUrl: '/company/customer/employed',
      };
      this.$cache.local.setJSON('backurls', obj);
      getUser(applyName).then(res => {
        if (this.userinfo.userId == res.data.userId) {
          this.errNameMsg = '修改';
        } else {
          this.errNameMsg = '查看';
        }
        let parms = {
          selfCode: selfCode,
          selfType: 2,
        };
        checkdetail(parms).then(err => {
          this.errArrName = err[err.length - 1].checkReasult;
          const h = this.$createElement;
          this.$confirm(
            '', {
            message: h('div', null, [
              h('i', { class: 'el-icon-question', style: 'color:#f90;font-size:30px;' }),
              h('span', { style: 'margin-left:10px;font-size:16px;line-height:30px;font-weight:600;vertical-align:top;' }, '温馨提示'),
              h('P', { style: 'margin:20px 0 0 40px;' }, this.errArrName),
              h('p', { style: 'margin:5px 0 0 40px;' }, '请等待' + res.data.userName + '(' + res.data.phonenumber + ')' + '修改')
            ]),



            confirmButtonText: this.errNameMsg,
            cancelButtonText: '关闭',
            closeOnClickModal: false,
            closeOnPressEscape: false,

          }).then(() => {
            if (this.errNameMsg == '修改') {
               this.$tab.openPage("个体户名称修改", "/company/customer/editEmployedName")
            } else {
               this.$tab.openPage("个体户名称查看", "/company/customer/namedetail")
            }
          }).catch(() => {

          });

        })






      });
    },
    //异常信息
    errInfo(scope, selfCode, applyName) {
      this.$cache.local.setJSON('employedInfo', scope);
      let obj = {
        backUrl: '/company/customer/employed',
      };
      this.$cache.local.setJSON('backurls', obj);
      getUser(applyName).then(res => {

        if (this.userinfo.userId == res.data.userId) {
          this.errInfoMsg = '修改';
        } else {
          this.errInfoMsg = '查看';
        }
        let parms = {
          selfCode: selfCode,
          selfType: 3,
        };
        checkdetail(parms).then(err => {
          this.errArrInfo = err[err.length - 1].checkReasult;
          const h = this.$createElement;
          this.$confirm(
            '', {
            message: h('div', null, [
              h('i', { class: 'el-icon-question', style: 'color:#f90;font-size:30px;' }),
              h('span', { style: 'margin-left:10px;font-size:16px;line-height:30px;font-weight:600;vertical-align:top;' }, '温馨提示'),
              h('P', { style: 'margin:20px 0 0 40px;' }, this.errArrInfo),
              h('p', { style: 'margin:5px 0 0 40px;' }, '请等待' + res.data.userName + '(' + res.data.phonenumber + ')' + '修改')
            ]),



            confirmButtonText: this.errInfoMsg,
            cancelButtonText: '关闭',
            closeOnClickModal: false,
            closeOnPressEscape: false,

          }).then(() => {
            if (this.errInfoMsg == '修改') {
               this.$tab.openPage("个体户信息修改", "/company/customer/editEmployedInfo")
              } else {
              this.$tab.openPage("个体户信息查看", "/company/customer/infodetail")
              }
          }).catch(() => {

          });

        })






      });


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
      this.$cache.local.setJSON('employedName', scope);

    },
    //名称详情
    nameDetail() {
      let obj = {
        backUrl: '/company/customer/employed',
      };
      this.$cache.local.setJSON('backurls', obj);
      this.nameVisible = false;
      this.$tab.openPage("个体户名称查看", "/company/customer/namedetail")
     

    },
    //信息审核 已完成
    finishInfo(scope, selfCode) {
      this.checkInfo(selfCode);
      this.$cache.local.setJSON('employedInfo', scope);

    },


    //信息详情
    infoDetail() {
      this.infoVisible = false;
      let obj = {
        backUrl: '/company/customer/employed',
      };
      this.$cache.local.setJSON('backurls', obj);
      this.$tab.openPage("个体户信息查看", "/company/customer/infodetail")
      
    },

    //办理实名 已完成
    finishCer(scope, selfCode) {
      this.checkCer(selfCode);
      this.$cache.local.setJSON("employednewlist", scope);
      this.$cache.local.setJSON('employedInfo', scope);

    },

    //实名详情
    cerDetail() {
      this.cerVisible = false;
      this.$tab.openPage("个体户实名查看", "/company/customer/detailCer")
     
    },
    //注册确认详情
    confirmDetail() {
      this.confirmVisible = false;
      let obj = {
        backUrl: '/company/customer/employed',
      };
      this.$cache.local.setJSON('backurls', obj);
      this.$tab.openPage("个体户注册确认查看", "/company/customer/RegReviewDetail")
      

    },

    //办理工商 已完成
    finishBus(scope, selfCode) {
      this.checkBus(selfCode);
      this.$cache.local.setJSON("employednewlist", scope);
      this.$cache.local.setJSON('employedInfo', scope);

    },

    //工商详情
    busDetail() {
      this.busVisible = false;
      let obj = {
        backUrl: '/company/customer/employed',
      };
      this.$cache.local.setJSON('backurls', obj);
      this.$tab.openPage("个体户工商查看", "/company/customer/detailBusiness")
      

    },

    //办理税务 已完成
    finishTax(scope, selfCode) {
      this.checkTax(selfCode);
      this.$cache.local.setJSON("employednewlist", scope);
      this.$cache.local.setJSON('employedInfo', scope);

    },

    //税务详情
    taxDetail() {
      this.taxVisible = false;
      let obj = {
        backUrl: '/company/customer/employed',
      };
      this.$cache.local.setJSON('backurls', obj);
      this.$tab.openPage("个体户税务查看", "/company/customer/detailTax")
     
    },

    //办理银行 已完成
    finishBank(scope, selfCode) {

      this.checkBank(selfCode);
      this.$cache.local.setJSON("employednewlist", scope);
      this.$cache.local.setJSON('employedInfo', scope);

    },

    //银行详情
    bankDetail() {
      this.bankVisible = false;
      let obj = {
        backUrl: '/company/customer/employed',
      };
      this.$cache.local.setJSON('backurls', obj);
      this.$tab.openPage("个体户银行查看", "/company/customer/detailBank")
    

    },
    getCount() {
      getCount(this.queryParams).then(res => {
        this.errLabel = "异常(" + res.error + ")";
        this.allLabel = "全部(" + res.total + ")";
        this.loadingLabel = "办理中(" + res.unfinished + ")";
        this.finishLabel = "完成(" + res.finished + ")";
      });
    },

    /** 查询个体商户列表 */
    getList() {

      if (this.projectTime != null) {//如果不选择时间，或者选择时间再将时间清除，直接点击查询，会报错，所以要判断一下，这个为时间不为空走这个。
        this.queryParams.start = this.projectTime[0] + ' ' + '00:00:00';
        this.queryParams.end = this.projectTime[1] + ' ' + '23:59:59';
      } else {//判断选择时间再将时间清除
        this.projectTime = null;
        this.queryParams.start = null;
        this.queryParams.end = null;
      };
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
      this.endStatus = '0';
      this.projectTime = null;
      this.queryParams = {
        pageNum: 1,
        type: 1,
        pageSize: 10,
        placeAliasName: null,
        legalPersonName: null,
        userId: null,
        endStatus: 0,
        start: null,
        end: null,
      };

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
        this.$tab.openPage("个体户注册", "/company/customer/addEmployedInfo")
    },
    /** 删除按钮操作 */
    handleDelete() {
      this.$confirm('是否确认注销此个体户的注册信息?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delEmployed(this.ids).then(res => {


          if (res.code == 200) {
            this.getList();
            this.$modal.msgSuccess("删除成功");
          } else {
            this.getList();
            this.$modal.msgError("删除失败");
          }

        })

      }).catch(() => {

      });




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
::v-deep .el-message-box__content {
  height: 200px !important;
}

::v-deep .el-tabs__nav-wrap::after {
  background-color: rgba(0, 0, 0, 0) !important;
}
</style>
