<template>
  <div class="app-container">
    <div>
      <el-row type="flex" class="row-bg" justify="end">
        <el-col :span="6" style="display: flex;justify-content: flex-end;">
          <el-button type="primary" v-print="'#printMe'">打印</el-button>
        </el-col>
      </el-row>

      <el-form id="printMe">
        <div :style="{ height: screenHeight * 0.5 + 'px' }">
          <div class="reimtitle" style="text-align:center;position: relative;margin-bottom: 5px;">
            <span>借支单</span>
            <!-- <span style="font-size:15px;letter-spacing:0px; position:absolute;right:0;top:10px">报销单号:{{
               ruleForm.expenseCode  }}</span> -->
          </div>
          <el-row>
            <el-col :span="8">
 
              <div style="margin-bottom:5px">部门:{{  ruleForm.deptName  }}</div>
            </el-col>
            <el-col :span="8">
              借支单编号:{{ ruleForm.borrowCode  }}
            </el-col>
            <el-col :span="8">
              <div>

                <div style="display:flex; justify-content: flex-end;margin-bottom:5px">

                  <span>借款日期:{{  ruleForm.borrowDate  }}</span>

                </div>
              </div>

            </el-col>

          </el-row>
          <el-row>
            <el-col :span="4">
              <div class="comborder" style="border-top:1px solid #333;border-left:1px solid #333">借支人</div>
            
              
            </el-col>
            <el-col :span="4">
              <div class="comborder" style="border-top:1px solid #333;border-left:1px solid #333">{{ruleForm.borrowName}}</div>
            
              
            </el-col>
          
            <el-col :span="4">
              <div class="comborder">金额</div>
            
              
            </el-col>
            <el-col :span="4">
              <div class="comborder" style="border-top:1px solid #333;border-left:1px solid #333">{{ruleForm.totalMoney}}</div>
            
              
            </el-col>
            <el-col :span="4">
              <div class="comborder">职务</div>
             
             
            </el-col>
            <el-col :span="4">
              <div class="comborder" style="border-top:1px solid #333;border-left:1px solid #333">{{ruleForm.job}}</div>
            
              
            </el-col>

          </el-row>
          <el-row>

          </el-row>
          <el-row>
            <el-col :span="4">
              <div class="comborder" :style="[c2]">
                借支用途
              </div>
            </el-col>
            <el-col :span="20">
              <div class="comborder" id="remark1" :style="[c1]">
                 {{ruleForm.borrowDesc}}
              </div>
            </el-col>
          </el-row>
         
          <el-row>
            <el-col :span="24">
              <div class="comborder" style="text-align: left;padding-left: 4px; letter-spacing: 4px;">总计(大写)人民币:<span>
                  {{  digitUppercase(ruleForm.totalMoney)
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
        <div :style="{ height: screenHeight * 0.5 + 'px'}">
          <el-row class="row-bg" :style="{ width: screenWidth + 'px', height: screenHeight * 0.5 + 'px',paddingTop:'15px' }">

            <el-col :span="24">
              <span style="font-size: 20px;margin-bottom: 20px;color: blue;">审批进度</span>
              <el-table :data="checks" style="margin-top: 20px;" 
                :cell-style="{border:'1px solid #333'}"
                
                fit
                :header-cell-style="{border:'1px solid #333'}"
            
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
        
        <div v-for="(item, index) in imgArr" :key="index">
          <div v-if="index>0 && index %2 !==0"  :style="{
              height: screenHeight * 0.01 + 'px',}">
          </div>
         <div :style="{ height: screenHeight * 0.1 + 'px',display:'flex',justifyContent:'space-between',alignItems: 'center',paddingBottom:'10px' }">
            报销凭证影像
            <span> 借支单编号:{{ruleForm.borrowCode  }} </span>
          </div>
         
          <el-image :src="item.url" fill="scale-down"
            :style="{ height: screenHeight * 0.4 + 'px', width: screenWidth + 'px' }"></el-image>
            <div v-if="imgArr.length ==1 "
            :style="{ height: screenHeight * 0.51 + 'px', width: screenWidth + 'px' }">

        </div>
     

        </div>
        <div v-if="imgArr.length>1 && imgArr.length %2 !==0"  :style="{ height: screenHeight * 0.51 + 'px', width: screenWidth + 'px' }">

        </div>
       
        

       
        <div v-for="(item, index) in imgArr2" :key="index">
          <div v-if="index>0 && index %2 !==0"  :style="{
              height: screenHeight * 0.01 + 'px',}">
          </div>
          <div :style="{ height: screenHeight * 0.1 + 'px',display:'flex',justifyContent:'space-between',alignItems: 'center',paddingBottom:'10px'  }">付款凭证影像
            <span> 借支单编号:{{ruleForm.borrowCode  }} </span>
          </div>
          <el-image :src="item.url" fill="scale-down"
            :style="{ height: screenHeight * 0.4 + 'px', width: screenWidth + 'px' }"></el-image>
            <div v-if="imgArr2.length ==1"
          :style="{ height: screenHeight * 0.51+ 'px', width: screenWidth + 'px' }">

        </div>   

        </div>
        
        <div v-if="imgArr2.length>1 && imgArr2.length %2 !==1" 
          :style="{ height: screenHeight * 0.51 + 'px', width: screenWidth + 'px' }">

        </div>

      </el-form>



    </div>
    <el-row type="flex" class="row-bg " justify="space-around">
      <el-col :span="8"></el-col>
      <el-col :span='8' class="flexs">
        <el-button type="danger" @click="beforePage" style="width:130px">关闭</el-button>

      </el-col>
      <el-col :span="8"></el-col>
    </el-row>
  </div>
</template>

<script>
import { getAllCheck } from '@/api/invoices/checkInvoices'
import { Decimal } from 'decimal.js'
export default {

  components: {
    // pdf,

  },
  data() {
    return {
      dpi: '',
      screenWidth: 756, // 屏幕尺寸
      screenHeight: 1086, // 屏幕尺寸
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
    this.ruleForm=JSON.parse(window.localStorage.getItem('borrows')).list[0];
    var expenses = JSON.parse(window.localStorage.getItem('borrows')).list
    var expenseCode = expenses[0].borrowCode;
      getAllCheck({ invoiceCode: expenseCode }).then(res => {
      console.log('selectAllCheck==', res);
      this.checks = res
    })
    this.expenseImage = this.ruleForm.borrowImage;
    this.expenseImage2 = this.ruleForm.borrowImage2;

    this.imgArr = [];
    this.imgArr2 = [];
    var imgArr = this.expenseImage.split(",");
    if (imgArr[0] == "") {


    } else {
      imgArr.map((item, index) => {
        if (item != null && item != "") {
          var suffix = item.substring(item.lastIndexOf('.') + 1, item.length);
          if (suffix == 'pdf') {
            
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
    beforePage() {
      this.$tab.closeOpenPage({ path: '/invoices/showBorrow' });
    },
     getHeight() {
      this.$nextTick(() => {
        console.log(document.getElementById('remark1').scrollHeight);
        if (document.getElementById('remark1').scrollHeight !== 0) {
          this.c2 = { 'height': document.getElementById('remark1').scrollHeight * 4 + 'px', 'line-height': document.getElementById('remark1').scrollHeight *4 + 'px' };
          this.c1 = { 'height': document.getElementById('remark1').scrollHeight * 4 + 'px', 'line-height': document.getElementById('remark1').scrollHeight / 2 + 'px','text-align':'left' };

        } else {
          this.c1 = { 'height': '40px','line-height':'40px' };
          this.c2 = { 'height': '40px','line-height':'40px'  };
       

        }
       
      })
    },
    //设置表格行的样式
    tableRowStyle({ row, rowIndex }) {
      return 'background-color:pink;font-size:15px;'
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
<style scoped>
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
.flexsS{
  justify-content: flex-start !important;
  text-align: left !important;
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

/* .el-table::before {
  background-color: #333;
  height: 2px;
} */
.exporttable {
  border-color:  black;
  }
/* .el-table tr{
  border: 1px solid #333;
}
.el-table th{
  border: 1px solid #333;
}
.el-table td{
  border: 1px solid #333;
}
.el-table__cell{
  border: 1px solid #333;
} */








@media print {
  @page {
    size: A4 portrait;
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