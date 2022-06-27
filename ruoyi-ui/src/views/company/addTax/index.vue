<template>
   <div class="paddingbg-s">

      <el-form ref="formtax" :model="formtax" :rules="rules" label-width="200px">
         <el-row :gutter="80">
            <el-col :span="10">
               <el-form-item label="个体户名称" prop="selfName">
                  <el-input v-model="formtax.selfName" disabled></el-input>
               </el-form-item>

               <el-form-item label="法人姓名" prop="legalPersonName">
                  <el-input v-model="formtax.legalPersonName" disabled></el-input>
               </el-form-item>
               <el-form-item label="核定通知书" prop="fileName2">
                  <el-upload class="upload-demo" action="http://36.133.2.179:8000/api/files/doUpload"
                     :on-success="handlesuccess"
                     :on-preview="handlePreview" :on-remove="handleRemove" :before-remove="beforeRemove" multiple
                     :limit="9" :on-exceed="handleExceed" :file-list="fileName2" list-type="picture">
                     <el-button size="small" type="primary">点击上传</el-button>

                  </el-upload>
                  <el-dialog :visible.sync="dialogVisible" append-to-body>
                     <img width="100%" :src="dialogImageUrl" alt="" />
                  </el-dialog>

               </el-form-item>
            </el-col>
            <el-col :span="1">
               <div class="grid-content bg-purple" style="color:rgba(0,0,0,0)">.</div>
            </el-col>
            <el-col :span="10">
               <el-form-item label="纳税人识别号" prop="taxId">
                  <el-input v-model="formtax.taxId" disabled></el-input>
               </el-form-item>
            </el-col>
         </el-row>



      </el-form>
      <div class="footers grid-content">
         <el-footer>
            <el-button type="danger" @click="resetForm">返回</el-button>
            <el-button type="primary" @click="onSubmit">提交</el-button>
         </el-footer>
      </div>
   </div>
</template>

<script>
import { addEmployed,updateEmployed } from "@/api/company/employed";
export default {
   data() {
      return {
         formtax: {
            taxStatus:1,
            selfId:'',
            legalPersonName: '',
            selfName: '',
            taxId: '',
            fileName2: []
         },
         
         fileName2:[],
         dialogVisible: false,
         previewPath: "",
         dialogImageUrl: '',
         rules: {
            selfName: [
               { required: true, message: '请输入个体户名称', trigger: 'blur' },
               // { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
            ],
            legalPersonName: [
               { required: true, message: '请输入法人姓名', trigger: 'blur' }
            ],
            taxId: [
               { required: true, message: '请输入纳税人识别号', trigger: 'blur' }
            ],
            fileName2: [
               { required: true, message: '请上传文件', trigger: 'change' }
            ]

         }

      };
   },
   created() {
      let list=this.$cache.local.getJSON('employednewlist');
      this.formtax.selfId=list.selfId;
      this.formtax.selfName = list.selfName;
      this.formtax.legalPersonName = list.legalPersonName;
      this.formtax.taxId = list.taxId;
   },
   beforeRouteLeave(to, from, next) {
      to.meta.keepAlive = true
      next(0)
   },

   methods: {
      resetForm() {
           this.$router.back();
      },
      //提交
      onSubmit() {
         this.$refs['formtax'].validate(valid => {
            if (valid) {
                this.formtax.fileName2=JSON.stringify(this.formtax.fileName2);
               updateEmployed(this.formtax).then(res => {
                  if (res != undefined) {
                     if (res.code === 200) {
                        this.$modal.msgSuccess("修改成功");
                        this.$nextTick(function () {
                            this.$tab.openPage("税务办理", "manageTax").then(() => {
                           })
                         //  this.$router.push({ path: "/customer/manageTax" });
                        });
                     } else {
                        this.$modal.msgError(res.msg);
                     }

                  }

               }).catch(error => {
                  this.$modal.msgError(error);
               });
            } else {
               this.$modal.msgError('请填写完整');
            }

         })
      },
      handlesuccess(file, fileList){
          this.formtax.fileName2.push(file.obj);
      },
      handleRemove(file, fileList) {
         const i = this.formtax.fileName2.findIndex((item) => item === fileList)
         this.formtax.fileName2.splice(i, 1);
      },
      handlePreview(file) {
         this.dialogImageUrl = file.url;
         this.dialogVisible = true;
      },
      handleExceed(files, fileList) {
         this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      },
      beforeRemove(file, fileList) {
         return this.$confirm(`确定移除 ${file.name}？`);
      }
   }
}
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
</style>