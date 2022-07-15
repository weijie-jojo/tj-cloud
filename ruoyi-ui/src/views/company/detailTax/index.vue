<template>
  <div class="paddingbg-s">
    <el-form ref="formtax" :model="formtax" :rules="rules" label-width="auto">
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="个体户名称" prop="selfName">
            <el-input
              v-model="formtax.selfName"
              :readonly="true"
            ></el-input>
          </el-form-item>
          <el-form-item class="comright" label="法人姓名" prop="legalPersonName">
            <el-input
              v-model="formtax.legalPersonName"
              :readonly="true"
            ></el-input>
          </el-form-item>
          <el-form-item label="核定通知书" prop="fileName2">
               <div v-for="(item, index) in fileName2" :key="index">
                  <el-image
                    lazy
                    :preview-src-list="fileName2"
                    style="width: 150px; height: 150px"
                    :src="item"
                    alt=""
                  />
                </div>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="纳税人识别号" prop="taxId">
            <el-input
              v-model="formtax.taxId"
              :readonly="true"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg " justify="space-around">
         <el-col :span="8"></el-col>
         <el-col :span='8' class="flexs">
             <el-button type="danger" @click="resetForm">返回</el-button> 
             <el-button type="primary" @click="onSubmit">提交</el-button>
         </el-col>
         <el-col :span="8"></el-col>
     </el-row>
    </el-form>
   </div>
</template>

<script>
import { addEmployed, updateEmployed } from "@/api/company/employed";
export default {
  data() {
    return {
      formtax: {
        taxStatus: 1,
        selfId: "",
        legalPersonName: "",
        selfName: "",
        taxId: "",
        fileName2: [],
      },

      fileName2: [],
      dialogVisible: false,
      previewPath: "",
      dialogImageUrl: "",
      rules: {
        selfName: [
          { required: true, message: "请输入个体户名称", trigger: "blur" },
          // { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        legalPersonName: [
          { required: true, message: "请输入法人姓名", trigger: "blur" },
        ],
        taxId: [
          { required: true, message: "请输入纳税人识别号", trigger: "blur" },
        ],
        fileName2: [
          { required: true, message: "请上传文件", trigger: "change" },
        ],
      },
    };
  },
  created() {
    let list = this.$cache.local.getJSON("employednewlist");
    this.formtax = list;
      this.fileName2=JSON.parse(this.$cache.local.getJSON('employednewlist').fileName2);
    for(let k1 in this.fileName2){
      this.fileName2[k1]=this.baseImgPath+this.fileName2[k1];
    } 
    // this.formtax.selfName = list.selfName;
    // this.formtax.legalPersonName = list.legalPersonName;
    // this.formtax.taxId = list.taxId;
  },
  beforeRouteLeave(to, from, next) {
    to.meta.keepAlive = true;
    next(0);
  },

  methods: {
    resetForm() {
      this.$router.back();
    },
    //提交
    onSubmit() {
      this.$refs["formtax"].validate((valid) => {
        if (valid) {
          this.formtax.fileName2 = JSON.stringify(this.formtax.fileName2);
          updateEmployed(this.formtax)
            .then((res) => {
              if (res != undefined) {
                if (res.code === 200) {
                  this.$nextTick(function () {
                     this.$tab.refreshPage({ path: "/company/customer/manageTax"}).then(() => {
                     let  resmsg='办理税务成功';
                     let obj={
                        title:'税务办理',
                        backUrl:'/company/customer/manageTax',
                        resmsg:resmsg
                        };
                      this.$cache.local.setJSON('successNew', obj);
                      this.$tab.closeOpenPage({ path: "/company/customer/successNew"});
                    });
                   });
                } else {
                  this.$modal.msgError(res.msg);
                }
              }
            })
            .catch((error) => {
              this.$modal.msgError(error);
            });
        } else {
          this.$modal.msgError("请填写完整");
        }
      });
    },
    handlesuccess(file, fileList) {
      this.formtax.fileName2.push(file.obj);
    },
    handleRemove(file, fileList) {
      const i = this.formtax.fileName2.findIndex((item) => item === fileList);
      this.formtax.fileName2.splice(i, 1);
    },
    handlePreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    handleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${
          files.length + fileList.length
        } 个文件`
      );
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
  },
};
</script>

<style>
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
.comright{
  padding-right: 10%;
}
.flexs{
  display: flex;
  justify-content: center;

}
</style>