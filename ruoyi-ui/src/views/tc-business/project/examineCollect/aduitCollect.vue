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
            <el-input
              :disabled="true"
              v-model="formData.receiveName"
            ></el-input>
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
            <el-input :readonly="true" v-model="formData.paymentName"></el-input>
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
          
          <el-form-item class="comright" label="收款时间" :required="true">
            <el-date-picker
              disabled
              style="width: 100%"
              v-model="formData.receiveTime"
              value-format="yyyy-MM-dd"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              align="right"
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item class="comright" label="收款账号" prop="receiveAccount">
            <el-input
              v-model="formData.receiveAccount"
              :disabled="true"
            ></el-input>
          </el-form-item>
         
          <el-form-item class="comright" label="付款账号" prop="paymentAccount">
            <el-input :readonly="true" v-model="formData.paymentAccount"></el-input>
          </el-form-item>
          
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="21">
          <el-form-item
            class="comright"
            style="padding-right: 4.2%; margin-left: -7%"
          >
            <el-radio v-model="isokradioS" label="1"> 通过</el-radio>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="21">
          <el-form-item
            class="comright"
            style="padding-right: 4.2%; margin-left: -7%"
          >
            <div
              style="
                display: flex;
                align-items: center;
                justify-content: flex-start;
              "
            >
              <el-radio v-model="isokradioS" label="2">不通过 </el-radio>
              <el-input
                type="textarea"
                placeholder="请输入不通过说明"
                v-model="remark"
                :disabled="isokradioS == 1"
              ></el-input>
            </div>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around" style="margin-bottom:20px">
        <el-col :span="8"></el-col>
        <el-col :span="8" class="flexs">
          <el-button type="danger" @click="resetForm">关闭</el-button>
          <el-button
            v-if="isokradioS == 2"
            type="primary"
            @click="submitForm(2)"
            >提交</el-button
          >
          <el-button v-else type="primary" @click="submitForm(1)"
            >提交</el-button
          >
        </el-col>
        <el-col :span="8"></el-col>
      </el-row>
    </el-form>
  </div>
</template>
<script>
import uploadSmall from "@/components/douploads/uploadCollect";
import { check, detail, editReceive, edit,detailCollect,receiveList2} from "@/api/tc-api/project/list";
import { getInfo } from "@/api/login";
import { Decimal } from 'decimal.js'
export default {
  name: "AduitCollect",
  components: { uploadSmall },
  data() {
    return {
      parms:{},
      projectStatusNew: "0",
      isokradioS: "1",
      fileNameN: [],
      isNoneArray: [],
      index: 0,
      remark: "",
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
      detailCollect(this.$cache.local.getJSON("tc-collReceiveId")).then((response) => {
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
    getCommonList() {
      detail({
        projectCode: this.$cache.local.getJSON("tc-project-code"),
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
          projectType: "21",
        };
        check(parms)
          .then((res) => {
            console.log("审核收款成功!");
          })
          .catch((error) => {});
      });
    },

    //返回
    resetForm() {
      if (this.$cache.local.getJSON("tc-ifcollect") == 0) {
        this.$tab.closeOpenPage({
          path: "/tc-business/project/aduitCollectList",
        });
      } else {
        this.$tab.closeOpenPage({
          path: this.$cache.local.getJSON("tc-aduitback").backurl,
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
          let params = {
            receiveId: this.formData.receiveId,
            isCheck: type,
            receiveRemark: this.remark,
          };
          this.$modal.loading("正在提交中，请稍后...");
          editReceive(params).then((res) => {
            if (res != undefined) {
              if (res.code === 200) {
                receiveList2({
                  projectCode: this.publicList.projectCode,
                })
                  .then((res) => {
                    let arr = res;
                    detail({
                  projectCode: this.$cache.local.getJSON("tc-project-code"),
                }).then((response) => {
                  let publicList = response.data;
                  if (
                  publicList.projectDutypaidStatus == 1 &&
                  publicList.projectPayStatus == 1 &&
                  publicList.projectTicketStatus == 1 &&
                  publicList.projectAcceptanceStatus == 1 &&
                  publicList.projectContractStatus == 1 &&
                  publicList.projectCheckStatus == 1
                ) {
                  this.projectStatusNew = 2;
                } else if (
                  publicList.projectDutypaidStatus == 2 ||
                  publicList.projectPayStatus == 2 ||
                  publicList.projectTicketStatus == 2 ||
                  publicList.projectAcceptanceStatus == 2 ||
                  publicList.projectCheckStatus == 2 ||
                  publicList.projectContractStatus == 2
                ) {
                  this.projectStatusNew = 1;
                } else {
                  this.projectStatusNew = 0;
                }

                if (type == 1) {
                  this.check("收款审核完成");
                  if (new Decimal(this.publicList.receiveRemainMoneys).sub(new Decimal(this.formData.receiveMoney)) == 0) {
                       
                      this.publicList.projectReceiveStatus=1;
                      this.publicList.projectStatus=this.projectStatusNew;
                   
                  } else {
                      this.publicList.projectReceiveStatus=0;
                      this.publicList.projectStatus=this.projectStatusNew;
                   }
                   arr.map((item) => {
                     if (item.isCheck == 2) {
                      this.publicList.projectReceiveStatus=2;
                      return (this.publicList.projectStatus = 1);
                     }
                  });
                  
                } else {
                    this.publicList.projectReceiveStatus=2;
                    this.publicList.projectStatus=1;
                    this.check("收款审核不通过。" + "原因:" + this.remark);
                }
                this.publicList.receiveRemark=this.remark;
                this.$nextTick(function () {
                  edit(this.publicList).then(res=>{
                  this.$modal.closeLoading();  
                  let obj = {
                  title: "收款审核审核",
                  backUrl: this.$cache.local.getJSON("tc-aduitback").backurl,
                  resmsg: "收款审核完成",
                  backName: this.$cache.local.getJSON("tc-aduitback").name,
                };
                this.$cache.local.setJSON("tc-successProject", obj);
                this.$tab.closeOpenPage({ path: "/tc-business/project/success" });
                })

                })
                 })
              })
              }
            }
          });
        } else {
           this.$modal.closeLoading();
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
