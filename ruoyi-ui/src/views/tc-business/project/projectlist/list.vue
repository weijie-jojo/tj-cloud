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
      <el-form-item label="销货单位（乙方）">
        <el-input
          v-model="queryParams.selfName"
          placeholder="请输入销货单位（乙方）"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="项目时间">
        <el-date-picker
          @change="prjecs"
          v-model="projectTime"
          value-format="yyyy-MM-dd"
          type="daterange"
          :picker-options="pickerOptions"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="['00:00:00', '23:59:59']"
          align="right"
        >
        </el-date-picker>
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

    <el-row :gutter="10" class="mb8">
      <el-col :span="15">
        <el-tabs v-model="endStatus" @tab-click="handleClick">
          <el-tab-pane :label="loadingLabel" name="0"></el-tab-pane>
          <el-tab-pane :label="errLabel" name="1"></el-tab-pane>
          <el-tab-pane :label="finishLabel" name="2"></el-tab-pane>
          <el-tab-pane :label="allLabel" name="-1"></el-tab-pane>
        </el-tabs>
      </el-col>

      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>
    <el-button
      style="margin-top: -8px; margin-bottom: 16px"
      type="primary"
      plain
      icon="el-icon-plus"
      size="mini"
      @click="handleAdd"
      >新增</el-button
    >
    <el-button
      style="margin-top: -8px; margin-bottom: 16px"
      type="danger"
      plain
      icon="el-icon-delete"
      size="mini"
      :disabled="multiple"
      @click="handleDelete"
      >删除</el-button
    >

    <el-table
      v-loading="loading"
      :data="projectList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />

      <el-table-column
        label="购货单位（甲方）"
        align="center"
        prop="purchCompany"
        width="200"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="销货单位（乙方）"
        align="center"
        prop="selfName"
        width="200"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="项目名称"
        align="center"
        prop="projectName"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="项目时间"
        align="center"
        :show-overflow-tooltip="true"
      >
        <template slot-scope="scope">
          {{ scope.row.createTime | filterTime }}</template
        >
      </el-table-column>
      <el-table-column
        label="业务经理"
        align="center"
        prop="projectLeader"
        :show-overflow-tooltip="true"
      />
      <el-table-column label="进度状态" align="center" prop="projectStatus">
        <template slot-scope="scope">
          <el-link
            :underline="false"
            @click="progressNew(scope.row.projectCode, 0)"
            type="danger"
            v-if="scope.row.projectStatus == '1'"
            >异常</el-link
          >
          <el-link
            :underline="false"
            @click="progressNew(scope.row.projectCode, 0)"
            type="success"
            v-if="scope.row.projectStatus == '2'"
            >完成</el-link
          >
          <el-link
            :underline="false"
            @click="progressNew(scope.row.projectCode, 0)"
            type="primary"
            v-if="scope.row.projectStatus == '0'"
            >办理中</el-link
          >
        </template>
      </el-table-column>

      <el-table-column
        label="项目审核"
        align="center"
        prop="projectCheckStatus"
        :filters="filterList2"
        :filter-method="filterHandler"
      >
        <template slot-scope="scope">
          <el-link
            :underline="false"
            type="primary"
            @click="
              examine(scope.row.userId, scope.row, 1, scope.row.projectCode)
            "
            v-if="scope.row.projectCheckStatus == '0'"
            >审核中</el-link
          >

          <el-link
            :underline="false"
            type="danger"
            @click="progressError(scope.row.projectCode, scope.row, 1)"
            v-if="scope.row.projectCheckStatus == '2'"
            >异常</el-link
          >
          <el-link
            :underline="false"
            type="success"
            @click="projectFinish(scope.row.projectCode, scope.row, 1)"
            v-if="scope.row.projectCheckStatus == '1'"
            >完成</el-link
          >
        </template>
      </el-table-column>

      <el-table-column
        label="项目票据"
        prop="projectTicketStatus"
        align="center"
        :filters="filterList"
        :filter-method="filterHandler"
      >
        <template slot-scope="scope">
          <el-link
            @click="
              examine(scope.row.userId, scope.row, 2, scope.row.projectCode)
            "
            :underline="false"
            type="primary"
            v-if="scope.row.projectTicketStatus == '0'"
            >开票中</el-link
          >

          <el-link
            :underline="false"
            type="danger"
            @click="progressError(scope.row.projectCode, scope.row, 2)"
            v-if="scope.row.projectTicketStatus == '2'"
            >异常</el-link
          >

          <el-link
            @click="projectFinish(scope.row.projectCode, scope.row, 2)"
            :underline="false"
            type="success"
            v-if="scope.row.projectTicketStatus == '1'"
            >完成</el-link
          >
        </template>
      </el-table-column>
      <el-table-column
        label="项目收款"
        prop="projectReceiveStatus"
        align="center"
        :filters="filterList3"
        :filter-method="filterHandler"
      >
        <template slot-scope="scope">
          <el-link
            :underline="false"
            type="primary"
            @click="
              examine(scope.row.userId, scope.row, 6, scope.row.projectCode)
            "
            v-if="scope.row.projectReceiveStatus == '0'"
            >收款中</el-link
          >

          <el-link
            :underline="false"
            type="danger"
            @click="progressError(scope.row.projectCode, scope.row, 6)"
            v-if="scope.row.projectReceiveStatus == '2'"
            >异常</el-link
          >
          <el-link
            :underline="false"
            type="success"
            @click="projectFinish(scope.row.projectCode, scope.row, 6)"
            v-if="scope.row.projectReceiveStatus == '1'"
            >完成</el-link
          >
        </template>
      </el-table-column>
      <el-table-column
        label="项目出款"
        prop="projectPayStatus"
        align="center"
        :filters="filterList4"
        :filter-method="filterHandler"
      >
        <template slot-scope="scope">
          <el-link
            :underline="false"
            type="primary"
            @click="
              examine(scope.row.userId, scope.row, 7, scope.row.projectCode)
            "
            v-if="scope.row.projectPayStatus == '0'"
            >出款中</el-link
          >

          <el-link
            :underline="false"
            type="danger"
            @click="progressError(scope.row.projectCode, scope.row, 7)"
            v-if="scope.row.projectPayStatus == '2'"
            >异常</el-link
          >
          <el-link
            :underline="false"
            type="success"
            @click="projectFinish(scope.row.projectCode, scope.row, 7)"
            v-if="scope.row.projectPayStatus == '1'"
            >完成</el-link
          >
        </template>
      </el-table-column>
      <el-table-column
        label="项目资料"
        align="center"
        prop="projectAcceptanceStatus"
        :filters="filterList1"
        :filter-method="filterHandler"
      >
        <template slot-scope="scope">
          <el-link
            :underline="false"
            type="primary"
            @click="
              examine(scope.row.userId, scope.row, 4, scope.row.projectCode)
            "
            v-if="
              scope.row.projectAcceptanceStatus == '-1' &&
              scope.row.projectContractStatus == '-1'
            "
            >办理中
          </el-link>
          <el-link
            :underline="false"
            type="primary"
            @click="
              examine(scope.row.userId, scope.row, 4, scope.row.projectCode)
            "
            v-if="
              scope.row.projectAcceptanceStatus == '0' &&
              scope.row.projectContractStatus == '0'
            "
            >审核中
          </el-link>

          <el-link
            :underline="false"
            type="danger"
            @click="progressError(scope.row.projectCode, scope.row, 4)"
            v-if="
              scope.row.projectAcceptanceStatus == '2' &&
              scope.row.projectContractStatus == '2'
            "
            >异常
          </el-link>
          <el-link
            :underline="false"
            type="success"
            @click="projectFinish(scope.row.projectCode, scope.row, 4)"
            v-if="
              scope.row.projectAcceptanceStatus == '1' &&
              scope.row.projectContractStatus == '1'
            "
            >完成
          </el-link>
        </template>
      </el-table-column>
      <el-table-column
        label="项目完税"
        align="center"
        prop="projectDutypaidStatus"
        :filters="filterList1"
        :filter-method="filterHandler"
      >
        <template slot-scope="scope">
          <el-link
            :underline="false"
            type="primary"
            @click="
              examine(scope.row.userId, scope.row, 5, scope.row.projectCode)
            "
            v-if="scope.row.projectDutypaidStatus == '-1'"
            >办理中
          </el-link>
          <el-link
            :underline="false"
            type="primary"
            @click="
              examine(scope.row.userId, scope.row, 5, scope.row.projectCode)
            "
            v-if="scope.row.projectDutypaidStatus == '0'"
            >审核中
          </el-link>
          <el-link
            :underline="false"
            type="danger"
            @click="progressError(scope.row.projectCode, scope.row, 5)"
            v-if="scope.row.projectDutypaidStatus == '2'"
            >异常
          </el-link>
          <el-link
            :underline="false"
            type="success"
            @click="projectFinish(scope.row.projectCode, scope.row, 5)"
            v-if="scope.row.projectDutypaidStatus == '1'"
            >完成
          </el-link>
        </template>
      </el-table-column>
  
    
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
    <!-- 进度提示 -->
    <el-dialog
      :closeOnClickModal="false"
      :closeOnPressEscape="false"
      :title="title"
      :visible.sync="dialogVisible"
      width="70%"
    >
      <el-table :data="progressList">
        <el-table-column
          label="步骤"
          align="center"
          prop="dictLabel"
          :show-overflow-tooltip="true"
        />
        <el-table-column
          label="时间"
          align="center"
          prop="checkDate"
          width="180"
        />
        <el-table-column label="用户" align="center" prop="checkUser" />
        <el-table-column
          label="说明"
          align="center"
          prop="checkReasult"
          :show-overflow-tooltip="true"
        />
      </el-table>
      <pagination
        v-show="totalCheck > 0"
        :total="totalCheck"
        :page.sync="queryParamsCheck.pageNum"
        :limit.sync="queryParamsCheck.pageSize"
        @pagination="getListCheck"
      />
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">关闭</el-button>
        <el-button type="primary" v-if="lookstatus" @click="findList"
          >查看</el-button
        >
        <el-button type="primary" v-if="editstatus" @click="editList"
          >修改</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { getInfo } from "@/api/login";
import moment from "moment";
import { getLeaderByUserId } from "@/api/company/employed";
import { getUser } from "@/api/system/user";
import { list, del, checkdetail, getCount } from "@/api/tc-api/project/list";

export default {
  name: "List",
  data() {
    return {
      queryParamsCheck: {
        pageNum: 1,
        pageSize: 10,
        projectCode: "",
      },
      filterList: [
        { text: "开票中", value: 0 },
        { text: "异常", value: 2 },
        { text: "完成", value: 1 },
      ],
      filterList1: [
        { text: "办理中", value: -1 },
        { text: "审核中", value: 0 },
        { text: "异常", value: 2 },
        { text: "完成", value: 1 },
      ],
      filterList2: [
        { text: "审核中", value: 0 },
        { text: "异常", value: 2 },
        { text: "完成", value: 1 },
      ],
      filterList3: [
        { text: "收款中", value: 0 },
        { text: "异常", value: 2 },
        { text: "完成", value: 1 },
      ],
      filterList4: [
        { text: "出款中", value: 0 },
        { text: "异常", value: 2 },
        { text: "完成", value: 1 },
      ],
      mylist: {},
      errArrName: "",
      msgs: "",
      userinfo: {},

      allLabel: "全部",
      errLabel: "异常",
      loadingLabel: "审核中",
      finishLabel: "完成",
      endStatus: "0",
      types: 0,
      lookstatus: false,
      editstatus: false,
      dialogVisible: false,
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
      totalCheck: 0,
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
        selfName: null, //销货单位（乙方）
        projectTimeStart: null, //开始
        projectTimeEnd: null, //结束
        projectStatus: 0, //项目状态
        start: null, //开始
        end: null, //结束
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
    this.getLoginInfo();
    this.getList();
  },
  methods: {
    filterHandler(value, row, column) {
      const property = column["property"];
      return row[property] === value;
    },
    getLoginInfo() {
      getInfo().then((res) => {
        this.userinfo = res.user;
      });
    },
    prjecs(e) {
      if (!this.projectTime) {
        this.queryParams.start = null;
        this.queryParams.end = null;
      }
    },
    handleClick() {
      if (this.endStatus == "-1") {
        this.queryParams.projectStatus = null;
      } else {
        this.queryParams.projectStatus = this.endStatus;
      }
      this.queryParams.pageNum = 1;
      this.getList();
    },
    examine(applyName, scope, type, code) {
      this.$cache.local.setJSON("tc-project-code", code);
     
      var msg = "审核";
      if (type == 3) {
      } else if (type == 4) {
        if (
          scope.projectAcceptanceStatus == -1 &&
            scope.projectContractStatus ==
            -1
        ) {
          msg = "办理";
        } else {
          msg = "审核";
        }
      } else if (type == 5) {
        if (
          scope.projectDutypaidStatus ==
          -1
        ) {
          msg = "办理";
        } else {
          msg = "审核";
        }
      } else if (type == 6) {
        msg = "收款";
      } else if (type == 7) {
        msg = "出款";
      }
      this.types = type;
      if (this.types == 2) {
        msg = "开票";
      }
      getLeaderByUserId({
        userId: applyName,
      })
        .then((res) => {
          getUser(applyName).then((success) => {
            this.msgs = "查看";
            if (this.userinfo.userId == success.data.userId) {
              this.msgs = msg;
            } else {
              this.msgs = "查看";
            }
            let userName = success.data.nickName;
            let phonenumber = success.data.phonenumber;
            const h = this.$createElement;
            this.$confirm("", {
              message: h("div", null, [
                h("i", {
                  class: "el-icon-question",
                  style: "color:#f90;font-size:30px;",
                }),
                h(
                  "span",
                  {
                    style:
                      "margin-left:10px;font-size:16px;line-height:30px;font-weight:600;vertical-align:top;",
                  },
                  "温馨提示"
                ),
                h(
                  "p",
                  { style: "margin:40px 0 0 40px;height:80px" },
                  "请等待" + userName + "(" + phonenumber + ")" + msg
                ),
              ]),
              confirmButtonText: this.msgs,
              cancelButtonText: "关闭",
              closeOnClickModal: false,
              closeOnPressEscape: false,
            })
              .then(() => {
                if (this.msgs == "查看") {
                 
                  this.findList();
                } else if (this.msgs == "办理") {
                  this.addList();
                } else if (this.msgs == "审核") {
                  this.aduit();
                } else if (this.msgs == "开票") {
                  let obj = {
                    backurl: "/tc-business/project/list",
                  };
                  this.$cache.local.setJSON("tc-backTicket", obj);
                  this.$tab.openPage("票据列表", "/tc-business/project/ticketlist");
                } else if (this.msgs == "收款") {
                  let obj = {
                    backurl: "/tc-business/project/list",
                  };
                  let objList = {
                    backurl: "/tc-business/project/list",
                    name: "List",
                  };

                  this.$cache.local.setJSON("tc-ifcollect", 1);
                  this.$cache.local.setJSON("tc-addback", objList);
                  this.$cache.local.setJSON("tc-backTicket", obj);

                  this.$tab.openPage(
                    "收款列表",
                    "/tc-business/project/aduitCollectList"
                  );
                } else if (this.msgs == "出款") {
                  let obj = {
                    backurl: "/tc-business/project/list",
                  };
                  let objList = {
                    backurl: "/tc-business/project/list",
                    name: "List",
                  };

                  this.$cache.local.setJSON("tc-ifcollect", 1);
                  this.$cache.local.setJSON("tc-addback", objList);
                  this.$cache.local.setJSON("tc-backTicket", obj);
                  this.$tab.openPage(
                    "出款列表",
                    "/tc-business/project/aduitDisburseList"
                  );
                }
              })
              .catch(() => {});
          });
        })
        .catch((error) => {
          console.log(error);
        });
    },

    //新增
    addList() {
      let obj = {
        backurl: "/tc-business/project/list",
        name: "List",
      };

      this.$cache.local.setJSON("tc-addback", obj);
      switch (this.types) {
        case 2:
          let obj1 = {
            backurl: "/tc-business/project/list",
          };
          this.$cache.local.setJSON("tc-backTicket", obj1);
          this.$tab.closeOpenPage({ path: "/tc-business/project/ticketList" });
          break;
        case 4:
          this.$tab.closeOpenPage({ path: "/tc-business/project/addMeans" });
          break;
        case 5:
          this.$tab.closeOpenPage({ path: "/tc-business/project/addDutypaid" });
          break;
      }
    },
    //审核
    aduit() {
      let obj = {
        backurl: "/tc-business/project/list",
        name: "List",
      };
      this.$cache.local.setJSON("tc-ifcollect", 1);
      this.$cache.local.setJSON("tc-aduitback", obj);
      switch (this.types) {
        case 1:
          this.$tab.closeOpenPage({ path: "/tc-business/project/auditItems" });
          break;
        case 4:
          this.$tab.closeOpenPage({ path: "/tc-business/project/aduitMeans" });
          break;
        case 5:
          this.$tab.closeOpenPage({ path: "/tc-business/project/aduitDutypaid" });
          break;
      }
    },
    //修改
    editList() {
      let obj = {
        backurl: "/tc-business/project/list",
        name: "List",
      };
      this.$cache.local.setJSON("tc-ifcollect", 1);
      this.$cache.local.setJSON("tc-edit-project", obj);
      this.$cache.local.setJSON("tc-backTicket", obj);
      switch (this.types) {
        case 1:
          this.$tab.closeOpenPage({ path: "/tc-business/project/itemsEdit" });
          break;
        case 2:
          this.$tab.closeOpenPage({ path: "/tc-business/project/ticketlist" });
          break;
        case 4:
          this.$tab.closeOpenPage({ path: "/tc-business/project/meansEdit" });
          break;
        case 5:
          this.$tab.closeOpenPage({ path: "/tc-business/project/dutypaidsEdit" });
          break;
        case 6:
          this.$tab.closeOpenPage({ path: "/tc-business/project/aduitCollectList" });
          break;
        case 7:
          this.$tab.closeOpenPage({ path: "/tc-business/project/aduitDisburseList" });
          break;
      }
    },
    //详情
    findList() {
      this.dialogVisible = false;
      let obj = {
        backurl: "/tc-business/project/list",
      };
      this.$cache.local.setJSON("tc-backTicket", obj);
     
      switch (this.types) {
        case 1:
          this.$tab.openPage("项目审核查看", "/tc-business/project/auditDetail");
          break;
        case 2:
          this.$tab.openPage("票据列表查看", "/tc-business/project/ticketList");
          break;
        case 4:
          this.$tab.openPage("资料审核查看", "/tc-business/project/auditMeansDetail");
          break;
        case 5:
          this.$tab.openPage(
            "完税审核查看",
            "/tc-business/project/aduitDutypaidDetail"
          );
          break;
        case 6:
          this.$tab.openPage("出款列表查看", "/tc-business/project/aduitCollectList");
          break;
        case 7:
          this.$tab.openPage("收款列表查看", "/tc-business/project/aduitDisburseList");
          break;
      }
    },
    //获取对应日志分页数据
    getListCheck() {
      let types = this.mylist.type;
      this.queryParamsCheck.projectTypeArr = null;
      if (types == 1) {
        this.queryParamsCheck.projectTypeArr = '1, 6, 10';
      } else if (types == 2) {
        this.queryParamsCheck.projectTypeArr = '7, 17, 18';
      } else if (types == 4) {
        this.queryParamsCheck.projectTypeArr = '12, 15, 4';
      } else if (types == 5) {
        this.queryParamsCheck.projectTypeArr = '16, 13, 5';
      } else if (types == 6) {
        this.queryParamsCheck.projectTypeArr = '19, 20, 21';
      } else if (types == 7) {
        this.queryParamsCheck.projectTypeArr = '22, 23, 24';
      }
      checkdetail(this.queryParamsCheck).then((res) => {
        this.progressList = res.rows;
        this.totalCheck = res.total;
        this.title = this.mylist.title;
        this.dialogVisible = true;
      });
    },
    //进度弹框
    progressNew(code, type) {
      this.lookstatus = false;
      this.editstatus = false;

      if (type > 0) {
        this.types = type;
      } else {
        this.types = "";
      }
      this.mylist = {
        title: "进度跟踪",
        type: 0,
      };
      this.queryParamsCheck.pageNum = 1;
      this.queryParamsCheck.pageSize = 10;
      this.queryParamsCheck.projectCode = code;
      this.getListCheck();
    },
    //完成弹框
    projectFinish(code, row, type) {
      this.$cache.local.setJSON("tc-project-code", code);
      this.$cache.local.setJSON("publicTickets", row);
     
      this.lookstatus = true;
      this.editstatus = false;
      let msg = "";
      if (type == 1) {
        msg = "项目详情";
      } else if (type == 2) {
        msg = "票据详情";
      } else if (type == 3) {
        msg = "合同详情";
      } else if (type == 4) {
        msg = "资料详情";
      } else if (type == 5) {
        msg = "完税详情";
      } else if (type == 6) {
        msg = "收款详情";
      } else if (type == 6) {
        msg = "出款详情";
      }
      this.types = type;
      this.checkdetail(code, type, msg);
    },
    //异常弹框
    progressError(code, row, type) {
      this.types = type;
      if (type == 1) {
        this.errArrName = "异常原因:" + row.checkContent;
      } else if (type == 2) {
        this.errArrName = "异常原因:" + row.ticketRemark;
      } else if (type == 3) {
      } else if (type == 4) {
        this.errArrName = "异常原因:" + row.checkRemark;
      } else if (type == 5) {
        this.errArrName = "异常原因:" + row.taxRemark;
      } else if (type == 6) {
        this.errArrName = "异常原因:" + row.receiveRemark;
      } else if (type == 7) {
        this.errArrName = "异常原因:" + row.payRemark;
      }
      this.$cache.local.setJSON("tc-project-code", code);
     
      getLeaderByUserId({
        userId: row.userId,
      })
        .then((res) => {
          getUser(row.userId).then((success) => {
            this.msgs = "查看";
            if (this.userinfo.userId == success.data.userId) {
              this.msgs = "修改";
            } else {
              this.msgs = "查看";
            }
            let userName = success.data.nickName;
            let phonenumber = success.data.phonenumber;
            const h = this.$createElement;
            this.$confirm("", {
              message: h("div", null, [
                h("i", {
                  class: "el-icon-question",
                  style: "color:#f90;font-size:30px;",
                }),
                h(
                  "span",
                  {
                    style:
                      "margin-left:10px;font-size:16px;line-height:30px;font-weight:600;vertical-align:top;",
                  },
                  "温馨提示"
                ),
                h("P", { style: "margin:20px 0 0 40px;" }, this.errArrName),
                h(
                  "p",
                  { style: "margin:5px 0 0 40px;" },
                  "请等待" + userName + "(" + phonenumber + ")" + this.msgs
                ),
              ]),
              confirmButtonText: this.msgs,
              cancelButtonText: "关闭",
              closeOnClickModal: false,
              closeOnPressEscape: false,
            })
              .then(() => {
                if (this.msgs == "查看") {
                 
                  this.findList();
                } else if (this.msgs == "修改") {
                  this.editList();
                }
              })
              .catch(() => {});
          });
        })
        .catch((error) => {
          console.log(error);
        });
    },
    //完成详情接口
    checkdetail(code, type, msg) {
      this.progressList = [];

      if (type == 1) {
        //项目审核

        this.mylist = {
          title: "项目审核进度跟踪",
          type: 1,
        };
      } else if (type == 2) {
        this.mylist = {
          title: "票据审核进度跟踪",
          type: 2,
        };
      } else if (type == 3) {
      } else if (type == 4) {
        this.mylist = {
          title: "资料审核进度跟踪",
          type: 4,
        };
      } else if (type == 5) {
        this.mylist = {
          title: "完税审核进度跟踪",
          type: 5,
        };
      } else if (type == 6) {
        this.mylist = {
          title: "收款审核进度跟踪",
          type: 6,
        };
      } else if (type == 7) {
        this.mylist = {
          title: "出款审核进度跟踪",
          type: 7,
        };
      }
      this.queryParamsCheck.pageNum = 1;
      this.queryParamsCheck.pageSize = 10;
      this.queryParamsCheck.projectCode = code;
      this.getListCheck();
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
    getCount() {
      getCount(this.queryParams).then((res) => {
        this.errLabel = "异常(" + res.error + ")";
        this.allLabel = "全部(" + res.total + ")";
        this.loadingLabel = "办理中(" + res.unfinished + ")";
        this.finishLabel = "完成(" + res.finished + ")";
      });
    },
    /** 查询项目列表 */
    getList() {
      this.loading = true;
      if (this.projectTime != null) {
        //如果不选择时间，或者选择时间再将时间清除，直接点击查询，会报错，所以要判断一下，这个为时间不为空走这个。
        this.queryParams.start = this.projectTime[0] + " " + "00:00:00";
        this.queryParams.end = this.projectTime[1] + " " + "23:59:59";
      } else {
        //判断选择时间再将时间清除
        this.projectTime = null;
      }
      //    console.log( "projectTimeStart1==",this.queryParams.projectTimeStart[1]);
      list(this.queryParams)
        .then((response) => {
          this.projectList = response.rows;
          this.total = response.total;
          this.loading = false;
          this.getCount();
        })
        .catch((error) => {
          this.loading = false;
        });
    },

    detail(scope) {
      this.$cache.local.setJSON("tc-project-code", scope.projectCode);
      this.$tab.openPage("项目查看", "/tc-business/project/detail");
    },

    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.endStatus = "0";
      this.projectTime = null;
      this.queryParams = {
        type: 1,
        pageNum: 1,
        pageSize: 10,
        selfName: null, //销货单位（乙方）
        projectTimeStart: null, //开始
        projectTimeEnd: null, //结束
        projectStatus: 0, //项目状态
        start: null, //开始
        end: null, //结束
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

    /** 新增按钮操作 */
    handleAdd() {
      this.$tab.openPage("项目新增", "/tc-business/project/add");
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.$cache.local.setJSON("tc-project-code", row.projectCode);

      this.$tab.openPage("项目修改", "/tc-business/project/edit");
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
