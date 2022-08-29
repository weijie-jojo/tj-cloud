<template>
  <div class="app-container">
    <div>
      <el-row type="flex" class="row-bg" justify="end">
        <el-col :span="6" style="display: flex;justify-content: flex-end;">
          <el-button type="primary" @click="back">返回</el-button>
          <el-button type="primary" v-print="'#printMe'">确认打印</el-button>
        </el-col>
      </el-row>

      <el-form id="printMe">
        <div style="height: 50vh;">
          <div class="reimtitle" style="text-align:center;position: relative;">
            <span>费用报销单</span>
            <span style="font-size:15px;letter-spacing:0px; position:absolute;right:0;top:10px">报销单号:{{
               ruleForm.expenseCode  }}</span>
          </div>
          <el-row>
            <el-col :span="12">

              <div class="combold" style="margin-bottom:5px">部门:{{  ruleForm.deptName  }}</div>
            </el-col>
            <el-col :span="12">
              <div>

                <div style="display:flex; justify-content: flex-end;margin-bottom:5px">

                  <span class="combold">报销日期:{{  ruleForm.createTime  }}</span>

                </div>
              </div>

            </el-col>

          </el-row>
          <el-row>
            <el-col :span="6">
              <div class="comborder" style="border-top:1px solid #333;border-left:1px solid #333">报销项目</div>
              <div class="comborder1 flexs" :style="[c1]" style="border-left:1px solid #333">{{  ruleForm.item1Desc  }}
              </div>
              <div class="comborder1 flexs" :style="[c2]" style="border-left:1px solid #333">{{  ruleForm.item2Desc  }}
              </div>
              <div class="comborder1 flexs" :style="[c3]" style="border-left:1px solid #333">{{  ruleForm.item3Desc  }}
              </div>
              <div class="comborder1 flexs" :style="[c4]" style="border-left:1px solid #333">{{  ruleForm.item4Desc  }}
              </div>
              <div class="comborder1 flexs" :style="[c5]" style="border-left:1px solid #333">{{  ruleForm.item5Desc  }}
              </div>
            </el-col>
            <el-col :span="2">
              <div class="comborder">张数</div>
              <div class="comborder1 flexs" :style="[c1]">{{  ruleForm.accessoryNum1  }}</div>
              <div class="comborder1 flexs" :style="[c2]">{{  ruleForm.accessoryNum2  }}</div>
              <div class="comborder1 flexs" :style="[c3]">{{  ruleForm.accessoryNum3  }}</div>
              <div class="comborder1 flexs" :style="[c4]">{{  ruleForm.accessoryNum4  }}</div>
              <div class="comborder1 flexs" :style="[c5]">{{  ruleForm.accessoryNum5  }}</div>
            </el-col>
            <el-col :span="4">
              <div class="comborder">金额</div>
              <div class="comborder1 flexs" :style="[c1]">{{  ruleForm.item1Money  }}</div>
              <div class="comborder1 flexs" :style="[c2]">{{  ruleForm.item2Money  }}</div>
              <div class="comborder1 flexs" :style="[c3]">{{  ruleForm.item3Money  }}</div>
              <div class="comborder1 flexs" :style="[c4]">{{  ruleForm.item4Money  }}</div>
              <div class="comborder1 flexs" :style="[c5]">{{  ruleForm.item5Money  }}</div>
            </el-col>
            <el-col :span="12">
              <div class="comborder">备注</div>
              <div class="comborder1 flexs" :style="[c1]" id="remark1">{{  ruleForm.item1Remark  }}</div>
              <div class="comborder1 flexs" :style="[c2]" id="remark2">{{  ruleForm.item2Remark  }}</div>
              <div class="comborder1 flexs" :style="[c3]" id="remark3">{{  ruleForm.item3Remark  }}</div>
              <div class="comborder1 flexs" :style="[c4]" id="remark4">{{  ruleForm.item4Remark  }}</div>
              <div class="comborder1 flexs" :style="[c5]" id="remark5">{{  ruleForm.item5Remark  }}</div>
            </el-col>

          </el-row>
          <el-row>
            <el-col :span="6">
              <div class="comborder1" style="border-right:0px solid #333">合计</div>
            </el-col>
            <el-col :span="2">
              <div class="comborder1">
                {{  accessoryNum  }}张
              </div>
            </el-col>
            <el-col :span="4">
              <div class="comborder1" style="text-align:center">
                {{  ruleForm.item1Money + ruleForm.item2Money + ruleForm.item3Money + ruleForm.item4Money
                + ruleForm.item5Money


                }}
              </div>
            </el-col>
            <el-col :span="12">
              <div class="comborder1"></div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24">
              <div class="comborder" style="text-align: left;padding-left: 4px; letter-spacing: 4px;">总计(大写)人民币:<span>
                  {{  digitUppercase(ruleForm.item1Money + ruleForm.item2Money + ruleForm.item3Money +
                  ruleForm.item4Money + ruleForm.item5Money)


                  }}</span></div>
            </el-col>
          </el-row>
          <el-row style="margin-top:20px;">
            <el-col :span="6">
              <div>总经理:{{  ruleForm.gmCheck  }}</div>
            </el-col>
            <el-col :span="6">
              <div>财务:{{  ruleForm.financeCheck  }}</div>
            </el-col>
            <el-col :span="6">
              <div>部门经理:{{  ruleForm.dmCheck  }}</div>
            </el-col>
            <el-col :span="6">
              <div>报销人:{{  ruleForm.nickName  }}</div>
            </el-col>
          </el-row>
          
        </div>
        <el-row class="row-bg"  style="height:50vh;width:100%;padding-top:10px">
          
          <el-col :span="24">
            <span style="font-size: 20px;margin-bottom: 20px;color: blue;">审批进度</span>
            <el-table border :data="checks" style="margin-top: 20px;">
              <el-table-column prop="checkDate" label="时间" width="180" />
              <el-table-column prop="checkUser" label="人员" width="180" />
              <el-table-column prop="checkReasult" label="结果" />
            </el-table>
          </el-col>
        </el-row>
        <!-- transform: rotate(90deg); -->
        <div v-for="(item, index) in imgArr" :key="index">
          <div v-if="imgArr.length > 0">报销凭证影像</div>
          <el-image :src="item.url" fill="scale-down" :style="{ height: screenHeight*0.632+ 'px' }"></el-image>

        </div>
        <div v-if="imgArr.length / 2 !== 2 && imgArr.length > 0 && imgArr.length !== 2"
          :style="{ height: screenHeight*0.6+ 'px' }">
        </div>
        <div v-for="(item, index) in imgArr2" :key="index">
          <div v-if="imgArr2.length > 0">付款凭证影像</div>
          <el-image :src="item.url" fill="scale-down" :style="{ height: screenHeight*0.632+ 'px' }"></el-image>

        </div>
        <div v-if="imgArr2.length / 2 !== 2 && imgArr2.length > 0 && imgArr2.length !== 2"
          :style="{ height: screenHeight*0.6 + 'px' }">

        </div>
      </el-form>



    </div>

  </div>
</template>

<script>
import { getAllCheck } from '@/api/invoices/checkInvoices'
import { Decimal } from 'decimal.js'
// import pdf from 'vue-pdf-signature'
// import CMapReaderFactory from 'vue-pdf-signature/src/CMapReaderFactory.js'
export default {
  // name: 'ExpensePrint',
  components: {
    // pdf,

  },
  data() {
    return {
      screenHeight: document.body.clientHeight, // 屏幕尺寸
      clientHeight: '',
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
    }

  },

  mounted() {
    this.ruleForm = JSON.parse(window.localStorage.getItem('expenses')).list[0];
    if (!this.ruleForm.accessoryNum1) {
      this.ruleForm.accessoryNum1 = 0;
    }
    if (!this.ruleForm.accessoryNum2) {
      this.ruleForm.accessoryNum2 = 0;
    }
    if (!this.ruleForm.accessoryNum3) {
      this.ruleForm.accessoryNum3 = 0;
    }
    if (!this.ruleForm.accessoryNum4) {
      this.ruleForm.accessoryNum4 = 0;
    }
    if (!this.ruleForm.accessoryNum5) {
      this.ruleForm.accessoryNum5 = 0;
    }
    let arr1 = new Decimal(this.ruleForm.accessoryNum1).add(new Decimal(this.ruleForm.accessoryNum2));
    let arr2 = new Decimal(this.ruleForm.accessoryNum3).add(new Decimal(this.ruleForm.accessoryNum4));
    let arr3 = new Decimal(arr1).add(new Decimal(arr2));
    this.accessoryNum = arr3;

    var expenses = JSON.parse(window.localStorage.getItem('expenses')).list
    var expenseCode = expenses[0].expenseCode;
    console.log('expenseCode==', expenseCode);
    getAllCheck({ invoiceCode: expenseCode }).then(res => {
      console.log('selectAllCheck==', res);
      this.checks = res
    })
    this.expenseImage = this.ruleForm.expenseImage;
    this.expenseImage2 = this.ruleForm.expenseImage2;

    this.imgArr = [];
    this.imgArr2 = [];
    var imgArr = this.expenseImage.split(",");
    if (imgArr[0] == "") {


    } else {
      imgArr.map((item, index) => {
        if (item != null && item != "") {
          var suffix = item.substring(item.lastIndexOf('.') + 1, item.length);
          if (suffix == 'pdf') {
            // this.imgArr.push({
            //   url: pdf.createLoadingTask({ url: this.baseImgPath1 + item, CMapReaderFactory, cMapPacked: true }),
            //   suffix: suffix,
            // })
            this.imgArr.push({
              url: this.baseImgPath1 + item,
              suffix: suffix,
            })
          } else {
            this.imgArr.push({
              url: this.baseImgPath + item,
              suffix: suffix,
            })
          }
        }
      })
    }
    console.log("imgArr==", this.imgArr);
    var imgArr2 = this.expenseImage2.split(",");
    if (imgArr2[0] == "") {


    } else {
      imgArr2.map((item, index) => {
        if (item != null && item != "") {
          var suffix = item.substring(item.lastIndexOf('.') + 1, item.length);
          if (suffix == 'pdf') {
            // this.imgArr2.push({
            //   url: pdf.createLoadingTask({ url: this.baseImgPath1 + item, CMapReaderFactory, cMapPacked: true }),
            //   suffix: suffix,
            // })
            this.imgArr2.push({
              url: this.baseImgPath1 + item,
              suffix: suffix,
            })
          } else {
            this.imgArr2.push({
              url: this.baseImgPath + item,
              suffix: suffix,
            })
          }

        }
      })

    }


    this.getHeight();

  },
  methods: {
    getHeight() {
      this.$nextTick(() => {
        console.log(document.getElementById('remark1').scrollHeight);
        if (document.getElementById('remark1').scrollHeight !== 0) {
          this.c1 = { 'height': document.getElementById('remark1').scrollHeight * 1.4 + 'px', 'line-height': document.getElementById('remark1').scrollHeight / 2 + 'px' };

        } else {
          this.c1 = { 'height': '40px' };

        }
        if (document.getElementById('remark2').scrollHeight !== 0) {
          this.c2 = { 'height': document.getElementById('remark2').scrollHeight * 1.4 + 'px', 'line-height': document.getElementById('remark2').scrollHeight / 2 + 'px' };
        } else {
          this.c1 = { 'height': '40px' };

        }
        if (document.getElementById('remark3').scrollHeight !== 0) {
          this.c3 = { 'height': document.getElementById('remark3').scrollHeight * 1.4 + 'px', 'line-height': document.getElementById('remark3').scrollHeight / 2 + 'px' };
        } else {
          this.c1 = { 'height': '40px' };

        }
        if (document.getElementById('remark4').scrollHeight !== 0) {
          this.c4 = { 'height': document.getElementById('remark4').scrollHeight * 1.4 + 'px', 'line-height': document.getElementById('remark4').scrollHeight / 2 + 'px' };
        } else {
          this.c1 = { 'height': '40px' };

        }
        if (document.getElementById('remark5').scrollHeight !== 0) {
          this.c5 = { 'height': document.getElementById('remark5').scrollHeight + 'px', 'line-height': document.getElementById('remark5').scrollHeight / 2 + 'px' };
        } else {
          this.c1 = { 'height': '40px' };

        }
      })
    },
    back() {
      this.$tab.closeOpenPage({ path: '/invoices/showExpense' });
    },
    //转换大小写
    digitUppercase(n = 0) {
      var fraction = ['角', '分'];
      var digit = [
        '零', '壹', '贰', '叁', '肆',
        '伍', '陆', '柒', '捌', '玖'
      ];
      var unit = [
        ['元', '万', '亿'],
        ['', '拾', '佰', '仟']
      ];
      var head = n < 0 ? '欠' : '';
      n = Math.abs(n);
      var s = '';
      for (var i = 0; i < fraction.length; i++) {
        s += (digit[Math.floor(n * 10 * Math.pow(10, i)) % 10] + fraction[i]).replace(/零./, '');
      }
      s = s || '整';
      n = Math.floor(n);
      for (var i = 0; i < unit[0].length && n > 0; i++) {
        var p = '';
        for (var j = 0; j < unit[1].length && n > 0; j++) {
          p = digit[n % 10] + unit[1][j] + p;
          n = Math.floor(n / 10);
        }
        s = p.replace(/(零.)*零$/, '').replace(/^$/, '零') + unit[0][i] + s;
      }
      return head + s.replace(/(零.)*零元/, '元')
        .replace(/(零.)+/g, '零')
        .replace(/^整$/, '零元整');
    },
    getnames(data) {
      console.log("parent");
      console.log(data)
    }
  }
}
</script>
<style>
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
  height: 40px;
  line-height: 40px;
  text-align: center;
  border: 1px solid #333;
}
.comborder {
  height: 60px;
  line-height: 60px;
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

#printMe {
  /* display: block; */
}
@media print {
  /* @page {
    size: auto;
    margin: 0mm;
  } */
}
</style>