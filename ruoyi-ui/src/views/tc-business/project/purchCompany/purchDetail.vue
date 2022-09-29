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
          <div class="bankno" style="width: 35%">购货单位（甲方）</div>
          <div style="width: 50%; hegiht: 10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="名称" :required="true">
            <el-input
              v-model="formData.purchCompany"
              :readonly="true"
            ></el-input>
          </el-form-item>

          <el-form-item class="comright" label="纳税人识别号" prop="projectName">
            <el-input
              v-model="formData.purchCompanyTaxid"
              :readonly="true"
            ></el-input>
          </el-form-item>
          <el-form-item class="comright" label="客户全名" :required="true">
            <el-input
              v-model="formData.placeAliasName"
              :readonly="true"
            ></el-input>
          </el-form-item>
          <el-form-item class="comright" label="地址" :required="true">
            <el-input
            :readonly="true"
              v-model="formData.purchCompanyAddress"
            ></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="9">
          <el-form-item class="comright" label="账号" :required="true">
            <el-input
              v-model="formData.bankCode"
              :readonly="true"
            ></el-input>
          </el-form-item>
          <el-form-item class="comright" label="开户行" :required="true">
            <el-input
              v-model="formData.bankName"
              :readonly="true"
            ></el-input>
          </el-form-item>
          <el-form-item class="comright" label="手机号" :required="true">
            <el-input
              v-model="formData.purchCompanyPhone"
              :readonly="true"
            ></el-input>
          </el-form-item>
       
         
        </el-col>
      </el-row>
     <el-row type="flex" class="row-bg" justify="space-around" style="margin-bottom:20px">
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
import uploadSmall from "@/components/douploads/uploadSmall";
import { detailPurch } from "@/api/tc-api/project/list";
import { Decimal } from "decimal.js";
export default {
  name: "PurchDetail",
  components: {
    uploadSmall,
  },
  data() {
    return {
      formData: { },
      baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
       rules: {},
    };
  },
  mounted() {
    this.getlist();
  },
  methods: {
     getlist() {
      detailPurch(
        this.$cache.local.getJSON("tc-purchId")
      ).then((response) => {
       this.formData=response.data;
      });
    },
    resetForm() {
      this.$tab.closeOpenPage({ path: "/tc-business/project/purchList" });
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

// 改变input框字体颜色
::v-deep textarea {
  background-color: transparent !important;
  color: black !important;
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
