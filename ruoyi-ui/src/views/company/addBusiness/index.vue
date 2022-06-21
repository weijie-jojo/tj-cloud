<template>
   <div class="paddingbg-s">

      <el-form ref="formbusiness" :model="formbusiness" :rules="rules" label-width="200px">
         <el-row :gutter="80">
            <el-col :span="10">
               <el-form-item label="个体户名称" prop="self_name">
                  <el-input v-model="formbusiness.self_name" disabled></el-input>
               </el-form-item>

               <el-form-item label="法人姓名" prop="legal_person_name">
                  <el-input v-model="formbusiness.legal_person_name"></el-input>
               </el-form-item>
               <el-form-item label="营业执照" prop="fileList">
                  <el-upload class="upload-demo" action="http://36.133.2.179:8000/api/files/doUpload"
                     :on-success="handlesuccess"
                     :on-preview="handlePreview" :on-remove="handleRemove" :before-remove="beforeRemove" multiple
                     :limit="9" :on-exceed="handleExceed" :file-list="fileList" list-type="picture">
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
               <el-form-item label="纳税人识别号" prop="tax_id">
                  <el-input v-model="formbusiness.tax_id"></el-input>
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
import { addPerson,updatePerson } from "@/api/company/person";
export default {
   data() {
      return {
         formbusiness: {
            legal_person_name: '',
            self_name: '',
            tax_id: '',
            fileList: []
         },
         fileList:[],
         dialogVisible: false,
         previewPath: "",
         dialogImageUrl: '',
         rules: {
            self_name: [
               { required: true, message: '请输入个体户名称', trigger: 'blur' },
               // { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
            ],
            legal_person_name: [
               { required: true, message: '请输入法人姓名', trigger: 'blur' }
            ],
            tax_id: [
               { required: true, message: '请输入纳税人识别号', trigger: 'blur' }
            ],
            fileList: [
               { required: true, message: '请上传文件', trigger: 'change' }
            ]

         }

      };
   },
   created() {
      this.formbusiness.legal_person_name = this.$route.query.legal_person_name;
   },
   beforeRouteLeave(to, from, next) {
      to.meta.keepAlive = true
      next(0)
   },

   methods: {
     //返回
      resetForm() {
           this.$router.back();
      },
      //返回
      onSubmit() {
         this.$refs['formbusiness'].validate(valid => {
            if (valid) {
               addPerson(this.formbusiness).then(res => {
                  if (res != undefined) {
                     if (res.code === 200) {
                        this.$modal.msgSuccess("新增成功");
                        this.$nextTick(function () {
                           this.$router.push({ path: "/customer/employed" });
                        });
                     } else {
                        this.$modal.msgError(error);
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
          this.formbusiness.fileList.push(file.obj);
      },
      handleRemove(file, fileList) {
         const i = this.formbusiness.fileList.findIndex((item) => item === fileList)
         this.formbusiness.fileList.splice(i, 1);
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