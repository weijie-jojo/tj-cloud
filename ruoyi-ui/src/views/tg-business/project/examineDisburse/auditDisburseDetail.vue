<template>
  <div>
    <el-form
      ref="elForm"
      :model="formData"
      :rules="rules"
      size="medium"
      label-width="140px"
    >
     
     <el-row type="flex" class="row-bg" justify="space-around" style="margin-top:20px">
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width: 35%">出款信息</div>
          <div style="width: 50%; hegiht: 10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="出账账户" prop="payName">
            <el-input  :disabled="true" v-model="formData.payName"></el-input>
          </el-form-item>
          <el-form-item class="comright" label="出账金额" prop="payMoney">
            <el-input
            :disabled="true"
              v-model="formData.payMoney"
             
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
          <el-form-item class="comright" label="付款账户" :required="true">
          <el-input :readonly="true" v-model="formData.paymentName"></el-input>
        </el-form-item>
          <el-form-item
            class="comright"
            label="出账凭证"
            prop="fileNamePay"
          >
            <uploadSmall
              ref="receive"
              :fileName="isNoneArray"
              :fileNameOld="fileNameN"
              :isDetail="isDetail"
              :index="0"
            ></uploadSmall>
          </el-form-item>

        </el-col>

        <el-col :span="9">
          <el-form-item class="comright" label="出账账号" prop="payAccount">
            <el-input v-model="formData.payAccount"  :disabled="true"></el-input>
          </el-form-item>
          <el-form-item class="comright" label="出款时间" :required="true">
            <el-date-picker
            disabled
            style="width: 100%"
            v-model="formData.payTime"
            value-format="yyyy-MM-dd"
            align="right"
          >
          </el-date-picker>
          </el-form-item>
          <el-form-item class="comright" label="付款账号" :required="true">
          <el-input :readonly="true" v-model="formData.paymentAccount"></el-input>
        </el-form-item>
        
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="21">
            <el-form-item class="comright" style="padding-right: 4.2%;margin-left: -7%;">
              <el-radio disabled v-model="isokradioS" label="1"> 通过</el-radio>
           </el-form-item>
        </el-col>

      </el-row>   
       <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="21">
          <el-form-item class="comright" style="padding-right: 4.2%;margin-left: -7%;">
            <div style="display: flex; align-items: center;justify-content: flex-start;">
              <el-radio disabled v-model="isokradioS" label="2">不通过 </el-radio>
              <el-input type="textarea" placeholder="请输入不通过说明" v-model="formData.payRemark" disabled></el-input>
            </div>


          </el-form-item>
        </el-col>

      </el-row>
      

      <el-row type="flex" class="row-bg" justify="space-around" style="margin-bottom:20px">
        <el-col :span="8"></el-col>
        <el-col :span="8" class="flexs">
          <el-button type="danger" @click="resetForm">关闭</el-button>
        </el-col>
        <el-col :span="8"></el-col>
      </el-row>
    </el-form>
  </div>
</template>
<script>
import uploadSmall from "@/components/douploads/uploadCollect";
import {detail,detailPay} from "@/api/tg-api/project/list";
export default {
  name: "AuditDisburseDetail",
  components: { uploadSmall },
  data() {
    return {
      isokradioS:'1',
      fileNameN:[],
      isNoneArray:[],
      index: 0,
      remark:'',
      publicList: {},
      userinfo: {},
      isDetail: "1",
      fileName: [],
      
      formData: {
      
      },
      rules: {
        payName: [
        {
          required: true,
          message: "出款信息出账账户不能为空",
          trigger: "blur",
        },
      ],
      payMoney: [
        {
          required: true,
          message: "出款信息出账金额不能为空",
          trigger: "blur",
        },
       ],
       payAccount: [
        {
          required: true,
          message: "出款信息出款账号不能为空",
          trigger: "blur",
        },
      ],
      fileNamePay: [
        {
          required: true,
          message: "出款信息出账凭证不能为空",
          trigger: "change",
        },
      ],
      },
      baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
    };
  },
  mounted() {
    this.getCommonList();
    this.getlist();
  },
  methods: {
    getlist(){
    this.$modal.loading("正在加载数据，请稍后...");
    detailPay(this.$cache.local.getJSON("tg-payId")).then((response) => {
    this.formData = response.data;
    this.$modal.closeLoading();
    this.formData.fileNamePay=JSON.parse(this.formData.fileNamePay)
    this.$refs.receive.getSrcList(this.formData.fileNamePay);
    for(let i in this.formData.fileNamePay){
          this.fileNameN.push({
            name:this.formData.fileNamePay[i],
            url:this.baseImgPath+this.formData.fileNamePay[i]
          })
        }
    
  }).catch((err) => {
        this.$modal.closeLoading();
      });
  },
    //获取公共数据
    getCommonList(){
        detail({
         projectCode: this.$cache.local.getJSON("tg-project-code")
        }).then((response) => {
         this.publicList = response.data;
       });
    },
  
   
    //返回
    resetForm() {
      if (this.$cache.local.getJSON("tg-ifcollect") == 1) {
      this.$tab.closeOpenPage({
        path: "/tg-business/project/list",
      });
    } else {
      this.$tab.closeOpenPage({
        path: '/tg-business/project/aduitDisburseList'
      });
    }
   },
    handleChange(val) {
      console.log(val);
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
::v-deep .el-input.is-disabled .el-input__inner {
    background-color: rgba(255, 255, 255, 1.5) !important;
    color: black !important;
    border-color: rgba(135, 206, 250, 0.7) !important;
}

::v-deep .el-input-group__append {
    background-color: rgba(255, 255, 255, 1.5) !important;
    color: black !important;
    border-color: rgba(135, 206, 250, 0.7) !important;
}
</style>
