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

          <!-- <el-form-item class="comright" label="项目完税资料">
                      <uploadSmall @getfileName="getfileNameS" :fileName="fileName" :fileNameOld="fileName" :isDetail="isDetail"></uploadSmall>
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
            <div v-if="formData.isUpRate == 0">
              <uploadSmall
                @getfileName="getPayTax"
                :fileName="fileName4"
                :fileNameOld="fileName4"
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
            <div v-if="formData.isUpDutypaid == 0">
              <uploadSmall
                @getfileName="getDuty"
                :fileName="fileName3"
                :fileNameOld="fileName3"
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
import { edit, check, detail } from "@/api/tg-api/project/list";
import { getInfo } from "@/api/login";
export default {
  name: "AddDutypaid",
  components: { uploadSmall },
  data() {
    return {
      fileName4: [],
      fileName3: [],
      userinfo: {},
      isDetail: "0",
      fileName: [],
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
        projectCode: this.$cache.local.getJSON("tg-project-code"),
      })
        .then((response) => {
          this.$modal.closeLoading();
          this.formData = response.data;
          this.formData.isUpDutypaid = "0";
          this.formData.isUpRate = "0";
          this.formData.fileName3 = [];
          this.formData.fileName4 = [];
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
          projectType: "16",
        };
        check(parms)
          .then((res) => {
            console.log("添加完税成功！");
          })
          .catch((error) => {});
      });
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
          this.formData.fileName3 = JSON.stringify(this.formData.fileName3);
          this.formData.fileName4 = JSON.stringify(this.formData.fileName4);
          let parms = {
            isUpRate: this.formData.isUpRate,
            isUpDutypaid: this.formData.isUpDutypaid,
            projectDutypaidStatus: 0,
            projectId: this.formData.projectId,
            fileName3: this.formData.fileName3,
            fileName4: this.formData.fileName4,
            isSelfCount: this.formData.isSelfCount,
            projectCode: this.formData.projectCode,
            projectOwner: this.formData.projectOwner,
            placeCode: this.formData.placeCode,
          };
          edit(parms).then((res) => {
            if (res != undefined) {
              if (res.code === 200) {
                this.$nextTick(function () {
                  this.check("完税办理成功");
                  this.$modal.msgSuccess("完税办理成功");
                  this.$tab
                    .closeOpenPage({
                      path: this.$cache.local.getJSON("tg-addback").backurl,
                    })
                    .then(() => {
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
