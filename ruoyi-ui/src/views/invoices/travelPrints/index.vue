<template>
  <div class="app-container">
    <div>
      <el-row type="flex" class="row-bg" justify="end">
        <el-col :span="6" style="display: flex; justify-content: flex-end">
          <el-button type="primary" v-print="'#printMe'">打印</el-button>
        </el-col>
      </el-row>

      <el-form id="printMe">
        <div :style="{ height: screenHeight * 0.5 + 'px' }">
          <div class="reimtitle" style="text-align: center; position: relative;margin-bottom: 5px;">
            <span>差旅费报销单</span>
            <!-- <span
              style="
                font-size: 15px;
                letter-spacing: 0px;
                position: absolute;
                right: 0;
                top: 10px;
              "
              >差旅报销单号:{{ ruleForm.travelExpenseCode }}</span
            > -->
          </div>
          <el-row>
            <el-col :span="8">
              <div  style="margin-bottom: 5px">
                部门:{{ ruleForm.deptName}}
              
              </div>
            </el-col>
            <el-col :span="8">
              报销单号:{{ ruleForm.travelExpenseCode }}
            </el-col>
            <el-col :span="8">
              <div>
                <div
                  style="
                    display: flex;
                    justify-content: flex-end;
                    margin-bottom: 5px;
                  "
                >
                  <span 
                    >报销日期:{{ ruleForm.createTime }}</span
                  >
                </div>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="2">
              <div class="comborder flexs" :style="[c1]">姓名</div>
            </el-col>
            <el-col :span="2">
              <div class="comborder flexs" :style="[c1]">
                {{ ruleForm.nickName }}
              </div>
            </el-col>
            <el-col :span="2">
              <div class="comborder flexs" :style="[c1]">同行人数</div>
            </el-col>
            <el-col :span="2">
              <div class="comborder flexs" :style="[c1]">
                {{ ruleForm.togetherNum }}
              </div>
            </el-col>
            <el-col :span="2">
              <div class="comborder flexs" :style="[c1]">出差事由</div>
            </el-col>
            <el-col :span="14">
              <div class="comborder flexs flexss" id="remark1" :style="[c1]">
                {{ ruleForm.reason }}
              </div>
            </el-col>
            <!-- <el-col :span="3">
              <div class="comborder flexs" :style="[c1]">出差地点</div>
            </el-col> -->
          </el-row>

          <el-row>
            <el-col :span="4">
              <div class="flexs borders" style="height: 60px; font-size: 10px">
                起讫时间
              </div>

              <el-row style="width: 100%; height: 50px">
                <el-col
                  :span="24"
                  class="flexs borders"
                  style="height: 50px; font-size: 10px"
                  >{{ ruleForm.travelDate1=='--Invalid Date'? '':ruleForm.travelDate1}}</el-col
                >
              </el-row>
              <el-row style="width: 100%; height: 50px">
                <el-col
                  :span="24"
                  class="flexs borders"
                  style="height: 50px; font-size: 10px"
                  >{{ ruleForm.travelDate2=='--Invalid Date'? '':ruleForm.travelDate2}}</el-col
                >
              </el-row>
              <el-row style="width: 100%; height: 50px">
                <el-col
                  :span="24"
                  class="flexs borders"
                  style="height: 50px; font-size: 10px"
                  >{{ ruleForm.travelDate3=='--Invalid Date'? '':ruleForm.travelDate3}}</el-col
                >
              </el-row>
             
            </el-col>
            <el-col :span="4">
              <div class="comborder" style="height: 60px">起讫地点</div>
              <div class="comborder" style="height: 50px">
                {{ this.ruleForm.place1 }}
              </div>
              <div class="comborder" style="height: 50px">
                {{ this.ruleForm.place2 }}
              </div>
              <div class="comborder" style="height: 50px">
                {{ this.ruleForm.place3 }}
              </div>
              
            </el-col>
            <el-col :span="2">
              <div class="flexs borders" style="height: 60px; font-size: 10px">
                交通费
              </div>
              
              <el-row style="width: 100%; height: 50px">
               

                <el-col
                  :span="24"
                  class="flexs borders"
                  style="height: 50px; font-size: 10px"
                >
                  {{ ruleForm.traffic1==0 ? '': ruleForm.traffic1  }}
                </el-col>
              </el-row>
              <el-row style="width: 100%; height: 50px">
                

                <el-col
                  :span="24"
                  class="flexs borders"
                  style="height: 50px; font-size: 10px"
                >
                  {{ ruleForm.traffic2==0 ? '': ruleForm.traffic2  }}
                </el-col>
              </el-row>
              <el-row style="width: 100%; height: 50px">
                

                <el-col
                  :span="24"
                  class="flexs borders"
                  style="height: 50px; font-size: 10px"
                >
                  {{ ruleForm.traffic3==0 ? '': ruleForm.traffic3   }}
                </el-col>
              </el-row>
             
            </el-col>

            <el-col :span="2">
              <div class="flexs borders" style="height: 60px; font-size: 10px">
                住宿费
              </div>
              
              <el-row style="width: 100%; height: 50px">
               

                <el-col
                  :span="24"
                  class="flexs borders"
                  style="height: 50px; font-size: 10px"
                >
                  {{ ruleForm.stay1 ==0 ? '': ruleForm.stay1  }}
                </el-col>
              </el-row>
              <el-row style="width: 100%; height: 50px">
                

                <el-col
                  :span="24"
                  class="flexs borders"
                  style="height: 50px; font-size: 10px"
                >
                  {{ ruleForm.stay2==0 ? '': ruleForm.stay2   }}
                </el-col>
              </el-row>
              <el-row style="width: 100%; height: 50px">
                

                <el-col
                  :span="24"
                  class="flexs borders"
                  style="height: 50px; font-size: 10px"
                >
                  {{ ruleForm.stay3==0 ? '': ruleForm.stay3  }}
                </el-col>
              </el-row>
          
            </el-col>

            <el-col :span="2">
              <div class="flexs borders" style="height: 60px; font-size: 10px">
                餐费
              </div>
             
              <el-row style="width: 100%; height: 50px">
              

                <el-col
                  :span="24"
                  class="flexs borders"
                  style="height: 50px; font-size: 10px"
                >
                  {{ ruleForm.subsidy1==0 ? '': ruleForm.subsidy1  }}
                </el-col>
              </el-row>
              <el-row style="width: 100%; height: 50px">
                

                <el-col
                  :span="24"
                  class="flexs borders"
                  style="height: 50px; font-size: 10px"
                >
                  {{ ruleForm.subsidy2==0 ? '': ruleForm.subsidy2 }}
                </el-col>
              </el-row>
              <el-row style="width: 100%; height: 50px">
               

                <el-col
                  :span="24"
                  class="flexs borders"
                  style="height: 50px; font-size: 10px"
                >
                  {{ ruleForm.subsidy3==0 ? '': ruleForm.subsidy3 }}
                </el-col>
              </el-row>
           
            </el-col>

            <el-col :span="2">
              <div class="flexs borders" style="height: 60px; font-size: 10px">
                其他
              </div>
              
              <el-row style="width: 100%; height: 50px">
                

                <el-col
                  :span="24"
                  class="flexs borders"
                  style="height: 50px; font-size: 10px"
                >
                  {{ ruleForm.other1==0 ? '': ruleForm.other1}}
                </el-col>
              </el-row>
              <el-row style="width: 100%; height: 50px">
                

                <el-col
                  :span="24"
                  class="flexs borders"
                  style="height: 50px; font-size: 10px"
                >
                  {{ ruleForm.other2==0 ? '': ruleForm.other2 }}
                </el-col>
              </el-row>
              <el-row style="width: 100%; height: 50px">
                

                <el-col
                  :span="24"
                  class="flexs borders"
                  style="height: 50px; font-size: 10px"
                >
                  {{ ruleForm.other3 ==0 ? '': ruleForm.other3}}
                </el-col>
              </el-row>
             
            </el-col>
            <el-col :span="2">
              <div class="flexs borders" style="height: 60px; font-size: 10px">
                出差天数
              </div>
              <el-row style="width: 100%; height: 50px">
                <el-col
                  :span="24"
                  class="flexs borders"
                  style="height: 50px; font-size: 10px"
                >
                  {{ ruleForm.days1==0 ? '': ruleForm.days1  }}
                </el-col>

                
              </el-row>
              <el-row style="width: 100%; height: 50px">
                <el-col
                  :span="24"
                  class="flexs borders"
                  style="height: 50px; font-size: 10px"
                >
                  {{ ruleForm.days2==0 ? '': ruleForm.days2  }}
                </el-col>

                
              </el-row>
              <el-row style="width: 100%; height: 50px">
                <el-col
                  :span="24"
                  class="flexs borders"
                  style="height: 50px; font-size: 10px"
                >
                  {{ ruleForm.days3==0 ? '': ruleForm.days3  }}
                </el-col>

                
              </el-row>
              
            </el-col>
            <el-col :span="3">
              <div class="flexs borders" style="height: 60px; font-size: 10px">
                单据数量
              </div>

              <el-row style="width: 100%; height: 50px">
                <el-col
                  :span="24"
                  class="flexs borders"
                  style="height: 50px; font-size: 10px"
                >
                  {{ ruleForm.accessoryNum1 ==0 ? '':ruleForm.accessoryNum1 }}
                </el-col>
              </el-row>
              <el-row style="width: 100%; height: 50px">
                <el-col
                  :span="24"
                  class="flexs borders"
                  style="height: 50px; font-size: 10px"
                >
                {{ ruleForm.accessoryNum2 ==0 ? '':ruleForm.accessoryNum2 }}
                </el-col>
              </el-row>
              <el-row style="width: 100%; height: 50px">
                <el-col
                  :span="24"
                  class="flexs borders"
                  style="height: 50px; font-size: 10px"
                >
                {{ ruleForm.accessoryNum3 ==0 ? '':ruleForm.accessoryNum3 }}
                </el-col>
              </el-row>
           
            </el-col>

            <el-col :span="3">
              <div class="flexs borders" style="height: 60px; font-size: 10px">
                费用合计
              </div>

              <el-row style="width: 100%; height: 50px">
                <el-col
                  :span="24"
                  class="flexs borders"
                  style="height: 50px; font-size: 10px"
                >
                  {{ subTotalMoney1 ==0 ? '':subTotalMoney1 }}
                </el-col>
              </el-row>
              <el-row style="width: 100%; height: 50px">
                <el-col
                  :span="24"
                  class="flexs borders"
                  style="height: 50px; font-size: 10px"
                >
                  {{ subTotalMoney2 ==0 ? '':subTotalMoney2 }}
                </el-col>
              </el-row>
              <el-row style="width: 100%; height: 50px">
                <el-col
                  :span="24"
                  class="flexs borders"
                  style="height: 50px; font-size: 10px"
                >
                  {{ subTotalMoney3 ==0 ? '':subTotalMoney3 }}
                </el-col>
              </el-row>
           
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8">
              <div class="comborder1 borders">各项费用合计</div>
            </el-col>
           
            <el-col :span="2">
              <div class="comborder1 borders">
                 {{totalTraffic ==0 ? '':totalTraffic}}
              </div>
            </el-col>
           
            <el-col :span="2">
              <div class="comborder1 borders">
                {{totalStay==0 ? '':totalStay}}
              </div>
            </el-col>
            
            <el-col :span="2">
              <div class="comborder1 borders">
                {{totalSubsidy==0 ? '': totalSubsidy}}
              </div>
            </el-col>
           
            <el-col :span="2">
              <div class="comborder1 borders">
                {{totalOther==0 ? '': totalOther}}
              </div>
            </el-col>
            <el-col :span="2">
              <div class="comborder1 borders">
                {{totalDay==0 ? '':totalDay}}
              </div>
            </el-col>
            <el-col :span="3">
              <div class="comborder1 borders">
                {{accessoryNum==0 ? '': accessoryNum}}
              </div>
            </el-col>
            <el-col :span="3">
              <div class="comborder1 borders">
                {{ruleForm.totalAllMoney==0 ? '': totalAllMoney}}
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <div
                class="comborder"
                style="
                  text-align: left;
                  padding-left: 4px;
                  letter-spacing: 4px;
                  height: 40px;
                "
              >
                总计(大写)人民币:<span>
                  {{digitUppercase(ruleForm.totalAllMoney)}}  </span
                >
              </div>
            </el-col>
            <el-col :span="12">
              <div
                class="comborder"
                style="
                  text-align: left;
                  padding-left: 4px;
                  letter-spacing: 4px;
                  height: 40px;
                "
              >
                总计(小写)人民币:<span>
                  {{ruleForm.totalAllMoney}}    </span
                >
              </div>
            </el-col>
          </el-row>
          <el-row style="margin-top: 20px">
            <el-col :span="6">
              <div>总经理:{{ ruleForm.gmCheck }}</div>
            </el-col>
            <el-col :span="6">
              <div>财务:{{ ruleForm.financeCheck }}</div>
            </el-col>
            <el-col :span="6">
              <div>部门经理:{{ ruleForm.dmCheck }}</div>
            </el-col>
            <el-col :span="6">
              <div>报销人:{{ ruleForm.nickName }}</div>
            </el-col>
          </el-row>
        </div>

        <div :style="{ height: screenHeight * 0.5 + 'px' }">
          <el-row
            class="row-bg"
            :style="{
              width: screenWidth + 'px',
              height: screenHeight * 0.5 + 'px',
              paddingTop: '15px',
            }"
          >
            <el-col :span="24">
              <span style="font-size: 20px; margin-bottom: 20px; color: blue"
                >审批进度</span
              >
              <el-table
                :data="checks"
                style="margin-top: 20px"
                :cell-style="{ border: '1px solid #333' }"
                fit
                :header-cell-style="{ border: '1px solid #333' }"
                class="exporttable"
              >
                <el-table-column prop="checkDate" label="时间" width="180" />
                <el-table-column prop="checkUser" label="人员" width="180" />
                <el-table-column prop="checkReasult" label="结果" />
              </el-table>
            </el-col>
          </el-row>
        </div>

        <!-- transform: rotate(90deg); -->
        <!-- transform: rotate(90deg); -->

        <div v-for="(item, index) in imgArr" :key="index">
          <div v-if="index>0 && index %2 !==0"  :style="{
              height: screenHeight * 0.01 + 'px',}">
          </div>
          <div
            :style="{
              height: screenHeight * 0.1 + 'px',
              display: 'flex',
              justifyContent: 'space-between'
              ,alignItems: 'center'
              ,paddingBottom:'10px'
            }"
          >
            报销凭证影像
            <span> 报销单号:{{ ruleForm.travelExpenseCode }} </span>
          </div>

          <!-- <div :style="{ height: screenHeight * 0.1 + 'px', width: screenWidth + 'px' }">

          </div> -->

          <el-image
            :src="item.url"
            fill="scale-down"
            :style="{
              height: screenHeight * 0.4 + 'px',
              width: screenWidth + 'px',
            }"
          ></el-image>
          <div
          v-if="imgArr.length == 1"
          :style="{
            height: screenHeight * 0.51 + 'px',
            width: screenWidth + 'px',
          }"
        ></div>
        </div>
        
        <div
          v-if="imgArr.length % 2 !== 1 && imgArr.length > 1"
          :style="{
            height: screenHeight * 0.51 + 'px',
            width: screenWidth + 'px',
          }"
        ></div>

        <div v-for="(item, index) in imgArr2" :key="index">
          <div v-if="index>0 && index %2 !==0"  :style="{
              height: screenHeight * 0.01 + 'px',}">
          </div>
          <div
            :style="{
              height: screenHeight * 0.05 + 'px',
              display: 'flex',
              justifyContent: 'space-between',
              alignItems: 'center'
              ,paddingBottom:'10px'
            }"
          >
            付款凭证影像
            <span> 报销单号:{{ ruleForm.travelExpenseCode }} </span>
          </div>
          <el-image
            :src="item.url"
            fill="scale-down"
            :style="{
              height: screenHeight * 0.4 + 'px',
              width: screenWidth + 'px',
            }"
          ></el-image>
          <div v-if="imgArr2.length ==1"
          :style="{ height: screenHeight * 0.51+ 'px', width: screenWidth + 'px' }">

        </div>
        </div>
       
        <div
          v-if="imgArr2.length % 2 !== 1 && imgArr2.length > 1"
          :style="{
            height: screenHeight * 0.51 + 'px',
            width: screenWidth + 'px',
          }"
        ></div>
      </el-form>
    </div>
    <el-row type="flex" class="row-bg" justify="space-around">
      <el-col :span="8"></el-col>
      <el-col :span="8" class="flexs">
        <el-button type="danger" @click="beforePage" style="width: 130px"
          >关闭</el-button
        >
      </el-col>
      <el-col :span="8"></el-col>
    </el-row>
  </div>
</template>

<script>
import { getAllCheck } from "@/api/invoices/checkInvoices";
import { Decimal } from "decimal.js";
export default {
  components: {
    // pdf,
  },
  data() {
    return {
      totalTraffic:'',
      totalStay:'',
      totalDay:"",
      totalSubsidy:'',
      totalOther:'',
      subTotalMoney1: "",
      subTotalMoney2: "",
      subTotalMoney3: "",
      subTotalMoney4: "",
      subTotalMoney5: "",
      screenWidth: 756, // 屏幕尺寸
      screenHeight: 1086, // 屏幕尺寸
      clientHeight: "",
      scalesNum: 0.4,
      c1: {},
      c2: {},
      c3: {},
      c4: {},
      c5: {},
      baseImgPath1: "/eladmin/api/files/showPdf?imgPath=",
      baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
      imgArr: [],
      imgArr2: [],
      expenseImage: [],
      expenseImage2: [],
      ruleForm: {},
      checks: [],
      accessoryNum: 0,
    };
  },

  mounted() {
    this.ruleForm = JSON.parse(
      window.localStorage.getItem("travelExpenses")
    ).list[0];
    if (!this.ruleForm.accessoryNum1) {
      this.ruleForm.accessoryNum1 = 0;
    }
    if (!this.ruleForm.accessoryNum2) {
      this.ruleForm.accessoryNum2 = 0;
    }
    if (!this.ruleForm.accessoryNum3) {
      this.ruleForm.accessoryNum3 = 0;
    }
    

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
    this.subTotalMoney1 = new Decimal(this.ruleForm.traffic1)
      .add(new Decimal(this.ruleForm.stay1))
      .add(new Decimal(this.ruleForm.subsidy1))
      .add(new Decimal(this.ruleForm.other1));

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
    this.subTotalMoney2 = new Decimal(this.ruleForm.traffic2)
      .add(new Decimal(this.ruleForm.stay2))
      .add(new Decimal(this.ruleForm.subsidy2))
      .add(new Decimal(this.ruleForm.other2));

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
    this.subTotalMoney3 = new Decimal(this.ruleForm.traffic3)
      .add(new Decimal(this.ruleForm.stay3))
      .add(new Decimal(this.ruleForm.subsidy3))
      .add(new Decimal(this.ruleForm.other3));
  let arr1 = new Decimal(this.ruleForm.accessoryNum1).add(
       new Decimal(this.ruleForm.accessoryNum2)
  );
 this.accessoryNum = new Decimal(arr1).add(new Decimal(this.ruleForm.accessoryNum3));
  this.totalDay= this.ruleForm.days1*1+this.ruleForm.days2*1+this.ruleForm.days3*1;
  this.totalTraffic=new Decimal(this.ruleForm.traffic1).add(new Decimal(this.ruleForm.traffic2)).add(new Decimal(this.ruleForm.traffic3));
  this.totalStay=new Decimal(this.ruleForm.stay1).add(new Decimal(this.ruleForm.stay2)).add(new Decimal(this.ruleForm.stay3));
  this.totalSubsidy=new Decimal(this.ruleForm.subsidy1).add(new Decimal(this.ruleForm.subsidy2)).add(new Decimal(this.ruleForm.subsidy3));
  this.totalOther=new Decimal(this.ruleForm.other1).add(new Decimal(this.ruleForm.other2)).add(new Decimal(this.ruleForm.other3));
 

    var expenses = JSON.parse(
      window.localStorage.getItem("travelExpenses")
    ).list;
    var travelExpenseCode = expenses[0].travelExpenseCode;

    getAllCheck({ invoiceCode: travelExpenseCode }).then((res) => {
      console.log("selectAllCheck==", res);
      this.checks = res;
    });
    this.expenseImage = this.ruleForm.expenseImage;
    this.expenseImage2 = this.ruleForm.expenseImage2;

    this.imgArr = [];
    this.imgArr2 = [];
    var imgArr = this.expenseImage.split(",");
    if (imgArr[0] == "") {
    } else {
      imgArr.map((item, index) => {
        if (item != null && item != "") {
          var suffix = item.substring(item.lastIndexOf(".") + 1, item.length);
          if (suffix == "pdf") {
            // this.imgArr.push({
            //   url: pdf.createLoadingTask({ url: this.baseImgPath1 + item, CMapReaderFactory, cMapPacked: true }),
            //   suffix: suffix,
            // })
            this.imgArr.push({
              url: this.baseImgPath1 + item,
              suffix: suffix,
            });
          } else {
            this.imgArr.push({
              url: this.baseImgPath + item,
              suffix: suffix,
            });
          }
        }
      });
    }
    console.log("imgArr==", this.imgArr);
    var imgArr2 = this.expenseImage2.split(",");
    if (imgArr2[0] == "") {
    } else {
      imgArr2.map((item, index) => {
        if (item != null && item != "") {
          var suffix = item.substring(item.lastIndexOf(".") + 1, item.length);
          if (suffix == "pdf") {
            // this.imgArr2.push({
            //   url: pdf.createLoadingTask({ url: this.baseImgPath1 + item, CMapReaderFactory, cMapPacked: true }),
            //   suffix: suffix,
            // })
            this.imgArr2.push({
              url: this.baseImgPath1 + item,
              suffix: suffix,
            });
          } else {
            this.imgArr2.push({
              url: this.baseImgPath + item,
              suffix: suffix,
            });
          }
        }
      });
    }

    this.getHeight();
  },
  methods: {
    beforePage() {
      this.$tab.closeOpenPage({ path: "/invoices/showTravelExpense" });
    },

    getHeight() {
      this.$nextTick(() => {
        if (document.getElementById("remark1").scrollHeight !== 0) {
          this.c1 = {
            height:
              document.getElementById("remark1").scrollHeight * 2 + "px",
            "line-height":
              document.getElementById("remark1").scrollHeight / 4 + "px",
          };
        } else {
          this.c1 = { height: "40px" };
        }
        console.log(this.c1);
        // if (document.getElementById('remark2').scrollHeight !== 0) {
        //   this.c2 = { 'height': document.getElementById('remark2').scrollHeight * 1.4 + 'px', 'line-height': document.getElementById('remark2').scrollHeight / 2 + 'px' };
        // } else {
        //   this.c1 = { 'height': '40px' };

        // }
        // if (document.getElementById('remark3').scrollHeight !== 0) {
        //   this.c3 = { 'height': document.getElementById('remark3').scrollHeight * 1.4 + 'px', 'line-height': document.getElementById('remark3').scrollHeight / 2 + 'px' };
        // } else {
        //   this.c1 = { 'height': '40px' };

        // }
        // if (document.getElementById('remark4').scrollHeight !== 0) {
        //   this.c4 = { 'height': document.getElementById('remark4').scrollHeight * 1.4 + 'px', 'line-height': document.getElementById('remark4').scrollHeight / 2 + 'px' };
        // } else {
        //   this.c1 = { 'height': '40px' };

        // }
        // if (document.getElementById('remark5').scrollHeight !== 0) {
        //   this.c5 = { 'height': document.getElementById('remark5').scrollHeight + 'px', 'line-height': document.getElementById('remark5').scrollHeight / 2 + 'px' };
        // } else {
        //   this.c1 = { 'height': '40px' };

        // }
      });
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
    getnames(data) {
      console.log("parent");
      console.log(data);
    },
  },
};
</script>
<style scoped>
.borders {
  border: 1px solid #333;
}
.flexss{
  align-items:flex-start  !important;
  text-align: left !important;
}
.flexs {
  display: flex;
  align-items: center;
  justify-content: center;
}

.combold {
  font-weight: bold;
  color: #333;
}

.reimtitle {
  text-align: center;
  font-size: 25px;
  letter-spacing: 20px;
  color: #333;
  font-weight: bold;
}

.comborder1 {
  font-size: 10px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  border: 1px solid #333;
}
.comborder {
  font-size: 10px;
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  border: 1px solid #333;
}

#remark1 {
  padding: 5px;
}

#remark2 {
  padding: 5px;
}

#remark3 {
  padding: 5px;
}

#remark4 {
  padding: 5px;
}

#remark5 {
  padding: 5px;
}
/* .el-table::before{
  background-color: #333;
} */
.exporttable {
  border-color: black;
}
@media print {
  @page {
    size: auto;
    margin-left: 10mm;
    margin-right: 10mm;
  }
}
.el-table--border:after,
.el-table--group:after,
.el-table:before {
  /* background-color:1px solid black; */
  border-bottom: 1px solid black;
}
</style>
