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
              v-model="formData.projectCode"
              :readonly="true"
            ></el-input>
          </el-form-item>

          <el-form-item class="comright" label="项目名称" :required="true">
            <el-input
              v-model="formData.projectName"
              :readonly="true"
            ></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="9">
          <el-form-item class="comright" label="项目时间" :required="true">
            <el-date-picker
              disabled
              style="width: 100%"
              v-model="formData.projectTimeStart"
              value-format="yyyy-MM-dd"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              align="right"
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item class="comright" label="项目金额" :required="true">
            <el-input
              :readonly="true"
              type="number"
              style="width: 100%"
              v-model="formData.projectTotalAmount"
              :step="0.00001"
              :min="0"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
            >
              <template slot="append"> 元 </template>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item
            class="comright"
            label="购货单位（甲方）"
            :required="true"
          >
            <el-input
              v-model="formData.purchCompany"
              :readonly="true"
            ></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="9">
          <el-form-item
            class="comright"
            label="销货单位（乙方）"
            prop="projectOwner"
          >
            <el-input v-model="formData.selfName" :readonly="true"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row
        type="flex"
        class="row-bg"
        justify="space-around"
        style="margin-bottom: 10px; margin-top: -10px"
      >
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width: 35%">项目合同</div>
          <div style="width: 50%; hegiht: 10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="项目合同资料">
            <el-radio v-model="formData.isUpContract" label="0">有</el-radio>
            <el-radio v-model="formData.isUpContract" label="1">无</el-radio>
            <div v-if="formData.isUpContract == 0">
              <uploadSmall
                @getfileName="getContractFile"
                :fileName="fileName1"
                :fileNameOld="fileName1"
                :isDetail="isDetail"
              ></uploadSmall>
            </div>
          </el-form-item>
        </el-col>
        <el-col :span="9"> </el-col>
      </el-row>
      <el-row
        type="flex"
        class="row-bg"
        justify="space-around"
        style="margin-bottom: 10px; margin-top: -10px"
      >
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width: 35%">项目验收</div>
          <div style="width: 50%; hegiht: 10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="项目结算单">
            <el-radio v-model="formData.isUpAcceptance" label="0">有</el-radio>
            <el-radio v-model="formData.isUpAcceptance" label="1">无</el-radio>
            <div v-if="formData.isUpAcceptance == 0">
              <uploadS
                @getfileName="getAccepFile"
                :fileName="fileName2"
                :fileNameOld="fileName2"
                :isDetail="isDetail"
              ></uploadS>
            </div>
          </el-form-item>
          <el-form-item class="comright" label="作业考勤出工记录">
            <el-radio v-model="formData.isUpCheckwork" label="0">有</el-radio>
            <el-radio v-model="formData.isUpCheckwork" label="1">无</el-radio>
            <div v-if="formData.isUpCheckwork == 0">
              <uploadS
                @getfileName="getAccepFile5"
                :fileName="fileName5"
                :fileNameOld="fileName5"
                :isDetail="isDetail"
              ></uploadS>
            </div>
          </el-form-item>
          <el-form-item class="comright" label="项目图片">
            <el-radio v-model="formData.isUpImage" label="0">有</el-radio>
            <el-radio v-model="formData.isUpImage" label="1">无</el-radio>
            <div v-if="formData.isUpImage == 0">
              <uploadS
                @getfileName="getAccepFile6"
                :fileName="fileName6"
                :fileNameOld="fileName6"
                :isDetail="isDetail"
              ></uploadS>
            </div>
          </el-form-item>
          <el-form-item class="comright" label="购买方营业执照">
            <el-radio v-model="formData.isUpLicense" label="0">有</el-radio>
            <el-radio v-model="formData.isUpLicense" label="1">无</el-radio>
            <div v-if="formData.isUpLicense == 0">
              <uploadS
                @getfileName="getAccepFile7"
                :fileName="fileName7"
                :fileNameOld="fileName7"
                :isDetail="isDetail"
              ></uploadS>
            </div>
          </el-form-item>
          <el-form-item class="comright" label="银行回单及对账单">
            <el-radio v-model="formData.isUpStatement" label="0">有</el-radio>
            <el-radio v-model="formData.isUpStatement" label="1">无</el-radio>
            <div v-if="formData.isUpStatement == 0">
              <uploadS
                @getfileName="getAccepFile8"
                :fileName="fileName8"
                :fileNameOld="fileName8"
                :isDetail="isDetail"
              ></uploadS>
            </div>
          </el-form-item>
          <el-form-item class="comright" label="企业资质证书">
            <el-radio v-model="formData.isUpCertification" label="0">有</el-radio>
            <el-radio v-model="formData.isUpCertification" label="1">无</el-radio>
            <div v-if="formData.isUpCertification == 0">
              <uploadS
                @getfileName="getAccepFile9"
                :fileName="fileName9"
                :fileNameOld="fileName9"
                :isDetail="isDetail"
              ></uploadS>
            </div>
          </el-form-item>
        </el-col>
        <el-col :span="9"> </el-col>
      </el-row>
   
      
      
      
      <el-row
        type="flex"
        class="row-bg"
        justify="space-around"
        style="margin-bottom: 20px"
      >
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
import uploadS from "@/components/douploads/uploadSmall";
import uploadSmall from "@/components/douploads/uploadFiles";
import { edit, check, detail } from "@/api/tg-api/project/list";
import { getInfo } from "@/api/login";
export default {
  name: "AddMeans",
  components: { uploadSmall,uploadS },
  data() {
    return {
      contractIf: "0",
      acceptf: "0",
      userinfo: {},
      isDetail: "0",
      fileName1: [],
      fileName2: [],
      fileName5: [],
      fileName6: [],
      fileName7: [],
      fileName8: [],
      fileName9: [],
      formData: {},
      rules: { },
      baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
    };
  },
  mounted() {
    this.getlist();
  },
  methods: {
    getlist() {
      this.$modal.loading("正在加载数据，请稍后...");
      detail({
        projectCode: this.$cache.local.getJSON("tg-project-code"),
      })
        .then((response) => {
          this.$modal.closeLoading();
          this.formData = response.data;
          this.formData.fileName5 = [];
          this.formData.fileName6 = [];
          this.formData.fileName7 = [];
          this.formData.fileName8 = [];
          this.formData.fileName9 = [];
          this.formData.fileName2 = [];
          this.formData.fileName1 = [];
          this.formData.isUpContract = "0";
          this.formData.isUpAcceptance = "0";
          this.formData.isUpCheckwork='0';
          this.formData.isUpImage='0';
          this.formData.isUpLicense='0';
          this.formData.isUpStatement='0';
          this.formData.isUpCertification='0';

        })
        .catch((error) => {
          this.$modal.closeLoading();
        });
    },
    check(resmsg) {
      getInfo().then((res) => {
        this.userinfo = res.user;
        let parms = {
          checkReasult: resmsg,
          checkUser: this.userinfo.userName,
          phonenumber: this.userinfo.phonenumber,
          projectCode: this.formData.projectCode,
          projectType: "15",
        };
        check(parms)
          .then((res) => {
            console.log("资料办理成功！");
          })
          .catch((error) => {});
      });
    },
    //合同
    getContractFile(data) {
      this.formData.fileName1 = data;
    },
    //验收 项目结算单
    getAccepFile(data) {
      this.formData.fileName2 = data;
    },
    //验收作业考勤出工记录
    getAccepFile5(data) {
      this.formData.fileName5 = data;
    },
    //验收 项目图片
    getAccepFile6(data) {
      this.formData.fileName6 = data;
    },
    //验收  购买方营业执照
    getAccepFile7(data) {
      this.formData.fileName7 = data;
    },
    //验收  银行回单及对账单
    getAccepFile8(data) {
      this.formData.fileName8 = data;
    },
    //验收  企业资质证书

    getAccepFile9(data) {
      this.formData.fileName9 = data;
    },
    //返回
    resetForm() {
      this.$tab.closeOpenPage({
        path: this.$cache.local.getJSON("tg-addback").backurl,
      });
    },
    handleChange(val) {
      console.log(val);
    },
    onSubmit() {
      this.$refs["elForm"].validate((valid) => {
        // TODO 提交表单
        if (valid) {
          if (Array.isArray(this.formData.fileName2)) {
            this.formData.fileName2 = JSON.stringify(
              this.formData.fileName2
            );
          }
          if (Array.isArray(this.formData.fileName1)) {
            this.formData.fileName1 = JSON.stringify(
              this.formData.fileName1
            );
          }
          if (Array.isArray(this.formData.fileName5)) {
            this.formData.fileName5 = JSON.stringify(
              this.formData.fileName5
            );
          }
          if (Array.isArray(this.formData.fileName6)) {
            this.formData.fileName6 = JSON.stringify(
              this.formData.fileName6
            );
          }
          if (Array.isArray(this.formData.fileName7)) {
            this.formData.fileName7 = JSON.stringify(
              this.formData.fileName7
            );
          }
          if (Array.isArray(this.formData.fileName8)) {
            this.formData.fileName8 = JSON.stringify(
              this.formData.fileName8
            );
          }
          if (Array.isArray(this.formData.fileName9)) {
            this.formData.fileName9 = JSON.stringify(
              this.formData.fileName9
            );
          }
          let parms = {
            projectId: this.formData.projectId,
            fileName2: this.formData.fileName2,
            fileName1: this.formData.fileName1,
            fileName5: this.formData.fileName5,
            fileName6: this.formData.fileName6,
            fileName7: this.formData.fileName7,
            fileName8: this.formData.fileName8,
            fileName9: this.formData.fileName9,
            isUpCheckwork:this.formData.isUpCheckwork,
            isUpImage:this.formData.isUpImage,
            isUpLicense:this.formData.isUpLicense,
            isUpStatement:this.formData.isUpStatement,
            isUpCertification: this.formData.isUpCertification,
            projectAcceptanceStatus: 0,
            projectContractStatus: 0,
            isUpAcceptance: this.formData.isUpAcceptance,
            isUpContract: this.formData.isUpContract,
            isSelfCount: this.formData.isSelfCount,
            projectCode: this.formData.projectCode,
            projectOwner: this.formData.projectOwner,
          };
          edit(parms).then((res) => {
            if (res != undefined) {
              if (res.code === 200) {
                this.$nextTick(function () {
                  this.check("资料办理成功");
                  this.$modal.msgSuccess("资料办理成功");
                  this.$tab
                    .closeOpenPage({
                      path: this.$cache.local.getJSON("tg-addback").backurl,
                    })
                    .then(() => {
                      // 执行结束的逻辑
                      this.$tab.refreshPage({
                        path: this.$cache.local.getJSON("tg-addback").backurl,
                        name: this.$cache.local.getJSON("tg-addback").name,
                      });
                    });
                });
              } else {
                this.$modal.msgError(res.msg);
                this.$tab.closeOpenPage({
                  path: this.$cache.local.getJSON("tg-addback").backurl,
                });
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
</style>
