<template>
  <div class="app-container">
    <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="auto">
      <el-row>
            <el-col :span="24" style="font-size:25px;text-align: center;font-weight: bold;">差旅报销单新增</el-col>
          </el-row>  
      <el-row>
        <el-col :span="5">
          <el-form-item label="报销单号">
            <span>{{ ruleForm.travelExpenseCode }}</span>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row type="flex" justify="center">
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
        <el-col :span="4">
          <el-form-item label="姓名" prop="expenseName">
            <el-input
              v-model="ruleForm.expenseName"
              placeholder=""
              disabled
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item label="部门">
            <el-input
                v-model="ruleForm.dept"
                disabled
              ></el-input>
            </el-form-item>
        </el-col>
        <el-col :span="6">
          <div class="grid-content bg-purple">
            <el-form-item label="职别" prop="job">
              <el-input
                v-model="ruleForm.job"
                placeholder="请输入职别"
                disabled
              ></el-input>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="5">
          <div class="grid-content bg-purple">
            <el-form-item label="同行人数" prop="togetherNum">
              <el-input
                type="number"
                style="width: 100%"
                v-model="ruleForm.togetherNum"
                :step="1"
                :min="0"
                :max="1000"
              >
                <template slot="append"> 人 </template>
              </el-input>
            </el-form-item>
          </div>
        </el-col>
      </el-row>
      <el-row style="margin-top: 20px">
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
            style="width:100%"
              v-model="ruleForm.travelDate1"
              type="daterange"
              align="right"
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
          <el-form-item label="天数">
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
          <el-form-item label="单据">
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
          <el-form-item label="其他">
            <el-input
              type="number"
              v-model="ruleForm.other1"
              :step="0.1"
              :min="0"
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
          <el-form-item label="合计">
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
          <el-form-item label="天数">
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
          <el-form-item label="单据">
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
          <el-form-item label="其他">
            <el-input
              type="number"
              v-model="ruleForm.other2"
              :step="0.1"
              :min="0"
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
          <el-form-item label="合计">
            <el-input v-model="subTotalMoney2">
              <template slot="append"> 元 </template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          
          <el-form-item label="起讫时间">
            <el-date-picker
            style="width:100%"
              v-model="ruleForm.travelDate3"
              type="daterange"
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
          <el-form-item label="天数">
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
          <el-form-item label="单据">
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
      <el-row style="margin-top: 20px" type="flex">
        <el-col :span="8" >
                    <el-form-item  label="总计单据" >        
                        {{accessoryNumTotal}}    
                    </el-form-item>
       </el-col>
        <el-col :span="8">
          <el-form-item label="总计金额(小写)">
            <el-input disabled v-model="totalMoney">
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <div></div>
        </el-col>
        <el-col :span="8">
          <el-form-item label="总计金额(大写)">
            {{ digitUppercase(totalMoney) + "元" }}
          </el-form-item>
        </el-col>
      </el-row>
      <el-row style="margin-top: 20px">
        <el-col :span="8">
          <el-form-item label="收款人：">
            <el-input v-model="ruleForm.expenseName" disabled></el-input>
            <!-- <el-select
                            v-model="ruleForm.userGetid"
                            @change="getCardInfoBycompany"
                            placeholder="请选择收款单位"
                            style=" width: 200PX;">
                            <el-option
                                v-for="item in searchGetUsers"
                                :key="item.userId"
                                :label="item.nickName"
                                :value="item.userId"
                            > </el-option>
                        </el-select> -->
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="收款帐号：">
            <el-input v-model="ruleForm.bankcardGetid" disabled></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="收款开户行：">
            <el-input v-model="ruleForm.bankGetname" disabled></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="报销单位：" prop="companyPayId">
            <el-select
              style="width: 100%"
              v-model="ruleForm.companyPayId"
              placeholder="请选择付款单位"
              @change="getCarInfoByCompanyId"
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
            <el-input v-model="ruleForm.bankPaycode" disabled></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="借款开户行：">
            <el-input v-model="ruleForm.bankPayname" disabled></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="报销凭证影像：">
            <uploadInvoices
              @getfileName="getThree"
              :fileName="isNone"
              :fileNameOld="isNone"
              :isDetail="isDetail"
            ></uploadInvoices>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="报销人：">
            <el-input
              v-model="ruleForm.expenseName"
              class="inputCss"
              placeholder=""
              disabled
            ></el-input>
          </el-form-item>
        </el-col>
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
    </el-form>
  </div>
</template>
<script>
import uploadInvoices from "@/components/douploads/uploadInvoices";
import { addCheckInvoices } from "@/api/invoices/checkInvoices";
import { getCardInfoBycompany, getPost } from "@/api/invoices/expense";
import { getAllCompany, getAllGetUser } from "@/api/invoices/borrow";
import { getInfo } from "@/api/login";
import { getCode, addTravelExpense } from "@/api/invoices/travelExpense";
import { Decimal } from "decimal.js";
export default {
  components: {
    uploadInvoices,
  },
  name: "travelExpense",
  data() {
    return {
      accessoryNumTotal:'',
      isDetail: "0",
      isNone: [],
      roles: [],
      //影像上传参数
      imgArr: [],
      imgDialog: false,
      dialogImageUrl: "",
      dialogVisible: false,

      hideUpload: false,
      limitNum: 10, //可上传数量

      payCompanys: [], //所有单位
      searchGetUsers: [], //所有收款用户信息
      ruleForm: {
        dept:'',
        deptName:"",
        deptId: "", //部门id
        travelExpenseCode: "", //报销单号
        expenseDate: "", //报销时间
        expenseName: "", //报销人createUser
        expenseId: "", //报销人id
        job: "", //职别
        togetherNum: "0", //同行人数
        reason: "", //出差事由

        travelDate1: "", //起忆时间
        place1: "", //起忆地点
        travelWay1: "", //出行方式
        days1: "0", //天数
        traffic1: "0.00", //交通费
        stay1: "", //住宿费
        subsidy1: "", //餐费
        other1: "", //其他费用
        accessoryNum1: "0", //单据

        travelDate2: "", //起忆时间
        place2: "", //起忆地点
        travelWay2: "", //出行方式
        days2: "0", //天数
        traffic2: "0.00", //交通费
        stay2: "", //住宿费
        subsidy2: "", //餐费
        other2: "", //其他费用
        accessoryNum2: "0", //单据

        travelDate3: "", //起忆时间
        place3: "", //起忆地点
        travelWay3: "", //出行方式
        days3: "0", //天数
        traffic3: "0.00", //交通费
        stay3: "", //住宿费
        subsidy3: "", //餐费
        other3: "", //其他费用
        accessoryNum3: "0", //单据

        userGetid: "", //收款单位/人id
        bankcardGetid: "", //收款单位/人银行卡号
        bankGetname: "", //收款单位/人银行卡所属银行

        companyPayId: "", //付款单位id
        bankPaycode: "", //付款单位银行卡号
        bankPayname: "", //付款单位银行卡所属银行

        dmCheck: "", //部门主管
        gmCheck: "", //总经理
      },
      rules: {
        userGetid: [
          { required: true, message: "请选择收款人", trigger: "change" },
        ],
        companyPayId: [
          { required: true, message: "请选择出款单位名", trigger: "change" },
        ],
        expenseDate: [
          {
            type: "date",
            required: true,
            message: "请选择报销时间",
            trigger: "change",
          },
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
    };
  },
  mounted: function () {
    //获取登录用户信息
    getInfo().then((res) => {
      console.log("getInfo==", res);
      this.ruleForm.expenseName = res.user.nickName;
      this.ruleForm.expenseId = res.user.userId;
      this.ruleForm.deptId = res.user.deptId;
      this.ruleForm.dept=res.user.dept.deptName;
      this.roles = res.user.roles;
      this.ruleForm.userGetid = res.user.id;
      //根据收款人id查询岗位
      getPost(res.user.userId).then((res) => {
        console.log("getPost==", res.postName);
        this.ruleForm.job = res.postName;
      });
      //根据收款人id查找收款银行卡信息
      getCardInfoBycompany(res.user.userId).then((res) => {
        console.log("getCardInfoBycompany==", res);
        this.ruleForm.bankcardGetid = res.payCheck;
        this.ruleForm.bankGetname = res.accountCardBank;
        this.ruleForm.userGetid = res.nickName;
      });
    });

    this.ruleForm.expenseDate = new Date();
    this.getTravelExpenseCode();
    this.getAllCompany();
    // this.getAllGetUser();
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
      console.log(111);
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
    totalMoney: function () {
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
  methods: {
    getThree(data) {
      this.imgArr = data;
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
    // getAllGetUser() {
    //     getAllGetUser().then(res => {
    //         console.log('getAllGetUser==',res.list);
    //         this.searchGetUsers = res.list;
    //         // this.ruleForm.userGetid=res.list.userId;

    //     })
    // },
    //根据收款人id查找收款银行卡信息
    // getCardInfoBycompany() {
    //     getCardInfoBycompany(this.ruleForm.userGetid).then(res => {
    //         console.log('getCardInfoBycompany==',res);
    //         this.ruleForm.bankcardGetid=res.accountCard;
    //         this.ruleForm.bankGetname=res.accountCardBank;
    //         this.ruleForm.userGetid=res.nickName;
    //     })
    // },

    getTravelExpenseCode() {
      getCode().then((res) => {
        console.log("getExpenseCode", res.message);
        this.ruleForm.travelExpenseCode = res.message;
      });
    },

    //提交表单
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var invoiceType = 1;
          var checkReasult = "发起";
          this.roles.map((item) => {
            if (item.roleId == 5 || item.roleId == 6) {
              //总经理
              invoiceType = 2; //新增默认部门主管审批
              checkReasult = "发起，部门主管审批同意";
              this.ruleForm.dmCheck = this.ruleForm.expenseName;
            }
          });
          let params = {
            dmCheck: this.ruleForm.dmCheck,

            invoiceType: invoiceType, //发起状态
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
            accessoryNum1: this.ruleForm.accessoryNum1, //单据

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
            accessoryNum2: this.ruleForm.accessoryNum2, //单据

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
            accessoryNum3: this.ruleForm.accessoryNum3, //单据

            userGetid: this.ruleForm.userGetid,
            bankcardGetid: this.ruleForm.bankcardGetid,
            bankGetname: this.ruleForm.bankGetname,
            companyPayId: this.ruleForm.companyPayId,
            bankPaycode: this.ruleForm.bankPaycode,
            bankPayname: this.ruleForm.bankPayname,

            expenseImage: this.imgArr.join(),

            //总费用
            totalAllMoney: parseFloat(this.totalMoney),
          };
          let params2 = {
            invoiceCode: this.ruleForm.travelExpenseCode,
            checkReasult: checkReasult,
            checkUser: this.ruleForm.expenseName,
            checkDate: this.returnTime(new Date()),
            invoiceType: 2, //单据类型（差旅报销单）
          };
          console.log("submit!");
          addTravelExpense(params).then((res) => {
            console.log("addTravelExpense==", res.message);
            if (res.id == 0) {
              this.$message({
                message: res.message,
                type: "success",
              });
              addCheckInvoices(params2);
            } else {
              this.$message({
                message: res.message,
                type: "warning",
              });
            }
            this.$refs[formName].resetFields();
            this.$router.push({
              path: "/invoices/addInvoices",
            });
          });
        } else {
          console.log("error submit!!");
          this.$message({
            message: "请填写完整",
            type: "warning",
          });
          return false;
        }
      });
    },
    //重置
    // resetForm(formName) {
    //     this.$refs[formName].resetFields();
    //     console.log(1111);
    // },
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

    //取消按钮
    cancel() {
      console.log("12121");
      this.imgDialog = false;
    },
  },
};
</script>


<style rel="stylesheet/scss" lang="scss" scoped>
#title {
  margin-left: 0;
  text-align: center;
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
  width: 140px;
}
.travelWayCss {
  margin-left: 680px;
}
.travelWayCss2 {
  margin-left: -390px;
  width: 140px;
}
.daysCss {
  margin-left: 450px;
}
.trafficCss {
  margin-left: 610px;
}
.stayCss {
  margin-left: 800px;
}
.subsidyCss {
  margin-left: 990px;
}

.otherCss {
  margin-left: 1180px;
}
.subtotal {
  margin-left: 1370px;
}
.numberInput {
  width: 140px;
}
.btn {
  margin-left: 460px;
  margin-top: 40px;
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
.btn2 {
  margin-left: 160px;
  margin-top: 30px;
}
// 改变input框字体颜色
::v-deep .is-disabled .el-input__inner {
  background-color: transparent !important;
  color: black;
}
</style>
