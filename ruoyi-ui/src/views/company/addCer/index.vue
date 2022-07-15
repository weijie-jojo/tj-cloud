<template>
  <div class="paddingbg-s">
    <el-form
      ref="formBank"
      :model="formBank"
      :rules="rules"
      label-width="auto"
    >
      <el-row  type="flex" class="row-bg " justify="space-around">
        <el-col :span="21">
         <el-form-item style="padding-right:0.2%" class="comright" label="法人姓名" prop="legalPersonName">
            <el-input  v-model="formBank.legalPersonName" disabled></el-input>
          </el-form-item>
        </el-col>
       </el-row>
       <el-row  type="flex" class="row-bg " justify="space-around" style="margin-top:50px">
        <el-col :span="9">
          
          <el-form-item  label="工商实名" prop="fileName6">
            <el-upload
              class="upload-demo"
              action="http://36.133.2.179:8000/api/files/doUpload"
              :on-success="handlesuccess1"
              :on-preview="handlePreview1"
              :on-remove="handleRemove1"
              :before-remove="beforeRemove1"
              multiple
              :limit="9"
              :on-exceed="handleExceed1"
              :file-list="fileName6"
              list-type="picture"
            >
              <el-button size="small" type="primary">点击上传</el-button>
            </el-upload>
            <el-dialog :visible.sync="dialogVisible1" append-to-body>
              <img width="100%" :src="dialogImageUrl1" alt="" />
            </el-dialog>
          </el-form-item>
        </el-col>
        
        <el-col :span="9">
          
           
          <el-form-item label="税务实名" prop="fileName7">
            <el-upload
              class="upload-demo"
              action="http://36.133.2.179:8000/api/files/doUpload"
              :on-success="handlesuccess2"
              :on-preview="handlePreview2"
              :on-remove="handleRemove2"
              :before-remove="beforeRemove2"
              multiple
              :limit="9"
              :on-exceed="handleExceed2"
              :file-list="fileName7"
              list-type="picture"
            >
              <el-button size="small" type="primary">点击上传</el-button>
            </el-upload>
            <el-dialog :visible.sync="dialogVisible2" append-to-body>
              <img width="100%" :src="dialogImageUrl2" alt="" />
            </el-dialog>
          </el-form-item>
        </el-col>
      </el-row>
        <el-row type="flex" class="row-bg " justify="space-around" style="margin-top:100px">
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
import { getInfo } from '@/api/login'
import { all } from "@/api/company/payTaxInfo";
import { addEmployed, updateEmployed, check } from "@/api/company/employed";
export default {
  data() {
    return {
      formBank: {
        realnameStatus: 1,
        selfId: "",
        selfName: "",
        legalPersonName: "",
        fileName6: [],
        fileName7: [],
      },
      userinfo:{},
      accountType: "",
      activeNames: ["1"],
      dialogImageUrl1: "",
      fileName6: [],
      dialogImageUrl2: "",
      fileName7: [],
      dialogVisible1: false,
      dialogVisible2: false,
      rules: {
        legalPersonName: [
          { required: true, message: "请输入法人姓名", trigger: "blur" },
        ],
         fileName6: [
          {
            required: true,
            message: "请上传工商实名文件",
            trigger: "change",
          },
        ],
        fileName7: [
          {
            required: true,
            message: "请上传税务实名文件",
            trigger: "change",
          },
        ],
      },
    };
  },
  created() {
    let list = this.$cache.local.getJSON("employednewlist");
    this.formBank.selfId = list.selfId;
    this.formBank.selfName = list.selfName;
    this.formBank.legalPersonName = list.legalPersonName;
    this.getInfo();
},
  beforeRouteLeave(to, from, next) {
    to.meta.keepAlive = true;
    next(0);
  },

  methods: {
     handleChange(val) {
      console.log(val);
    },
    resetForm() {
        this.$tab.closeOpenPage({ path: "/company/customer/manageCer"});
    },
    getInfo(){
        getInfo().then(res=>{
          this.userinfo=res.user;
        })
    },
    //新增实名办理进度
    check(resmsg) {
      let parms = {
        "checkReasult": resmsg,
        "checkUser": this.userinfo.userName,
        'phonenumber':this.userinfo.phonenumber,
        "selfCode": this.$cache.local.getJSON("employednewlist").selfCode,
        "selfType": "4",
      }
      check(parms).then(res => {
        console.log('实名办理插入日志成功！');
      }).catch(error => {

      });
    },
    onSubmit() {
      this.$refs["formBank"].validate((valid) => {
        if (valid) {
          this.formBank.fileName6 = JSON.stringify(this.formBank.fileName6);
          this.formBank.fileName7 = JSON.stringify(this.formBank.fileName7);
          updateEmployed(this.formBank)
            .then((res) => {
              if (res != undefined) {
                if (res.code === 200) {
                   this.$nextTick(function () {
                     this.$tab.refreshPage({ path: "/company/customer/manageCer"}).then(() => {
                     let  resmsg='办理实名完成';
                     this.check(resmsg);
                     let obj={
                        title:'实名办理',
                        backUrl:'/company/customer/manageCer',
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
    handlesuccess1(file, fileList) {
      this.formBank.fileName6.push(file.obj);
    },
    handleRemove1(file, fileList) {
      const i = this.formBank.fileName6.findIndex((item) => item === fileList);
      this.formBank.fileName6.splice(i, 1);
    },
    handlePreview1(file) {
      this.dialogImageUrl1 = file.url;
      this.dialogVisible1 = true;
    },
    handleExceed1(files, fileList) {
      this.$message.warning(
        `当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${
          files.length + fileList.length
        } 个文件`
      );
    },
    beforeRemove1(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    handlesuccess2(file, fileList) {
      this.formBank.fileName7.push(file.obj);
    },
    handleRemove2(file, fileList) {
      const i = this.formBank.fileName7.findIndex((item) => item === fileList);
      this.formBank.fileName7.splice(i, 1);
    },
    handlePreview2(file) {
      this.dialogImageUrl2 = file.url;
      this.dialogVisible2 = true;
    },
    handleExceed2(files, fileList) {
      this.$message.warning(
        `当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${
          files.length + fileList.length
        } 个文件`
      );
    },
    beforeRemove2(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
  },
};
</script>

<style>
.bankno {
 
  letter-spacing: 2px;

  font-size: 20px;

  color: blue;
}

.paddingbg-s {
  padding-top: 15px;
}

.footers {
  display: flex;
  justify-content: center;
  align-items: center;
}
.combottom{
  margin-bottom: 10px;
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