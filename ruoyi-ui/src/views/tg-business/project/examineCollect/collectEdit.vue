<template>
  <div>
    <el-form
      ref="elForm"
      :model="formData"
      :rules="rules"
      size="medium"
      label-width="140px"
    >
      <el-row type="flex" class="row-bg" justify="space-around">
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
            <el-select
              v-model="formData.receiveName"
              placeholder="请选择付款单位"
              @change="getCarInfoByCompanyId"
              style="width: 100%"
            >
              <el-option
                v-for="item in payCompanys"
                :key="item.groupCode"
                :label="item.groupName"
                :value="item.groupName"
              >
              </el-option>
            </el-select>
            <!-- <el-input v-model="formData.receiveName"></el-input> -->
          </el-form-item>
          <el-form-item class="comright" label="收款时间" :required="true">
            <el-input v-model="formData.receiveTime" disabled></el-input>
          </el-form-item>
          <el-form-item class="comright" label="付款账户" prop="paymentName">
            <el-input v-model="formData.paymentName"></el-input>
          </el-form-item>
         
          <el-form-item
            class="comright"
            label="转账凭证"
            prop="fileNameReceive"
          >
            <uploadSmall
              @getfileName="getReceive"
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
            <el-input v-model="formData.receiveAccount"></el-input>
          </el-form-item>
          <el-form-item class="comright" label="收款金额" prop="receiveMoney">
            <el-input
              :disabled="true"
              v-model="formData.receiveMoney"
              :step="0.00001"
              :min="0"
              onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
          <el-form-item class="comright" label="付款账号" prop="paymentAccount">
            <el-input v-model="formData.paymentAccount"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around" style="margin-bottom:20px">
        <el-col :span="8"></el-col>
        <el-col :span="8" class="flexs">
          <el-button type="danger" @click="resetForm">关闭</el-button>
          <el-button type="primary" @click="submitForm">提交</el-button>
        </el-col>
        <el-col :span="8"></el-col>
      </el-row>
    </el-form>
  </div>
</template>
<script>
import { getAllCompany } from "@/api/invoices/borrow";
import uploadSmall from "@/components/douploads/uploadCollect";
import { check, detail, editReceive, detailCollect } from "@/api/tg-api/project/list";
import { getInfo } from "@/api/login";
export default {
  name: "CollectEdit",
  components: { uploadSmall },
  data() {
    return {
      payCompanys: [],
      types: "0",
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
        
      },
      rules: {
       
        receiveName: [
          {
            required: true,
            message: "收款账户不能为空",
            trigger: "blur",
          },
        ],
        receiveMoney: [
          {
            required: true,
            message: "收款金额不能为空",
            trigger: "blur",
          },
        ],
        receiveAccount: [
          {
            required: true,
            message: "收款账号不能为空",
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
    this.getCommonList();
    this.getlist();
    this.getAllCompany();
  },
  methods: {
    getAllCompany() {
      getAllCompany().then((res) => {
        this.payCompanys = res.list;
        console.log("payCompanys==", this.payCompanys);
      });
    },
    //根据出款单位id查找出款银行卡信息
    getCarInfoByCompanyId() {
      var cardInfo = this.payCompanys.find(
        (item) => item.groupName == this.formData.receiveName
      );
      this.formData.receiveAccount = cardInfo.groupBankAccount;
    },
    getlist() {
      this.$modal.loading("正在加载数据，请稍后...");
      detailCollect(this.$cache.local.getJSON("tg-collReceiveId"))
        .then((response) => {
          this.formData = response.data;
          this.$modal.closeLoading();
          this.formData.fileNameReceive = JSON.parse(
            this.formData.fileNameReceive
          );
          this.$refs.receive.getSrcList(this.formData.fileNameReceive);
          for (let i in this.formData.fileNameReceive) {
            this.fileNameN.push({
              name: this.formData.fileNameReceive[i],
              url: this.baseImgPath + this.formData.fileNameReceive[i],
            });
          }
        })
        .catch((err) => {
          this.$modal.closeLoading();
        });
    },
    getReceive(data) {
      this.formData.fileNameReceive = data;
      console.log(3333, data);
    },
    //获取公共数据
    getCommonList() {
      detail({
        projectCode: this.$cache.local.getJSON("tg-project-code"),
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
          projectType: "20",
        };
        check(parms)
          .then((res) => {
            console.log("修改收款成功!");
          })
          .catch((error) => {});
      });
    },

    //返回
    resetForm() {
      if (this.$cache.local.getJSON("tg-ifcollect") == 0) {
        this.$tab.closeOpenPage({
          path: "/tg-business/project/aduitCollectList",
        });
      } else {
        this.$tab.closeOpenPage({
          path: this.$cache.local.getJSON("tg-aduitback").backurl,
        });
      }
    },
    handleChange(val) {
      console.log(val);
    },

    submitForm() {
      this.$refs["elForm"].validate((valid) => {
        // TODO 提交表单
        if (valid) {
          this.formData.receiveRemark = "";
          this.formData.isCheck = 0;
          if (isArray(this.formData.fileNameReceive)) {
            this.formData.fileNameReceive = JSON.stringify(
              this.formData.fileNameReceive
            );
          }

          editReceive(this.formData).then((res) => {
            if (res != undefined) {
              if (res.code === 200) {
                this.check("修改收款完成");
                this.$modal.msgSuccess("修改收款成功");
                if (this.$cache.local.getJSON("tg-ifcollect") == 0) {
                  this.$tab.refreshPage({
                    path: "/tg-business/project/aduitCollectList",
                    name: "AduitCollectList",
                  });
                } else {
                  this.$tab.refreshPage({
                    path: this.$cache.local.getJSON("tg-edit-project").backurl,
                    name: this.$cache.local.getJSON("tg-edit-project").name,
                  });
                }
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
