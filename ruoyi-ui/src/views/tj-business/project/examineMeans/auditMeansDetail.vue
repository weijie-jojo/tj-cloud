<template>
  <div>
    <el-form ref="elForm" :model="formData" size="medium" label-width="140px">
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
            <el-radio disabled v-model="formData.isUpContract" label="0"
              >有</el-radio
            >
            <el-radio disabled v-model="formData.isUpContract" label="1"
              >无</el-radio
            >
            <div v-show="formData.isUpContract == 0">
              <uploadSmall
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
            <el-radio disabled v-model="formData.isUpAcceptance" label="0"
              >有</el-radio
            >
            <el-radio disabled v-model="formData.isUpAcceptance" label="1"
              >无</el-radio
            >
            <div v-show="formData.isUpAcceptance == 0">
              <uploadSmall
                ref="productImage2"
                :fileName="fileName2"
                :fileNameOld="fileNameN2"
                :isDetail="isDetail"
              ></uploadSmall>
            </div>
          </el-form-item>
          <el-form-item class="comright" label="作业考勤出工记录">
            <el-radio disabled v-model="formData.isUpCheckwork" label="0">有</el-radio>
            <el-radio disabled v-model="formData.isUpCheckwork" label="1">无</el-radio>
            <div v-show="formData.isUpCheckwork == 0">
              <uploadSmall
                 ref="productImage5"
                :fileName="fileName5"
                :fileNameOld="fileNameN5"
                :isDetail="isDetail"
              ></uploadSmall>
            </div>
          </el-form-item>
          <el-form-item class="comright" label="项目图片">
            <el-radio disabled v-model="formData.isUpImage" label="0">有</el-radio>
            <el-radio disabled v-model="formData.isUpImage" label="1">无</el-radio>
            <div v-show="formData.isUpImage == 0">
              <uploadSmall
                 ref="productImage6"
                :fileName="fileName6"
                :fileNameOld="fileNameN6"
                :isDetail="isDetail"
              ></uploadSmall>
            </div>
          </el-form-item>
          <el-form-item class="comright" label="购买方营业执照">
            <el-radio disabled v-model="formData.isUpLicense" label="0">有</el-radio>
            <el-radio disabled v-model="formData.isUpLicense" label="1">无</el-radio>
            <div v-show="formData.isUpLicense == 0">
              <uploadSmall
                 ref="productImage7"
                :fileName="fileName7"
                :fileNameOld="fileNameN7"
                :isDetail="isDetail"
              ></uploadSmall>
            </div>
          </el-form-item>
          <el-form-item class="comright" label="银行回单及对账单">
            <el-radio disabled v-model="formData.isUpStatement" label="0">有</el-radio>
            <el-radio disabled v-model="formData.isUpStatement" label="1">无</el-radio>
            <div v-show="formData.isUpStatement == 0">
              <uploadSmall
                 ref="productImage8"
                :fileName="fileName8"
                :fileNameOld="fileNameN8"
                :isDetail="isDetail"
              ></uploadSmall>
            </div>
          </el-form-item>
          <el-form-item class="comright" label="企业资质证书">
            <el-radio disabled v-model="formData.isUpCertification" label="0">有</el-radio>
            <el-radio disabled v-model="formData.isUpCertification" label="1">无</el-radio>
            <div v-show="formData.isUpCertification == 0">
              <uploadSmall
                 ref="productImage9"
                :fileName="fileName9"
                :fileNameOld="fileNameN9"
                :isDetail="isDetail"
              ></uploadSmall>
            </div>
          </el-form-item>
        </el-col>
        <el-col :span="9"> </el-col>
      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="21">
          <el-form-item
            class="comright"
            style="padding-right: 4.2%; margin-left: -7%"
          >
            <el-radio disabled v-model="projectAcceptanceStatus" label="1">
              通过</el-radio
            >
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
              <el-radio disabled v-model="projectAcceptanceStatus" label="2"
                >不通过
              </el-radio>
              <el-input
                type="textarea"
                placeholder="请输入不通过说明"
                v-model="remark"
                :disabled="projectAcceptanceStatus == 1"
              ></el-input>
            </div>
          </el-form-item>
        </el-col>
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
        </el-col>
        <el-col :span="8"></el-col>
      </el-row>
    </el-form>
  </div>
</template>
<script>
import uploadSmall from "@/components/douploads/uploadFiles";
import { detail } from "@/api/project/list";
export default {
  name: "AuditMeansDetail",
  components: { uploadSmall },
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
      remark: "",
      isNone: [],
      isDetail: "1",
      projectAcceptanceStatus: "1",
      fileName: [],
      userinfo: {},
      formData: {},
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
        projectCode: this.$cache.local.getJSON("tj-project-code"),
      })
        .then((response) => {
          this.$modal.closeLoading();
          this.formData.industryType = "";
          this.formData = response.data;
          this.remark = this.formData.checkRemark;
          this.projectAcceptanceStatus = JSON.stringify(
            this.formData.projectAcceptanceStatus
          );
          this.formData.fileName1 = JSON.parse(this.formData.fileName1);
          this.formData.fileName2 = JSON.parse(this.formData.fileName2);
          this.formData.fileName5 = JSON.parse(this.formData.fileName5);
          this.formData.fileName6 = JSON.parse(this.formData.fileName6);
          this.formData.fileName7 = JSON.parse(this.formData.fileName7);
          this.formData.fileName8 = JSON.parse(this.formData.fileName8);
          this.formData.fileName9 = JSON.parse(this.formData.fileName9);
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
    getfileNameS() {},

    //返回
    resetForm() {
      this.$tab.closeOpenPage({
        path: this.$cache.local.getJSON("tj-backMean").backurl,
      });
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
