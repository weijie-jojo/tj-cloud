<template>
  <div>
    <el-form
      ref="elForm"
      :model="formData"
      :rules="rules"
      size="medium"
      label-width="140px"
    >
     <el-row type="flex" class="row-bg" justify="space-around" style="margin-top:10px">
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width: 35%">收款信息</div>
          <div style="width: 50%; hegiht: 10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="收款账户" prop="receiveName">
            <el-input  :disabled="true" v-model="formData.receiveName"></el-input>
          </el-form-item>
          <el-form-item class="comright" label="收款金额" prop="receiveMoney">
            <el-input
            :disabled="true"
              v-model="formData.receiveMoney"
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
         
          <el-form-item class="comright" label="付款账户" prop="paymentName">
          <el-input v-model="formData.paymentName"></el-input>
        </el-form-item>
          <el-form-item
            class="comright"
            label="收款凭证"
            prop="fileNameReceive"
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
          <el-form-item class="comright" label="收款账号" prop="receiveAccount">
            <el-input v-model="formData.receiveAccount"  :disabled="true"></el-input>
          </el-form-item>
          <el-form-item class="comright" label="收款时间" :required="true">
            <el-date-picker
            disabled
            style="width: 100%"
            v-model="formData.receiveTime"
            value-format="yyyy-MM-dd"
            align="right"
          >
          </el-date-picker>
          </el-form-item>
         
          <el-form-item class="comright" label="付款账号" prop="paymentAccount">
          <el-input v-model="formData.paymentAccount"></el-input>
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
              <el-input type="textarea" placeholder="请输入不通过说明" v-model="formData.receiveRemark" disabled></el-input>
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
import {detail,detailCollect} from "@/api/tg-api/project/list";
export default {
  name: "AuditCollectDetail",
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
        isCheck: 0,
        fileNameReceive: [],
        projectCode: "",
        projectName: "",
        receiveTime: "", //转账时间 收款信息
        receiveSysCode: "", //流水号
        receiveAccount: "", //转账账号 收款信息
        receiveName: "", //转账账户 收款信息
        receiveMoney: "0.00", //收款金额 收款信息
      },
      rules: {
        paymentAccount: [
        {
          required: true,
          message: "收款信息付款账号不能为空",
          trigger: "blur",
        },
      ],
      paymentName: [
        {
          required: true,
          message: "收款信息付款账户不能为空",
          trigger: "blur",
        },
      ],

      receiveName: [
        {
          required: true,
          message: "收款信息收款账户不能为空",
          trigger: "blur",
        },
      ],
      receiveMoney: [
        {
          required: true,
          message: "收款信息收款金额不能为空",
          trigger: "blur",
        },
      ],
      receiveAccount: [
        {
          required: true,
          message: "收款信息收款账号不能为空",
          trigger: "blur",
        },
      ],
      fileNameReceive: [
        {
          required: true,
          message: "收款信息收款凭证不能为空",
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
    detailCollect(this.$cache.local.getJSON("tg-collReceiveId")).then((response) => {
    this.formData = response.data;
    this.$modal.closeLoading();
    this.formData.fileNameReceive = JSON.parse(this.formData.fileNameReceive);
    this.$refs.receive.getSrcList(this.formData.fileNameReceive);
    for (let i in this.formData.fileNameReceive) {
     this.fileNameN.push({
       name: this.formData.fileNameReceive[i],
       url: this.baseImgPath + this.formData.fileNameReceive[i],
      });
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
        path: '/tg-business/project/aduitCollectList'
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
