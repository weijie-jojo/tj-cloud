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
              :step="0.01"
              :min="0"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
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
          <!-- <el-form-item class="comright" label="项目合同资料" :required="true">
                        <el-radio label="0">有</el-radio>
                        <el-radio label="1">无</el-radio>
                        <uploadSmall ref="productImage1" :fileName="fileName1" :fileNameOld="fileNameN1"
                            :isDetail="isDetail"></uploadSmall>
                    </el-form-item> -->
        </el-col>

        <el-col :span="9">
          <el-form-item
            class="comright"
            label="销货单位（乙方）"
            prop="projectOwner"
          >
            <el-input v-model="formData.selfName" :readonly="true"></el-input>
          </el-form-item>
          <!-- <el-form-item class="comright" label="项目验收资料" :required="true">
                        <el-radio label="0">有</el-radio>
                        <el-radio label="1">无</el-radio>
                        <uploadSmall ref="productImage2" :fileName="fileName2" :fileNameOld="fileNameN2"
                            :isDetail="isDetail"></uploadSmall>
                    </el-form-item> -->
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
            <div v-show="formData.isUpContract == 0">
              <uploadSmall
                @getfileName="getContractFile"
                ref="productImage1"
                :fileName="fileName1"
                :fileNameOld="fileNameN1"
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
                ref="productImage12"
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
              ref="productImage5"
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
                ref="productImage6"
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
                ref="productImage7"
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
                ref="productImage8"
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
                ref="productImage9"
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
import { edit, check, detail } from "@/api/project/list";
import { getInfo } from "@/api/login";
export default {
  name: "MeansEdit",
  components: { uploadSmall,uploadS },
  data() {
    return {
      fileNameN1: [],
      fileNameN2: [],
      fileNameN5: [],
      fileNameN6: [],
      fileNameN7: [],
      fileNameN8: [],
      fileNameN9: [],
      fileName1: [],
      fileName2: [],
      fileName5: [],
      fileName6: [],
      fileName7: [],
      fileName8: [],
      fileName9: [],
      projectStatusNew: 0,
      userinfo: {},
      isDetail: "0",
      fileName: [],
      fileNames: [],
      formData: {
        fileName2: [],
      },
      rules: {
        fileName2: [
          {
            required: true,
            message: "项目验收资料不能为空",
            trigger: "change",
          },
        ],
      },
      baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
    };
  },
  computed: {},
  mounted() {
    this.getlist();
  },
  methods: {
    getlist() {
      this.$modal.loading("正在加载数据，请稍后...");
      detail({
        projectCode: this.$cache.local.getJSON("tj-project-code"),
      })
        .then((response) => {
          this.$modal.closeLoading();
          this.formData = response.data;
          this.formData.fileName1 = JSON.parse(this.formData.fileName1);
          this.formData.fileName2 = JSON.parse(this.formData.fileName2);
          this.formData.isUpContract = JSON.stringify(
            this.formData.isUpContract
          );
          this.formData.isUpAcceptance = JSON.stringify(
            this.formData.isUpAcceptance
          );
          this.formData.isUpCheckwork = JSON.stringify(
            this.formData.isUpCheckwork
          );
          this.formData.isUpImage = JSON.stringify(
            this.formData.isUpImage
          );
          this.formData.isUpLicense = JSON.stringify(
            this.formData.isUpLicense
          );
          this.formData.isUpStatement = JSON.stringify(
            this.formData.isUpStatement
          );
          this.formData.isUpCertification = JSON.stringify(
            this.formData.isUpCertification
          );
          this.$refs.productImage1.getSrcList(this.formData.fileName1);
          this.$refs.productImage2.getSrcList(this.formData.fileName2);
          this.$refs.productImage5.getSrcList(this.formData.fileName5);
          this.$refs.productImage6.getSrcList(this.formData.fileName6);
          this.$refs.productImage7.getSrcList(this.formData.fileName7);
          this.$refs.productImage8.getSrcList(this.formData.fileName8);
          this.$refs.productImage9.getSrcList(this.formData.fileName9);
          for (let j in this.formData.fileName1) {
            this.fileNameN1.push({
              name: this.formData.fileName1[j],
              url: this.baseImgPath + this.formData.fileName1[j],
            });
          }

          for (let i in this.formData.fileName2) {
            this.fileNameN2.push({
              name: this.formData.fileName2[i],
              url: this.baseImgPath + this.formData.fileName2[i],
            });
          }
          for (let h in this.formData.fileName5) {
            this.fileNameN5.push({
              name: this.formData.fileName5[h],
              url: this.baseImgPath + this.formData.fileName5[h],
            });
          }
          for (let k in this.formData.fileName6) {
            this.fileNameN6.push({
              name: this.formData.fileName6[k],
              url: this.baseImgPath + this.formData.fileName6[k],
            });
          }
          for (let p in this.formData.fileName7) {
            this.fileNameN7.push({
              name: this.formData.fileName7[p],
              url: this.baseImgPath + this.formData.fileName7[p],
            });
          }
          for (let g in this.formData.fileName8) {
            this.fileNameN8.push({
              name: this.formData.fileName8[g],
              url: this.baseImgPath + this.formData.fileName8[g],
            });
          }
          for (let r in this.formData.fileName9) {
            this.fileNameN9.push({
              name: this.formData.fileName9[r],
              url: this.baseImgPath + this.formData.fileName9[r],
            });
          }
        })
        .catch((error) => {
          this.$modal.closeLoading();
        });
    },
    //合同
    getContractFile(data) {
      this.formData.fileName1 = data;
      console.log(1, data);
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
    check(resmsg) {
      getInfo().then((res) => {
        this.userinfo = res.user;
        let parms = {
          checkReasult: resmsg,
          checkUser: this.userinfo.userName,
          phonenumber: this.userinfo.phonenumber,
          projectCode: this.formData.projectCode,
          projectType: "12",
        };
        check(parms)
          .then((res) => {
            console.log("资料修改成功！");
          })
          .catch((error) => {});
      });
    },
    getfileNameS(data) {
      this.formData.fileName2 = data;
      console.log(this.formData.fileName2);
    },
    //返回
    resetForm() {
      this.$tab.closeOpenPage({
        path: this.$cache.local.getJSON("tj-edit-project").url,
      });
    },
    handleChange(val) {
      console.log(val);
    },
    onSubmit() {
      this.$refs["elForm"].validate((valid) => {
        // TODO 提交表单
        this.$modal.loading("正在提交中，请稍后...");
        if (valid) {
          detail({
            projectCode: this.$cache.local.getJSON("tj-project-code"),
          }).then((response) => {
            let list=response.data;
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

          if (
            list.projectReceiveStatus == 1 &&
            list.projectPayStatus == 1 &&
            list.projectDutypaidStatus == 1 &&
            list.projectTicketStatus == 1 &&
            list.projectCheckStatus == 1
          ) {
            this.projectStatusNew = 2;
          } else if (
            list.projectReceiveStatus == 2 ||
            list.projectPayStatus == 2 ||
            list.projectDutypaidStatus == 2 ||
            list.projectTicketStatus == 2 ||
            list.projectCheckStatus == 2
          ) {
            this.projectStatusNew = 1;
          }else{
            this.projectStatusNew = 0;
          }
          this.formData.projectAcceptanceStatus=0,
          this.formData.projectContractStatus=0,
          this.$nextTick(function () {
          edit(this.formData).then((res) => {
            if (res != undefined) {
              if (res.code === 200) {
                this.$modal.closeLoading();
                this.$nextTick(function () {
                  this.check("资料修改完成");
                  this.$modal.msgSuccess("资料修改完成");
                  this.$tab
                    .closeOpenPage({
                      path: this.$cache.local.getJSON("tj-edit-project").url,
                    })
                    .then(() => {
                      // 执行结束的逻辑
                      this.$tab.refreshPage({
                        path: this.$cache.local.getJSON("tj-edit-project").url,
                        name: this.$cache.local.getJSON("tj-edit-project").name,
                      });
                    });
                });
              } else {
                this.$modal.closeLoading();
                this.$modal.msgError(res.msg);
                this.$tab.closeOpenPage({
                  path: this.$cache.local.getJSON("tj-edit-project").url,
                });
              }
            }
          });

          })
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
