<template>
  <div>
    <el-form
      ref="elForm"
      :model="formData"
      :rules="rules"
      size="medium"
      label-width="140px"
    >
      <el-row
        type="flex"
        class="row-bg"
        style="margin-top: 20px"
        justify="space-around"
      >
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width: 35%">项目信息</div>
          <div style="width: 50%; hegiht: 10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="项目编号">
            <el-input v-model="Father.projectCode" disabled></el-input>
          </el-form-item>

          <el-form-item class="comright" label="项目名称" :required="true">
            <el-input disabled v-model="Father.projectName"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="9">
          <el-form-item class="comright" label="项目时间" :required="true">
            <el-date-picker
            disabled
            style="width:100%"
            v-model="Father.projectTimeStart"
            value-format="yyyy-MM-dd"
            align="right"
          >
        </el-date-picker>
          </el-form-item>
          <el-form-item class="comright" label="项目金额" :required="true">
            <el-input
              disabled
              type="number"
              style="width: 100%"
              v-model="Father.projectTotalAmount"
              :step="0.01"
              :min="0"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="已开金额" :required="true">
            <el-input
              disabled
              type="number"
              style="width: 100%"
              v-model="Father.projectPackageAmount"
              :step="0.01"
              :min="0"
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
        </el-col>

        <el-col :span="9">
          <el-form-item class="comright" label="剩余金额" :required="true">
            <el-input
              disabled
              type="number"
              style="width: 100%"
              v-model="Father.projectRemainAmount"
              :step="0.01"
              :min="0"
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row
        type="flex"
        class="row-bg"
        style="margin-bottom: 20px"
        justify="space-around"
      >
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width: 35%">购货单位（甲方）</div>
          <div style="width: 50%; hegiht: 10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="名称" :required="true">
            <el-input disabled v-model="Father.purchCompany"></el-input>
          </el-form-item>
          <el-form-item class="comright" label="地址">
          <el-input
            v-model="Father.purchCompanyAddress"
            :readonly="true"
            maxlength="250"
            show-word-limit
            type="textarea"
            :rows="1"
          ></el-input>
        </el-form-item>
        <el-form-item class="comright" label="开户行">
          <el-input v-model="Father.bankName" :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="添加购方列表" :required="true">
            <el-radio disabled v-model="Father.isAddBuyer" label="1">是</el-radio>
            <el-radio disabled v-model="Father.isAddBuyer" label="0">否</el-radio>
         </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="纳税人识别号" :required="true">
            <el-input disabled v-model="Father.purchCompanyTaxid"></el-input>
          </el-form-item>
          <el-form-item class="comright" label="电话">
          <el-input
            v-model="Father.purchCompanyPhone"
            :readonly="true"
          ></el-input>
        </el-form-item>
        <el-form-item class="comright" label="帐号">
          <el-input v-model="Father.bankCode" :readonly="true"></el-input>
        </el-form-item>
        </el-col>
      </el-row>

      <el-row
        type="flex"
        class="row-bg"
        style="margin-bottom: 20px"
        justify="space-around"
      >
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width: 35%">销货单位（乙方）</div>
          <div style="width: 50%; hegiht: 10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="名称" :required="true">
            <el-input v-model="Father.selfName" disabled></el-input>
          </el-form-item>
          <el-form-item class="comright" label="地址">
          <el-input
            :readonly="true"
            v-model="Father.residence"
            maxlength="250"
            show-word-limit
            type="textarea"
            :rows="1"
          ></el-input>
        </el-form-item>
        <el-form-item class="comright" label="开户行">
          <el-input
            v-if="Father.accountType == 1"
            v-model="Father.privateDepositBank"
            :readonly="true"
          ></el-input>
          <el-input
            v-else
            v-model="Father.publicDepositBank1"
            :readonly="true"
          ></el-input>
        </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="纳税人识别号" :required="true">
            <el-input disabled v-model="Father.projectOwnerTaxid"></el-input>
          </el-form-item>

          <el-form-item class="comright" label="电话">
          <el-input
            :readonly="true"
            v-model="Father.contactPhone"
          ></el-input>
        </el-form-item>
        <el-form-item class="comright" label="帐号">
          <el-input
            v-if="Father.accountType == 1"
            v-model="Father.privateAccountNumber"
            :readonly="true"
          ></el-input>
          <el-input
            v-else
            v-model="Father.publicAccountNumber1"
            :readonly="true"
          ></el-input>
        </el-form-item>
        </el-col>
      </el-row>
      <el-row
        type="flex"
        class="row-bg"
        style="margin-bottom: 20px"
        justify="space-around"
      >
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width: 35%">票据信息</div>
          <div style="width: 50%; hegiht: 10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="发票类型" prop="ticketType">
            <el-select
              style="width: 100%"
              clearable
              v-model="formData.ticketType"
              @change="tickettaxvip"
            >
              <el-option
                v-for="item in ticketTypeoptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item class="comright" label="收款人">
            <el-input v-model="formData.receiveUser"></el-input>
          </el-form-item>
          <el-form-item class="comright" label="开票人">
            <el-input v-model="formData.createUser"></el-input>
          </el-form-item>
         
          <el-form-item class="comright" label="开票内容类型">
            <el-radio
              disabled
              v-model="fileNameradio"
              label="1"
              @change="filenamer"
              >手动输入</el-radio
            >
            <el-radio
              disabled
              v-model="fileNameradio"
              label="2"
              @change="filenamer"
              >上传附件
            </el-radio>
          </el-form-item>
          <el-form-item
            class="comright"
            label="开票内容附件"
            v-if="fileNameradio == 2"
          >
            <uploadSmall
              @getfileName="getfileNameS"
              :fileName="isNone"
              :fileNameOld="fileNames"
              :isDetail="isDetail"
            ></uploadSmall>
          </el-form-item>
          <el-form-item
            v-if="fileNameradio == 1"
            class="comright"
            label="发票种类编号"
            prop="ticketTypeCode"
          >
            <el-input v-model="formData.ticketTypeCode"></el-input>
          </el-form-item>
          <el-form-item
            class="comright"
            label="发票时间"
            :required="true"
            v-if="fileNameradio == 1"
          >
            <el-date-picker
                style="width:100%"
                v-model="formData.ticketTime"
                value-format="yyyy-MM-dd"
                :picker-options="pickerOptions"
                align="right"
              >
              </el-date-picker>
          </el-form-item>
          <el-form-item v-if="fileNameradio == 1" class="comright" label="发票总数" prop="ticketNum">
            <el-input v-model="formData.ticketNum" ></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="9">
          <el-form-item v-if="tickettaxvipok" label="发票税率" prop="ticketTax">
            <el-input
              style="width: 86%"
              v-model="formData.ticketTax"
              :disabled="true"
            ></el-input>
          </el-form-item>
          <el-form-item
            v-else
            class="comright"
            label="发票税率"
            prop="ticketTax"
          >
            <el-select
              style="width: 100%"
              clearable
              v-model="formData.ticketTax"
            >
              <el-option
                v-for="item in ticketNormal"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="复核" class="comright">
            <el-input v-model="formData.checkUser"></el-input>
          </el-form-item>
          <el-form-item class="comright" label="发票备注">
            <el-input
              maxlength="50"
              show-word-limit
              type="textarea"
              :rows="1"
              placeholder="请输入发票备注"
              v-model="formData.ticketRemark"
            >
            </el-input>
          </el-form-item>

          <el-form-item
            class="comright"
            label="开票内容"
            v-if="fileNameradio == 1"
          >
            <el-input
              maxlength="50"
              show-word-limit
              type="textarea"
              disabled
              :rows="1"
              v-model="Father.fileName"
            >
            </el-input>
          </el-form-item>

          <el-form-item
            
            label="发票编号"
            prop="ticketCode"
            v-if="fileNameradio == 1"
          >
        
          <el-input type="number" v-model="ticketCode1" @change="ticketChanges" style="width:42%"></el-input>
            <span style="width:10%;text-align: center;
    font-size: 15px;">至</span>
            <el-input type="number" v-model="ticketCode2" @change="ticketChanges" style="width:42%"></el-input>
            <el-tooltip
                  style="position: relative; left: 5px;font-size: 20px;"
                  class="item"
                  effect="dark"
                  content="单张发票时,填写001-001 ,连续发票:填写001-003,发票编号不连续需分别新增!!"
                  placement="top-start"
                >
                  <i class="header-icon el-icon-info"></i>
                </el-tooltip>
          </el-form-item>
        
          <el-form-item
            class="comright"
            label="发票总金额"
            prop="ticketAmount"
            v-if="fileNameradio == 1"
          >
            <el-input
              @input="ticketAsee"
              @change="ticketAsee"
              type="number"
              style="width: 100%"
              v-model="formData.ticketAmount"
              :step="0.01"
              :min="0"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row
        type="flex"
        class="row-bg"
        justify="space-around"
        v-if="fileNameradio == 2"
      >
        <el-col :span="9">
          <el-form-item
            class="comright"
            label="发票种类编号"
            prop="ticketTypeCode"
          >
            <el-input v-model="formData.ticketTypeCode"></el-input>
          </el-form-item>
          <el-form-item class="comright" label="发票时间" :required="true">
                  <el-date-picker
                style="width:100%"
                v-model="formData.ticketTime"
                value-format="yyyy-MM-dd"
                :picker-options="pickerOptions"
                
                align="right"
              >
              </el-date-picker>
           
          </el-form-item>
          <el-form-item class="comright" label="发票总数" prop="ticketNum">
            <el-input  v-model="formData.ticketNum"></el-input>
          </el-form-item>
        </el-col>
        
        <el-col :span="9">
          <el-form-item  label="发票编号" prop="ticketCode">
            <div class="flexs" style="margin-left:0px;width:91%">
              <el-input type="number" v-model="ticketCode1" style="width:45%" @change="ticketChanges"></el-input>
            <span style="width:10%;text-align: center;
    font-size: 15px;">至</span>
            <el-input type="number" v-model="ticketCode2" style="width:45%" @change="ticketChanges"></el-input>
            <el-tooltip
                  style="position: relative; left: 10px;font-size: 20px;"
                  class="item"
                  effect="dark"
                  content="单张发票时,填写001-001 ,连续发票:填写001-003,发票编号不连续需分别新增!!"
                  placement="top-start"
                >
                  <i class="header-icon el-icon-info"></i>
                </el-tooltip>
            </div>
          
          </el-form-item>
          <el-form-item class="comright" label="发票总金额" prop="ticketAmount">
            <el-input
              @input="ticketAsee"
              @change="ticketAsee"
              type="number"
              style="width: 100%"
              v-model="formData.ticketAmount"
              :step="0.01"
              :min="0"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
 
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="发票影像" prop="fileName">
            <uploadSmall
              @getfileName="getfileNameSS"
              :fileName="isNone"
              :fileNameOld="isNone"
              :isDetail="isDetails"
            ></uploadSmall>
          </el-form-item>
        </el-col>
        <el-col :span="9"> </el-col>
      </el-row>

      <el-row
        type="flex"
        class="row-bg"
        justify="space-around"
        style="margin-bottom: 20px"
      >
        <el-col :span="8"></el-col>
        <el-col :span="8" class="flexs">
          <el-button type="danger" @click="resetForm">关闭</el-button>
          <el-button type="primary" @click="onSubmit">提交</el-button>
        </el-col>
        <el-col :span="8"></el-col>
      </el-row>
    </el-form>
  </div>
</template>
<script>
import uploadSmall from "@/components/douploads/uploadSmall";
import crudRate from "@/api/project/rate";
import { list2, add } from "@/api/tg-api/project/ticket";
import {
  detail,
  getcode,
  getinfoByUserId,
  ownlist,
  edit,
  check,
} from "@/api/tg-api/project/list";
import { getInfo } from "@/api/login";
import { Decimal } from "decimal.js";
export default {
  name: "TicketAdd",
  components: {
    uploadSmall,
  },
  data() {
    return {
      pickerOptions: {
          disabledDate(time) {
            return time.getTime() > Date.now();
          },
          shortcuts: [{
            text: '今天',
            onClick(picker) {
              picker.$emit('pick', new Date());
            }
          }, {
            text: '昨天',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit('pick', date);
            }
          }, {
            text: '一周前',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', date);
            }
          }]
        },
      ticketCode2:'',
      ticketCode1:'',
      projectRemainAmount: "0",
      isDetail: "1",
      isDetails: "0",
      isNone: [],
      fileNames: [],
      projectStatus: 1, //销货单位（乙方）状态
      username: "",
      userId: "",
      industryId: "",
      industryTypes: [],
      industryTypeList: [],
      fileName: [],
      fileNamefile: [], //附件
      dialogVisible1: false,
      dialogImageUrl1: "",
      fileNameradio: "1", //开票类型
      natureBusiness: "", //经营范围
      tickettaxvipok: false,
      placename: "",
      isokradio: "2",
      companyTax: "", //购货单位（甲方）纳税人识别号
      owerTax: "", //销货单位（乙方）纳税人识别号
      owntype: "", //销货单位（乙方）行业类型
      owerTaxfee: "", //销货单位（乙方）税率

      Father: [],
      formData: {
        receiveUser:'',//收款人
        checkUser:'',//复核
        createUser:'',//开票人
        projectCode: this.$cache.local.getJSON("tg-project-code"), //项目编号
        ticketRemark: "", //发票备注
        ticketTax: 3, //发票税率
        ticketType: 0, //发票类型
        ticketCode: "", //发票种类编号
        ticketTypeCode: "", //发票编号
        fileName: "", //开票内容
        ticketAmount: "0", //发票总金额
        ticketTime: "", //发票时间
        isDeleted: 2,
      },
      baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
      options: [
        {
          value: 0,
          label: "异常",
        },
        {
          value: 1,
          label: "正常",
        },
        {
          value: 2,
          label: "异常",
        },
      ],
      //购货单位（甲方）
      purchCompanyOptions: [],
      //销货单位（乙方）
      ownoptions: [],
      //销货单位（乙方）行业类型

      ownindustry: [],
      //发票类型
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
      //发票普通税率
      ticketNormal: [
        {
          value: 0,
          label: "免税",
        },
        {
          value: 3,
          label: "3%",
        },
      ],
      rules: {
        ticketNum:[
          {
            required: true,
            message: "请输入发票总数",
            trigger: "blur",
          },
        ],
        projectName: [
          {
            required: true,
            message: "请输入项目名称",
            trigger: "blur",
          },
        ],
        projectTotalAmount: [
          {
            required: true,
            message: "请输入项目额",
            trigger: "blur",
          },
        ],
        ticketAmount: [
          {
            required: true,
            message: "请输入发票总金额",
            trigger: "blur",
          },
        ],

        ticketType: [
          {
            required: true,
            message: "请选择发票类型",
            trigger: "change",
          },
        ],

        ticketTax: [
          {
            required: true,
            message: "发票税率不能为空",
            trigger: "change",
          },
        ],
        industryType: [
          {
            required: true,
            message: "请选择销货单位（乙方）行业类型",
            trigger: "change",
          },
        ],
        placeCode: [
          {
            required: true,
            message: "请选择客户",
            trigger: "change",
          },
        ],

        projectOwner: [
          {
            required: true,
            message: "请选择销货单位（乙方）",
            trigger: "change",
          },
        ],

        purchCompany: [
          {
            required: true,
            message: "请选择购货单位（甲方）",
            trigger: "change",
          },
        ],

        purchCompanyTaxid: [
          {
            required: true,
            message: "请输入购货单位（甲方）纳税人识别号",
            trigger: "blur",
          },
        ],

        fileName: [
          {
            required: true,
            message: "发票影像不能为空",
            trigger: "change",
          },
        ],
        projectDesc: [
          {
            required: true,
            message: "请输入发票描述",
            trigger: "blur",
          },
        ],
        ticketCode: [
          {
            required: true,
            message: "请输入发票编号",
            trigger: "blur",
          },
        ],
        ticketTypeCode: [
          {
            required: true,
            message: "请输入发票种类编号",
            trigger: "blur",
          },
        ],
      },
    };
  },

  mounted() {
    this.getlist();
  },

  methods: {
    ticketChanges(e){
        
         if(this.ticketCode1 && this.ticketCode2){
           this.formData.ticketCode=this.ticketCode1+'--'+this.ticketCode2;
         }else{
          this.formData.ticketCode='';
         }
    },
    check(resmsg) {
      getInfo().then((res) => {
        this.userinfo = res.user;
        let parms = {
          checkReasult: resmsg,
          checkUser: this.userinfo.userName,
          phonenumber: this.userinfo.phonenumber,
          projectCode: this.Father.projectCode,
          projectType: "7",
        };
        check(parms)
          .then((res) => {
            console.log("添加票据成功！");
          })
          .catch((error) => {});
      });
    },

    getfileNameS() {},
    getfileNameSS(data) {
      this.formData.fileName = data;
    },
    ticketAsee(e) {
      if (e > this.projectRemainAmount) {
        this.$alert("发票总金额不能大于剩余金额", "系统提示", {
          confirmButtonText: "确定",

          type: "warning",
        });
        this.formData.ticketAmount = 0;
        this.ticketByCode();
        return;
      } else {
        this.ticketByCode();
      }
    },

    //计算已开和剩余金额
    ticketByCode() {
      list2({
        projectCode: this.Father.projectCode,
      })
        .then((res) => {
          let arr = res;
          if (Array.isArray(arr) && arr.length > 0) {
            this.Father.projectPackageAmount = 0;
            for (let i in arr) {
              if (arr[i].ticketAmount > 0 && arr[i].isDeleted == 1) {
                this.Father.projectPackageAmount = new Decimal(
                  this.Father.projectPackageAmount
                ).add(new Decimal(arr[i].ticketAmount));
              }
            }
            //如果存在发票 累计发票 加上发票金额
            this.Father.projectPackageAmount = new Decimal(
              this.Father.projectPackageAmount
            ).add(new Decimal(this.formData.ticketAmount));
            this.Father.projectRemainAmount = new Decimal(
              this.Father.projectTotalAmount
            ).sub(new Decimal(this.Father.projectPackageAmount));
          } else {
            this.Father.projectPackageAmount = this.formData.ticketAmount;
            this.Father.projectRemainAmount = new Decimal(
              this.Father.projectTotalAmount
            ).sub(new Decimal(this.Father.projectPackageAmount));
          }
        })
        .catch((err) => {});
    },
    getlist() {
      this.$modal.loading("正在加载数据，请稍后...");
      detail({
        projectCode: this.$cache.local.getJSON("tg-project-code"),
      })
        .then((response) => {
          this.Father = response.data;
          this.Father.isAddBuyer=JSON.stringify(this.Father.isAddBuyer);
          this.$modal.closeLoading();
          this.projectRemainAmount = this.Father.projectRemainAmount;
          if (this.Father.fileName) {
            if (this.Father.fileName.indexOf("[") != -1) {
              this.Father.fileName = JSON.parse(this.Father.fileName);
            }

            if (Array.isArray(this.Father.fileName)) {
              this.fileNames = [];
              this.fileNameradio = "2";
              //如果是图片的话
              for (let j in this.Father.fileName) {
                this.fileNames.push({
                  url: this.baseImgPath + this.Father.fileName[j],
                  name: this.Father.fileName[j],
                });
              }
            } else {
              this.fileNameradio = "1";
            }
          } else {
            this.fileNameradio = "1";
          }
         
          this.getinfoByUserId();
          this.gettoday();
          this.getRate();
        })
        .catch((error) => {
          this.$modal.closeLoading();
        });
    },
    //监听开票内容选择
    filenamer(e) {
      if (e == 1) {
        this.formData.fileName = "";
      }
    },

    getRate() {
      crudRate.getAllRate().then((res) => {
        let tree = []; // 用来保存树状的数据形式
        this.parseTree(res.rows, tree, 0);
        this.industryTypes = tree;
        this.industryTypeList = res.rows;
      });
    },
    //把数据整成树状
    parseTree(industry, tree, pid) {
      for (var i = 0; i < industry.length; i++) {
        if (industry[i].parentId == pid) {
          var obj = {
            id: industry[i].industryId,
            label: industry[i].industryName,
            children: [],
          };
          tree.push(obj);
          this.parseTree(industry, obj.children, obj.id);
        }
      }
    },

    //监听渠道商状态
    placeNew(e) {
      console.log(e);
      for (let i in this.placeCodeOptions) {
        if (this.placeCodeOptions[i].placeCode == e) {
          this.isokradio = JSON.stringify(this.placeCodeOptions[i].placeStatus);
        }
      }
    },
    //返回
    resetForm() {
      this.$tab.closeOpenPage({ path: "/tg-business/project/ticketList" });
    },

    //渠道商接口  记得修改 userid
    getinfoByUserId() {
      getInfo().then((res) => {
        this.userId = res.user.userId;
        this.username = res.user.userName;
        this.formData.projectLeader = res.user.nickName;
        getinfoByUserId({ userId: this.userId }).then((res) => {
          this.placeCodeOptions = res.data;
        });
      });
    },

    //监听开票内容类型
    tickettaxvip(e) {
      if (e > 0) {
        this.tickettaxvipok = true;
      } else {
        this.tickettaxvipok = false;
      }
    },
    repair(i) {
      if (i >= 0 && i <= 9) {
        return "0" + i;
      } else {
        return i;
      }
    },
    gettoday() {
      var date = new Date(); //当前时间
      var year = date.getFullYear(); //年
      var month = this.repair(date.getMonth() + 1); //月
      var day = this.repair(date.getDate()); //日

      var hour = this.repair(date.getHours()); //时
      var minute = this.repair(date.getMinutes()); //分
      var second = this.repair(date.getSeconds()); //秒

      //当前时间
      var curTime =
        year +
        "-" +
        month +
        "-" +
        day;

      this.formData.ticketTime = curTime;
    },
    getRealTime(){
      var date = new Date(); //当前时间
      var year = date.getFullYear(); //年
      var month = this.repair(date.getMonth() + 1); //月
      var day = this.repair(date.getDate()); //日
      var hour = this.repair(date.getHours()); //时
      var minute = this.repair(date.getMinutes()); //分
      var second = this.repair(date.getSeconds()); //秒
      //当前时间
      
     return   hour +":" +minute + ":" + second;
    },
    getcode(selfCode) {
      getcode({ selfCode: selfCode })
        .then((res) => {
          this.formData.projectCode = res;
        })
        .catch((errore) => {
          console.log(errore);
        });
    },
    handleChange(val) {
      console.log(val);
    },
    onSubmit() {
      
      // if (this.formData.ticketAmount < 1) {
      //   this.$alert("发票金额必须大于1", "系统提示", {
      //     confirmButtonText: "确定",

      //     type: "warning",
      //   });

      //   return;
      // }
      
      this.$refs["elForm"].validate((valid) => {
        // TODO 提交表单
        if (valid) {
          //如果是附件的话
          if (Array.isArray(this.formData.fileName)) {
            this.formData.fileName = JSON.stringify(
              this.formData.fileName
            );
          }
         this.formData.ticketTime=this.formData.ticketTime+" "+this.getRealTime();
          add(this.formData).then((res) => {
            if (res != undefined) {
              if (res.code === 200) {
                this.$nextTick(function () {
                  this.check("票据新增");
                  this.$modal.msgSuccess("票据新增成功");
                  this.$tab
                    .closeOpenPage({ path: "/tg-business/project/ticketList" })
                    .then(() => {
                      this.$tab.refreshPage({
                        path: "/tg-business/project/ticketList",
                        name: "TicketList",
                      });
                    });
                });
              } else {
                this.$modal.msgError(res.msg);
                this.$tab.closeOpenPage({
                  path: "/tg-business/project/ticketList",
                });
              }
            }
          });
        } else {
          this.$alert("请正确填写", "系统提示", {
            confirmButtonText: "确定",

            type: "warning",
          });
        }
      });
    },
  },
};
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
.rowCss {
  margin-top: 10px;
}

// 改变input框字体颜色
::v-deep .is-disabled .el-input__inner {
  background-color: transparent !important;
  color: black;
}

.paddingbg-s {
  padding-top: 15px;
}

.footers {
  display: flex;
  justify-content: center;
  align-items: center;
}

.rowCss {
  margin-top: 10px;
}

.comright {
  padding-right: 10%;
}

.combottom {
  margin-bottom: 10px;
}

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
