<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      v-show="showSearch"
      label-width="auto"
    >
    <el-row type="flex" class="row-bg" justify="space-around" style="margin-top:-10px;margin-bottom: 5px;">
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width: 30%">项目信息</div>
          <div style="width: 50%; hegiht: 10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>
      <el-row
        type="flex"
        class="row-bg rowCss combottom"
       
        justify="space-around"
      >
        <el-col :span="9">
          <el-form-item class="comright" label="项目编号">
            <el-input
              :readonly="true"
              v-model="publicList.projectCode"
            ></el-input>
          </el-form-item>
          <el-form-item class="comright" label="项目名称 ">
            <el-input
              :readonly="true"
              v-model="publicList.projectName"
            ></el-input>
          </el-form-item>
          <el-form-item class="comright" label="购货单位（甲方）名称 ">
            <el-input
              :readonly="true"
              v-model="publicList.purchCompany"
            ></el-input>
          </el-form-item>
          
       
        
        </el-col>

        <el-col :span="9">
          <el-form-item class="comright" label="项目时间">
            <el-input
              :readonly="true"
              v-model="publicList.createTime"
            ></el-input>
          </el-form-item>

          <el-form-item class="comright" label="项目金额">
            <el-input
              :readonly="true"
              v-model="publicList.projectTotalAmount"
              :step="0.00001"
              :min="0"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
          <el-form-item class="comright" label="销货单位（乙方）名称">
            <el-input :readonly="true" v-model="publicList.selfName"></el-input>
          </el-form-item>
        
       
         
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around" style="margin-top:-10px;margin-bottom:5px">
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width: 30%">收款信息</div>
          <div style="width: 50%; hegiht: 10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>
     
  

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9"> 
          <el-form-item class="comright" label="应收账款">
            <el-input
              :readonly="true"
              v-model="publicList.projectTotalAmount"
              :step="0.00001"
              :min="0"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
            >
              <template slot="append">元</template>
            </el-input>
            
          </el-form-item>
       
          
        
        
        </el-col>

        <el-col :span="9"> </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
       <el-col :span="5">
        <el-form-item class="comright" label="服务费税款">
            <el-input
              :readonly="true"
            
              :step="0.00001"
              :min="0"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="5">
          <el-form-item class="comright" label="个税">
            <el-input
              :readonly="true"
             
              :step="0.00001"
              :min="0"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="5">
          <el-form-item class="comright" label="水利">
            <el-input
              :readonly="true"
           
              :step="0.00001"
              :min="0"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="已收账款">
            <el-input
              :readonly="true"
              v-model="publicList.receiveMoneys"
              :step="0.00001"
              :min="0"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="未收账款">
            <el-input
              :readonly="true"
              v-model="publicList.receiveRemainMoneys"
              :step="0.00001"
              :min="0"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          v-if="publicList.projectRemainAmount > 0"
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-circle-close"
          size="mini"
          @click="handleClose"
          >关闭</el-button
        >
      </el-col>

      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="projectList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
        label="收款流水号"
        align="center"
        prop="receiveSysCode"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="收款时间"
        align="center"
        prop="receiveTime"
        width="180"
      />
      <el-table-column
        label="收款金额"
        align="center"
        prop="receiveMoney"
        :show-overflow-tooltip="true"
      >
        <template slot-scope="scope">
          <div>{{ numberToCurrencyNo(scope.row.receiveMoney) }}</div>
        </template>
      </el-table-column>
      <el-table-column
        label="收款账户"
        align="center"
        prop="receiveName"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="收款账号"
        align="center"
        prop="receiveAccount"
        :show-overflow-tooltip="true"
      />

    
      <el-table-column
        label="收款状态"
        align="center"
        :show-overflow-tooltip="true"
      >
        <template slot-scope="scope">
          <el-link
            :underline="false"
            type="success"
            v-if="scope.row.isCheck == 1"
            >正常</el-link
          >
          <el-link
            :underline="false"
            type="primary"
            v-if="scope.row.isCheck == 0"
            >审核中</el-link
          >
          <el-link
            :underline="false"
            type="danger"
            v-if="scope.row.isCheck == 2"
            >未通过</el-link
          >
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
            v-if="scope.row.isCheck == 1"
            @click="detail(scope.row)"
            >查看</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-s-custom"
            v-if="scope.row.isCheck == 0"
            @click="aduit(scope.row)"
            >审核</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            v-if="scope.row.isCheck == 2"
            @click="handleUpdate(scope.row)"
            >修改
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
import { numberToCurrencyNo } from "@/utils/numberToCurrency";
import uploadSmall from "@/components/douploads/uploadSmall";
import {
  receiveList,
  detail,
  edit,
  delReceive,
  finshReceiveList,
  finshPayList,
} from "@/api/project/list";
import { Decimal } from "decimal.js";
export default {
  name: "AduitCollectList",
  components: {
    uploadSmall,
  },
  data() {
    return {
      finshPayLists: [],
      finshReceiveLists: [],
      numberToCurrencyNo,
      multipleSelection: [],
      isDetail: "1",
      fileName: [],
      baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
      fileNameradio: "1",
      fileName2: [],
      issuedAmount: 0.0, //已开金额
      balance: 0.0, //剩余金额
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
        pageNum: 1,
        pageSize: 10,
        projectCode: "",
      },
      ticketTypeoptions: [
        {
          value: 0,
          label: "增值税普通发票",
        },
        {
          value: 1,
          label: "增值税专用发票",
        },
      ],
      publicList: "",

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
          label: "完结",
        },
      ],
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
    };
  },

  mounted() {
   
    detail({
      projectCode: this.$cache.local.getJSON("tj-project-code"),
    }).then((response) => {
      this.publicList = response.data;
      this.queryParams = {
        pageNum: 1,
        pageSize: 10,
        projectCode: this.publicList.projectCode,
      };
      
      this.getList();
      this.getAllAcount();
      
    });
   
  },
  methods: {
    //关闭
    handleClose() {
      this.$tab.closeOpenPage({
        path: this.$cache.local.getJSON("tj-aduitback").backurl,
      });
    },
    //查看
    detail(row) {
      this.$cache.local.setJSON("tj-collReceiveId", row.receiveId);
      this.$tab.refreshPage({name:'AuditCollectDetail',path:"/tj-business/project/auditCollectDetail"});
    },
    //审核
    aduit(row) {
      console.log(row.receiveId);
      this.$cache.local.setJSON("tj-collReceiveId", row.receiveId);
      this.$tab.closeOpenPage({ path: "/tj-business/project/aduitCollect" });
    },
    
    //获取该项目全部收款信息
    getfinshRece() {
      finshReceiveList({
        projectCode: this.publicList.projectCode,
      }).then((res) => {
        this.finshReceiveLists = res.data;
        console.log(this.finshReceiveLists);
      });
    },
    //获取该项目全部出款信息
    getfinshPay() {
      finshPayList({
        projectCode: this.publicList.projectCode,
      }).then((res) => {
        this.finshPayLists = res.data;
        console.log(this.finshPayLists);
      });
    },
    //计算已收和已出账款
    getAllAcount() {
        finshReceiveList({
        projectCode: this.publicList.projectCode,
      }).then((res) => {
        this.finshReceiveLists = res.data;
        finshPayList({
        projectCode: this.publicList.projectCode,
      }).then((res) => {
        this.finshPayLists = res.data;
        let arr = this.finshReceiveLists;
        let brr = this.finshPayLists;
        this.publicList.payTotalMoneys=this.publicList.projectTotalAmount;
        if (Array.isArray(arr) && arr.length > 0) {
          this.publicList.receiveMoneys = 0;
          for (let i in arr) {
            if (arr[i].receiveMoney > 0 && arr[i].isCheck == 1) {
              this.publicList.receiveMoneys = new Decimal(
                this.publicList.receiveMoneys
              ).add(new Decimal(arr[i].receiveMoney));
              if(this.publicList.projectDutypaidStatus==1 && this.publicList.projectPayStatus==1 && this.publicList.projectDutypaidStatus==1 
               && this.publicList.projectAcceptanceStatus==1 && this.publicList.projectContractStatus==1 && this.publicList.projectCheckStatus==1 ){
                 this.publicList.projectStatus=2;
               }else{
                  this.publicList.projectStatus=0;
                }
            }else if(arr[i].isCheck==2){
                  this.publicList.projectStatus=1;
             }
          }
          this.publicList.receiveRemainMoneys = new Decimal(
            this.publicList.projectTotalAmount
          ).sub(new Decimal(this.publicList.receiveMoneys));
        }
        if (Array.isArray(brr) && brr.length > 0) {
          this.publicList.payMoneys = 0;
          for (let j in brr) {
            if (brr[j].payMoney > 0 && brr[j].isCheck == 1) {
              this.publicList.payMoneys = new Decimal(
                this.publicList.payMoneys
              ).add(new Decimal(brr[j].payMoney));

            }
          }
          this.publicList.payRemainMoneys = new Decimal(
            this.publicList.payTotalMoneys
          ).sub(new Decimal(this.publicList.payMoneys));
        }
           edit(this.publicList);




      });
      }); 
      
      
    },
    /** 查询列表 */
    getList() {
      this.loading = true;
      receiveList(this.queryParams)
        .then((response) => {
          this.projectList = response.rows;
          this.total = response.total;
          this.loading = false;
         
         
        })
        .catch((error) => {
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
      this.queryParams = {
        pageNum: 1,
        pageSize: 10,
        projectCode: this.publicList.projectCode,
      };
      this.handleQuery();
    },

    // 多选框选中数据
    handleSelectionChange(selection) {
      this.multipleSelection = selection.map((item) => item.receiveId);
      this.ids = selection.map((item) => item.receiveId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },

    /** 新增按钮操作 */
    handleAdd() {
      this.$tab.closeOpenPage({ path: "/tj-business/project/addCollect" });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.$cache.local.setJSON("tj-collReceiveId", row.receiveId);
      this.$tab.closeOpenPage({ path: "/tj-business/project/collectEdit" });
    },

    /** 删除按钮操作 */
    handleDelete() {
      const projectIds = this.ids;
      this.$modal
        .confirm("是否确认删除该收款信息")
        .then(function () {
          return delReceive(projectIds);
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

<style>
.flexs {
  display: flex;
  justify-content: center;
}
.bankno {
  letter-spacing: 2px;
  font-size: 20px;
  color: blue;
}
</style>
