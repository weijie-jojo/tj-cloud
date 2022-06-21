<template>
   <div class="paddingbg-s">

      <el-form ref="formBank" :model="formBank" :rules="rules" label-width="200px">
         <el-row :gutter="80">
            <el-col :span="10">
               <el-form-item label="个体户名称" prop="self_name">
                  <el-input v-model="formBank.self_name" disabled></el-input>
               </el-form-item>

               <el-form-item label="法人姓名" prop="legal_person_name">
                  <el-input v-model="formBank.legal_person_name" disabled></el-input>
               </el-form-item>

            </el-col>
            <el-col :span="1">
               <div class="grid-content bg-purple" style="color:rgba(0,0,0,0)"> <br></div>
            </el-col>
            <el-col :span="10">
               <el-form-item label="纳税人识别号" prop="tax_id">
                  <el-input v-model="formBank.tax_id" disabled></el-input>
               </el-form-item>
            </el-col>
         </el-row>

         <el-row>
            <el-col :span="7">
               <div class="bankno">银行账号</div>
            </el-col>
         </el-row>
         <el-row class="paddingbg-s">
            <el-col :span="3">
               <div class="grid-content bg-purple" style="color:rgba(0,0,0,0)"> <br></div>
            </el-col>
            <el-col :span="19" style="margin-left: -11px;">
               <el-collapse v-model="activeNames" @change="handleChange">
                  <el-collapse-item title="私人账号" name="1">
                     <el-row>
                        <el-col :span="10">
                           <el-form-item label="账号名称">
                              <el-input v-model="formBank.self_name" disabled></el-input>
                           </el-form-item>

                           <el-form-item label="开户银行">
                              <el-input v-model="formBank.self_name" disabled></el-input>
                           </el-form-item>

                        </el-col>

                        <el-col :span="10">
                           <el-form-item label="银行账号">
                              <el-input v-model="formBank.self_name" disabled></el-input>
                           </el-form-item>
                        </el-col>
                        <el-col :span="4">
                           <div class="grid-content bg-purple" style="color:rgba(0,0,0,0)"> <br></div>
                        </el-col>
                     </el-row>
                  </el-collapse-item>
                  <el-collapse-item title="对公基本户" name="2">
                     <el-row>
                        <el-col :span="10">
                           <el-form-item label="账号名称">
                              <el-input v-model="formBank.self_name" disabled></el-input>
                           </el-form-item>

                           <el-form-item label="开户银行">
                              <el-input v-model="formBank.public_deposit_bank_1"></el-input>
                           </el-form-item>

                        </el-col>

                        <el-col :span="10">
                           <el-form-item label="银行账号">
                              <el-input v-model="formBank.public_account_number_1"></el-input>
                           </el-form-item>
                        </el-col>
                        <el-col :span="4">
                           <div class="grid-content bg-purple" style="color:rgba(0,0,0,0)"> <br></div>
                        </el-col>
                     </el-row>
                  </el-collapse-item>
                  <el-collapse-item title="对公一般户" name="3">
                     <el-row>
                        <el-col :span="10">
                           <el-form-item label="账号名称">
                              <el-input v-model="formBank.self_name" disabled></el-input>
                           </el-form-item>

                           <el-form-item label="开户银行">
                              <el-input v-model="formBank.public_deposit_bank_2"></el-input>
                           </el-form-item>

                        </el-col>

                        <el-col :span="10">
                           <el-form-item label="银行账号">
                              <el-input v-model="formBank.public_account_number_2"></el-input>
                           </el-form-item>
                        </el-col>
                        <el-col :span="4">
                           <div class="grid-content bg-purple" style="color:rgba(0,0,0,0)"> <br></div>
                        </el-col>
                     </el-row>
                  </el-collapse-item>
               </el-collapse>
            </el-col>
            <el-col :span="2">
               <div class="grid-content bg-purple" style="color:rgba(0,0,0,0)"> <br></div>
            </el-col>
         </el-row>
         <el-row class="paddingbg-s">
            <el-col :span="7">
               <div class="bankno">纳税账号</div>
            </el-col>
         </el-row>

         <el-row :gutter="80" class="paddingbg-s">
            <el-col :span="10">
               <el-form-item label="户名" prop="account_name">
                  <el-input v-model="formBank.account_name"></el-input>
               </el-form-item>

               <el-form-item label="开户银行">
                  <el-input v-model="formBank.public_deposit_bank_3"></el-input>
               </el-form-item>
               <el-form-item label="纳税委托协议" prop="fileList2">
                  <el-upload class="upload-demo" action="http://36.133.2.179:8000/api/files/doUpload"
                     :on-success="handlesuccess1" :on-preview="handlePreview1" :on-remove="handleRemove1"
                     :before-remove="beforeRemove1" multiple :limit="9" :on-exceed="handleExceed1"
                     :file-list="fileList1" list-type="picture">
                     <el-button size="small" type="primary">点击上传</el-button>
                  </el-upload>
                  <el-dialog :visible.sync="dialogVisible1" append-to-body>
                     <img width="100%" :src="dialogImageUrl1" alt="" />
                  </el-dialog>
               </el-form-item>
            </el-col>
            <el-col :span="1">
               <div class="grid-content bg-purple" style="color:rgba(0,0,0,0)"> <br></div>
            </el-col>
            <el-col :span="10">
               <el-form-item label="银行账号">
                  <el-input v-model="formBank.public_account_number_3"></el-input>
               </el-form-item>
               <el-form-item style="color:rgba(0,0,0,0)">
                  <br>
               </el-form-item>
               <el-form-item label="三方协议签约凭证" prop="fileList2">
                  <el-upload class="upload-demo" action="http://36.133.2.179:8000/api/files/doUpload"
                     :on-success="handlesuccess2" :on-preview="handlePreview2" :on-remove="handleRemove2"
                     :before-remove="beforeRemove2" multiple :limit="9" :on-exceed="handleExceed2"
                     :file-list="fileList2" list-type="picture">
                     <el-button size="small" type="primary">点击上传</el-button>
                  </el-upload>
                  <el-dialog :visible.sync="dialogVisible2" append-to-body>
                     <img width="100%" :src="dialogImageUrl2" alt="" />
                  </el-dialog>
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
export default {
   data() {
      return {
         formBank: {
            activeNames: ['1'],
            account_name: '',
            public_deposit_bank_3: '',
            public_account_number_3: '',
            public_deposit_bank_1: '',
            public_account_number_1: '',
            public_deposit_bank_2: '',
            public_account_number_2: '',
            public_deposit_bank_3: '',
            public_account_number_3: '',
            self_name: '',
            legal_person_name: '',
            tax_id: '',
            fileList1: [],
            fileList2: [],
         },
         dialogImageUrl1: '',
         fileList1: [],
         dialogImageUrl2: '',
         fileList2: [],
         dialogVisible1: false,
         dialogVisible2: false,
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
            fileList1: [
               { required: true, message: '请上传纳税委托协议文件', trigger: 'change' }
            ],
             fileList2: [
               { required: true, message: '请上传三方协议签约凭证文件', trigger: 'change' }
            ]

         }
      };
   },
   created() {
      this.formBank.self_name = this.$route.query.self_name;
      this.formBank.legal_person_name = this.$route.query.legal_person_name;
      this.formBank.tax_id = this.$route.query.tax_id;
   },
   beforeRouteLeave(to, from, next) {
      to.meta.keepAlive = true
      next(0)
   },

   methods: {
      handleChange(val) {
         console.log(val);
      },
      resetForm() {
         this.$router.back();
      },
      onSubmit() {
           this.$refs['formBank'].validate(valid => {
            if (valid) {
               addPerson(this.formBank).then(res => {
                  if (res != undefined) {
                     if (res.code === 200) {
                        this.$modal.msgSuccess("新增成功");
                        this.$nextTick(function () {
                           this.$router.push({ path: "/customer/employed" });
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
      handlesuccess1() {
         this.formBank.fileList1.push(file.obj);
      },
      handleRemove1(file, fileList) {
         const i = this.formBank.fileList1.findIndex((item) => item === fileList)
         this.formBank.fileList1.splice(i, 1);
      },
      handlePreview1(file) {
         this.dialogImageUrl1 = file.url;
         this.dialogVisible1 = true;
      },
      handleExceed1(files, fileList) {
         this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      },
      beforeRemove1(file, fileList) {
         return this.$confirm(`确定移除 ${file.name}？`);
      },
      handlesuccess2() {
         this.formBank.fileList2.push(file.obj);
      },
      handleRemove2(file, fileList) {
         const i = this.formBank.fileList2.findIndex((item) => item === fileList)
         this.formBank.fileList2.splice(i, 1);
      },
      handlePreview2(file) {
         this.dialogImageUrl2 = file.url;
         this.dialogVisible2 = true;
      },
      handleExceed2(files, fileList) {
         this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      },
      beforeRemove2(file, fileList) {
         return this.$confirm(`确定移除 ${file.name}？`);
      }
   }
}
</script>

<style>
.bankno {
   letter-spacing: 5px;
   text-align: center;
   font-size: 20px;
   font-weight: bold;
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
</style>