<template>
  <div>
    <el-form
      ref="elForm"
      :model="formData"
      :rules="rules"
      size="medium"
      label-width="auto"
    >
     <el-row type="flex" class="row-bg" justify="space-around" style="margin-top:20px">
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width: 45%">出款信息</div>
          <div style="width: 50%; hegiht: 10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="出款账户" :required="true">
            <el-input  v-model="formData.payName"   :readonly="true"></el-input>
          </el-form-item>
          <el-form-item class="comright" label="出款时间" :required="true">
            <el-date-picker
              disabled
              style="width: 100%"
              v-model="formData.payTime"
              value-format="yyyy-MM-dd"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              align="right"
            >
            </el-date-picker>
          </el-form-item>

          <el-form-item class="comright" label="收款账户">
            <el-input v-model="formData.receiveName" :readonly="true"></el-input>
          </el-form-item>
          <el-form-item class="comright" label="付款账户" prop="paymentName" >
            <el-input  v-model="formData.paymentName" ></el-input>
          </el-form-item>
       
          <el-form-item
            class="comright"
            label="转账凭证"
            prop="fileNameReceive"
          >
            <uploadSmall
             @getfileName="getReceive"
              :fileName="isNoneArray"
              :fileNameOld="isNoneArray"
              :isDetail="isDetail"
              :index="0"
            ></uploadSmall>
          </el-form-item>

        </el-col>

        <el-col :span="9">
          <el-form-item class="comright" label="出款账号" :required="true">
            <el-input v-model="formData.payAccount"  :disabled="true" ></el-input>
          </el-form-item>
         
          <el-form-item class="comright" label="出款金额" :required="true">
            <el-input
            v-model="formData.payMoney"
            :readonly="true"
             
              :step="0.00001"
              :min="0"
              onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
          <el-form-item class="comright" label="收款账号" :required="true">
            <el-input :readonly="true" v-model="formData.receiveAccount"></el-input>
          </el-form-item>
          <el-form-item class="comright" label="付款账号" prop="paymentAccount" >
            <el-input  v-model="formData.paymentAccount" ></el-input>
          </el-form-item>
          

         
         
        </el-col>
      </el-row>
     

      <el-row type="flex" class="row-bg" justify="space-around" style="margin-bottom:20px">
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
import uploadSmall from "@/components/douploads/uploadCollect";
import {detailPay,editPay,check} from "@/api/tg-api/project/list";
import { getInfo } from "@/api/login";
export default {
  name: "PayDibuse",
  components: { uploadSmall },
  data() {
    return {
      isNoneArray:[],
      index: 0,
     
      publicList: {},
      userinfo: {},
      isDetail: "0",
      fileName: [],
      newlist:{},
      formData: {
        
      },
      rules: {
        paymentAccount: [
            {
              required: true,
              message: "付款账户不能为空",
              trigger: "blur",
            },
          ],
          paymentName: [
            {
              required: true,
              message: "出账账号不能为空",
              trigger: "blur",
            },
          ],
       
      },
      baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
    };
  },
  mounted() {
   this.getDetails();
   },
  methods: {
      //收款日志
      check(resmsg) {
        getInfo().then((res) => {
          this.userinfo = res.user;
          let parms = {
            checkReasult: resmsg,
            checkUser: this.userinfo.userName,
            phonenumber: this.userinfo.phonenumber,
            projectCode: this.formData.projectCode,
            projectType: "25",
          };
          check(parms)
            .then((res) => {
             
            })
            .catch((error) => {});
        });
      },
    getDetails(){
      detailPay(
          this.$cache.local.getJSON("tg-payId")
        ).then((response) => {
         this.formData=response.data;
        });
    },
   //获取转账时间
    getPayTime(){
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
        day +
        " " +
        hour +
        ":" +
        minute +
        ":" +
        second;
     
    
      return curTime;
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
        day +
        " " +
        hour +
        ":" +
        minute +
        ":" +
        second;
      // this.formData.createTime = curTime;
      this.formData.receiveTime = curTime;
      this.disburseList[0].payTime = curTime;
    },
    
   getReceive(data) {
      this.formData.fileNameReceive = data;
      console.log(3333,data);
    },
    //返回
    resetForm() {
      this.$tab.closeOpenPage({
          path:'/tg-business/project/aduitDisburseList'
         })
     },
    handleChange(val) {
      console.log(val);
    },
    async sleep(n) {
        var start = new Date().getTime();
        while (true) {
            if (new Date().getTime() - start > n) {
                break;
            }
        }
    },
    onSubmit() {
      this.$refs["elForm"].validate((valid) => {
        // TODO 提交表单
        if (valid) {
          if(Array.isArray(this.formData.fileNameReceive)){
            this.formData.fileNameReceive=JSON.stringify(this.formData.fileNameReceive);
          }
          this.formData.isPay=1;
          editPay(this.formData).then((res) => {
            if (res != undefined) {
              if (res.code === 200) {
                 this.$modal.loading("正在提交数据，请稍后...");
                  let that=this;
                  that.$modal.closeLoading();
                  that.check('财务付款成功')
                  that.$modal.msgSuccess("新增付款成功");
                  that.$tab.refreshPage({
                      path:'/tg-business/project/aduitDisburseList',
                      name:'AduitDisburseList'
                     })
                  } 
            }
          }).catch(()=>{
          })
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
