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
          <el-form-item class="comright" label="项目编号" :required="true">
            <el-input
              v-model="publicList.projectCode"
              :disabled="true"
            ></el-input>
          </el-form-item>

          <el-form-item class="comright" label="项目名称" :required="true">
            <el-input
              v-model="publicList.projectName"
              :disabled="true"
            ></el-input>
          </el-form-item>
          <el-form-item class="comright" label="应收账款" :required="true">
            <el-input
              :disabled="true"
              v-model="publicList.projectTotalAmount"
              onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
          <el-form-item class="comright" label="已收账款" :required="true">
            <el-input
              :disabled="true"
              v-model="publicList.receiveMoneys"
              :step="0.00001"
              :min="0"
              onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
          <el-form-item class="comright" label="未收账款" :required="true">
            <el-input
              :disabled="true"
              v-model="publicList.receiveRemainMoneys"
              :step="0.00001"
              :min="0"
              onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
        </el-col>

        <el-col :span="9">
          <el-form-item class="comright" label="项目时间" :required="true">
            <el-input v-model="publicList.createTime" disabled></el-input>
          </el-form-item>
          <el-form-item class="comright" label="项目金额" :required="true">
            <el-input
              :disabled="true"
              type="number"
              v-model="publicList.projectTotalAmount"
              :step="0.00001"
              :min="0"
              onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
            >
              <template slot="append"> 元 </template>
            </el-input>
          </el-form-item>
          <el-form-item class="comright" label="应出账款" :required="true">
            <el-input
              :disabled="true"
              v-model="publicList.payTotalMoneys"
              :step="0.00001"
              :min="0"
              onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
          <el-form-item class="comright" label="已出账款" :required="true">
            <el-input
              :disabled="true"
              v-model="publicList.payMoneys"
              :step="0.00001"
              :min="0"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
          <el-form-item class="comright" label="未出账款" :required="true">
            <el-input
              disabled
              v-model="publicList.payRemainMoneys"
              :step="0.00001"
              :min="0"
              onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
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
              :step="0.00001"
              :min="0"
              onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
            >
              <template slot="append">元</template>
            </el-input>
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
          <el-form-item class="comright" label="出账时间" :required="true">
            <el-input v-model="formData.payTime" disabled></el-input>
          </el-form-item>

          <el-form-item class="comright" label="出账账号" prop="payAccount">
            <el-input v-model="formData.payAccount"  :disabled="true"></el-input>
          </el-form-item>
          <el-form-item class="comright" label="财务流水号" prop="payCode">
            <el-input v-model="formData.payCode"  :disabled="true"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="21">
            <el-form-item class="comright" style="padding-right: 4.2%;margin-left: -7%;">
              <el-radio v-model="isokradioS" label="1"> 通过</el-radio>
           </el-form-item>
        </el-col>

      </el-row>   
       <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="21">
          <el-form-item class="comright" style="padding-right: 4.2%;margin-left: -7%;">
            <div style="display: flex; align-items: center;justify-content: flex-start;">
              <el-radio v-model="isokradioS" label="2">不通过 </el-radio>
              <el-input type="textarea" placeholder="请输入不通过说明" v-model="remark" :disabled="isokradioS == 1"></el-input>
            </div>


          </el-form-item>
        </el-col>

      </el-row>
      

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="8"></el-col>
        <el-col :span="8" class="flexs">
          <el-button type="danger" @click="resetForm">关闭</el-button>
          <el-button v-if="isokradioS == 2" type="primary" @click="submitForm(2)">提交</el-button>
          <el-button v-else type="primary" @click="submitForm(1)">提交</el-button>
        </el-col>
        <el-col :span="8"></el-col>
      </el-row>
    </el-form>
  </div>
</template>
<script>
import uploadSmall from "@/components/douploads/uploadCollect";
import { check,detail,editPay,edit} from "@/api/project/list";
import { getInfo } from "@/api/login";
import { Decimal } from 'decimal.js'
export default {
  name: "AduitDisburse",
  components: { uploadSmall },
  data() {
    return {
      parms:{},
      projectStatusNew:'0',
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
        
        payCode: [
          {
            required: true,
            message: "出账流水号不能为空",
            trigger: "blur",
          },
        ],
        payName: [
          {
            required: true,
            message: "出账账户不能为空",
            trigger: "blur",
          },
        ],
        payMoney: [
          {
            required: true,
            message: "出账金额不能为空",
            trigger: "blur",
          },
        ],
        payAccount: [
          {
            required: true,
            message: "出账账号不能为空",
            trigger: "blur",
          },
        ],
        fileNamePay: [
          {
            required: true,
            message: "出账凭证不能为空",
            trigger: "change",
          },
        ],
      },
      baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
    };
  },
  computed: {},
  mounted() {
    this.getCommonList();
    this.formData=this.$cache.local.getJSON("collectDetails");
    this.formData.fileNamePay=JSON.parse(this.formData.fileNamePay)
    this.$refs.receive.getSrcList(this.formData.fileNamePay);
    for(let i in this.formData.fileNamePay){
           this.fileNameN.push({
            name:this.formData.fileNamePay[i],
            url:this.baseImgPath+this.formData.fileNamePay[i]
           })
        }
  },
  methods: {
    //获取公共数据
    getCommonList(){
        detail({
         projectCode: this.$cache.local.getJSON("projectCodeNew")
        }).then((response) => {
         this.publicList = response.data;
       });
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
          projectType: "24",
        };
        check(parms)
          .then((res) => {
            console.log("审核出款成功!");
          })
          .catch((error) => {});
      });
    },
   
    //返回
    resetForm() {
      if(this.$cache.local.getJSON('iscollect')==0){
         this.$tab.closeOpenPage({
          path:'/projectlist/aduitDisburseList'
         })
      }else{
        this.$tab.closeOpenPage({
        path: this.$cache.local.getJSON("aduitProjectBack").backurl,
      });
      }
      
    },
    handleChange(val) {
      console.log(val);
    },
   
    submitForm(type) {
      this.$refs["elForm"].validate((valid) => {
        // TODO 提交表单
        if (valid) {
         let params={
          payId:this.formData.payId,
          isCheck:type,
          payRemark:this.remark,
         };
         
         editPay(params).then((res) => {
            if (res != undefined) {
              if (res.code === 200) {
                
                if (
                  this.formData.projectDutypaidStatus == 1 &&
                  this.formData.projectReceiveStatus == 1 &&
                  this.formData.projectTicketStatus == 1 &&
                  this.formData.projectAcceptanceStatus == 1 &&
                  this.formData.projectContractStatus == 1 &&
                  this.formData.projectCheckStatus == 1
                ) {
                  this.projectStatusNew = 2;
                } else if (
                  this.formData.projectDutypaidStatus == 2 ||
                  this.formData.projectReceiveStatus == 2 ||
                  this.formData.projectTicketStatus == 2 ||
                  this.formData.projectAcceptanceStatus == 2||
                  this.formData.projectCheckStatus == 2 ||
                  this.formData.projectContractStatus == 2
                ) {
                  this.projectStatusNew = 1;
                } else {
                  this.projectStatusNew = 0;
                }
  
               if (type == 1) {
                      this.check('出款审核完成');
                      if (new Decimal(this.publicList.payRemainMoneys).sub(new Decimal(this.formData.payMoney)) == 0) {
                       this.parms = {
                            projectId: this.Father.projectId,
                            projectPayStatus: 1,
                            projectStatus:this.projectStatusNew

                        };
                      } else {
                       this.parms = {
                            projectId: this.publicList.projectId,
                            projectPayStatus: 0,
                            projectStatus:this.projectStatusNew

                        };
                       }
                    } else {
                      this.parms = {
                        projectId: this.publicList.projectId,
                        projectPayStatus:2,
                        payRemark:this.remark,
                        projectStatus: 1,
                      };
                      edit(this.parms);
                      this.check('出款审核不通过。'+'原因:'+this.remark);
                    }
                    let obj = {
                      title: '出款审核',
                      backUrl: this.$cache.local.getJSON('aduitProjectBack').backurl,
                      resmsg: '出款审核完成',
                      backName:this.$cache.local.getJSON('aduitProjectBack').name

                    }
                    this.$cache.local.setJSON('successProject', obj);
                    this.$tab.closeOpenPage({ path: "/projectlist/success" });
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
