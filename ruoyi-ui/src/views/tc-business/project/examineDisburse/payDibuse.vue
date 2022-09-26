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
          <el-form-item class="comright" label="出款账户" prop="receiveName">
            <el-input></el-input>
          </el-form-item>
          <el-form-item class="comright" label="出款时间" :required="true">
            <el-input v-model="formData.receiveTime" disabled></el-input>
          </el-form-item>

          <el-form-item class="comright" label="付款账户">
            <el-input></el-input>
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
          <el-form-item class="comright" label="出款账号" prop="receiveAccount">
            <el-input ></el-input>
          </el-form-item>
         
          <el-form-item class="comright" label="出款金额" prop="receiveMoney">
            <el-input
              
             
              @change="receiveSee"
              :step="0.00001"
              :min="0"
              onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
          <el-form-item class="comright" label="收款账号">
            <el-input></el-input>
          </el-form-item>
          

         
         
        </el-col>
      </el-row>
     

      <el-row type="flex" class="row-bg" justify="space-around">
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
import { getReceiveCode,getPayCode, check,detail,addReceive,addPay} from "@/api/tc-api/project/list";
import { getInfo } from "@/api/login";
export default {
  name: "PayDibuse",
  components: { uploadSmall },
  data() {
    return {
      isNoneArray:[],
      index: 0,
      disburseList: [
        {
          receiveSysCode:'',
          payTime:'',
          projectCode: "",
          projectName: "",
          isCheck: 0,
          payAccount: "",
          paySysCode: "",
          payCode:'',
          payName: "",
          payMoney: "",
          
          fileNamePay: [],
        },
      ],
      publicList: {},
      userinfo: {},
      isDetail: "0",
      fileName: [],
      newlist:{},
      formData: {
        isCheck: 0,
        fileNameReceive: [],
        projectCode: "",
        projectName: "",
        receiveTime: "", //转账时间 收款信息
        receiveSysCode: "", //流水号
        receiveAccount: "", //转账账号 收款信息
        receiveName: "", //转账账户 收款信息
        receiveMoney: "0.00000", //收款金额 收款信息
      },
      rules: {
        
        receiveCode: [
          {
            required: true,
            message: "财务流水号不能为空",
            trigger: "blur",
          },
        ],
        receiveName: [
          {
            required: true,
            message: "转账账户不能为空",
            trigger: "blur",
          },
        ],
        receiveMoney: [
          {
            required: true,
            message: "转账金额不能为空",
            trigger: "blur",
          },
        ],
        receiveAccount: [
          {
            required: true,
            message: "转账账号不能为空",
            trigger: "blur",
          },
        ],
        fileNameReceive: [
          {
            required: true,
            message: "转账凭证不能为空",
            trigger: "change",
          },
        ],
      },
      baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
    };
  },
  computed: {},
  mounted() {
    this.gettoday();
   
   
   
   
  },
  methods: {
    receiveSee(e) {
            if (e > this.publicList.projectTotalAmount) {
                  this.$alert('收账金额不能大于应收金额', '系统提示', {
                    confirmButtonText: '确定',
                    type: 'warning'
                });
                this.formData.receiveMoney = 0;
                return;
           }else{
           }
         },
    paySee(e) {
       if (this.disburseList[e].payMoney*1 > this.formData.receiveMoney*1) {
                  this.$alert('出账金额不能大于转账金额', '系统提示', {
                    confirmButtonText: '确定',
                    type: 'warning'
                });
                this.disburseList[e].payMoney=0;
               
       }else{

       }
   
     
    },
    getDetails(){
        detail({
         projectCode: this.$cache.local.getcSON("tc-project-code")
        }).then((response) => {
         this.publicList = response.data;
         this.formData.projectCode = this.publicList.projectCode;
         this.formData.projectName = this.publicList.projectName;
         this.getReceiveCode();
        
         this.disburseList[0].projectCode = this.publicList.projectCode;
         this.disburseList[0].projectName = this.publicList.projectName;
         //this.ticketByCode();
        });
    },
    dels(index) {
      this.disburseList.splice(index, 1);
    },
    adds() {
      this.disburseList.push({
        receiveSysCode:this.formData.receiveSysCode,
        isCheck: 0,
        paySysCode: "",
        payCode:'',
        projectCode:this.publicList.projectCode,
        projectName:this.publicList.projectName,
        payTime:this.getPayTime(),
        payAccount: "",
        payName: "",
        payMoney: "",
        fileNamePay: [],
      });
    },
    getindexNew(data) {
      this.index = data;
    },
    getPay(data) {
      console.log(444,data);
      this.disburseList[this.index].fileNamePay = data;
    },
    getindexs() {},
    repair(i) {
      if (i >= 0 && i <= 9) {
        return "0" + i;
      } else {
        return i;
      }
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
    //收款日志
    check(resmsg) {
      getInfo().then((res) => {
        this.userinfo = res.user;
        let parms = {
          checkReasult: resmsg,
          checkUser: this.userinfo.userName,
          phonenumber: this.userinfo.phonenumber,
          projectCode: this.formData.projectCode,
          projectType: "19",
        };
        check(parms)
          .then((res) => {
            console.log("新增收款成功!");
          })
          .catch((error) => {});
      });
    },
    //出款日志
    checkDisburse(resmsg) {
      let parms = {
          checkReasult: resmsg,
          checkUser: this.userinfo.userName,
          phonenumber: this.userinfo.phonenumber,
          projectCode: this.formData.projectCode,
          projectType: "22",
        };
        check(parms)
          .then((res) => {
            console.log("新增出款成功!");
          })
          .catch((error) => {});
    
    },
    //获取收款流水号
    getReceiveCode() {
      getReceiveCode({projectCode: this.formData.projectCode}).then((res) => {
        this.disburseList[0].receiveSysCode=res;
        this.formData.receiveSysCode = res;
      });
    },
    //获取出款流水号 
    getDisburseCode(index) {
      this.getArr1(index);
    },
     getArr1(index){
       getPayCode({projectCode: this.formData.projectCode }).then((res) => {
       this.getArr2(res,index);
       this.getArr3();
      
       });
    },
    getArr2(res,index){
      this.disburseList[index].paySysCode=res;
      let parms= this.disburseList[index];
        addPay(parms);
    },
    getArr3(){
      this.checkDisburse("新增出款成功");
    },
    getReceive(data) {
      this.formData.fileNameReceive = data;
      console.log(3333,data);
    },
    //返回
    resetForm() {
      if(this.$cache.local.getcSON('tc-ifcollect')==0){
         this.$tab.closeOpenPage({
          path:'/tc-business/project/aduitCollectList'
         })
      }else{
        this.$tab.closeOpenPage({
        path: this.$cache.local.getcSON("tc-addback").backurl,
      });
      }
      
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
        addReceive(this.formData).then((res) => {
            if (res != undefined) {
              if (res.code === 200) {
                  this.check("新增收款成功");
                  this.$modal.loading("正在提交数据，请稍后...");
                  for (let i in this.disburseList) {
                    let that=this;
                    //出款获取流水号并且入库出款信息
                   if(Array.isArray(this.disburseList[i].fileNamePay)){
                      this.disburseList[i].fileNamePay=JSON.stringify(this.disburseList[i].fileNamePay);
                    }
                      (function (j) { 
                        setTimeout(function() { 
                          that.getDisburseCode(i);
                       }, 1500 * j); 
                        })(i); 
                  }
                  let that=this;
                  setTimeout(function() { 
                  that.$modal.closeLoading();
                  that.$modal.msgSuccess("新增收款成功");
                  if(that.$cache.local.getcSON('tc-ifcollect')==0){
                    that.$tab.refreshPage({
                      path:'/tc-business/project/aduitCollectList',
                      name:'AduitCollectList'
                     })
                  }else{
                     that.$tab.refreshPage({
                        path: that.$cache.local.getcSON("tc-addback").backurl,
                       name: that.$cache.local.getcSON("tc-addback").name,
                       });
                  }
                  
                     }, 1500 * that.disburseList.length)
                  
                } 
            }
          }).catch(()=>{
            this.getReceiveCode();
            this.$alert("已重新获取流水号,请重新提交", "系统提示", {
                confirmButtonText: "确定",
                type: "success",
              });

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
