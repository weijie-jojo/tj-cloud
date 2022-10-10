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
        style="margin-bottom: 10px; margin-top: -10px"
        justify="space-around"
      >
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width: 35%">缴税信息</div>
          <div style="width: 50%; hegiht: 10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="缴税凭证">
            <el-radio v-model="formData.isUpRate" label="0">有</el-radio>
            <el-radio v-model="formData.isUpRate" label="1">无</el-radio>
            <div v-show="formData.isUpRate == 0">
              <uploadSmall
                ref="productImage2"
                @getfileName="getPayTax"
                :fileName="isNone"
                :fileNameOld="fileNameN2"
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
        style="margin-bottom: 10px; margin-top: -10px"
        justify="space-around"
      >
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width: 35%">完税信息</div>
          <div style="width: 50%; hegiht: 10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="完税凭证">
            <el-radio v-model="formData.isUpDutypaid" label="0">有</el-radio>
            <el-radio v-model="formData.isUpDutypaid" label="1">无</el-radio>
            <div v-show="formData.isUpDutypaid == 0">
              <uploadSmall
                ref="productImage1"
                @getfileName="getDuty"
                :fileName="isNone"
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
import uploadSmall from "@/components/douploads/uploadSmall";
import { edit, check, detail } from "@/api/tc-api/project/list";
import { getInfo } from "@/api/login";
export default {
  name: "DutypaidsEdit",
  components: { uploadSmall },
  data() {
    return {
      isNone: [],
      fileNameN1: [],
      fileNameN2: [],
      projectStatusNew: 0,
      userinfo: {},
      isDetail: "0",
      fileName: [],
      fileNameS: [],
      formData: {},
      baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
      rules: {
        fileName3: [
          {
            required: true,
            message: "项目完税资料不能为空",
            trigger: "change",
          },
        ],
      },
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
        projectCode: this.$cache.local.getJSON("tc-project-code"),
      })
        .then((response) => {
          this.$modal.closeLoading();
          this.formData = response.data;
          this.formData.fileName3 = JSON.parse(this.formData.fileName3);
          this.formData.fileName4 = JSON.parse(this.formData.fileName4);
          this.formData.isUpDutypaid = JSON.stringify(
            this.formData.isUpDutypaid
          );
          this.formData.isUpRate = JSON.stringify(this.formData.isUpRate);
          this.$refs.productImage1.getSrcList(this.formData.fileName3);
          this.$refs.productImage2.getSrcList(this.formData.fileName4);

          for (let i in this.formData.fileName3) {
            this.fileNameN1.push({
              url: this.baseImgPath + this.formData.fileName3[i],
              name: this.formData.fileName3[i],
            });
          }
          for (let j in this.formData.fileName4) {
            this.fileNameN2.push({
              url: this.baseImgPath + this.formData.fileName4[j],
              name: this.formData.fileName4[j],
            });
          }
        })
        .catch((error) => {
          this.$modal.closeLoading();
        });
    },
    getPayTax(data) {
      this.formData.fileName4 = data;
    },
    getDuty(data) {
      this.formData.fileName3 = data;
    },

    check(resmsg) {
      getInfo().then((res) => {
        this.userinfo = res.user;
        let parms = {
          checkReasult: resmsg,
          checkUser: this.userinfo.userName,
          phonenumber: this.userinfo.phonenumber,
          projectCode: this.formData.projectCode,
          projectType: "13",
        };
        check(parms)
          .then((res) => {
            console.log("修改完税成功！");
          })
          .catch((error) => {});
      });
    },
    //返回
    resetForm() {
      this.$tab.closeOpenPage({
        path: this.$cache.local.getJSON("tc-edit-project").url,
      });
    },
    handleChange(val) {
      console.log(val);
    },
    onSubmit() {
      this.$refs["elForm"].validate((valid) => {
        // TODO 提交表单
        if (valid) {
          this.$modal.loading("正在提交中，请稍后...");
          
          if (Array.isArray(this.formData.fileName3)) {
            this.formData.fileName3 = JSON.stringify(
              this.formData.fileName3
            );
          }
          if (Array.isArray(this.formData.fileName4)) {
            this.formData.fileName4 = JSON.stringify(
              this.formData.fileName4
            );
          }
          
          detail({
            projectCode: this.$cache.local.getJSON("tc-project-code"),
          }).then((response) => {
            let list=response.data;
            if (
              list.projectReceiveStatus == 1 &&
              list.projectPayStatus == 1 &&
              list.projectTicketStatus == 1 &&
              list.projectAcceptanceStatus == 1 &&
              list.projectContractStatus == 1 &&
              list.projectCheckStatus == 1
          ) {
            this.projectStatusNew = 2;
          } else if (
            list.projectReceiveStatus == 2 ||
            list.projectPayStatus == 2 ||
            list.projectTicketStatus == 2 ||
            list.projectAcceptanceStatus == 2 ||
            list.projectCheckStatus == 2 ||
            list.projectContractStatus == 2
          ) {
            this.projectStatusNew = 1;
          } else {
            this.projectStatusNew = 0;
          }
          this.formData.projectDutypaidStatus=0;
          this.formData.projectStatus=this.projectStatusNew;
          this.$nextTick(function () {
          edit(this.formData).then((res) => {
            if (res != undefined) {
              if (res.code === 200) {
                this.$modal.closeLoading();
                this.$nextTick(function () {
                  this.check("完税修改完成");
                  this.$modal.msgSuccess("完税修改完成");
                  this.$tab
                    .closeOpenPage({
                      path: this.$cache.local.getJSON("tc-edit-project").url,
                    })
                    .then(() => {
                      this.$tab.refreshPage({
                        path: this.$cache.local.getJSON("tc-edit-project").url,
                        name: this.$cache.local.getJSON("tc-edit-project").name,
                      });
                    });
                });
              } else {
                this.$modal.msgError(res.msg);
                this.$tab.closeOpenPage({
                  path: this.$cache.local.getJSON("tc-edit-project").url,
                });
              }
            }
          });
          })
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
</style>
v