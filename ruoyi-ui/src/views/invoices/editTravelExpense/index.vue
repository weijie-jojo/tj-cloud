<template>
  <div class="app-container">
    <el-form
      ref="ruleForm"
      :model="ruleForm"
      :rules="rules"
      label-width="auto"
    >
      <el-row>
       <el-col :span="24" style="margin-bottom: 20px;font-size:25px;text-align: center;font-weight: bold;">差旅报销单</el-col>
      </el-row>  
      <el-row type="flex" justify="center">
        <el-col :span="6">
             <el-form-item label="部门">
             <el-input disabled v-model="ruleForm.deptName"> </el-input>
             </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="报销日期" prop="expenseDate">
            <el-date-picker
              disabled
              v-model="ruleForm.expenseDate"
              type="date"
              placeholder="选择日期"
            >
            </el-date-picker>
          </el-form-item>
        </el-col>
       
        <el-col :span="6">
          <div class="grid-content bg-purple">
            <el-form-item label="同行人数" prop="togetherNum">
              <el-input
                type="number"
                v-model="ruleForm.togetherNum"
                :step="1"
                :min="0"
                :max="1000"
              >
                <template slot="append">人</template>
              </el-input>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="6">
          <el-form-item label="报销单号">
            <el-input :readonly="true" v-model="ruleForm.travelExpenseCode"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="出差事由" prop="reason">
            <el-input
              type="textarea"
              :rows="4"
              placeholder="请输入出差事由"
              v-model="ruleForm.reason"
            >
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          
          <el-form-item label="起讫时间" prop="travelDate1">
            <el-date-picker
              v-model="ruleForm.travelDate1"
              type="daterange"
              align="right"
              style="width:100%"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item label="起讫地点" prop="place1">
            <el-input
              v-model="ruleForm.place1"
              placeholder="请输入起讫地点"
            ></el-input>
          </el-form-item>
          <el-form-item label="出行方式">
            <el-input
              v-model="ruleForm.travelWay1"
              placeholder="请输入出行方式"
            ></el-input>
          </el-form-item>
          <el-form-item label="出差天数">
            <el-input
              type="number"
              v-model="ruleForm.days1"
              :step="1"
              :min="0"
              :max="1000"
              style="width: 100%"
            >
              <template slot="append">天</template>
            </el-input>
          </el-form-item>
          <el-form-item label="单据数量">
            <el-input
              type="number"
              v-model="ruleForm.accessoryNum1"
              :step="1"
              :min="0"
              style="width: 100%"
            >
              <template slot="append">张</template>
            </el-input>
          </el-form-item>
          <el-form-item label="交通费">
            <el-input
              type="number"
              v-model="ruleForm.traffic1"
              :step="0.1"
              :min="0"
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
          <el-form-item label="住宿费">
            <el-input
              type="number"
              v-model="ruleForm.stay1"
              :step="0.1"
              :min="0"
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
          <el-form-item label="餐费">
            <el-input
              type="number"
              v-model="ruleForm.subsidy1"
              :step="0.1"
              :min="0"
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
          <el-form-item label="其它">
            <el-input
              type="number"
              v-model="ruleForm.other1"
              :step="0.1"
              :min="0"
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
          <el-form-item label="合计金额">
            <el-input v-model="subTotalMoney1">
              <template slot="append"> 元 </template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          
          <el-form-item label="起讫时间">
            <el-date-picker
              style="width:100%"
              v-model="ruleForm.travelDate2"
              type="daterange"
              align="right"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item label="起讫地点">
            <el-input
              v-model="ruleForm.place2"
              placeholder="请输入起讫地点"
            ></el-input>
          </el-form-item>
          <el-form-item label="出行方式">
            <el-input
              v-model="ruleForm.travelWay2"
              placeholder="请输入出行方式"
            ></el-input>
          </el-form-item>
          <el-form-item label="出差天数">
            <el-input
              type="number"
              v-model="ruleForm.days2"
              :step="1"
              :min="0"
              :max="1000"
              style="width: 100%"
            >
              <template slot="append">天</template>
            </el-input>
          </el-form-item>
          <el-form-item label="单据数量">
            <el-input
              type="number"
              v-model="ruleForm.accessoryNum2"
              :step="1"
              :min="0"
              style="width: 100%"
            >
              <template slot="append">张</template>
            </el-input>
          </el-form-item>
          <el-form-item label="交通费">
            <el-input
              type="number"
              v-model="ruleForm.traffic2"
              :step="0.1"
              :min="0"
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
          <el-form-item label="住宿费">
            <el-input
              type="number"
              v-model="ruleForm.stay2"
              :step="0.1"
              :min="0"
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
          <el-form-item label="餐费">
            <el-input
              type="number"
              v-model="ruleForm.subsidy2"
              :step="0.1"
              :min="0"
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
          <el-form-item label="其它">
            <el-input
              type="number"
              v-model="ruleForm.other2"
              :step="0.1"
              :min="0"
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
          <el-form-item label="合计金额">
            <el-input v-model="subTotalMoney2">
              <template slot="append"> 元 </template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          
          <el-form-item label="起讫时间">
            <el-date-picker
              v-model="ruleForm.travelDate3"
              type="daterange"
              style="width:100%"
              align="right"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item label="起讫地点">
            <el-input
              v-model="ruleForm.place3"
              placeholder="请输入起讫地点"
            ></el-input>
          </el-form-item>
          <el-form-item label="出行方式">
            <el-input
              v-model="ruleForm.travelWay3"
              placeholder="请输入出行方式"
            ></el-input>
          </el-form-item>
          <el-form-item label="出差天数">
            <el-input
              type="number"
              v-model="ruleForm.days3"
              :step="1"
              :min="0"
              :max="1000"
              style="width: 100%"
            >
              <template slot="append">天</template>
            </el-input>
          </el-form-item>
          <el-form-item label="单据数量">
            <el-input
              type="number"
              v-model="ruleForm.accessoryNum3"
              :step="1"
              :min="0"
              style="width: 100%"
            >
              <template slot="append">张</template>
            </el-input>
          </el-form-item>
          <el-form-item label="交通费">
            <el-input
              type="number"
              v-model="ruleForm.traffic3"
              :step="0.1"
              :min="0"
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
          <el-form-item label="住宿费">
            <el-input
              type="number"
              v-model="ruleForm.stay3"
              :step="0.1"
              :min="0"
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
          <el-form-item label="餐费">
            <el-input
              type="number"
              v-model="ruleForm.subsidy3"
              :step="0.1"
              :min="0"
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
          <el-form-item label="其它">
            <el-input
              type="number"
              v-model="ruleForm.other3"
              :step="0.1"
              :min="0"
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
          <el-form-item label="合计金额">
            <el-input v-model="subTotalMoney3">
              <template slot="append"> 元 </template>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
  
      <el-row>
        <el-col :span="8" >
         <el-form-item  label="总计单据" >        
           {{accessoryNumTotal}}    
           </el-form-item>
         </el-col>
        <el-col :span="8">
          <el-form-item label="总计金额(小写)">
            <el-input
              disabled
              v-model="totalAllMoney"
             
            >
          <template slot="append
          ">元</template>
          </el-input>
           
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="总计金额(大写)">
            {{ digitUppercase(totalAllMoney) }}
          </el-form-item>
        </el-col>
      </el-row>
      <el-row >
        <el-col :span="8">
          <el-form-item label="收款人：">
            <el-input
              v-model="ruleForm.expenseName"
              
              disabled
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="收款帐号：">
            <el-input
              disabled
              v-model="ruleForm.bankcardGetid"
             
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="收款开户行：">
            <el-input
              disabled
              v-model="ruleForm.bankGetname"
             ></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="报销单位：" prop="companyPayId">
            <el-select
              v-model="ruleForm.companyPayId"
              placeholder="请选择付款单位"
              @change="getCarInfoByCompanyId"
              style="width: 100%"
            >
              <el-option
                v-for="item in payCompanys"
                :key="item.groupCode"
                :label="item.groupName"
                :value="item.groupCode"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="借款帐号：">
            <el-input
              v-model="ruleForm.bankPaycode"
             
              disabled
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="借款开户行：">
            <el-input
              v-model="ruleForm.bankPayname"
              
              disabled
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="12">
          <el-form-item label="报销凭证影像">
            <uploadInvoices
              ref="invoices"
              @getfileName="getExpense"
              :fileName="isNone"
              :fileNameOld="imgArrOld"
              :isDetail="isDetails"
            ></uploadInvoices>
          </el-form-item>
        </el-col>
        <el-col :span="12"></el-col>
      </el-row>
      <el-row type="flex">
        <el-col :span="8"></el-col>
        <el-col :span="8" class="flexs">
          <el-button type="danger" @click="toReturn" style="width: 100px"
            >关闭</el-button
          >
          <el-button
            type="primary"
            @click="submitForm('ruleForm')"
            style="width: 100px; margin-left: 10px"
            >提交</el-button
          >
        </el-col>
        <el-col :span="8"></el-col>
      </el-row>
      <!-- <el-form-item id="btn">
                <el-button @click="toReturn" style="width:100px;">关闭</el-button>
                <el-button type="primary" @click="submitForm('ruleForm')" style="width:100px;margin-left: 120px">确认修改</el-button>
            </el-form-item> -->
    </el-form>
  </div>
</template>
<script>
import uploadInvoices from "@/components/douploads/uploadInvoices";
import { getAllCheck, addCheckInvoices } from "@/api/invoices/checkInvoices";
import { getCardInfoBycompany } from "@/api/invoices/expense";
import { getAllCompany, getAllGetUser } from "@/api/invoices/borrow";
import { getInfo } from "@/api/login";
import { Decimal } from "decimal.js";
import {
  getCode,
  getExpenseItem,
  editTravelExpenseById,
  editTravelExpense2,
} from "@/api/invoices/travelExpense";
export default {
  name: "travelExpense",
  components: {
    uploadInvoices,
  },
  data() {
    return {
      
      isNone: [],
      isDetails: "0",
      isDetail: "0",
      imgArrOld: [],
      imgArr: [],
      imgArr2: [],
      srcList: [],
      baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
      //影像上传参数
      limitNum: 10,
      expenseImage: "",
      imgDialog: false,
      dialogImageUrl: "",
      dialogVisible: false,
      //影像查看参数
      imgpath: "",
      imageVisible: false,

      isDisabled: true,
      filePath2: "",
      id: "",
      items: [],
      payCompanys: [], //所有单位
      searchGetUsers: [], //所有收款用户信息
      ruleForm: {
        deptName:'',
        deptId: "", //部门id
        travelExpenseCode: "", //报销单号
        expenseDate: "", //报销时间
        expenseName: "", //报销人createUser
        expenseId: "", //报销人id
        job: "", //职别
        togetherNum: "", //同行人数
        reason: "", //出差事由

        travelDate1: "", //起忆时间
        place1: "", //起忆地点
        travelWay1: "", //出行方式
        days1: "", //天数
        traffic1: "", //交通费
        stay1: "", //住宿费
        subsidy1: "", //餐费
        other1: "", //其他费用

        travelDate2: "", //起忆时间
        place2: "", //起忆地点
        travelWay2: "", //出行方式
        days2: "", //天数
        traffic2: "", //交通费
        stay2: "", //住宿费
        subsidy2: "", //餐费
        other2: "", //其他费用

        travelDate3: "", //起忆时间
        place3: "", //起忆地点
        travelWay3: "", //出行方式
        days3: "", //天数
        traffic3: "", //交通费
        stay3: "", //住宿费
        subsidy3: "", //餐费
        other3: "", //其他费用

        accessoryNum1: 0,
        accessoryNum2: 0,
        accessoryNum3: 0,

        userGetid: "", //收款单位/人id
        bankcardGetid: "", //收款单位/人银行卡号
        bankGetname: "", //收款单位/人银行卡所属银行

        companyPayId: "", //付款单位id
        bankPaycode: "", //付款单位银行卡号
        bankPayname: "", //付款单位银行卡所属银行

        gm: "", //总经理
        dm: "", //部门主管
        finance: "", //财务

        gmCheck: "",
        financeCheck: "",
        dmCheck: "",

        role: "",
        checkType: "",
      },
      rules: {
        userGetid: [
          { required: true, message: "请选择收款人", trigger: "change" },
        ],
        companyPayId: [
          { required: true, message: "请选择出款单位名", trigger: "change" },
        ],
        expenseDate: [
          { required: true, message: "请选择报销时间", trigger: "change" },
        ],
        expenseName: [
          { required: true, message: "请输入姓名", trigger: "blur" },
        ],
        job: [{ required: true, message: "请输入职位", trigger: "blur" }],
        togetherNum: [
          { required: true, message: "请输入同行人数", trigger: "blur" },
        ],
        reason: [
          { required: true, message: "请输入出差事由", trigger: "blur" },
        ],

        travelDate: [
          { required: true, message: "请选择起讫时间", trigger: "change" },
        ],
        place: [{ required: true, message: "请输入起讫地点", trigger: "blur" }],
        days: [{ required: true, message: "请输入住宿天数", trigger: "blur" }],
      },
      travelExpenses: [],
      isAgree: 1,
      rejectReasult: "",
      checks: [],
    };
  },
  computed: {
    accessoryNumTotal: function () {
        return this.ruleForm.accessoryNum1*1+this.ruleForm.accessoryNum2*1+this.ruleForm.accessoryNum3*1;
    },
    subTotalMoney1: function () {
      if (!this.ruleForm.traffic1) {
        this.ruleForm.traffic1 = 0;
      }
      if (!this.ruleForm.stay1) {
        this.ruleForm.stay1 = 0;
      }
      if (!this.ruleForm.subsidy1) {
        this.ruleForm.subsidy1 = 0;
      }
      if (!this.ruleForm.other1) {
        this.ruleForm.other1 = 0;
      }
      return new Decimal(this.ruleForm.traffic1)
        .add(new Decimal(this.ruleForm.stay1))
        .add(new Decimal(this.ruleForm.subsidy1))
        .add(new Decimal(this.ruleForm.other1));
    },
    subTotalMoney2: function () {
      if (!this.ruleForm.traffic2) {
        this.ruleForm.traffic2 = 0;
      }
      if (!this.ruleForm.stay2) {
        this.ruleForm.stay2 = 0;
      }
      if (!this.ruleForm.subsidy2) {
        this.ruleForm.subsidy2 = 0;
      }
      if (!this.ruleForm.other2) {
        this.ruleForm.other2 = 0;
      }
      return new Decimal(this.ruleForm.traffic2)
        .add(new Decimal(this.ruleForm.stay2))
        .add(new Decimal(this.ruleForm.subsidy2))
        .add(new Decimal(this.ruleForm.other2));
    },
    subTotalMoney3: function () {
      if (!this.ruleForm.traffic3) {
        this.ruleForm.traffic3 = 0;
      }
      if (!this.ruleForm.stay3) {
        this.ruleForm.stay3 = 0;
      }
      if (!this.ruleForm.subsidy3) {
        this.ruleForm.subsidy3 = 0;
      }
      if (!this.ruleForm.other3) {
        this.ruleForm.other3 = 0;
      }
      return new Decimal(this.ruleForm.traffic3)
        .add(new Decimal(this.ruleForm.stay3))
        .add(new Decimal(this.ruleForm.subsidy3))
        .add(new Decimal(this.ruleForm.other3));
    },
    totalAllMoney: function () {
      if (!this.subTotalMoney1) {
        this.subTotalMoney1 = 0;
      }
      if (!this.subTotalMoney2) {
        this.subTotalMoney2 = 0;
      }
      if (!this.subTotalMoney3) {
        this.subTotalMoney3 = 0;
      }
      return new Decimal(this.subTotalMoney1)
        .add(new Decimal(this.subTotalMoney2))
        .add(new Decimal(this.subTotalMoney3));
    },
  },
  mounted: function () {
    this.getAllCompany();
    this.getAllGetUser();
    this.selectAllCheck();
    //获取登录用户信息
    // getInfo().then(res => {
    //     console.log("getInfo==", res);
    //      //根据收款人id查找收款银行卡信息
    //     getCardInfoBycompany(res.user.id).then(res => {
    //         console.log('getCardInfoBycompany==',res);
    //         this.ruleForm.bankcardGetid=res.accountCard;
    //         this.ruleForm.bankGetname=res.accountCardBank;
    //         this.ruleForm.userGetid=res.nickName;
    //     })
    // })
    this.getExpenseItem();
    this.travelExpenses = JSON.parse(
      window.localStorage.getItem("travelExpenses")
    ).list;
    console.log("travelExpenses==", this.travelExpenses[0]);
    this.id = this.travelExpenses[0].id;

    this.ruleForm.job = this.travelExpenses[0].job;
    this.ruleForm.expenseDate = this.travelExpenses[0].createTime;
    this.ruleForm.travelExpenseCode = this.travelExpenses[0].travelExpenseCode;
    this.ruleForm.expenseName = this.travelExpenses[0].nickName;

    this.ruleForm.togetherNum = this.travelExpenses[0].togetherNum;
    this.ruleForm.reason = this.travelExpenses[0].reason;

    this.ruleForm.travelDate1 = this.travelExpenses[0].travelDate1.split("--");
    this.ruleForm.place1 = this.travelExpenses[0].place1;
    this.ruleForm.travelWay1 = this.travelExpenses[0].travelWay1;
    this.ruleForm.days1 = this.travelExpenses[0].days1;
    this.ruleForm.traffic1 = this.travelExpenses[0].traffic1;
    this.ruleForm.stay1 = this.travelExpenses[0].stay1;
    this.ruleForm.subsidy1 = this.travelExpenses[0].subsidy1;
    this.ruleForm.other1 = this.travelExpenses[0].other1;

    this.ruleForm.travelDate2 = this.travelExpenses[0].travelDate2.split("--");

    this.ruleForm.place2 = this.travelExpenses[0].place2;
    this.ruleForm.travelWay2 = this.travelExpenses[0].travelWay2;
    this.ruleForm.days2 = this.travelExpenses[0].days2;
    this.ruleForm.traffic2 = this.travelExpenses[0].traffic2;
    this.ruleForm.stay2 = this.travelExpenses[0].stay2;
    this.ruleForm.subsidy2 = this.travelExpenses[0].subsidy2;
    this.ruleForm.other2 = this.travelExpenses[0].other2;

    this.ruleForm.travelDate3 = this.travelExpenses[0].travelDate3.split("--");
    this.ruleForm.place3 = this.travelExpenses[0].place3;
    this.ruleForm.travelWay3 = this.travelExpenses[0].travelWay3;
    this.ruleForm.days3 = this.travelExpenses[0].days3;
    this.ruleForm.traffic3 = this.travelExpenses[0].traffic3;
    this.ruleForm.stay3 = this.travelExpenses[0].stay3;
    this.ruleForm.subsidy3 = this.travelExpenses[0].subsidy3;
    this.ruleForm.other3 = this.travelExpenses[0].other3;

    this.ruleForm.accessoryNum1 = this.travelExpenses[0].accessoryNum1;
    this.ruleForm.accessoryNum2 = this.travelExpenses[0].accessoryNum2;
    this.ruleForm.accessoryNum3 = this.travelExpenses[0].accessoryNum3;

    this.ruleForm.totalAllMoney = this.travelExpenses[0].totalAllMoney;
    this.totalAllMoney = this.travelExpenses[0].totalAllMoney;
    this.ruleForm.paywayId = this.travelExpenses[0].label;
    this.ruleForm.payDate = this.travelExpenses[0].payDate;

    this.ruleForm.companyPayId = this.travelExpenses[0].companyPayId;
    this.ruleForm.bankPaycode = this.travelExpenses[0].bankPaycode;
    this.ruleForm.bankPayname = this.travelExpenses[0].bankGetname;
    this.ruleForm.userGetid = this.travelExpenses[0].userGetid;
    this.ruleForm.bankcardGetid = this.travelExpenses[0].bankcardGetid;
    this.ruleForm.bankGetname = this.travelExpenses[0].bankGetname;

    this.ruleForm.deptId = this.travelExpenses[0].deptId;
    this.ruleForm.deptName = this.travelExpenses[0].deptName;

    this.accessoryNumTotal=this.ruleForm.accessoryNum1*1+this.ruleForm.accessoryNum2*1+this.ruleForm.accessoryNum3*1;

    this.ruleForm.gm = JSON.parse(
      window.localStorage.getItem("travelExpenseCheckPerson")
    );
    this.ruleForm.finance = JSON.parse(
      window.localStorage.getItem("travelExpenseCheckPerson")
    );
    this.ruleForm.dm = JSON.parse(
      window.localStorage.getItem("travelExpenseCheckPerson")
    );

    this.borrowId = this.travelExpenses[0].borrowId;

    this.ruleForm.role = JSON.parse(
      window.localStorage.getItem("travelExpenseRoles")
    )[0].id;

    this.ruleForm.gmCheck = this.travelExpenses[0].gmCheck;
    this.ruleForm.financeCheck = this.travelExpenses[0].financeCheck;
    this.ruleForm.dmCheck = this.travelExpenses[0].dmCheck;

    this.expenseImage = this.travelExpenses[0].expenseImage;
    // this.imgArrOld = [];
    this.imgArr2 = [];
    var imgArr = this.expenseImage.split(",");
    imgArr.map((item, index) => {
      if (item != null && item != "") {
        this.imgArrOld.push({
          name: item,
          url: this.baseImgPath + item,
        });
        this.imgArr2.push(item);

        //   this.imgArr.push({id:index,value:item});
      }
    });
    this.$refs.invoices.getSrcList(this.imgArr2);
    console.log("imgArr=====", this.imgArr);
  },
  methods: {
    getExpense(data) {
      this.imgArr2 = data;
      console.log(data);
    },
    isAgrees() {
      console.log("点了是否同意");
      if (this.isAgree == 2) {
        this.isDisabled = false;
      }
      if (this.isAgree == 1) {
        this.isDisabled = true;
      }
    },
    //获取所有审批结果
    selectAllCheck() {
      var travelExpenses = JSON.parse(
        window.localStorage.getItem("travelExpenses")
      ).list;
      var travelExpenseCode = travelExpenses[0].travelExpenseCode;
      console.log("travelExpenseCode==", travelExpenseCode);
      getAllCheck({ invoiceCode: travelExpenseCode }).then((res) => {
        console.log("selectAllCheck==", res);
        this.checks = res;
      });
    },
    //审核
    checkInvoices() {
      console.log("role==", this.ruleForm.role);
      if (this.ruleForm.role == 1 || this.ruleForm.role == 5) {
        if (this.ruleForm.gmCheck == undefined || this.ruleForm.gmCheck == "") {
          //未审核过
          console.log("总经理审核");
          if (this.isAgree == 1) {
            this.rejectReasult = "总经理审批同意";
            this.checkType = 3;
          }
          if (this.isAgree == 2) {
            //驳回
            this.rejectReasult = "审批驳回：" + this.rejectReasult;
            this.checkType = 6;
          }
          var params1 = {
            invoiceCode: this.ruleForm.travelExpenseCode,
            checkReasult: this.rejectReasult,
            checkUser: this.ruleForm.gm,
            checkDate: this.returnTime(new Date()),
            invoiceType: 2, //单据类别（差旅报销单）
          };
          var params2 = {
            filePath2: this.filePath2,
            gmCheck: this.ruleForm.gm,
            id: this.id,
            invoiceType: this.checkType,
          };
          addCheckInvoices(params1).then((res) => {
            this.$message({
              message: res.message,
              type: "success",
            });
          });
          editTravelExpenseById(params2).then((res) => {
            this.$message({
              message: res.message,
              type: "success",
            });
          });
          this.$router.push({
            path: "/invoices/invoicesMgr",
          });
        } else {
          this.$message({
            message: "审核过了",
            type: "warning",
          });
        }
      }
      if (this.ruleForm.role == 7) {
        if (
          this.ruleForm.financeCheck == undefined ||
          this.ruleForm.financeCheck == ""
        ) {
          //未审核过
          console.log("财务审核");
          if (this.isAgree == 1) {
            this.rejectReasult = "财务审批同意";
            this.checkType = 4;
          }
          if (this.isAgree == 2) {
            //驳回
            this.rejectReasult = "审批驳回：" + this.rejectReasult;
            this.checkType = 6;
          }
          var params1 = {
            invoiceCode: this.ruleForm.travelExpenseCode,
            checkReasult: this.rejectReasult,
            checkUser: this.ruleForm.finance,
            checkDate: this.returnTime(new Date()),
            invoiceType: 2,
          };
          var params2 = {
            filePath2: this.filePath2,
            financeCheck: this.ruleForm.finance,
            id: this.id,
            invoiceType: this.checkType,
          };
          addCheckInvoices(params1).then((res) => {
            this.$message({
              message: res.message,
              type: "success",
            });
          });
          editTravelExpenseById(params2).then((res) => {
            this.$message({
              message: res.message,
              type: "success",
            });
          });
          this.$router.push({
            path: "/invoices/invoicesMgr",
          });
        } else {
          this.$message({
            message: "审核过了",
            type: "warning",
          });
        }
      }
      if (this.ruleForm.role == 12 || this.ruleForm.role == 10) {
        if (this.ruleForm.dmCheck == undefined || this.ruleForm.dmCheck == "") {
          //未审核过
          console.log("部门主管审核");
          if (this.isAgree == 1) {
            this.rejectReasult = "部门主管审批同意";
            this.checkType = 2;
          }
          if (this.isAgree == 2) {
            //驳回
            this.rejectReasult = "审批驳回：" + this.rejectReasult;
            this.checkType = 6;
          }
          var params1 = {
            invoiceCode: this.ruleForm.travelExpenseCode,
            checkReasult: this.rejectReasult,
            checkUser: this.ruleForm.dm,
            checkDate: this.returnTime(new Date()),
            invoiceType: 2,
          };
          var params2 = {
            filePath2: this.filePath2,
            dmCheck: this.ruleForm.dm,
            id: this.id,
            invoiceType: this.checkType,
          };
          addCheckInvoices(params1).then((res) => {
            this.$message({
              message: res.message,
              type: "success",
            });
          });
          editTravelExpenseById(params2).then((res) => {
            this.$message({
              message: res.message,
              type: "success",
            });
          });
          this.$router.push({
            path: "/invoices/invoicesMgr",
          });
        } else {
          this.$message({
            message: "审核过了",
            type: "warning",
          });
        }
      }
    },
    //获取所有出款单位信息
    getAllCompany() {
      getAllCompany().then((res) => {
        this.payCompanys = res.list;
        console.log("payCompanys==", this.payCompanys);
      });
    },
    //根据出款单位id查找出款银行卡信息
    getCarInfoByCompanyId() {
      var cardInfo = this.payCompanys.find(
        (item) => item.groupCode == this.ruleForm.companyPayId
      );
      this.ruleForm.bankPaycode = cardInfo.groupBankAccount;
      this.ruleForm.bankPayname = cardInfo.groupOpenBank;
      console.log("cardInfo==", cardInfo);
    },
    //获取报销人信息
    getAllGetUser() {
      getAllGetUser().then((res) => {
        console.log("getAllGetUser==", res.list);
        this.searchGetUsers = res.list;
      });
    },
    //根据收款人id查找收款银行卡信息
    getCardInfoBycompany() {
      getCardInfoBycompany(this.ruleForm.userGetid).then((res) => {
        console.log("getCardInfoBycompany==", res);
        this.ruleForm.bankcardGetid = res.payCheck;
        this.ruleForm.bankGetname = res.accountCardBank;
      });
    },

    getTravelExpenseCode() {
      getCode().then((res) => {
        console.log("getExpenseCode", res.message);
        this.ruleForm.travelExpenseCode = res.message;
      });
    },
    //获取报销项目的金额
    // getItemMoney(){
    //     console.log("item1money==",this.ruleForm.item1Money);
    //     this.items.map(item=>{
    //         if(item.id==this.ruleForm.item1){
    //             this.ruleForm.item1Money=item.itemMoney;
    //         }
    //         if(item.id==this.ruleForm.item2){
    //             this.ruleForm.item2Money=item.itemMoney;
    //         }
    //         if(item.id==this.ruleForm.item3){
    //             this.ruleForm.item3Money=item.itemMoney;
    //         }
    //         if(item.id==this.ruleForm.item4){
    //             this.ruleForm.item4Money=item.itemMoney;
    //         }
    //     })
    // },
    //获取报销项目
    getExpenseItem() {
      getExpenseItem().then((res) => {
        this.items = res;
      });
    },
    //提交表单
    submitForm(formName) {
      // this.$refs[formName].validate((valid) => {
      // if (valid) {
      let params = {
        id: this.id,
        invoiceType: 1, //发起状态
        deptId: this.ruleForm.deptId,
        travelExpenseCode: this.ruleForm.travelExpenseCode,
        createTime: this.returnTime(this.ruleForm.expenseDate),
        createUser: this.ruleForm.expenseId,
        job: this.ruleForm.job,
        togetherNum: this.ruleForm.togetherNum,
        reason: this.ruleForm.reason,

        travelDate1:
          this.returnTime(this.ruleForm.travelDate1[0]) +
          "--" +
          this.returnTime(this.ruleForm.travelDate1[1]),
        place1: this.ruleForm.place1,
        travelWay1: this.ruleForm.travelWay1,
        days1: this.ruleForm.days1,
        traffic1: this.ruleForm.traffic1,
        stay1: this.ruleForm.stay1,
        subsidy1: this.ruleForm.subsidy1,
        other1: this.ruleForm.other1,

        travelDate2:
          this.returnTime(this.ruleForm.travelDate2[0]) +
          "--" +
          this.returnTime(this.ruleForm.travelDate2[1]),
        place2: this.ruleForm.place2,
        travelWay2: this.ruleForm.travelWay2,
        days2: this.ruleForm.days2,
        traffic2: this.ruleForm.traffic2,
        stay2: this.ruleForm.stay2,
        subsidy2: this.ruleForm.subsidy2,
        other2: this.ruleForm.other2,

        travelDate3:
          this.returnTime(this.ruleForm.travelDate3[0]) +
          "--" +
          this.returnTime(this.ruleForm.travelDate3[1]),
        place3: this.ruleForm.place3,
        travelWay3: this.ruleForm.travelWay3,
        days3: this.ruleForm.days3,
        traffic3: this.ruleForm.traffic3,
        stay3: this.ruleForm.stay3,
        subsidy3: this.ruleForm.subsidy3,
        other3: this.ruleForm.other3,

        accessoryNum1: this.ruleForm.accessoryNum1, //单据
        accessoryNum2: this.ruleForm.accessoryNum2, //单据
        accessoryNum3: this.ruleForm.accessoryNum3, //单据

        userGetid: this.ruleForm.userGetid,
        bankcardGetid: this.ruleForm.bankcardGetid,
        bankGetname: this.ruleForm.bankGetname,
        companyPayId: this.ruleForm.companyPayId,
        bankPaycode: this.ruleForm.bankPaycode,
        bankPayname: this.ruleForm.bankPayname,

        expenseImage: this.imgArr2.join(),

        //总费用
        totalAllMoney: parseFloat(this.totalAllMoney),
      };

      console.log("submit!");
      editTravelExpense2(params).then((res) => {
        console.log("editTravelExpense2==", res.message);
        this.$message({
          message: "修改成功",
          type: "success",
        });
      });

      this.$refs[formName].resetFields();
      this.$tab.closeOpenPage({path:"/invoices/addInvoices"}).then(() => {
      this.$tab.refreshPage({ path: "/invoices/addInvoices", name: "AddInvoices" });     
       })
      // }
      // else {
      //     console.log('error submit!!');
      //     this.$message({
      //         message: "请填写完整",
      //         type: 'warning',
      //     });
      //     return false;
      // }
      // });
    },

    //返回
    toReturn() {
      this.$tab.closeOpenPage({
        path: "/invoices/addInvoices",
      });
    },
    //返回当前时间
    returnTime(time2) {
      var time = new Date(time2);
      if (time2 == "") {
        return "";
      }
      return time.toLocaleDateString();
    },
    //转换大小写
    digitUppercase(n = 0) {
      var fraction = ["角", "分"];
      var digit = ["零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"];
      var unit = [
        ["元", "万", "亿"],
        ["", "拾", "佰", "仟"],
      ];
      var head = n < 0 ? "欠" : "";
      n = Math.abs(n);
      var s = "";
      for (var i = 0; i < fraction.length; i++) {
        s += (
          digit[Math.floor(n * 10 * Math.pow(10, i)) % 10] + fraction[i]
        ).replace(/零./, "");
      }
      s = s || "整";
      n = Math.floor(n);
      for (var i = 0; i < unit[0].length && n > 0; i++) {
        var p = "";
        for (var j = 0; j < unit[1].length && n > 0; j++) {
          p = digit[n % 10] + unit[1][j] + p;
          n = Math.floor(n / 10);
        }
        s = p.replace(/(零.)*零$/, "").replace(/^$/, "零") + unit[0][i] + s;
      }
      return (
        head +
        s
          .replace(/(零.)*零元/, "元")
          .replace(/(零.)+/g, "零")
          .replace(/^整$/, "零元整")
      );
    },
  },
};
</script>


<style rel="stylesheet/scss" lang="scss" scoped>
#title {
  margin-bottom: 40px;
  margin-left: 510px;
  margin-top: 20px;
}
.yuan {
  margin-left: 150px;
  margin-top: -30px;
}
.travelDate {
  width: 220px;
  margin-left: -110px;
}
.placeCss {
  margin-left: 460px;
}
.placeCss2 {
  margin-left: -330px;
  width: 180px;
}
.travelWayCss {
  margin-left: 720px;
}
.travelWayCss2 {
  margin-left: -390px;
  width: 180px;
}
.daysCss {
  margin-left: 530px;
}
.trafficCss {
  margin-left: 680px;
}
.stayCss {
  margin-left: 860px;
}
.subsidyCss {
  margin-left: 1040px;
}

.otherCss {
  margin-left: 1220px;
}
.subtotal {
  margin-left: 1410px;
}
.numberInput {
  width: 140px;
}
#btn {
  margin-left: 460px;
  margin-top: 40px;
}
// .daysCss{//天数
//     margin-left: 710px;
//     margin-top:-50px;
// }
// .subsidyCss{//出差补助
//     width: 120px;
//     margin-left: 980px;
//     margin-top:-50px;
// }
// .otherCss{
//     margin-left:1080px;
//     margin-top:-50px;
//     // width:400px;
// }

// .mealCss{ //餐费
//     margin-left: 220px;
//     margin-top:-50px;

// }
// .trafficCss{
//     margin-left: 470px;
//     margin-top: -50px;

// }
// .stayCss{
//     margin-left:710px;
//     margin-top:-50px;
//     width:400px;
// }

// .totalMoneyCss{
//     margin-left: 970px;
//     margin-top:-50px;
// }
.inputCss {
  width: 200px;
}
::v-deep .el-input-number .el-input__inner {
  text-align: left;
}
::v-deep .vue-treeselect__control,
::v-deep .vue-treeselect__placeholder,
::v-deep .vue-treeselect__single-value {
  height: 30px;
  line-height: 30px;
}
// 改变input框字体颜色
::v-deep .is-disabled .el-input__inner {
  background-color: transparent !important;
  color: black;
}
</style>